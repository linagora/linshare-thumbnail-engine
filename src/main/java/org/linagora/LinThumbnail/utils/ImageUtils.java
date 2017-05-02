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
