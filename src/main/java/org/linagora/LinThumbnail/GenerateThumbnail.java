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
				FileResource fileResource = factory.getFileResource(file);
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
