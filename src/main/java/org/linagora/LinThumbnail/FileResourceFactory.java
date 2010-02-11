package org.linagora.LinThumbnail;

import java.io.File;

import org.linagora.LinThumbnail.formats.ImageResource;
import org.linagora.LinThumbnail.formats.MSFDocumentResource;
import org.linagora.LinThumbnail.formats.OpenDocumentResource;
import org.linagora.LinThumbnail.formats.PDFResource;
import org.linagora.LinThumbnail.formats.TextResource;
import org.linagora.LinThumbnail.utils.FileUtils;

/**
 * Factory of FileResource given a File object.
 * 
 * @author sduprey
 */
public class FileResourceFactory {
    private static FileResourceFactory instance = null;
    private FileResourceFactory() {};
    
    public final static FileResourceFactory getInstance() {
        if (instance == null) 
            instance = new FileResourceFactory();
        return instance;
    }

	public FileResource getFileResource(File file) {
		String inputFormat = FileUtils.getExtension(file);

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
				|| inputFormat.equalsIgnoreCase("ods")) {
			return new OpenDocumentResource(file);
		}
		if (inputFormat.equalsIgnoreCase("pdf")) {
			return new PDFResource(file);
		}
		if (inputFormat.equalsIgnoreCase("doc")
				|| inputFormat.equalsIgnoreCase("xls")
				|| inputFormat.equalsIgnoreCase("ppt")) {
			return new MSFDocumentResource(file);
		}
		if (inputFormat.equalsIgnoreCase("xml")
				|| inputFormat.equalsIgnoreCase("xsl")
				|| inputFormat.equalsIgnoreCase("htm")
				|| inputFormat.equalsIgnoreCase("html")
				|| inputFormat.equalsIgnoreCase("xhtml")
				|| inputFormat.equalsIgnoreCase("tml")
				|| inputFormat.equalsIgnoreCase("php")
				|| inputFormat.equalsIgnoreCase("rb")
				|| inputFormat.equalsIgnoreCase("py")
				|| inputFormat.equalsIgnoreCase("pl")
				|| inputFormat.equalsIgnoreCase("java")
				|| inputFormat.equalsIgnoreCase("jsp")
				|| inputFormat.equalsIgnoreCase("c")
				|| inputFormat.equalsIgnoreCase("h")
				|| inputFormat.equalsIgnoreCase("cpp")
				|| inputFormat.equalsIgnoreCase("txt")) {
			return new TextResource(file);
		}

		return null;
	}

}
