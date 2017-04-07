package org.linagora.LinThumbnail.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Help class for image manipulation
 * 
 * @author sduprey
 */
public class ImageUtils {

	/**
	 * Scale an image to the maxDim dimension
	 * 
	 * @param source
	 * @param maxDim
	 * @return
	 */
	public static BufferedImage scale(BufferedImage source, int maxDim) {
		if (source == null) {
			return null;
		}
		int initialWidth = source.getWidth(null);
		int initialHeight = source.getHeight(null);
		int maxOfThem = Math.max(initialHeight, initialWidth);
		double scale = maxDim / (double) maxOfThem;
		int w = (int) (scale * (double) initialWidth);
		int h = (int) (scale * (double) initialHeight);

		BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = buf.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, w, h, null);
		g.dispose();
		return buf;
	}

	/**
	 * Get the InputStream corresponding to the given image
	 * 
	 * @param image
	 * @param imageType
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStreamFromImage(BufferedImage image, String imageType) throws IOException {
		if (image == null) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, imageType, out);
		byte[] imgData = out.toByteArray();
		InputStream inputStream = new ByteArrayInputStream(imgData);
		return inputStream;
	}

	/**
	 * Get the image corresponding to the given InputStream
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage getBufferedImageFromInputStream(InputStream stream) throws IOException {
		if (stream == null) {
			return null;
		}
		BufferedImage bufferedImage = ImageIO.read(stream);
		return bufferedImage;
	}

}
