package com.lcc.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Vector;
/**
 * Created by lcc on 2016/11/29.
 */
public class ManageFile {

    /**
     *  改变文件属性 如果是只读,改变成可读写
     *
     *@param  fileName  @TODO: not complete
     */
    public static void setFileWritable(String fileName) {
        File f = new File(fileName);
        if (f.exists() && f.canRead() && !f.canWrite()) {
            f.setReadOnly();
        }
    }


    /**
     *  获取文件所在的路径 add by wjz 1013
     *
     *@param  fileAbsoluteName
     *@return
     */
    public static String getFileDir(String fileAbsoluteName) {
        if (fileAbsoluteName == null) {
            return "";
        }
        int pos1 = fileAbsoluteName.lastIndexOf("\\");
        int pos2 = fileAbsoluteName.lastIndexOf("/");
        int pos = Math.max(pos1, pos2);
        if (pos == -1) {
            return "";
        }
        return refineFilePath(fileAbsoluteName.substring(0, pos + 1));
    }


    /**
     *  获取文件的扩展名 add by wjz 031211
     *
     *@param  fileName  Description of the Parameter
     *@return
     */
    public static String getFileExt(String fileName) {
        if (fileName == null) {
            return "";
        }
        int pos = fileName.lastIndexOf(".");

        if (pos == -1) {
            return "";
        }
        return fileName.substring(pos + 1);
    }


    /**
     *  根据文件路径取得该文件路径下的所有文件名称：
     *
     *@param  filePath
     *@return           存放文件名称的数组
     */
    public static String[] getFileNames(String filePath) {
        //modify by wjz 0225,考虑到.-当前目录
        if (filePath == null || filePath.length() <= 0) {
            //end of modify by wjz
            //      if (filePath == null || filePath.length() <= 2) {
            //       ShowDialog.ShowInformationDialog("getFileNames() 文件路径错误！", "错误",ShowDialog.ERROR_MESSAGE);//"文件路径错误！");
            return null;
        }
        File f = new File(filePath);
        if (f.isFile()) {
            //      ShowDialog.ShowInformationDialog("getFileNames() 文件路径非文件夹！", "错误",ShowDialog.ERROR_MESSAGE);//"文件路径非文件夹！");
            return null;
        }
        String[] flName = f.list();
        f = null;
        return flName;
    }


    //end by add

    /**
     *  Gets the fileSeparator attribute of the ManageFile class
     *
     *@return    The fileSeparator value
     */
    public final static String getFileSeparator() {
        return File.separator;
    }


    /**
     *  根据文件路径取得该文件路径下的所有文件：
     *
     *@param  filePath
     *@return           存放文件的数组
     */
    public static File[] getFiles(String filePath) {
        if (filePath == null || filePath.length() <= 2) {
            //      ShowDialog.ShowInformationDialog("getFiles() 文件路径错误！", "错误",ShowDialog.ERROR_MESSAGE);
            LogUtil.getLogger().error("文件路径错误！");
            return null;
        }
        File f = new File(filePath);
        if (f.isFile()) {
            //      ShowDialog.ShowInformationDialog("getFiles() 文件路径非文件夹！", "错误",ShowDialog.ERROR_MESSAGE);
            LogUtil.getLogger().error("文件路径非文件夹！");
            return new File[]{
            };
        }
        File[] fls = f.listFiles();
        f = null;
        return fls;
    }


    /**
     *  获取父目录
     *
     *@param  strFileName
     *@return
     */
    public static String getParentPath(String strFileName) {
        try {
            String parentPath = strFileName;
            while (parentPath.lastIndexOf(File.separator)
                    == (parentPath.length() - 1)
                    || parentPath.lastIndexOf("/") == (parentPath.length() - 1)) {
                parentPath = parentPath.substring(0, parentPath.length() - 2);
            }
            int idx = parentPath.lastIndexOf(File.separator);
            parentPath = parentPath.substring(0, idx);
            return parentPath;
        } catch (Exception ex) {
            LogUtil.getLogger().error(ex.getMessage());
            return null;
        }

    }


