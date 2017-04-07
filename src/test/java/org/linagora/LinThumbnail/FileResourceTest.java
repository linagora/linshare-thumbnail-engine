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
		FileResource fr = fabrique.getFileResource(file);
		fr.generateThumbnail();

		File file2 = new File(pathToTestFiles + "testingThumbnail.odt");
		FileResource fr2 = fabrique.getFileResource(file2);
		fr2.generateThumbnail();

		File file3 = new File(pathToTestFiles + "testingThumbnail.ods");
		FileResource fr3 = fabrique.getFileResource(file3);
		fr3.generateThumbnail();

		File file3b = new File(pathToTestFiles + "testingThumbnail.odg");
		FileResource fr3b = fabrique.getFileResource(file3b);
		fr3b.generateThumbnail();

		File file4 = new File(pathToTestFiles + "testingThumbnail.jpg");
		FileResource fr4 = fabrique.getFileResource(file4);
		fr4.generateThumbnail();

		File file5 = new File(pathToTestFiles + "testingThumbnail.png");
		FileResource fr5 = fabrique.getFileResource(file5);
		fr5.generateThumbnail();

		File file6 = new File(pathToTestFiles + "testingThumbnail.gif");
		FileResource fr6 = fabrique.getFileResource(file6);
		fr6.generateThumbnail();

		File file7 = new File(pathToTestFiles + "testingThumbnail.bmp");
		FileResource fr7 = fabrique.getFileResource(file7);
		fr7.generateThumbnail();

		File file8 = new File(pathToTestFiles + "testingThumbnail.pdf");
		FileResource fr8 = fabrique.getFileResource(file8);
		fr8.generateThumbnail();

		File file9 = new File(pathToTestFiles + "testingThumbnail.txt");
		FileResource fr9 = fabrique.getFileResource(file9);
		fr9.generateThumbnail();

		File file9b = new File(pathToTestFiles + "testingThumbnail.xml");
		FileResource fr9b = fabrique.getFileResource(file9b);
		fr9b.generateThumbnail();

		File file10 = new File(pathToTestFiles + "testingThumbnail.doc");
		FileResource fr10 = fabrique.getFileResource(file10);
		fr10.generateThumbnail();

		File file11 = new File(pathToTestFiles + "testingThumbnail.xls");
		FileResource fr11 = fabrique.getFileResource(file11);
		fr11.generateThumbnail();

		File file12 = new File(pathToTestFiles + "testingThumbnail.ppt");
		FileResource fr12 = fabrique.getFileResource(file12);
		fr12.generateThumbnail();

		File file13 = new File(pathToTestFiles + "testingThumbnail.docx");
		FileResource fr13 = fabrique.getFileResource(file13);
		fr13.generateThumbnail();

		File file14 = new File(pathToTestFiles + "testingThumbnail.xlsx");
		FileResource fr14 = fabrique.getFileResource(file14);
		fr14.generateThumbnail();

		File file15 = new File(pathToTestFiles + "testingThumbnail.pptx");
		FileResource fr15 = fabrique.getFileResource(file15);
		fr15.generateThumbnail();

		File file16 = new File(pathToTestFiles + "testingThumbnail.java");
		FileResource fr16 = fabrique.getFileResource(file16);
		fr16.generateThumbnail();

		File file17 = new File(pathToTestFiles + "testingThumbnail.html");
		FileResource fr17 = fabrique.getFileResource(file17);
		fr17.generateThumbnail();

		File file18 = new File(pathToTestFiles + "testingThumbnail.php");
		FileResource fr18 = fabrique.getFileResource(file18);
		fr18.generateThumbnail();

		File file19 = new File(pathToTestFiles + "testingThumbnail.cpp");
		FileResource fr19 = fabrique.getFileResource(file19);
		fr19.generateThumbnail();

		File file20 = new File(pathToTestFiles + "testingThumbnail.log");
		FileResource fr20 = fabrique.getFileResource(file20);
		fr20.generateThumbnail();

		File file21 = new File(pathToTestFiles + "testingThumbnail.css");
		FileResource fr21 = fabrique.getFileResource(file21);
		fr21.generateThumbnail();

		File file22 = new File(pathToTestFiles + "testingThumbnail.less");
		FileResource fr22 = fabrique.getFileResource(file22);
		fr22.generateThumbnail();

		File file23 = new File(pathToTestFiles + "testingThumbnail.js");
		FileResource fr23 = fabrique.getFileResource(file23);
		fr23.generateThumbnail();

		File file25 = new File(pathToTestFiles + "testingThumbnail.yml");
		FileResource fr25 = fabrique.getFileResource(file25);
		fr25.generateThumbnail();

		File file26 = new File(pathToTestFiles + "testingThumbnail.xslt");
		FileResource fr26 = fabrique.getFileResource(file26);
		fr26.generateThumbnail();

		File file27 = new File(pathToTestFiles + "Firefox.Setup.29.0.1.exe");
		FileResource fr27 = fabrique.getFileResource(file27);
		if (fr27 != null) {
			fr27.generateThumbnail();
		}

	}

}
