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
