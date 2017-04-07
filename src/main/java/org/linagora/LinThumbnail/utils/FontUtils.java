package org.linagora.LinThumbnail.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Singleton to get the font file (to avoid copy the InputStream each time we
 * create a TextResource thumbnail)
 * 
 * @author sduprey
 *
 */
public class FontUtils {
	private static FontUtils fontUtil = null;
	private File file = null;

	private FontUtils() {
		InputStream inputStream = FontUtils.class.getClassLoader().getResourceAsStream("font/VeraMono.ttf");
		try {
			file = File.createTempFile("LinThumbnail.FontUtils.font", "VeraMono.ttf");
			OutputStream out = new FileOutputStream(file);
			byte buf[] = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final synchronized static File getFontFile() {
		if (fontUtil == null) {
			fontUtil = new FontUtils();
		}
		return fontUtil.file;
	}

}
