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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command line convenience to generate thumbnail from file
 * 
 * @author sduprey
 */
public class GenerateThumbnail {
	private static final String sofficeInfo = "If you need to convert Open document or Microsoft format documents, "
			+ "don't forget install OpenOffice or LibreOffice";

	public static Logger logger = LoggerFactory.getLogger(GenerateThumbnail.class);

	public static void main(String[] args) {
		GenerateThumbnail app = new GenerateThumbnail();
		FileResourceFactory factory = FileResourceFactory.getInstance();
		String thumbnailFile = null;
		boolean success = false;

		try {
			if (args.length < 1) {
				app.usage();
				return;
			} else {
				for (int i = 0; i < args.length - 1; i++) {
					if (args[i].equals("-o")) {
						i++;
						thumbnailFile = args[i];
					} else {
						app.usage();
						return;
					}
				}
				File file = new File(args[args.length - 1]);
				FileResource fileResource = factory.getFileResource(file, "text/plain");
				if (thumbnailFile == null) {
					success = fileResource.generateThumbnail();
				} else {
					success = fileResource.generateThumbnail(thumbnailFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!success) {
				logger.info("Generation of thumbnail failed");
				logger.info(sofficeInfo);
			} else {
				logger.info("Generation of thumbnail succeeded");
			}
		}
	}

	private void usage() {
		logger.info("usage: " + this.getClass().getName() + " [options] <file>");
		logger.info("-o <output-path/output-file> The location and name of the thumbnail");
		logger.info(sofficeInfo);
	}

}
