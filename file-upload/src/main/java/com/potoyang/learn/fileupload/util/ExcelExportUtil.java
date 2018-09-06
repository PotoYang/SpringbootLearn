package com.potoyang.learn.fileupload.util;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/29 10:16
 * Modified By:
 * Description: 依赖于poi构建的excel表格导出工具类
 * <!-- poi -->
 * <dependency>
 * <groupId>org.apache.poi</groupId>
 * <artifactId>poi-ooxml</artifactId>
 * <version>3.10-FINAL</version>
 * </dependency>
 */
public class ExcelExportUtil<T> {
    private Class<T> clazz;

    public ExcelExportUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getExcel(String excelName, String[] titles, List<T> data, HttpServletResponse response) {
        try {
//            response.setContentType("application/x-xls;charset=UTF-8");
            Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            String fileName = excelName + "-" + milliSecond + ".xls";
            fileName = MimeUtility.encodeText(URLEncoder.encode(fileName, "UTF-8"), "UTF-8", "B");
//            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//            ServletOutputStream outputStream = response.getOutputStream();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            HSSFRow hssfRow = hssfSheet.createRow(0);
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);
                hssfCell.setCellValue(titles[i]);
                hssfCell.setCellStyle(hssfCellStyle);
            }

            // 获取需要写入的数据
            for (int i = 0; i < data.size(); i++) {
                hssfRow = hssfSheet.createRow(i + 1);

                T t = data.get(i);
                boolean flag = false;

                Field[] fields = clazz.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    String fieldName = fields[j].getName();
                    if ("serialVersionUID".equals(fieldName)) {
                        flag = true;
                        continue;
                    }
                    // 类中需要有类成员的get方法
                    Method method = clazz.getMethod("get" + change(fieldName), (Class<?>[]) null);
                    Object obj = method.invoke(t, (Object[]) null);
                    if (flag) {
                        hssfRow.createCell(j - 1).setCellValue(String.valueOf(obj));
                    } else {
                        hssfRow.createCell(j).setCellValue(String.valueOf(obj));
                    }
                }
            }

//             输出文件到浏览器
//            try {
//                workbook.write(outputStream);
//                outputStream.flush();
//                outputStream.close();
//                return "success";
//            } catch (Exception e) {
//                e.printStackTrace();
//                return e.getMessage();
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "12";
    }

    /**
     * @param src 源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
     */
    private String change(String src) {
        if (src != null) {
            StringBuilder stringBuilder = new StringBuilder(src);
            stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
            return stringBuilder.toString();
        } else {
            return null;
        }
    }
}
