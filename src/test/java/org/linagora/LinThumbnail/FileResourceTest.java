/*
 * LinShare is an open source filesharing software, part of the LinPKI software
 * suite, developed by Linagora.
 * 
 * Copyright (C) 2010-2017 LINAGORA
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version, provided you comply with the Additional Terms applicable for
 * LinShare software by Linagora pursuant to Section 7 of the GNU Affero General
 * Public License, subsections (b), (c), and (e), pursuant to which you must
 * notably (i) retain the display of the “LinShare™” trademark/logo at the top
 * of the interface window, the display of the “You are using the Open Source
 * and free version of LinShare™, powered by Linagora © 2009–2017. Contribute to
 * Linshare R&D by subscribing to an Enterprise offer!” infobox and in the
 * e-mails sent with the Program, (ii) retain all hypertext links between
 * LinShare and linshare.org, between linagora.com and Linagora, and (iii)
 * refrain from infringing Linagora intellectual property rights over its
 * trademarks and commercial brands. Other Additional Terms apply, see
 * <http://www.linagora.com/licenses/> for more details.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License and
 * its applicable Additional Terms for LinShare along with this program. If not,
 * see <http://www.gnu.org/licenses/> for the GNU Affero General Public License
 * version 3 and <http://www.linagora.com/licenses/> for the Additional Terms
 * applicable to LinShare software.
 */

package org.linagora.LinThumbnail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.linagora.LinThumbnail.utils.Constants;
import org.linagora.LinThumbnail.utils.ThumbnailEnum;

public class FileResourceTest {

	private ThumbnailService ts = new ThumbnailService();

	@Before
	public void setUp() {
		ts.start();
	}

	@After
	public void tearDown() {
		ts.stop();
	}

