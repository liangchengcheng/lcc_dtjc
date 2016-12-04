package com.lcc.framework.web;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.framework.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Created by lcc on 2016/12/4.
 */
public class FileUploadInterceptor implements Interceptor{

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(FileUploadInterceptor.class);

    private long fileSize = 1024 * 1024 * 8; // 文件大小限制

    private Set<String> fileTypeSet = new HashSet<String>();// 文件类型，多个以;隔开

    private static final String DEFAULT_DELIMITER = ",";

    //private String allowedType;

    public void init() {

    }

    public void  setAllowedType(String allowType){
        if (allowType != null){
            StringTokenizer stringTokenizer = new StringTokenizer(allowType,DEFAULT_DELIMITER);
            while(stringTokenizer.hasMoreTokens()){
                String nextTokend = stringTokenizer.nextToken().toLowerCase().trim();
                if (nextTokend.length()>0){
                    fileTypeSet.add(nextTokend);
                }
            }
        }
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 校验文件是否能够上传，大小限制，文件类型限制
     */
    protected boolean acceptFile(File file, String contentType, ActionSupport action) {

        // If it's null the upload failed
        if (file == null) {
            action.addActionError("fileupload.filenull");
            log.error("file is null");
            return false;
        }
        if (fileSize < file.length()) {

            action.addActionError("fileupload.filesize");
            log.error("file size is not match");
            return false;
        }
        if ((!fileTypeSet.isEmpty())
                && (!fileTypeSet.contains(contentType.toLowerCase()))) {

            action.addActionError("fileupload.filetype");
            log.error("file type is not match");
            return false;
        }

        return true;
    }

    /**
     * 上传文件
     */
    private String doUpload(File file, String fileName) throws IOException {
        // 构建文件名
        Date currenTime = new Date();
        Random random = new Random();

        StringBuffer filePath = new StringBuffer(
                SystemConstants.UPLOAD_ROOT_PATH).append(
                DateUtil.format(currenTime, "yyyyMMdd")).append("/");
        StringBuffer currenFileName = new StringBuffer(DateUtil.format(
                currenTime, "HHmmss")).append(random.nextInt(10000)).append(
                getFileappfix(fileName));

        String localPathName = filePath.append(currenFileName).toString();
        String serverURL = localPathName.replaceFirst(
                SystemConstants.UPLOAD_ROOT_PATH,
                SystemConstants.UPLOAD_IMG_SERVER_PATH);
        FileUtils.copyFile(file, new File(localPathName));
        return serverURL;

    }

    private static boolean isNonEmpty(Object[] objArray) {
        boolean result = false;
        for (int index = 0; index < objArray.length && !result; index++) {
            if (objArray[index] != null) {
                result = true;
            }
        }
        return result;
    }

    // 文件类型
    protected String getFileappfix(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index, fileName.length()).intern();
    }

    public void destroy() {

    }
    @SuppressWarnings("unchecked")
    public String intercept(ActionInvocation invocation) throws Exception {
        String uploadFileURL = null;
        ActionContext ac = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
        // 开始Interceptor 文件 判断不是多文件的请求
        if (!(request instanceof MultiPartRequestWrapper)) {
            if (log.isDebugEnabled()) {
                log.debug("webwork.messages.bypass.request");
            }

            return invocation.invoke();
        }

        ActionSupport action = (ActionSupport) invocation.getAction();
        ValidationAware validation = null;

        if (action instanceof ValidationAware) {
            validation = (ValidationAware) action;
        }

        //判断是否有错误
        MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) request;
        if (multiWrapper.hasErrors()) {
            for (Iterator errorIter = multiWrapper.getErrors().iterator(); errorIter.hasNext();) {
                String error = (String) errorIter.next();
                if (validation != null) {
                    validation.addActionError(error);
                }

                log.error(error);
            }
        }

        //获取文件的参数
        Enumeration fileParameterNames = multiWrapper.getFileParameterNames();

        while (fileParameterNames.hasMoreElements()) {
            // get the value of this input tag，inputName=？
            String inputName = (String) fileParameterNames.nextElement();
            // get the content type contentType=?
            String[] contentType = multiWrapper.getContentTypes(inputName);
            if (isNonEmpty(contentType)) {
                // get the name of the file from the input tag
                String[] fileName = multiWrapper.getFileNames(inputName);
                if (isNonEmpty(fileName)) {
                    // Get a File object for the uploaded File
                    File[] files = multiWrapper.getFiles(inputName);
                    if (files != null) {
                        for (int index = 0; index < files.length; index++) {
                            if (acceptFile(files[0], contentType[0], action)) {
                                // 上传图片
                                try {
                                    uploadFileURL = doUpload(files[0], fileName[0]);
                                    Map map = ac.getParameters();
                                    map.put("uploadFileURL", uploadFileURL);
                                    map.put("uploadFileSize", files[0].length());
                                    map.put("uploadFileName", fileName[0]);
                                } catch (Exception e) {
                                    action.addActionError("fileupload.upload.error");
                                }
                            }
                        }
                    }
                } else {
                    log.error("file is empty");
                }
            } else {
                log.error("contentType is empty");
            }
        }
        String result = invocation.invoke();
        // cleanup
        // deleTempFile(uploadPath);
        fileParameterNames = multiWrapper.getFileParameterNames();
        while (fileParameterNames != null && fileParameterNames.hasMoreElements()) {
            String inputValue = (String) fileParameterNames.nextElement();
            File[] file = multiWrapper.getFiles(inputValue);
            for (int index = 0; index < file.length; index++) {
                File currentFile = file[index];
                log.info("webwork.messages.removing.file");
                if ((currentFile != null) && currentFile.isFile()) {
                    currentFile.delete();
                }
            }
        }
        return result;
    }
}
