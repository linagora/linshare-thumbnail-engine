/*
 * LinShare is an open source filesharing software, part of the LinPKI software
 * suite, developed by Linagora.
 *
 * Copyright (C) 2017 LINAGORA
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

package org.linagora.LinThumbnail.postprocessing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ResizeSmallImage extends ResizeDecorator {

	public ResizeSmallImage(Filter filter) {
		super(filter);
	}

	@Override
	public BufferedImage apply(BufferedImage image) {
		BufferedImage imageResize = filter.apply(image);
		int newMaxImageSize = calculMaxFormat(imageResize.getWidth(), imageResize.getHeight());
		filter.getThumbnailConfig().setMaxImageSize(newMaxImageSize);
		imageResize = filter.apply(image);
		imageResize = cropImage(imageResize);
		return imageResize;
	}

	/**
	 * Enlarging of the image to obtain at least the width = 180 or the height = 135.
	 * return the maximum size between width and height after enlarging the image,
	 * 
	 * @param width
	 * @param hight
	 * @return int
	 */
	public int calculMaxFormat(int width, int hight) {
		int result = 0;
		if ((width > hight) && (hight != 0)) {
			result = (((hightConstant * widthConstant) - (widthConstant * hight)) / hight) + widthConstant;
		} else if ((width < hight) && (width != 0)) {
			result = (((widthConstant * widthConstant) - (widthConstant * width)) / width) + widthConstant;
		} else {
			return width;
		}
		return result;
	}

	/**
	 * Crop image, to fit it into 180*135
	 * 
	 * @param image
	 * @return BufferedImage
	 */
	public BufferedImage cropImage(BufferedImage image) {
		int hight = image.getHeight();
		int width = image.getWidth();
		if (hight > hightConstant && width < widthConstant) {
			image = intersection(cropWidth(hight, width), image);
			return addBackground(image);
		} else if (hight < hightConstant && width > widthConstant) {
			image = intersection(cropHight(hight, width), image);
			return addBackground(image);
		} else if (((hight <= hightConstant) && (width <= widthConstant))) {
			return addBackground(image);
		} else if (hight > width) {
			return intersection(cropHight(hight, width), image);
		} else if (hight < width) {
			return intersection(cropWidth(hight, width), image);
		} else {
			return intersection(cropHight(hight, width), image);
		}
	}

	/**
	 * Intersection between the Rectangle and the image, and extraction of subimage
	 * 
	 * @param rectangleCrop
	 * @param image
	 * @return BufferedImage
	 */
	public BufferedImage intersection(Rectangle rectangleCrop, BufferedImage image) {
		Rectangle clip = rectangleCrop.intersection(new Rectangle(image.getWidth(), image.getHeight()));
		image = image.getSubimage(clip.x, clip.y, clip.width, clip.height);
		return image;
	}

	/**
	 * (addBackground) is applicated to the image smaller than 180*135
	 * its consist to add a transparent background to these images.
	 * 
	 * @param image
	 * @return BufferedImage
	 */
	public BufferedImage addBackground(BufferedImage image) {
		BufferedImage backgroundImage = new BufferedImage(widthConstant, hightConstant, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = backgroundImage.createGraphics();
		graphics.setColor(new Color(0, 0, 0, 0));
		graphics.fillRect(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
		BufferedImage combined = new BufferedImage(widthConstant, hightConstant, BufferedImage.TYPE_INT_ARGB);
		Graphics g = combined.getGraphics();
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(image, (widthConstant - image.getWidth()) / 2, (hightConstant - image.getHeight()) / 2, null);
		return combined;
	}

	/**
	 * CropHight : crop the image in the middle, removing the excess in height
	 * maximum height = hightConstant 
	 * 
	 * @param hight
	 * @param width
	 * @return Rectangle
	 */
	public Rectangle cropHight(int hight, int width) {
		int y = (hight - hightConstant) / 2;
		Rectangle rectangleCrop = new Rectangle(0, y, widthConstant, hightConstant);
		return rectangleCrop;
	}

	/**
	 * CropWidth : crop the image in the middle, removing the excess in width
	 * Maximum width = widthConstant
	 * 
	 * @param hight
	 * @param width
	 * @return Rectangle
	 */
	public Rectangle cropWidth(int hight, int width) {
		int x = (width - widthConstant) / 2;
		Rectangle rectangleCrop = new Rectangle(x, 0, widthConstant, hightConstant);
		return rectangleCrop;
	}

}
