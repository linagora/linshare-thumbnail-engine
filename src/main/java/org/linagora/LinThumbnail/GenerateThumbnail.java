package org.linagora.LinThumbnail;

import java.io.File;

/**
 * Command line convenience to generate thumbnail from file
 * 
 * @author sduprey
 */
public class GenerateThumbnail {
	private static final String sofficeCommand = "soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
	private static final String sofficeInfo = "If you need to convert Microsoft format documents, don't forget to run OpenOffice.org listening on port 8100:";
	
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
				System.err.println("*** Generation of thumbnail failed");
				System.err.println(sofficeInfo);
				System.err.println("    "+sofficeCommand);
			} else {
				System.out.println("*** Generation of thumbnail succeeded");
			}
		}
	}

	private void usage() {
		System.err.println("usage: " + this.getClass().getName() + " [options] <file>");
		System.err.println("    -o <output-path/output-file>         The location and name of the thumbnail");
		System.err.println(sofficeInfo);
		System.err.println("    "+sofficeCommand);
	}

}
