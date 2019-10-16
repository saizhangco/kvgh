package saizhang.erp.kvgh.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 10:16
 * @Description : TODO
 */
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static <T> void createExcel(String path, List<T> list, Class<T> clazz) throws Exception {
        createExcel(path, list, clazz, "Sheet1");
    }

    public static <T> List<T> readExcel(String path, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        Workbook wb = openWorkbook(path);
        if (wb.getNumberOfSheets() > 0) {
            Sheet sheet = wb.getSheetAt(0);
            if (sheet == null) {
                return list;
            }
            //遍历row
            Map<Integer, String> header = new HashMap<>();
            Row row = sheet.getRow(0);
            for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                Cell cell = row.getCell(cellIndex);
                header.put(cellIndex, cell.getStringCellValue());
            }
            List<Map<String, String>> body = new ArrayList<Map<String, String>>();
            for (int rowIndex = 1; rowIndex < sheet.getLastRowNum(); rowIndex++) {
                Row row1 = sheet.getRow(rowIndex);
                if (row1 == null) {
                    continue;
                }
                Map<String, String> oneLine = new HashMap<>();
                //遍历单元格 cell
                for (int cellIndex = 0; cellIndex < row1.getLastCellNum(); cellIndex++) {
                    Cell cell = row1.getCell(cellIndex);
                    if( cell == null ) {
                        continue;
                    }
                    switch (cell.getCellTypeEnum()) {
                        case NUMERIC:
                            oneLine.put(header.get(cellIndex), String.valueOf(cell.getNumericCellValue()));
                            break;
                        case STRING:
                            oneLine.put(header.get(cellIndex), cell.getStringCellValue());
                            break;
                        case BOOLEAN:
                            oneLine.put(header.get(cellIndex), cell.getBooleanCellValue() ? "TRUE" : "FALSE");
                            break;
                        case FORMULA:
                            oneLine.put(header.get(cellIndex), cell.getCellFormula());
                            break;
                        default:
                            break;
                    }

                }
                body.add(oneLine);
            }
            list = convertObject(body, clazz);
        }
        return list;
    }

    private static <T> List<T> convertObject(List<Map<String, String>> body, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        for (Map map : body) {
            list.add((T) JsonUtil.getObject(map, clazz));
        }
        return list;
    }

    //创建Excel文件
    public static <T> void createExcel(String path, List<T> list, Class<T> clazz, String sheetname) throws Exception {
        //创建Excel文件对象
        Workbook wb = getWorkbook(path);
        //用文件对象创建sheet对象
        Sheet sheet = wb.createSheet(sheetname);
        //用sheet对象创建行对象
        Row row = sheet.createRow(0);
        //创建单元格格式
        CellStyle cellStyle = wb.createCellStyle();
        List<String> columnNameList = JsonUtil.getColumnNameList(clazz);
        for (int i = 0; i < columnNameList.size(); i++) {
            //用行对象创建单元格对象cell
            Cell cell = row.createCell(i);
            //用cell对象读写，设置Excel工作表的值
            cell.setCellValue(columnNameList.get(i));
        }
        Integer rowIndex = 1;
        List<Map<String, String>> mapList = JsonUtil.getColumnValueList(list, clazz);
        for (Map map : mapList) {
            //用sheet对象创建行对象
            Row dataRow = sheet.createRow(rowIndex++);
            for (int i = 0; i < columnNameList.size(); i++) {
                //用行对象创建单元格对象cell
                Cell cell = dataRow.createCell(i);
                //用cell对象读写，设置Excel工作表的值
                String value = (String) map.get(columnNameList.get(i));
                cell.setCellValue(value);
            }
        }
        //输出Excel文件
        FileOutputStream outputStream = new FileOutputStream(path);
        wb.write(outputStream);
        outputStream.flush();
    }

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(String path) throws IOException {
        Workbook wb = null;
        //Excel&nbsp;2003
        if (path.endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook();
        } else if (path.endsWith(EXCEL_XLSX)) {
            // Excel 2007/2010
            wb = new XSSFWorkbook();
        }
        return wb;
    }

    public static Workbook openWorkbook(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        Workbook wb = null;
        if (path.endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook(new POIFSFileSystem(is));
        } else if (path.endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook(is);
        }
        return wb;
    }
}
