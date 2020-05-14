package com.unihyr.util;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.unihyr.constraints.GeneralConfig;
import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;
import com.unihyr.domain.PostConsultant;

public class TableToExcel
{
	public static void generateExcel(List<GlobalRating> globalRating){
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date date = new Date();
			String filename =GeneralConfig.UploadPath+dateFormat.format(date)+ "Write.xls";
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("FirstSheet");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue("SNo.");
			rowhead.createCell((short) 1).setCellValue("turnaround time");
			rowhead.createCell((short) 2).setCellValue("shortlistRatio");
			rowhead.createCell((short) 3).setCellValue("industry coverage");
			rowhead.createCell((short) 4).setCellValue("Consultant Name");
			rowhead.createCell((short) 5).setCellValue("Industry Id");
			int i = 1;
			for (GlobalRating rating : globalRating)
			{
				HSSFRow row = sheet.createRow(i);
				row.createCell((short) 0).setCellValue(i);
				row.createCell((short) 1).setCellValue(rating.getTurnAround());
				row.createCell((short) 2).setCellValue(rating.getShortlistRatio());
				row.createCell((short) 3).setCellValue(rating.getIndustrycoverage());
				row.createCell((short) 4).setCellValue(rating.getRegistration().getUserid());
				row.createCell((short) 5).setCellValue(rating.getIndustryId());
				i++;
			}
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
		} catch ( Exception ex ) {
	    System.out.println(ex);
	}}
		public static void generateExcelwhenread(List<GlobalRatingPercentile> postconsultant,List<GlobalRating> gRating){
			try
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				Date date = new Date();
				System.out.println(dateFormat.format(date));
				String filename = GeneralConfig.UploadPath+dateFormat.format(date)+"Read.xls";
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("FirstSheet");
				HSSFRow rowhead = sheet.createRow((short) 0);
				rowhead.createCell((short) 0).setCellValue("SNo.");
				rowhead.createCell((short) 1).setCellValue("turnaround time");
				rowhead.createCell((short) 2).setCellValue("shortlistRatio");
				rowhead.createCell((short) 3).setCellValue("closure");
				rowhead.createCell((short) 4).setCellValue("offerdrop");
				rowhead.createCell((short) 5).setCellValue("industry coverage");
				rowhead.createCell((short) 6).setCellValue("Consultant Name");
				rowhead.createCell((short) 7).setCellValue("Industry Id");
			int i = 1;
				for (GlobalRatingPercentile rating : postconsultant)
				{
					HSSFRow row = sheet.createRow(i);
					row.createCell((short) 0).setCellValue(i);
					row.createCell((short) 1).setCellValue(rating.getPercentileTr());
					row.createCell((short) 2).setCellValue(rating.getPercentileSh());
					row.createCell((short) 3).setCellValue(rating.getPercentileCl());
					row.createCell((short) 4).setCellValue(rating.getPercentileOd());
					row.createCell((short) 5).setCellValue(rating.getPercentileInC());
					row.createCell((short) 6).setCellValue(rating.getRegistration().getUserid());
					row.createCell((short) 7).setCellValue(rating.getIndustryId());
					i++;
				}
				FileOutputStream fileOut = new FileOutputStream(filename);
				workbook.write(fileOut);
				
				
				 filename = GeneralConfig.UploadPath+dateFormat.format(date)+"Read1.xls";
				 workbook = new HSSFWorkbook();
				 sheet = workbook.createSheet("FirstSheet");
				 rowhead = sheet.createRow((short) 0);
				rowhead.createCell((short) 0).setCellValue("SNo.");
				rowhead.createCell((short) 1).setCellValue("turnaround time");
				rowhead.createCell((short) 2).setCellValue("shortlistRatio");
				rowhead.createCell((short) 3).setCellValue("industry coverage");
				rowhead.createCell((short) 4).setCellValue("Consultant Name");
				rowhead.createCell((short) 5).setCellValue("Industry Id");
			 i = 1;
				for (GlobalRating rating : gRating)
				{
					HSSFRow row = sheet.createRow(i);
					row.createCell((short) 0).setCellValue(i);
					row.createCell((short) 1).setCellValue(rating.getTurnAround());
					row.createCell((short) 2).setCellValue(rating.getShortlistRatio());
					row.createCell((short) 3).setCellValue(rating.getIndustrycoverage());
					row.createCell((short) 4).setCellValue(rating.getRegistration().getUserid());
					row.createCell((short) 5).setCellValue(rating.getIndustryId());
					i++;
				}
				 fileOut = new FileOutputStream(filename);
				workbook.write(fileOut);
				fileOut.close();
			} catch ( Exception ex ) {
		    System.out.println(ex);
		}
}
	
}
