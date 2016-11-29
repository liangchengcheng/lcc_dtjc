package com.lcc.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 导出excel文件的公共类
 *
 * @author :wanchangxu
 * @version：2012-9-08
 *  头与内容存放在String[]数组里 一起封装在list里
 * @throws Exception
 */
public class ExcelUtil {

    public ExcelUtil() {

    }

    private ByteArrayOutputStream writeExcel(List<String[]> list,
                                             String fileName) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableWorkbook wwb = Workbook.createWorkbook(baos);
        WritableSheet ws = wwb.createSheet("Sheet1", 0);
        // 设置字体样式
        WritableFont wfc = new WritableFont(WritableFont.ARIAL, 16,
                WritableFont.BOLD, false);
        WritableCellFormat wcfFC = new WritableCellFormat(wfc);
        // 遍历列头字段名
        for (int i = 0; i < list.get(0).length; i++) {
            Label labelC = new jxl.write.Label(i, 0, list.get(0)[i], wcfFC);
            ws.addCell(labelC);
        }
        // 遍历内容
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                Label labelC = new jxl.write.Label(j, i, list.get(i)[j]);
                ws.addCell(labelC);
            }
        }
//		 ws.mergeCells(0, 0, 2, 0);
//		 ws.mergeCells(0, 1, 2, 0);
//		 ws.mergeCells(0, 2, 1, 0);
        // 写入Exel工作表
        wwb.write();
        // 关闭Excel工作薄对象
        wwb.close();
        return baos;

    }

    private ByteArrayOutputStream writeExcelHebing(List<String[]> list,
                                                   String fileName) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableWorkbook wwb = Workbook.createWorkbook(baos);
        WritableSheet ws = wwb.createSheet("Sheet1", 0);
        // 设置标题栏字体样式
        WritableFont wfc = new WritableFont(WritableFont.ARIAL, 16,
                WritableFont.BOLD, false);
        WritableCellFormat wcfFC = new WritableCellFormat(wfc);
        wcfFC.setBorder(Border.ALL, BorderLineStyle.HAIR);
        // 设置内容字体样式
        WritableFont wfclr = new WritableFont(WritableFont.ARIAL, 9,
                WritableFont.BOLD, false);
        WritableCellFormat wcfFClr = new WritableCellFormat(wfclr);
        wcfFClr.setBorder(Border.ALL, BorderLineStyle.DOUBLE);
        // 遍历列头字段名
        for (int i = 0; i < list.get(0).length; i++) {
            Label labelC = new jxl.write.Label(i, 0, list.get(0)[i], wcfFC);
            ws.addCell(labelC);
        }
        // 遍历内容
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                Label labelC = new jxl.write.Label(j, i, list.get(i)[j],wcfFClr);
                ws.addCell(labelC);
            }
        }
        //mergeCells(a,b,c,d) a 单元格的列号b 单元格的行号c 从单元格[a,b]起，向下合并的列数d 从单元格[a,b]起，向下合并的行数
        ws.mergeCells(0, 0, 2, 0);//指标
        ws.mergeCells(0, 1, 2, 0);//甲
        ws.mergeCells(0, 2, 1, 0);//出入境辆次
        ws.mergeCells(0, 2, 0, 11);//货物运输

        // 写入Exel工作表
        wwb.write();
        // 关闭Excel工作薄对象
        wwb.close();
        return baos;

    }

    public void exportExcel(HttpServletResponse resp, List<String[]> list,
                            String fileName) throws Exception {
        InputStream xlsStream = new ByteArrayInputStream(writeExcel(list,
                fileName).toByteArray());
        int i = xlsStream.available();
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Accept-Ranges", "bytes");
        resp.setHeader("Accept-Length", (new Integer(i)).toString());
        resp.setHeader("Content-disposition", "attachment;  filename="
                + 111111 + ".xls");
        byte[] bytes = new byte[4096];
        int length = -1;
        while ((length = xlsStream.read(bytes)) != -1) {
            resp.getOutputStream().write(bytes, 0, length);
            resp.flushBuffer();
        }
        xlsStream.close();
    }

    public void exportExcelHebing(HttpServletResponse resp, List<String[]> list,
                                  String fileName) throws Exception {
        InputStream xlsStream = new ByteArrayInputStream(writeExcelHebing(list,
                fileName).toByteArray());
        int i = xlsStream.available();
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Accept-Ranges", "bytes");
        resp.setHeader("Accept-Length", (new Integer(i)).toString());
        resp.setHeader("Content-disposition", "attachment;  filename="
                + new String(fileName.getBytes("GB2312"), "8859_1") + ".xls");
        byte[] bytes = new byte[4096];
        int length = -1;
        while ((length = xlsStream.read(bytes)) != -1) {
            resp.getOutputStream().write(bytes, 0, length);
            resp.flushBuffer();
        }
        xlsStream.close();
    }

    public static void main(String[] args) {
        List<String[]> list = new ArrayList<String[]>();

        list.add(new String[] { "a", "b", "c", "d" });
        list.add(new String[] { "1", "2", "3", "4" });
        list.add(new String[] { "5", "6", "7", "8" });
        try {
            // exportExcel(list,"123");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
