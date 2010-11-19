package org.linagora.LinThumbnail.utils;

import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtilsTest {
	private static Logger logger = LoggerFactory.getLogger(ImageUtilsTest.class);

	@Test
	public void testScale() {
		File file = new File("src/test/resources/testingThumbnail.png");
		try {
			BufferedImage image = ImageIO.read(file);
			logger.info("Test file of dimension "+image.getWidth(null)+"x"+image.getHeight(null));

			BufferedImage imageScaled = ImageUtils.scale(image, 256);
			logger.info("Scaled file of dimension "+imageScaled.getWidth(null)+"x"+imageScaled.getHeight(null));

			if (imageScaled.getHeight(null)>256 || imageScaled.getWidth(null)>256) {
				fail();
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
	}

}