    //end by add
    /**
     *  获取文件名（绝对路径）的相对文件名 add by wjz 1013
     *
     *@param  fileAbsoluteName
     *@return
     */
    public static String getRelativeFileName(String fileAbsoluteName) {
        if (fileAbsoluteName == null) {
            return null;
        }
        int pos1 = fileAbsoluteName.lastIndexOf("\\");
        int pos2 = fileAbsoluteName.lastIndexOf("/");
        int pos = Math.max(pos1, pos2);
        if (pos == -1) {
            return fileAbsoluteName;
        }
        return fileAbsoluteName.substring(pos + 1);
    }


    /**
     *  从数据流里面读取字符串
     *
     *@param  is
     *@return
     *@throws  java.io.IOException
     */
    public final static String getTextFromInputStream(InputStream is)
            throws java.io.IOException {
        if (is == null) {
            return null;
        }
        try {
            is.reset();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        java.io.InputStreamReader r = new java.io.InputStreamReader(is);
        StringBuffer buf = new StringBuffer();
        for (; ; ) {
            int c = r.read();
            if (c <= 0) {
                break;
            }
            buf.append((char) c);
        }
        return buf.toString();
    }


    /**
     *  filePath路径下是否存在文件fn
     *
     *@param  filePath
     *@param  fn        Description of the Parameter
     *@return           存放文件名称的数组
     */
    public static boolean isFileExist(String filePath, String fn) {
        if (filePath == null || fn == null) {
            return false;
        }
        String absPath = null;
        if (filePath.endsWith("/") || filePath.endsWith("\\")) {
            absPath = filePath + fn;
        } else {
            absPath = filePath + File.separator + fn;
        }
        return (new File(absPath)).exists();
    }


    /**
     *  文件fn是否存在
     *
     *@param  fn
     *@return
     */
    public static boolean isFileExist(String fn) {
        if (fn == null) {
            return false;
        }
        return (new File(fn)).exists();
    }


    /**
     *  create new file
     *
     *@param  fileName
     *@return           true is success
     */
    public static boolean CreateFile(String fileName) {
        boolean blret = false;
        try {
            File f = new File(fileName);
            if (f.exists()) {
                if (f.isDirectory()) {
                    LogUtil.getLogger().error(
                            "file:" + fileName + "  exist and is a directory");
                    return false;
                } else {
                    f.delete();
                }
            }
            return f.createNewFile();
        } catch (Exception e) {
            LogUtil.getLogger().error(e.getMessage(), e);
            blret = false;
        } finally {
            return blret;
        }

    }


    /**
     *  删除文件（不是目录）<p>
     * add by wjz 040318<p>
     *
     *@param  fileName  Description of the Parameter
     *@return           Description of the Return Value
     */
    public static boolean DeleteFile(String fileName) {

        try {
            if (fileName == null) {
                return false;
            }
            File f = new File(fileName);

            if (f == null || !f.exists()) {
                return false;
            }

            if (f.isDirectory()) {
                return false;
            }
            return f.delete();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     *  删除文件
     *@param  dirName    源文件或目录
     *@param  isRecurse  如果是目录,是否删除其下的子目录
     *@return            true-成功,false-失败
     */
    public static boolean DeleteFile(String dirName, boolean isRecurse) {
        boolean blret = false;
        try {
            File f = new File(dirName);
            //add by wjz 0304
            if (f == null || !f.exists()) {
                return false;
            }
            if (f.isFile()) {
                blret = f.delete();
                return blret;
            } else {
                File[] flst = f.listFiles();
                if (flst == null || flst.length <= 0) {
                    f.delete();//add by yql 2004-04-30
                    return true;
                }
                int filenumber = flst.length;
                File[] fchilda = f.listFiles();
                for (int i = 0; i < filenumber; i++) {
                    File fchild = fchilda[i];
                    if (fchild.isFile()) {
                        blret = fchild.delete();
                        if (!blret) {
                            break;
                        }
                    } else if (isRecurse) {
                        blret = DeleteFile(fchild.getAbsolutePath(), true);
                    }
                }
                blret = new File(dirName).delete();
            }
        } catch (Exception e) {
            LogUtil.getLogger().info(e.getMessage(), e);
            blret = false;
        } finally {
            return blret;
        }

    }


    /**
     *  删除文件
     *
     *@param  pathName   源文件或目录
     *@param  isRecurse  如果是目录,是否删除其下的子目录
     *@return            true-成功,false-失败
     */
    public static boolean DeleteFilesAndSubDir(
            String pathName,
            boolean isRecurse) {
        boolean blret = false;
        try {
            File f = new File(pathName);
            if (f.isFile()) {
                blret = f.delete();
                return blret;
            }

            int filenumber = f.listFiles().length;
            File[] fchilda = f.listFiles();
            if (fchilda == null || fchilda.length <= 0) {
                return true;
            }
            for (int i = 0; i < filenumber; i++) {
                File fchild = fchilda[i];
                if (fchild.isFile()) {
                    blret = fchild.delete();
                    if (!blret) {
                        break;
                    }
                } else if (isRecurse) {
                    blret = DeleteFile(fchild.getAbsolutePath(), true);
                }
            }

        }
        //try
        catch (Exception e) {
            LogUtil.getLogger().error(e.getMessage(), e);
            blret = false;
        } finally {
            return blret;
        }
        //catch
    }


    /**
     *  从fn中读取所有内容(返回为字符串格式) <p>
     *
     *  wujinzhong 0531<p>
     *
     *
     *
     *@param  fn  源文件名
     *@return     byte[]
     *@roseuid    3C297D710384
     */
    public static String ReadFile(String fn) {
        if (java.util.Locale.getDefault().toString().indexOf("CN") > -1) {
            return new String(loadFromFile(fn));
        } else {
            return DataTypeUtil.toCHNString(new String(loadFromFile(fn)));
        }
    }


    /**
     *  读配置文件 searchstr="stic"; srcPath="c:\zxtic.ini"
     *
     *@param  searchstr  Description of the Parameter
     *@param  srcPath    Description of the Parameter
     *@return            Description of the Return Value
     */
    public static String ReadINI(String searchstr, String srcPath) {
        String str = null;
        String temp = null;
        BufferedReader br = null;
        searchstr = searchstr.toLowerCase();
        try {
            br =
                    new BufferedReader(
                            new InputStreamReader(new FileInputStream(srcPath)));
            //"C:/zxtic.ini"
            str = br.readLine();
            while (str != null) {
                str = str.trim();
                if (str.length() > 0 && !str.startsWith("#")) {
                    if (str.toLowerCase().indexOf(searchstr) >= 0) {
                        temp = str.substring(str.indexOf("=") + 1);
                        temp = temp.replace('\\', '/');
                        break;
                    }
                }
                str = br.readLine();
            }

        } catch (Exception e) {
            LogUtil.getLogger().error("read ini file error", e);
        } finally {
            try {
                br.close();
            } catch (Exception ex) {

            }
        }

        return temp;
    }


    //add by henson 20030605
    /**
     *  查找压缩文件
     *
     *@param  bFlag       搜索位置标志 true：从左到右 false：从右到左
     *@param  sSearchStr  要搜索的字符串 return 文件名
     *@param  sFilePath   Description of the Parameter
     *@return             Description of the Return Value
     */
    public static String SearchFile(
            String sSearchStr,
            boolean bFlag,
            String sFilePath) {
        try {
            String sFileName = "";
            String[] fls = ManageFile.getFileNames(sFilePath);
            if (fls != null && fls.length > 0) {
                for (int i = 0; i < fls.length; i++) {
                    if (bFlag) {
                        if (((String) fls[i])
                                .toLowerCase()
                                .indexOf(sSearchStr.toLowerCase())
                                >= 0) {
                            sFileName = fls[i].toString();
                            break;
                        }
                    } else {
                        if (fls[i]
                                .toString()
                                .toLowerCase()
                                .lastIndexOf(sSearchStr.toLowerCase())
                                >= 0) {
                            sFileName = fls[i].toString();
                            break;
                        }
                    }
                }
            }
            return sFileName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * add by yql 2004-04-18
     * @param sSearchStr
     * @param iFlag
     * @param sFilePath
     * @return
     */
    public static String[] SearchFileArr(String sSearchStr,int iFlag,String sFilePath) {
        Vector vecFileNames=new Vector();
        if(iFlag<0)
            return new String[]{SearchFile(sSearchStr,true,sFilePath)};
        else if(iFlag>0)
            return new String[]{SearchFile(sSearchStr,false,sFilePath)};
        else
        {
            String[] fls = ManageFile.getFileNames(sFilePath);
            if(fls==null||fls.length<=0)
                return DataTypeUtil.vecToStrArr(vecFileNames);
            else
            {
                int iLen=fls.length;
                for(int i=0;i<iLen;i++)
                {
                    if(fls[i]==null||fls[i].length()<=0)
                        continue;
                    else if(fls[i].toUpperCase().indexOf(sSearchStr.toUpperCase())<0)
                    {
                        continue;
                    }else
                    {
                        vecFileNames.addElement(fls[i]);
                    }
                }
            }
        }
        return DataTypeUtil.vecToStrArr(vecFileNames);
    }



    /**
     *  检查文件名是否满足Windows风格的通配符<p>
     *
     *  如*表示任意多个字符，？表示任意一个字符<p>
     *
     *  add by wjz 031211 <p>
     *
     *
     *
     *@param  fileName  Description of the Parameter
     *@param  filters   Description of the Parameter
     *@return
     */
    public static boolean checkFileNames(String fileName, String filters) {

        fileName = getRelativeFileName(fileName);
        if (fileName == null || filters == null) {
            return false;
        }
        String[] arr_filters = filters.split(",");
        //没有通配符，表示任意字符
        if (arr_filters == null || arr_filters.length == 0) {
            return true;
        }
        int len = arr_filters.length;
        String filter = "";
        for (int i = 0; i < len; i++) {
            filter = arr_filters[i];
            filter = filter.trim();
            //java中,"."表示任意字符
            filter = filter.replaceAll("\\.", "\\\\.");
            filter = filter.replaceAll("\\*", "\\.\\*");
            filter = filter.replace('?', '.');
            //     System.err.println(filter);
            if (fileName.matches(filter)) {
                return true;
            }
        }

        return false;
    }


    //  end by add--------------------------------------------------------------------------------------------------------------------------------
    /**
     *  本方法主要用于将一个文件拷贝到另一个文件件
     *
     *@param  from                                       源文件
     *@param  to                                         目的文件
     *@return
     *      返回字节数,-1:读文件错误，－2：文件未找到，-3： IO错误
     *@exception  java.io.FileNotFoundException          Description of the
     *      Exception
     *@throws  java.io.FileNotFoundException,抛出文件没有发现异常
     *@throws  java.io.IOException                       抛出IO异常
     */
    public final static int copyFile(String from, String to)
            throws java.io.FileNotFoundException, java.io.IOException {
        java.io.FileInputStream is = null;
        java.io.FileOutputStream os = null;
        int n = 0;
        try {
            is = new java.io.FileInputStream(from);
            byte[] bts = new byte[is.available()];
            int len = bts.length;
            //add by wjz 0318
            DeleteFile(to);
            //end of add by wjz


            os = new java.io.FileOutputStream(to);
            int len1 = is.read(bts, 0, bts.length);
            os.write(bts, 0, bts.length);
            bts = null;
            n = len == len1 ? len : -1;

        } catch (java.io.FileNotFoundException fe) {
            LogUtil.getLogger().error(fe.getMessage(), fe);
            n = -2;

        } catch (java.io.IOException ie) {
            LogUtil.getLogger().error(ie.getMessage(), ie);
            n = -3;
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
            }
            try {
                os.close();
            } catch (Exception ex) {
            }
        }
        return n;
    }


    /**
     *  将原文件拷贝到目标文件夹下,支持通配符格式,但不支持目录拷贝<p>
     *
     *  add by wjz 0225
     *
     *@param  srcFileName  原文件
     *@param  destPath     目标文件夹
     *@return              成功拷贝的文件个数
     */
    public final static int copyFileToDir(
            String srcFileName,
            String destPath) {
        if (srcFileName == null || srcFileName.trim().length() == 0) {
            throw new IllegalArgumentException("源文件名为空");
        }
        //System.out.println("hello2");
        if (destPath == null || destPath.trim().length() == 0) {
            throw new IllegalArgumentException("目标路径为空");
        }
        if (!new File(destPath).isDirectory()) {
            throw new IllegalArgumentException("目标路径不是一个合法的文件路径");
        }

        String s = srcFileName.replace('\\', File.separatorChar);
        s = srcFileName.replace('/', File.separatorChar);

        int pos1 = srcFileName.lastIndexOf("?");
        int pos2 = srcFileName.lastIndexOf("*");
        Vector vFiles = new Vector(0);
        //表明是通配符操作
        if (pos1 >= 0 || pos2 >= 0) {
            String srcDir = getFileDir(srcFileName);
            //获取通配符
            String pattern = getRelativeFileName(srcFileName);
            if (srcDir == null || srcDir.length() == 0) {
                srcDir = ".";
            }
            String[] fileNames = getFileNames(srcDir);
            if (fileNames == null) {
                return -1;
            }
            for (int i = 0; i < fileNames.length; i++) {
                if (checkFileNames(fileNames[i], pattern)) {
                    //modify by wjz 0216
                    //                    vFiles.add(fileNames[i]);
                    vFiles.add(srcDir + File.separator + fileNames[i]);
                    //end of modify by wjz

                }
            }

        }
        //只有一个文件
        else {
            File f = new File(srcFileName);
            if (f.isDirectory()) {
                throw new IllegalArgumentException("不支持目录拷贝");
            }
            if (f.exists()) {
                vFiles.add(srcFileName);
            }
        }
        //拷贝符合条件所有文件
        int size = vFiles.size();
        int rets = 0;
        for (int i = 0; i < size; i++) {
            try {
                String fn = new File(vFiles.get(i) + "").getAbsolutePath();
                String rela = getRelativeFileName(fn);
                int ret =
                        ManageFile.copyFile(
                                fn,
                                destPath + File.separatorChar + rela);
                if (ret >= 0) {

                    rets++;
                } else {
                    throw new java.lang.Exception(
                            "拷贝文件:" + fn + "到" + destPath + " 失败");
                }
            } catch (Exception ex) {

                return -1;
            }

        }

        return rets;
    }


    //add by hejiang 20030915
    /**
     *  Description of the Method
     *
     *@param  srcFileDir   Description of the Parameter
     *@param  destFileDir  Description of the Parameter
     */
    public static void copyFileToIcDir(String srcFileDir, String destFileDir) {
        String[] srcFiles = getFileNames(srcFileDir);
        try {
            if (srcFiles == null || srcFiles.length == 0) {
                return;
            }
            //if
            else {
                for (int i = 0; i < srcFiles.length; i++) {
                    if (!srcFiles[i].endsWith(".zip")) {
                        copyFile(
                                srcFileDir + "\\" + srcFiles[i],
                                destFileDir + "\\" + srcFiles[i]);
                    }
                    //if
                }
                //for
            }
            //else
        }
        //try
        catch (Exception e) {
            LogUtil.getLogger().error("拷贝文件出错！", e);
        }
    }


    /**
     *  将输入流拷贝到输出流
     *
     *@param  in            输入流
     *@param  out           输出流
     *@throws  IOException
     */
    public final static void copyStream(InputStream in, OutputStream out)
            throws IOException {
        int count;
        for (count = 0; ; count++) {
            int ch = in.read();
            if (ch == -1) {
                break;
            }
            out.write(ch);
        }
        in.close();
        out.flush();
        out.close();
    }


    /**
     *  从fn中读取所有内容
     *
     *@param  fn  源文件名
     *@return     byte[]
     *@roseuid    3C297D710384
     */
    public static synchronized byte[] loadFromFile(String fn) {
        try {
            FileInputStream fis = new FileInputStream(fn);
            byte[] data = new byte[fis.available()];
            fis.read(data, 0, data.length);
            fis.close();
            return data;
        } catch (IOException e) {
            LogUtil.getLogger().error(e.getMessage(), e);
            return null;
        }
    }


    /**
     *  从fn中读取所有内容
     *
     *@param  fn  源文件名
     *@return     byte[]
     *@roseuid    3C297D710384
     */
    public static byte[] loadFromFile(File fn) {
        try {
            FileInputStream fis = new FileInputStream(fn);
            byte[] data = new byte[fis.available()];
            fis.read(data, 0, data.length);
            fis.close();
            return data;
        } catch (IOException e) {
            //    ShowDialog.ShowInformationDialog("loadFromFile() 读文件出错！", "错误",ShowDialog.ERROR_MESSAGE);//"文件路径错误！");
            LogUtil.getLogger().error(e.getMessage(), e);
            return null;
        }
    }


    /**
     *@param  args  The command line arguments
     */
    public static void main(String[] args) {
//      System.err.println(ManageFile.DeleteFile("c:/a/fdasfdas.xml"));
        Vector v=getFiles("E:\\Projects\\vss\\sinopec\\FPGL\\webapp","*.html");
        Object [] aa=v.toArray();
        for (int i = 0; i < aa.length; i++) {
            System.out.println(i+"="+aa[i].toString());
        }
        System.err.println(ManageFile.saveFile("c:/a.xml", "sssaa".getBytes()));
        //System.err.println(getRelativeFileName("c:/b/c/\\aaa.doc"));
        //        System.err.println(getFileExt("c:/b/c/\\aaa.doc"));
        //        String s = "aa.eoc";
        //        String f = "*.doc,*.exe,a*.eoc";
        //        System.err.println("f=" + f);
        //        System.err.println(checkFileNames(s, f));
//		System.err.println(
//			ManageFile.copyFileToDir(
//				"E:/Projects/vss\\common/zxtpub\\*.xml",
//				"c:\\test"));
//		System.err.println(
//			ManageFile.copyFileToDir(
//				"E:/Projects/vss\\common/zxtpub\\zxtpub.xml",
//				"c:\\test\\"));

    }


    /**
     *  创建文件路径
     *
     *@param  baseDir  该目录所在的父目录
     *@param  dirName  目录名
     *@return          true-成功;False-不成功
     */
    public static boolean mkDir(String baseDir, String dirName) {
        return mkDir(baseDir + File.separator + dirName);
    }


    /**
     *  创建文件路径
     *
     *@param  destDir  目录名
     *@return          true-成功;False-不成功
     */
    public static boolean mkDir(String destDir) {
        boolean blRet = false;
        int idx = destDir.lastIndexOf(File.separator);
        if (destDir.lastIndexOf("/") == -1
                && destDir.lastIndexOf("\\") == -1) {
            throw new java.lang.IllegalArgumentException("输入参数不是一个合法的路径");
        }
        return new File(destDir).mkdirs();
    }


    /**
     *  从输入流中所有内容到数组中
     *
     *@param  is  Description of Parameter
     *@return     Description of the Returned Value
     */
    public static byte[] readToBytes(InputStream is) {
        byte buffer[] = null;
        int SIZE = 64 * 1024;
        ByteArrayOutputStream bao = null;
        try {

            bao = new ByteArrayOutputStream();

            buffer = new byte[SIZE];
            BufferedInputStream in = new BufferedInputStream(is, SIZE);
            int iBytes = 0;
            while (iBytes != -1) {
                iBytes = in.read(buffer, 0, SIZE);
                if (iBytes != -1) {
                    bao.write(buffer, 0, iBytes);
                }
            }

            return bao.toByteArray();
        } catch (java.io.IOException ex) {
            LogUtil.getLogger().error(ex.getMessage(), ex);
            return null;
        } finally {
            try {
                bao.close();
                //   LogUtil.getLogger().debug("close ByteArrayOutputStream");
            } catch (Exception ex2) {

            }

        }

    }


    /**
     *  Description of the Method
     *
     *@param  fn  Description of the Parameter
     *@return     Description of the Return Value
     */
    public static String refineFilePath(String fn) {
        String s = fn.replace('\\', File.separatorChar);
        s = fn.replace('/', File.separatorChar);
        int i = s.indexOf("//");
        if (i > 0) {
            s = s.substring(0, i) + s.substring(i + 1);
        }
        i = s.indexOf("\\\\");
        if (i > 0) {
            s = s.substring(0, i) + s.substring(i + 1);
        }
        return s;
    }


    /**
     *  将data写到fn中 fn为绝对路径
     *
     *@param  fn    保存文件名
     *@param  data  数据
     *@return       int
     *@since        2001-12-25
     *@roseuid      3C297D4D0102
     */
    public static int saveFile(String fn, byte[] data) {
        try {
            //add by wjz 0318,保证目标为文件且不是只读
            File f = new File(fn);
            if (f.isDirectory()) {
                return 101;
            }
            f.delete();

            //end of add by wjz

            FileOutputStream fos = new FileOutputStream(fn);

            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
            return 0;
        } catch (IOException e) {
            LogUtil.getLogger().error(e.getMessage(), e);
            return 100;
        }
    }

    /**
     *  通过追加的方式将data写到fn中 fn为绝对路径
     *
     *@param  fn    保存文件名
     *@param  data  数据
     *@return       int
     *@since        2009-08-14
     *@roseuid      3C297D4D0102
     */
    public static int saveFileByAppend(String fn, byte[] data) {
        try {
            FileOutputStream fos = new FileOutputStream(fn,true);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
            return 0;
        } catch (IOException e) {
            LogUtil.getLogger().error(e.getMessage(), e);
            return 100;
        }
    }

    /**
     *  yql2003－08－27
     *
     *@param  text      文件内容
     *@param  fileName  文件名称
     *@param  append    是否追加的文件的尾部 如果append=true 则将内容追加的文件的尾部 否则，从头开始
     *@return           Description of the Return Value
     */
    public final static boolean writeTextToFile(
            String text,
            String fileName,
            boolean append) {
        boolean flags = append;
        try {
            Writer write = new FileWriter(fileName, flags);
            write.write(text);
            write.flush();
            write.close();
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }


    /**
     * add by yuelaohu
     *
     * @param fileName 文件名
     * @param rowNo 行号 注意,行号从0开始算起
     * @return 返回 取得的某一行的内容 如果文件不存在或者不错在该行,则返回"null"
     */
//    public static String getLineFromFile(String fileName,int rowNo)
//    {
//      String strLine=null;
//      try {
//        if(isFileExist(fileName))
//        {
//          String strCon=ReadFile(fileName);
//          strLine= StringUtil.getLineFromStr(strCon,rowNo);
//        }
//      }
//      catch (Exception ex) {
//        LogUtil.getLogger().error("取文件行函数错误，没有找到文件名 "+fileName);
//      }
//      return strLine;
//    }
    /**
     * 递归查找所有满足条件的文件
     * @param srcDir String
     * @param filePattern String
     * @return Vector
     */
    public static Vector getFiles(String srcDir,String filePattern){
        Vector v=new Vector();
        if (srcDir == null || srcDir.trim().length() == 0) {
            return v;
        }
        File f=new File(srcDir);
        if(!f.exists()) return v;
        if(f.isFile()){
            if(checkFileNames(f.getAbsolutePath(),filePattern)){
                v.add(f.getAbsolutePath());
            }
        }else{
            File[] fs=f.listFiles();
            for (int i = 0; i < fs.length; i++) {
                v.addAll(getFiles(fs[i].getAbsolutePath(),filePattern));
            }
        }
        return v;

    }
}
