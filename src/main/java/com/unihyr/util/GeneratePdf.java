package com.unihyr.util;

import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.UUID;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.unihyr.constraints.GeneralConfig;
import com.unihyr.domain.BillingDetails;

public class GeneratePdf
{
public static void generatePdf(String content,String pathToStore){
	try
		{
			Document document = new Document(PageSize.A4);
			String path = GeneralConfig.UploadPath +pathToStore ;
			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.addAuthor("Unihyr");
			document.addCreator("Unihyr");
			document.addSubject("Unihyr Generated Doc");
			document.addCreationDate();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(content));
			document.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
}
}
