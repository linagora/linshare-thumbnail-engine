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

import org.linagora.LinThumbnail.formats.DocumentResource;
import org.linagora.LinThumbnail.formats.ImageResource;
import org.linagora.LinThumbnail.formats.PDFResource;
import org.linagora.LinThumbnail.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory of FileResource given a File object.
 * 
 * @author sduprey
 */
public class FileResourceFactory {

	private static FileResourceFactory instance = null;

	public Logger logger = LoggerFactory.getLogger(FileResourceFactory.class);

	public final static FileResourceFactory getInstance() {
		if (instance == null) {
			instance = new FileResourceFactory();
		}
		return instance;
	}

	public FileResource getFileResource(File file) {
		String inputFormat = FileUtils.getExtension(file);
		logger.info("The current format is: " + inputFormat);
		if (inputFormat.equalsIgnoreCase("jpeg")
				|| inputFormat.equalsIgnoreCase("jpg")
				|| inputFormat.equalsIgnoreCase("png")
				|| inputFormat.equalsIgnoreCase("gif")
				|| inputFormat.equalsIgnoreCase("bmp")) {
			return new ImageResource(file);
		}
		if (inputFormat.equalsIgnoreCase("odt")
				|| inputFormat.equalsIgnoreCase("odp")
				|| inputFormat.equalsIgnoreCase("odg")
				|| inputFormat.equalsIgnoreCase("ods")
				|| inputFormat.equalsIgnoreCase("doc")
				|| inputFormat.equalsIgnoreCase("xls")
				|| inputFormat.equalsIgnoreCase("ppt")
				|| inputFormat.equalsIgnoreCase("docx")
				|| inputFormat.equalsIgnoreCase("xlsx")
				|| inputFormat.equalsIgnoreCase("pptx")
				|| inputFormat.equalsIgnoreCase("rtf")
				|| inputFormat.equalsIgnoreCase("java")
				|| inputFormat.equalsIgnoreCase("jsp")
				|| inputFormat.equalsIgnoreCase("c")
				|| inputFormat.equalsIgnoreCase("xml")
				|| inputFormat.equalsIgnoreCase("xsl")
				|| inputFormat.equalsIgnoreCase("htm")
				|| inputFormat.equalsIgnoreCase("html")
				|| inputFormat.equalsIgnoreCase("xhtml")
				|| inputFormat.equalsIgnoreCase("txt")
				|| inputFormat.equalsIgnoreCase("php")
				|| inputFormat.equalsIgnoreCase("cpp")
				|| inputFormat.equalsIgnoreCase("log")
				|| inputFormat.equalsIgnoreCase("h")
				|| inputFormat.equalsIgnoreCase("css")
				|| inputFormat.equalsIgnoreCase("js")
				|| inputFormat.equalsIgnoreCase("less")
				|| inputFormat.equalsIgnoreCase("twig")
				|| inputFormat.equalsIgnoreCase("yml")
				|| inputFormat.equalsIgnoreCase("xslt")
				|| inputFormat.equalsIgnoreCase("rb")
				|| inputFormat.equalsIgnoreCase("py")
				|| inputFormat.equalsIgnoreCase("pl")
				|| inputFormat.equalsIgnoreCase("tml")) {
			return new DocumentResource(file);
		}
		if (inputFormat.equalsIgnoreCase("pdf")) {
			return new PDFResource(file);
		}
		return null;
	}

}
