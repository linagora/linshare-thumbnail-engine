package org.linagora.LinThumbnail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileResourceTest {

	@Test
	public void testGenerateThumbnail() throws IOException {
		FileResourceFactory fabrique = FileResourceFactory.getInstance();
		String pathToTestFiles = "src/test/resources/";
		
		File file = new File(pathToTestFiles + "testingThumbnail.odp");
		FileResource fr = fabrique.getFileResource(file, "testingThumbnail.odp");
		fr.generateThumbnail();
		
		File file2 = new File(pathToTestFiles + "testingThumbnail.odt");
		FileResource fr2 = fabrique.getFileResource(file2, "odt");
		fr2.generateThumbnail();
		
		File file3 = new File(pathToTestFiles + "testingThumbnail.ods");
		FileResource fr3 = fabrique.getFileResource(file3, "ods");
		fr3.generateThumbnail();
		
		File file3b = new File(pathToTestFiles + "testingThumbnail.odg");
		FileResource fr3b = fabrique.getFileResource(file3b, "odg");
		fr3b.generateThumbnail();
		
		File file4 = new File(pathToTestFiles + "testingThumbnail.jpg");
		FileResource fr4 = fabrique.getFileResource(file4, "jpg");
		fr4.generateThumbnail();
		
		File file5 = new File(pathToTestFiles + "testingThumbnail.png");
		FileResource fr5 = fabrique.getFileResource(file5, "png");
		fr5.generateThumbnail();
		
		File file6 = new File(pathToTestFiles + "testingThumbnail.gif");
		FileResource fr6 = fabrique.getFileResource(file6, "gif");
		fr6.generateThumbnail();
		
		File file7 = new File(pathToTestFiles + "testingThumbnail.bmp");
		FileResource fr7 = fabrique.getFileResource(file7, "bmp");
		fr7.generateThumbnail();
		
		File file8 = new File(pathToTestFiles + "testingThumbnail.pdf");
		FileResource fr8 = fabrique.getFileResource(file8, "pdf");
		fr8.generateThumbnail();
		
		File file9 = new File(pathToTestFiles + "testingThumbnail.txt");
		FileResource fr9 = fabrique.getFileResource(file9, "txt");
		fr9.generateThumbnail();
		
		File file9b = new File(pathToTestFiles + "testingThumbnail.xml");
		FileResource fr9b = fabrique.getFileResource(file9b, "xml");
		fr9b.generateThumbnail();
		
		File file10 = new File(pathToTestFiles + "testingThumbnail.doc");
		FileResource fr10 = fabrique.getFileResource(file10, "doc");
		fr10.generateThumbnail();
		
		File file11 = new File(pathToTestFiles + "testingThumbnail.xls");
		FileResource fr11 = fabrique.getFileResource(file11, "xls");
		fr11.generateThumbnail();
		
		File file12 = new File(pathToTestFiles + "testingThumbnail.ppt");
		FileResource fr12 = fabrique.getFileResource(file12, "ppt");
		fr12.generateThumbnail();
		
	}

}
