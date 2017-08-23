package org.linagora.LinThumbnail.formats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.linagora.LinThumbnail.FileResource;
import org.linagora.LinThumbnail.utils.Constants;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents common image format files (BMP, PNG, JPEG, GIF)
 * 
 * @author sduprey
 */
public class ImageResource extends FileResource {
	public Logger logger = LoggerFactory.getLogger(ImageResource.class);

	public ImageResource(File resource, String extension) {
		this.resource = resource;
		this.extension = extension;
	}

	public BufferedImage generateThumbnailImage() throws IOException {
		BufferedImage image = null;
		BufferedImage thumbnailImage = null;
		
		image = ImageIO.read(this.resource);
		thumbnailImage = ImageUtils.scale(image, Constants.MAXDIM);
		
		return thumbnailImage;
	}

	public InputStream generateThumbnailInputStream() throws IOException {
		BufferedImage image = generateThumbnailImage();
		return ImageUtils.getInputStreamFromImage(image, "png");
	}

}
