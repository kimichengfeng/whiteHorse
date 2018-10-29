package com.wecash.nevermore.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.servlet.ServletResponse;

/**
 * Poi的工具类
 * 
 *
 */

public class PoiUtil
{
	private static Logger logger = LoggerFactory.getLogger(PoiUtil.class);
	private static final String OFFICE_URL = "https://view.officeapps.live.com/op/view.aspx?src=";
	/**
	 * 创建一张sheet
	 * 
	 * @param headerList
	 * @param dataList
	 * @return
	 */
	public static Sheet createSheet(Workbook wb ,String name, List<String> headerList, List<List<String>> dataList)
	{
		Sheet sheet = wb.createSheet(name);
		int rowCount = 0 ; 
		int cellCount = 0 ; 
		Row row = sheet.createRow(rowCount);
		for (Iterator<String> iterator = headerList.iterator(); iterator.hasNext();)
		{
			String header = iterator.next();
			Cell cell = row.createCell(cellCount);
			cell.setCellValue(header);
			cellCount ++ ; 
		}
		
		rowCount = 1 ; 
		for (Iterator<List<String>> iterator = dataList.iterator(); iterator.hasNext();)
		{
			List<String> dataRowList = (List<String>) iterator.next();
			Row rowTwo = sheet.createRow(rowCount);
			cellCount = 0 ; 
			for (Iterator<String> iterator2 = dataRowList.iterator(); iterator2.hasNext();)
			{
				String data = (String) iterator2.next();
				Cell cell = rowTwo.createCell(cellCount);
				cell.setCellValue(data);
				cellCount ++ ; 
			}
			rowCount ++ ; 
		}
	    return sheet ; 
	}
	/**
	 * 将数据导出xls
	 *
	 *   键为:sheetName:"",值为map:headerList:第一行表头,dataList:dataList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean writeExcelOs(OutputStream os, List<Map<String,Object>> sheetList)
	{
		Workbook wb = new HSSFWorkbook();
		for (Iterator<Map<String, Object>> iterator = sheetList.iterator(); iterator.hasNext();)
		{
			Map<String, Object> map =iterator.next();
			//sheet名字
			String sheetName = map.get("name") + "";
			List<String> headerList = (List<String>) map.get("headerList");
			List<List<String>> dataList = (List<List<String>>) map.get("dataList");
			//创建sheet
			createSheet(wb, sheetName, headerList, dataList);
		}
		
		try
		{

			wb.write(os);
			os.close();
			return true ; 
		} catch (Exception e)
		{
			logger.error("导出xls失败了",e);
		}
		return false;
	}

//	public static void writeExcel(ServletResponse response, List<Map<String,Object>> sheetList){
//		try {
//			OutputStream os=response.getOutputStream();
//			 writeExcelOs(os,sheetList);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static boolean writeExcel(String flieName,List<Map<String,Object>> sheetList){
		File tarFile = new File(flieName);
		try {
			OutputStream os = new FileOutputStream(tarFile);
			return writeExcelOs(os,sheetList);
		} catch (FileNotFoundException e) {
			logger.info("文件不存在");
		}
		return false;
	}


	
	/**
	 * 读取xls文件内容
	 * @param tarFile
	 * @return 最外面的map存放了多张sheet,值为map,即sheet里面的内容
	 * titleMap:标题(键为名字,值为序号)
	 * name:sheet名字
	 * dataList:sheet中的数据,里面放的是map:(键为名字,值为单元格里面的值)
	 */
	public static List<Map<String,Object>> readExcel(File tarFile)
	{
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try
		{
			FileInputStream fis = new FileInputStream(tarFile);
			Workbook wb = WorkbookFactory.create(fis);
			int sheetNums = wb.getNumberOfSheets();
			for (int i = 0; i < sheetNums; i++)
			{
				Sheet sheet = wb.getSheetAt(i);
				Map<String,Object> sheetMap = new HashMap<>();
				sheetMap.put("name", sheet.getSheetName());
				//读取第一行,将结果放到MAP中
				Row titleRow = sheet.getRow(0);
				Map<String, Integer> titleMap = new HashMap<String, Integer>();
				int count = 0 ; 
				for (Iterator<Cell> iterator = titleRow.cellIterator(); iterator.hasNext();)
				{
					Cell cell = (Cell) iterator.next();
					String cellStr = cell.getStringCellValue() ;
					titleMap.put(cellStr, count);
					count ++ ; 
				}
				sheetMap.put("titleMap", titleMap);
				
				List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
				//读取第二行
				for (int j = 1; j <= sheet.getLastRowNum(); j++)
				{
					Row rowTemp = sheet.getRow(j);
					Map<String,Object> dataRowMap = new HashMap<String,Object>();
					//读取第一行单元格的值
					for (Iterator<Entry<String, Integer>> iterator = titleMap.entrySet().iterator(); iterator.hasNext();)
					{
						Entry<String, Integer> me = iterator.next();
						String key = me.getKey() + "" ;
						int val = Integer.valueOf(me.getValue() + "") ;
						dataRowMap.put(key,rowTemp.getCell(val) + "") ;
					}
					dataList.add(dataRowMap);
				}
				
				sheetMap.put("dataList", dataList);
				
				resultList.add(sheetMap);
			}
		}catch (Exception e)
		{
			logger.error("导入xls失败了",e);
		}
		return resultList; 
	}

//	public static List<List<String>> readExcelByClassPath(String fileName){
//
//		ClassPathResource classPathResource = new ClassPathResource(fileName);
//
//		try {
//			File file = classPathResource.getFile();
//			return readExcelOneSheet(file);
//		} catch (Exception e) {
//			logger.error("按照classPath导入excel失败了：",e);
//
//		}
//		return null;
//
//	}
//	public static List<List<String>> readExcelByABPath(String fileName){
//		File file=new File(fileName);
//
//		return readExcelOneSheet(file);
//
//	}

	/**
	 * 该方法仅适用于耽搁sheet读取
	 * 将每行内容读入到单个list中
	 * 返回值中嵌套在里面的list为每一行的内容包含title行
	 * @param file
	 * @return
     */
	public static List<List<String>> readExcelOneSheet(File file){
		List<List<String>> resultList= Lists.newArrayList();
		try {
			FileInputStream fis=new FileInputStream(file);
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sheet=wb.getSheetAt(0);
			for (int i=0;i<=sheet.getLastRowNum();i++){
				List<String> list= Lists.newArrayList();
				Row tempRow=sheet.getRow(i);
				for (int j=0;j<=tempRow.getLastCellNum();j++){
					Cell cell=tempRow.getCell(j);
					if (cell==null){
						list.add("");
					}else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						list.add(cell.getStringCellValue());
					}
				}
				resultList.add(list);
			}
		} catch (Exception e) {
			logger.error("读取excel发生错误，文件名：{}",file.getName());
		}
		return resultList;
	}

	/**
	 * 在线预览excel、word等
	 * @param url
	 * @return
	 */
	public static String onlineDocument(String url){
		return OFFICE_URL+url;
	}

}
