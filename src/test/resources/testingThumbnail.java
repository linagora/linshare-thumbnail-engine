package com.poc.apache.test.test;

import java.awt.image.BufferedImage;
import java.io.File;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class JODConverter {

	public static void main(String[] args) {

		BufferedImage image = null;
		String inputResource = "target/ppt_file_1_.ppt";
		String outputResource = "target/ppt_file_1_.ppt.pdf";
		String imageOutputResource = "target/ppt_file_1_.ppt.pdf.png";

		try {

		
			File inputFile = new File(inputResource);
			File outputFile = new File(outputResource);
			
			// connect to an OpenOffice.org instance running on port 8100
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
			connection.connect();
	
			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			
			// close the connections
			connection.disconnect();

			// convertir en image
			PdfToPng pdfToPng = new PdfToPng(outputResource);
			image = pdfToPng.generateThumbnailImage(imageOutputResource);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
