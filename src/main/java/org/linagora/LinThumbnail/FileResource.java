package org.linagora.LinThumbnail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.linagora.LinThumbnail.utils.Constants;

/**
 * FileResource is the class containing the file object from which the thumbnail
 * is generated. <br/>
 * Supported formats of file are:
 * <ul>
 * <li>OpenDocumentFormat files: ODP, ODT, ODS, ODG</li>
 * <li>Images files: JPEG, PNG, GIF, BMP</li>
 * <li>PDF files</li>
 * <li>Text files: TXT, XML, ...</li>
 * <li>MSFDocumentFormat files: DOC, XLS, PPT</li>
 * </ul>
 * 
 * @author sduprey
 */
public abstract class FileResource {
	protected File resource;

	/**
	 * Generate the thumbnail of the FileResource in a BufferedImage
	 * 
	 * @return
	 * @throws IOException
	 */
	public abstract BufferedImage generateThumbnailImage() throws IOException;

	/**
	 * Generate the thumbnail of the FileResource in an InputStream
	 * 
	 * @return
	 * @throws IOException
	 */
	public abstract InputStream generateThumbnailInputStream() throws IOException;

	/**
	 * Generates a thumbnail of the FileResource to the given absolute path
	 * 
	 * @param thumbnailAbsolutePath
	 * @return GENERATE_OK or GENERATE_KO
	 * @throws IOException
	 */
	public boolean generateThumbnail(String thumbnailAbsolutePath) throws IOException {
		File thumbnailFile = new File(thumbnailAbsolutePath);
		BufferedImage thumbnailImage = generateThumbnailImage();

		if (thumbnailImage == null) {
			return Constants.GENERATE_KO;
		}
		thumbnailFile.createNewFile();
		ImageIO.write(thumbnailImage, Constants.THMB_DEFAULT_FORMAT, thumbnailFile);
		return Constants.GENERATE_OK;
	}

	/**
	 * Generates a thumbnail of the FileResource to the default absolute path
	 * 
	 * @return GENERATE_OK or GENERATE_KO
	 * @throws IOException
	 */
	public boolean generateThumbnail() throws IOException {
		return generateThumbnail(getThumbnailDefaultName());
	}

	/**
	 * Generates a thumbnail of the FileResource to the default absolute path
	 * and return this path
	 * 
	 * @return the path to the thumbnail
	 * @throws IOException
	 */
	public String generateThumbnailToDefaultPath() throws IOException {
		String path = getThumbnailDefaultName();
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

	/**
	 * Generates the thumbnail default location and name
	 * 
	 * @return the thumbnail default absolute path
	 */
	private String getThumbnailDefaultName() {
		return this.resource.getAbsolutePath() + Constants.THMB_DEFAULT_NAME;
	}
}
