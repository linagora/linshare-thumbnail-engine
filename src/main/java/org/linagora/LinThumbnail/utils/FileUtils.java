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
	 * @return
	 */
	public static String getExtension(File file) {
		String inputFilePath = file.getAbsolutePath();
		return inputFilePath.substring(inputFilePath.lastIndexOf('.') + 1, inputFilePath.length());
	}

	/**
	 * Get the name of the file without extension
	 * 
	 * @param file
	 * @return
	 */
	public static String getName(File file) {
		String inputFileName = file.getName();
		return inputFileName.substring(0, inputFileName.lastIndexOf('.'));
	}

	/**
	 * Get the path to the file without filename
	 * 
	 * @param file
	 * @return
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
