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

import java.io.File;

/**
 * Help class for file manipulation
 * 
 * @author sduprey
 */
public class FileUtils {

	/**
	 * Get the extension of the filename
	 * 
	 * @param file
	 * @return String
	 */
	public static String getExtension(File file) {
		String inputFilePath = file.getAbsolutePath();
		return inputFilePath.substring(inputFilePath.lastIndexOf('.') + 1, inputFilePath.length());
	}

	/**
	 * Get the name of the file without extension
	 * 
	 * @param file
	 * @return String
	 */
	public static String getName(File file) {
		String inputFileName = file.getName();
		return inputFileName.substring(0, inputFileName.lastIndexOf('.'));
	}

	/**
	 * Get the path to the file without filename
	 * 
	 * @param file
	 * @return String
	 */
	public static String getPath(File file) {
		String inputFilePath = file.getAbsolutePath();
		int fs1 = inputFilePath.lastIndexOf('/');
		int fs2 = inputFilePath.lastIndexOf('\\');
		int fs3 = inputFilePath.lastIndexOf(File.pathSeparator);
		int goodOne = Math.max(Math.max(fs1, fs2), fs3);
		if (goodOne < 1)
			return "";
		return inputFilePath.substring(0, goodOne);
	}

}
