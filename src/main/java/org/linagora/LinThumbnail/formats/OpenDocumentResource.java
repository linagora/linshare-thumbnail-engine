package org.linagora.LinThumbnail.formats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.linagora.LinThumbnail.FileResource;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents Open Document Format files (ODT, ODF, ODG, ODP)
 * 
 * @author sduprey
 */
public class OpenDocumentResource extends FileResource {
	public Logger logger = LoggerFactory.getLogger(OpenDocumentResource.class);

	public OpenDocumentResource(File resource, String extension) {
		this.resource = resource;
		this.extension = extension;
	}

	@SuppressWarnings("unchecked")
	public InputStream generateThumbnailInputStream() throws IOException {
		File thumbnail = File.createTempFile("thumbnailOD", this.resource.getName()+"_thmb.png");
		FileOutputStream outputStream = null;
		InputStream inputStream = null;
		Enumeration<ZipEntry> entries = null;
		ZipFile zipFile = null;

		try {
			thumbnail.createNewFile();
			outputStream = new FileOutputStream(thumbnail);
			zipFile = new ZipFile(this.resource);

			entries = (Enumeration<ZipEntry>) zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (entry.getName()
						.equalsIgnoreCase("Thumbnails/thumbnail.png")) {

					inputStream = zipFile.getInputStream(entry);
					byte buffer[] = new byte[512 * 1024];
					int nbLecture;
					while ((nbLecture = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, nbLecture);
					}
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (inputStream != null)
				inputStream.close();
			if (outputStream != null)
				outputStream.close();
		}
		InputStream stream = new FileInputStream(thumbnail);
		thumbnail.delete();
		return stream;
	}

	@Override
	public BufferedImage generateThumbnailImage() throws IOException {
		return ImageUtils.getBufferedImageFromInputStream(generateThumbnailInputStream());
	}

}