	@Test
	public void testGenerateThumbnail() throws IOException {

		FileResourceFactory fabrique = ts.getFactory();

		String pathToTestFiles = "src/test/resources/";

		File file = new File(pathToTestFiles + "testingThumbnail.odp");
		FileResource fr = fabrique.getFileResource(file, "application/vnd.oasis.opendocument.presentation");
		generateThumbnailMap(fr, file.getAbsolutePath());

		File file2 = new File(pathToTestFiles + "testingThumbnail.odt");
		FileResource fr2 = fabrique.getFileResource(file2, "application/vnd.oasis.opendocument.text");
		generateThumbnailMap(fr2, file2.getAbsolutePath());

		File file3 = new File(pathToTestFiles + "testingThumbnail.ods");
		FileResource fr3 = fabrique.getFileResource(file3, "application/vnd.oasis.opendocument.spreadsheet");
		generateThumbnailMap(fr3, file3.getAbsolutePath());

		File file3b = new File(pathToTestFiles + "testingThumbnail.odg");
		FileResource fr3b = fabrique.getFileResource(file3b, "application/vnd.oasis.opendocument.graphics");
		fr3b.generateThumbnail();

		File file4 = new File(pathToTestFiles + "testingThumbnail.jpg");
		FileResource fr4 = fabrique.getFileResource(file4, "image/jpeg");
		fr4.generateThumbnail();

		File file5 = new File(pathToTestFiles + "testingThumbnail.png");
		FileResource fr5 = fabrique.getFileResource(file5, "image/png");
		fr5.generateThumbnail();

		File file6 = new File(pathToTestFiles + "testingThumbnail.gif");
		FileResource fr6 = fabrique.getFileResource(file6, "image/gif");
		fr6.generateThumbnail();

		File file7 = new File(pathToTestFiles + "testingThumbnail.bmp");
		FileResource fr7 = fabrique.getFileResource(file7, "image/x-ms-bmp");
		fr7.generateThumbnail();

		File file8 = new File(pathToTestFiles + "testingThumbnail.pdf");
		FileResource fr8 = fabrique.getFileResource(file8, "application/pdf");
		fr8.generateThumbnail();

		File file9 = new File(pathToTestFiles + "testingThumbnail.txt");
		FileResource fr9 = fabrique.getFileResource(file9, "text/plain");
		fr9.generateThumbnail();

		File file9b = new File(pathToTestFiles + "testingThumbnail.xml");
		FileResource fr9b = fabrique.getFileResource(file9b, "application/xml");
		fr9b.generateThumbnail();

		File file10 = new File(pathToTestFiles + "testingThumbnail.doc");
		FileResource fr10 = fabrique.getFileResource(file10, "application/msword");
		fr10.generateThumbnail();

		File file11 = new File(pathToTestFiles + "testingThumbnail.xls");
		FileResource fr11 = fabrique.getFileResource(file11, "application/vnd.ms-excel");
		fr11.generateThumbnail();

		File file12 = new File(pathToTestFiles + "testingThumbnail.ppt");
		FileResource fr12 = fabrique.getFileResource(file12, "application/vnd.ms-powerpoint");
		fr12.generateThumbnail();

		File file13 = new File(pathToTestFiles + "testingThumbnail.docx");
		FileResource fr13 = fabrique.getFileResource(file13, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		fr13.generateThumbnail();

		File file14 = new File(pathToTestFiles + "testingThumbnail.xlsx");
		FileResource fr14 = fabrique.getFileResource(file14, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		fr14.generateThumbnail();

		File file15 = new File(pathToTestFiles + "testingThumbnail.pptx");
		FileResource fr15 = fabrique.getFileResource(file15, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		fr15.generateThumbnail();

		File file16 = new File(pathToTestFiles + "testingThumbnail.java");
		FileResource fr16 = fabrique.getFileResource(file16, "application/java");
		fr16.generateThumbnail();

		File file17 = new File(pathToTestFiles + "testingThumbnail.html");
		FileResource fr17 = fabrique.getFileResource(file17, "text/html");
		fr17.generateThumbnail();

		File file18 = new File(pathToTestFiles + "testingThumbnail.php");
		FileResource fr18 = fabrique.getFileResource(file18, "text/x-php");
		fr18.generateThumbnail();

		File file19 = new File(pathToTestFiles + "testingThumbnail.cpp");
		FileResource fr19 = fabrique.getFileResource(file19, "text/x-c++src");
		fr19.generateThumbnail();

		File file20 = new File(pathToTestFiles + "testingThumbnail.log");
		FileResource fr20 = fabrique.getFileResource(file20, "text/x-log");
		fr20.generateThumbnail();

		File file21 = new File(pathToTestFiles + "testingThumbnail.css");
		FileResource fr21 = fabrique.getFileResource(file21, "text/css");
		fr21.generateThumbnail();

		File file22 = new File(pathToTestFiles + "testingThumbnail.less");
		FileResource fr22 = fabrique.getFileResource(file22, "text/x-less");
		fr22.generateThumbnail();

		File file23 = new File(pathToTestFiles + "testingThumbnail.js");
		FileResource fr23 = fabrique.getFileResource(file23, "text/plain");
		fr23.generateThumbnail();

		File file25 = new File(pathToTestFiles + "testingThumbnail.yml");
		FileResource fr25 = fabrique.getFileResource(file25, "text/plain");
		fr25.generateThumbnail();

		File file26 = new File(pathToTestFiles + "testingThumbnail.xslt");
		FileResource fr26 = fabrique.getFileResource(file26, "application/xhtml+xml");
		fr26.generateThumbnail();
	}

	public void generateThumbnailMap(FileResource fr, String absolutePath) throws IOException {
		Map<ThumbnailEnum, BufferedImage> thmbImages = fr.generateThumbnailImageMap();
		for (Map.Entry<ThumbnailEnum, BufferedImage> entry : thmbImages.entrySet()) {
			File thumbnailFile = new File(absolutePath + "_" + entry.getKey() + ".png");
			BufferedImage thumbnailImage = entry.getValue();
			thumbnailFile.createNewFile();
			ImageIO.write(thumbnailImage, Constants.THMB_DEFAULT_FORMAT, thumbnailFile);
		}
	}

}
