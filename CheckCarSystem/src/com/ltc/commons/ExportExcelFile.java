package com.ltc.commons;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.ltc.domain.CarInfo;

public class ExportExcelFile {
	public void exprotExcelFile(List<CarInfo> list,String path) {
		try {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = workbook.createSheet("Sheet1");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = workbook.createCellStyle();
			
			sheet.setColumnWidth(0, 3800);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 3500);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 4000);
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 4000);
			sheet.setColumnWidth(8, 3500);
			
			// 设置这些样式
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			style.setFillForegroundColor(HSSFColor.YELLOW.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// 生成一个字体
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.VIOLET.index);
			font.setFontHeightInPoints((short) 14);
			font.setColor((short)160);
			font.setFontName("宋体");
			//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			// 把字体应用到当前的样式
			style.setFont(font);
			// 生成并设置另一个样式
			HSSFCellStyle style2 = workbook.createCellStyle();
		    style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 生成另一个字体
			HSSFFont font2 = workbook.createFont();
			font2.setColor(HSSFColor.VIOLET.index);
			font2.setFontHeightInPoints((short) 12);
			font2.setColor((short)160);
			font2.setFontName("宋体");
			// 把字体应用到当前的样式
			style2.setFont(font2);

			HSSFCell cell = row.createCell(0);
			cell.setCellValue("车牌号码");
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue("汽车类型");
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue("车主姓名");
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue("车辆年检");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue("技术等级");
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue("二级维护");
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue("强制保险");
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue("手机号码");
			cell.setCellStyle(style);
			
			cell = row.createCell(8);
			cell.setCellValue("是否通知");
			cell.setCellStyle(style);
			

			// 第五步，写入实体数据
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 1);
					CarInfo ci  = list.get(i);
					// 第四步，创建单元格，并设置值
					//添加车牌号信息到excel单元格中--------------
					cell = row.createCell(0);
					cell.setCellValue(ci.getCarNumber());
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加汽车类型信息到excel单元格中------------
					cell = row.createCell(1);
					cell.setCellValue(ci.getCarStyle());
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加车主姓名信息到excel单元格中------------
					cell = row.createCell(2);
					cell.setCellValue(ci.getUserName());
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加车辆年检信息到excel单元格中---------
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String cyc = sdf.format(ci.getCarYearCheck());
					cell = row.createCell(3);
					cell.setCellValue(cyc);
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加技术等级信息到excel单元格中---------
					String tcl = sdf.format(ci.getTechnologyLevel());
					cell = row.createCell(4);
					cell.setCellValue(tcl);
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加二级维护信息到excel单元格中---------
					String twl = sdf.format(ci.getTowLevel());
					cell = row.createCell(5);
					cell.setCellValue(twl);
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加强制保险信息到excel单元格中---------
					String cli = sdf.format(ci.getCompulsoryInsurance());
					cell = row.createCell(6);
					cell.setCellValue(cli);
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加手机号码信息到excel单元格中---------
					cell = row.createCell(7);
					cell.setCellValue(ci.getPhoneNum());
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
					//添加是否通知信息到excel单元格中---------
					cell = row.createCell(8);
					cell.setCellValue(ci.getNotice());
					//设置该单元格样式
					cell.setCellStyle(style2);
					//--------------------------------------
					
				}
			}
			// 第六步，将文件存到指定位置

			FileOutputStream fos = new FileOutputStream(path+"/车检信息备份文件"+new SimpleDateFormat("yyyy-MM-dd")
			.format(new Date())+".xls");
			workbook.write(fos);
			fos.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
