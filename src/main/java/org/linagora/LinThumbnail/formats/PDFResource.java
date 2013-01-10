package org.linagora.LinThumbnail.formats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.linagora.LinThumbnail.FileResource;
import org.linagora.LinThumbnail.utils.Constants;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents PDF files
 * 
 * @author sduprey
 */
public class PDFResource extends FileResource {
	public Logger logger = LoggerFactory.getLogger(PDFResource.class);

	public PDFResource(File resource) {
		this.resource = resource;
	}

	@SuppressWarnings("unchecked")
	public BufferedImage generateThumbnailImage() throws IOException {
		PDDocument document = null;
		int imageType = BufferedImage.TYPE_INT_RGB;
		BufferedImage image = null;

		try {
			document = PDDocument.load(this.resource);
			List<PDPage> pages = document.getDocumentCatalog().getAllPages();
			PDPage page = pages.get(0);
			page.setMediaBox(PDPage.PAGE_SIZE_A4);

			image = page.convertToImage(imageType, Constants.RESOLUTION);
			int maxDim = Math.max(image.getHeight(), image.getWidth());
			if (maxDim > Constants.MAXDIM) {
				image = ImageUtils.scale(image, Constants.MAXDIM);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (document != null) {
				document.close();
			}
		}
		return image;
	}

	public InputStream generateThumbnailInputStream() throws IOException {
		return ImageUtils.getInputStreamFromImage(generateThumbnailImage(), "png");
	}

}