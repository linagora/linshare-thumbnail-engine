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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.linagora.LinThumbnail.utils.Constants;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.linagora.LinThumbnail.utils.MediumThumbnail;
import org.linagora.LinThumbnail.utils.ThumbnailConfig;
import org.linagora.LinThumbnail.utils.ThumbnailKind;

/**
 * FileResource is the class containing the file object from which the thumbnail
 * is generated. <br/>
 * Supported formats of file are:
 * <ul>
 * <li>OpenDocumentFormat files: ODP, ODT, ODS, ODG</li>
 * <li>Images files: JPEG, PNG, GIF, BMP</li>
 * <li>PDF files</li>
 * <li>Text files: TXT, XML, ...</li>
 * <li>MSFDocumentFormat files: DOC, XLS, PPT, DOCX, XSLX, PPTX</li>
 * </ul>
 * 
 * @author sduprey
 */
public abstract class FileResource {

	protected File resource;

	private ThumbnailConfig defaultThumbnail;

	/**
	 * Generate the thumbnail of the FileResource in a BufferedImage
	 * 
	 * @param thumb
	 * @return BufferedImage
	 * @throws IOException
	 */
	public abstract BufferedImage generateThumbnailImage(ThumbnailConfig thumb) throws IOException;

	public Map<ThumbnailKind, BufferedImage> generateThumbnailImageMap() throws IOException {
		Map<ThumbnailKind, BufferedImage> thumbnailMap = new HashMap<ThumbnailKind, BufferedImage>();
		for(ThumbnailKind kind : ThumbnailKind.values()) {
			ThumbnailConfig thumbnailConfig = ThumbnailConfig.getThumbnailConfigFactory(resource.getAbsolutePath(), kind);
			thumbnailMap.put(kind, generateThumbnailImage(thumbnailConfig));
		}
		return thumbnailMap;
	}

	/**
	 * Generate the thumbnail of the FileResource in a BufferedImage
	 * 
	 * @return BufferedImage
	 * @throws IOException
	 */
	protected BufferedImage generateThumbnailImage() throws IOException {
		return generateThumbnailImage(getDefaultThumbnail());
	}

	/**
	 * Get the thumbnail
	 * 
	 * @return ThumbnailConfig
	 * @throws IOException
	 */
	public ThumbnailConfig getDefaultThumbnail() {
		if (this.defaultThumbnail == null) {
			return new MediumThumbnail(this.resource.getAbsolutePath());
		}
		return this.defaultThumbnail;
	}

	public void setDefaultThumbnail(ThumbnailConfig thumbnail) {
		this.defaultThumbnail = thumbnail;
	}

	/**
	 * Resize the image
	 * 
	 * @param image
	 * @param thumbnail
	 * @return image
	 * @throws IOException
	 */
	protected BufferedImage resizeImage(BufferedImage image, ThumbnailConfig thumbnail) {
		int maxDim = Math.max(image.getHeight(), image.getWidth());
		if (maxDim > thumbnail.getMaxImageSize()) {
			image = ImageUtils.scale(image, thumbnail.getMaxImageSize());
		}
		return image;
	}

	protected BufferedImage smallThumbnailResize(BufferedImage originalImage) {
		BufferedImage resizedImage = new BufferedImage(180, 135, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		if (originalImage.getHeight() >  originalImage.getWidth()) {
			g.drawImage(originalImage, 0, 0, 260, 135, 0, 0, 180, 135, null);
			g.dispose();
		} else {
			g.drawImage(originalImage, 0, 0, 180, 135, null);
			g.dispose();
		}
		return resizedImage;
	}

	/**
	 * Generate the thumbnail of the FileResource in an InputStream
	 * 
	 * @return InputStream
	 * @throws IOException
	 */
	public abstract InputStream generateThumbnailInputStream() throws IOException;

	/**
	 * Generates a thumbnail of the FileResource to the given absolute path
	 * 
	 * @param thumbnail
	 * @param thumbnailAbsolutePath
	 * @return GENERATE_OK or GENERATE_KO
	 * @throws IOException
	 */
	public boolean generateThumbnail(ThumbnailConfig thumbnail, String thumbnailAbsolutePath) throws IOException {
		File thumbnailFile = new File(thumbnailAbsolutePath);
		BufferedImage thumbnailImage = generateThumbnailImage(thumbnail);

		if (thumbnailImage == null) {
			return Constants.GENERATE_KO;
		}
		thumbnailFile.createNewFile();
		ImageIO.write(thumbnailImage, Constants.THMB_DEFAULT_FORMAT, thumbnailFile);
		return Constants.GENERATE_OK;
	}

	/**
	 * Generates a thumbnail of the FileResource to the given absolute path
	 * 
	 * @param thumbnailAbsolutePath
	 * @return boolean
	 * @throws IOException
	 */
	public boolean generateThumbnail(String thumbnailAbsolutePath) throws IOException {
		return generateThumbnail(getDefaultThumbnail(), thumbnailAbsolutePath);
	}

	public boolean generateThumbnail(ThumbnailConfig thumbnail) throws IOException {
		return generateThumbnail(thumbnail, thumbnail.getDefaultName());
	}

	/**
	 * Generates a thumbnail of the FileResource to the default absolute path
	 * 
	 * @return GENERATE_OK or GENERATE_KO
	 * @throws IOException
	 */
	public boolean generateThumbnail() throws IOException {
		return generateThumbnail(getDefaultThumbnail());
	}

	/**
	 * Generates a thumbnail of the FileResource to the default absolute path
	 * and return this path
	 * 
	 * @return the path to the thumbnail
	 * @throws IOException
	 */
	public String generateThumbnailToDefaultPath() throws IOException {
		String path = this.getDefaultThumbnail().getDefaultName();
		boolean result = generateThumbnail(path);
		if (result) {
			return path;
		}
		return null;
	}

	/**
	 * Generates a thumbnail of the FileResource to the given path and with the
	 * given name
	 * 
	 * @param thmbPath
	 *            the path to the thumbnail location
	 * @param thmbName
	 *            the thumbnail name
	 * @return GENERATE_OK or GENERATE_KO
	 * @throws IOException
	 */
	public boolean generateThumbnail(String thmbPath, String thmbName) throws IOException {
		return generateThumbnail(thmbPath + File.pathSeparator + thmbName);
	}
}
