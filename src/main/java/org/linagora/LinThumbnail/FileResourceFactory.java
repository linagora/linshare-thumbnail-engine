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
import java.util.HashSet;
import java.util.Set;

import org.linagora.LinThumbnail.formats.DocumentResource;
import org.linagora.LinThumbnail.formats.ImageResource;
import org.linagora.LinThumbnail.formats.PDFResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory of FileResource given a File object.
 * 
 * @author sduprey
 */
public class FileResourceFactory {

	public Logger logger = LoggerFactory.getLogger(FileResourceFactory.class);

	private static FileResourceFactory instance = null;

	private static Set<String> imageMimeType = new HashSet<String>();

	private static Set<String> pdfMimeType = new HashSet<String>();

	private static Set<String> documentMimeType = new HashSet<String>();

	public final static FileResourceFactory getInstance() {
		if (instance == null) {
			instance = new FileResourceFactory();
			initTypeMime();
		}
		return instance;
	}

	private static void initTypeMime() {
		// Image mime type
		imageMimeType.add("image/png");
		imageMimeType.add("image/jpeg");
		imageMimeType.add("image/gif");
		imageMimeType.add("image/x-ms-bmp");
		// PDF mime type
		pdfMimeType.add("application/pdf");
		// Document mime type
		documentMimeType.add("application/vnd.oasis.opendocument.text");
		documentMimeType.add("application/vnd.oasis.opendocument.presentation");
		documentMimeType.add("application/vnd.oasis.opendocument.spreadsheet");
		documentMimeType.add("application/vnd.oasis.opendocument.graphics");
		documentMimeType.add("application/msword");
		documentMimeType.add("application/vnd.ms-excel");
		documentMimeType.add("application/vnd.ms-powerpoint");
		documentMimeType.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		documentMimeType.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		documentMimeType.add("application/vnd.openxmlformats-officedocument.presentationml.presentation");
		documentMimeType.add("application/rtf");
		documentMimeType.add("application/java");
		documentMimeType.add("text/x-java-source");
		documentMimeType.add("application/xml");
		documentMimeType.add("application/xhtml+xml");
		documentMimeType.add("text/x-jsp");
		documentMimeType.add("text/html");
		documentMimeType.add("text/x-php");
		documentMimeType.add("text/x-c++src");
		documentMimeType.add("text/plain");
		documentMimeType.add("text/x-chdr");
		documentMimeType.add("text/css");
		documentMimeType.add("application/javascript");
		documentMimeType.add("text/x-less");
		documentMimeType.add("application/xslt+xml");
		documentMimeType.add("text/x-ruby");
		documentMimeType.add("text/x-script.phyton");
		documentMimeType.add("text/x-perl");
		documentMimeType.add("text/x-log");
	}

	public FileResource getFileResource(File file, String mimeType) {
		logger.info("the current mime type is : " + mimeType);
		if (imageMimeType.contains(mimeType)) {
			return new ImageResource(file);
		}
		if (documentMimeType.contains(mimeType)) {
			return new DocumentResource(file);
		}
		if (pdfMimeType.contains(mimeType)) {
			return new PDFResource(file);
		}
		return null;
	}

	public Boolean isSupportedMimeType(String mimeType) {
		if (imageMimeType.contains(mimeType)
				|| pdfMimeType.contains(mimeType)
				|| documentMimeType.contains(mimeType)) {
			return true;
		} else {
			return false;
		}
	}
}
