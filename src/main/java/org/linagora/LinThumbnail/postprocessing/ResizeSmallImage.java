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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ResizeSmallImage extends ResizeDecorator {

	public ResizeSmallImage(Filter filter) {
		super(filter);
	}

	@Override
	public BufferedImage apply(BufferedImage image, int maxImageSize) {
		BufferedImage imageResize = filter.apply(image, maxImageSize);
		int newMaxImageSize = calculMaxFormat(imageResize.getWidth(), imageResize.getHeight());
		imageResize = filter.apply(image, newMaxImageSize);
		imageResize = cropImage(imageResize);
		return imageResize;
	}

	protected int calculMaxFormat(int width, int hight) {
		int result = 0;
		if (width > hight) {
			result = (((h * w) - (w * hight)) / hight) + w; 
		} else if (width < hight) {
			result = (((w * w) - (w * width )) / width) + w; 
		} else {
			return width;
		}
		return result;
	}

	protected BufferedImage cropImage (BufferedImage image) {
		Rectangle goal;
		if (image.getHeight() > image.getWidth()) {
			goal = cropHight(image.getHeight(), image.getWidth());
		} else if (image.getHeight() < image.getWidth()) {
			goal = cropWidth(image.getHeight(), image.getWidth());
		} else {
			if (image.getHeight() == 180) {
				goal = cropHight(image.getHeight(), image.getWidth());
			} else {
				return image; // nothing to do
			}
		}
		Rectangle clip =  goal.intersection(new Rectangle(image.getWidth(), image.getHeight()));
		image = image.getSubimage(clip.x , clip.y, clip.width, clip.height);
		return image;
	}

	protected Rectangle cropHight (int hight, int width) {
		int y = (hight- h) / 2;
		Rectangle goal = new Rectangle( 0, y, w, h);
		return goal;
	}

	protected Rectangle cropWidth (int hight, int width) {
		int x = (width - w) / 2;
		Rectangle goal = new Rectangle(x, 0, w, h);
		return goal;
	}
}
