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

package org.linagora.LinThumbnail.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.junit.Test;
import org.linagora.LinThumbnail.postprocessing.Filter;
import org.linagora.LinThumbnail.postprocessing.Resize;
import org.linagora.LinThumbnail.postprocessing.ResizeSmallImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmallImageResizeTest {

	public Logger logger = LoggerFactory.getLogger(SmallImageResizeTest.class);

	private Filter filter = new Resize();

	private ResizeSmallImage resizeSmallImage = new ResizeSmallImage(filter);

	@Test
	public void testCalculMaxFormat() {
		BufferedImage image =  new BufferedImage(180, 50, BufferedImage.TYPE_INT_ARGB);
		int newSize = resizeSmallImage.calculMaxFormat(image.getWidth(), image.getHeight());
		logger.info("new size is " + newSize);
		assertTrue(newSize > 180);
	}

	@Test
	public void testCropImage() {
		BufferedImage image =  new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		BufferedImage newImage = resizeSmallImage.cropImage(image);
		assertEquals(newImage.getWidth(), 180);
		assertEquals(newImage.getHeight(), 135);

		image =  new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		newImage = resizeSmallImage.cropImage(image);
		assertEquals(newImage.getWidth(), 180);
		assertEquals(newImage.getHeight(), 135);

		image =  new BufferedImage(100, 135, BufferedImage.TYPE_INT_ARGB);
		newImage = resizeSmallImage.cropImage(image);
		assertEquals(newImage.getWidth(), 180);
		assertEquals(newImage.getHeight(), 135);

		image =  new BufferedImage(180, 100, BufferedImage.TYPE_INT_ARGB);
		newImage = resizeSmallImage.cropImage(image);
		assertEquals(newImage.getWidth(), 180);
		assertEquals(newImage.getHeight(), 135);

		image =  new BufferedImage(1000, 100, BufferedImage.TYPE_INT_ARGB);
		newImage = resizeSmallImage.cropImage(image);
		assertEquals(newImage.getWidth(), 180);
		assertEquals(newImage.getHeight(), 135);
	}

	@Test
	public void testAddBackround() {
		BufferedImage image =  new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		BufferedImage imageWithBackground = resizeSmallImage.addBackground(image);
		assertEquals(imageWithBackground.getWidth(), 180);
		assertEquals(imageWithBackground.getHeight(), 135);
	}

	@Test
	public void testCropWidthAndHight() {
		Rectangle cropWidth = resizeSmallImage.cropWidth(135, 200);
		assertTrue(cropWidth.getWidth() == 180);
		assertTrue(cropWidth.getHeight() == 135);

		Rectangle cropHight = resizeSmallImage.cropHight(135, 200);
		assertTrue(cropHight.getWidth() == 180);
		assertTrue(cropHight.getHeight() == 135);
	}

}
