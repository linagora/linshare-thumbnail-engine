package org.linagora.LinThumbnail.utils;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;
import org.linagora.LinThumbnail.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtilsTest {
	private static Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);

	@Test
	public void testGetName() {
		File file = new File("src/test/resources/testingThumbnail.txt");
		String name = FileUtils.getName(file);
		logger.info(name);
		if (!name.equalsIgnoreCase("testingThumbnail")) {
			fail();
		}
	}

	@Test
	public void testGetPath() {
		File file = new File("src/test/resources/testingThumbnail.txt");
		String path = FileUtils.getPath(file);
		logger.info(path);
		if (!path.contains("src/test/resources") && path.contains("testingThumbnail")) {
			fail();
		}
		file = new File("src\\test\\resources\\testingThumbnail.txt");
		path = FileUtils.getPath(file);
		logger.info(path);
		if (!path.contains("src/test/resources") && path.contains("testingThumbnail")) {
			fail();
		}
		file = new File("src"+File.pathSeparator+"test"+File.pathSeparator+"resources"+File.pathSeparator+"testingThumbnail.txt");
		path = FileUtils.getPath(file);
		logger.info(path);
		if (!path.contains("src/test/resources") && path.contains("testingThumbnail")) {
			fail();
		}
	}

}
