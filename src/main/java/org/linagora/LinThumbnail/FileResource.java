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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.linagora.LinThumbnail.utils.ThumbnailConfig;
import org.linagora.LinThumbnail.utils.ThumbnailKind;
import org.linagora.LinThumbnail.utils.impl.ThumbnailConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	public Logger logger = LoggerFactory.getLogger(FileResource.class);

	protected File resource;

	private ThumbnailConfig defaultThumbnail;

	/**
	 * Generate the thumbnail of the FileResource
	 * 
	 * @param thumb
	 * @return File
	 * @throws IOException
	 */
	public abstract File generateThumbnailImage(ThumbnailConfig thumb) throws IOException;

	public abstract Boolean needToGeneratePDFPreview();

	/**
	 * Generate the thumbnails (SMALL, MEDIUM, LARGE and PDF) of the FileResource
	 * PDF thumbnail is not generated for image files and pdf files
	 *
	 * @return Map<ThumbnailKind, File>
	 * @throws IOException
	 */
	public Map<ThumbnailKind, File> generateThumbnailImageMap() throws IOException {
		Map<ThumbnailKind, File> thumbnailMap = new HashMap<ThumbnailKind, File>();
		try {
			for (ThumbnailKind kind : ThumbnailKind.values()) {
				// No need to generate PDF preview, if the native document is image or pdf
				if (ThumbnailKind.PDF.equals(kind) && !needToGeneratePDFPreview()) {
					continue;
				}
				ThumbnailConfig thumbnailConfig = ThumbnailConfigImpl.getThumbnailConfigFactory(resource.getAbsolutePath(),
						kind);
				File tempThumbnail = generateThumbnailImage(thumbnailConfig);
				if (tempThumbnail == null) {
					logger.warn("Thumbnail was null, we aborting generation of thumbnails for this file");
					return cleanThumbnailMap(thumbnailMap);
				}
				thumbnailMap.put(kind, tempThumbnail);
			}
		} catch (IOException io) {
			return cleanThumbnailMap(thumbnailMap);
		}
		return thumbnailMap;
	}

	public Map<ThumbnailKind, File> cleanThumbnailMap(Map<ThumbnailKind, File> thumbnailMap) {
		for (Map.Entry<ThumbnailKind, File> entry : thumbnailMap.entrySet()) {
			thumbnailClean(entry.getValue());
		}
		thumbnailMap.clear();
		return thumbnailMap;
	}

	public void thumbnailClean(File file) {
		if (file != null) {
			file.delete();
		}
	}

	/**
	 * Generate the thumbnail of the FileResource in a BufferedImage
	 * 
	 * @return File
	 * @throws IOException
	 */
	protected File generateThumbnailImage() throws IOException {
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
			return ThumbnailConfigImpl.getThumbnailConfigFactory(resource.getAbsolutePath(), ThumbnailKind.MEDIUM);
		}
		return this.defaultThumbnail;
	}

	public void setDefaultThumbnail(ThumbnailConfig thumbnail) {
		this.defaultThumbnail = thumbnail;
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
	 * @return true or false
	 * @throws IOException
	 */
	public boolean generateThumbnail(ThumbnailConfig thumbnail, String thumbnailAbsolutePath) throws IOException {
		File thumbnailFile = new File(thumbnailAbsolutePath);
		try {
			File thumbnailImage = generateThumbnailImage(thumbnail);
			if (thumbnailImage == null) {
				return false;
			}
			thumbnailFile.createNewFile();
			FileUtils.copyFile(thumbnailImage, thumbnailFile);
			thumbnailClean(thumbnailImage);
			return true;
		} catch (IOException io) {
			thumbnailClean(thumbnailFile);
			return false;
		}
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
	 * Generates a thumbnail of the FileResource to the default absolute path and
	 * return this path
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
