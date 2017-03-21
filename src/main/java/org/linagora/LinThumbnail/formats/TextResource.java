package org.linagora.LinThumbnail.formats;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.linagora.LinThumbnail.FileResource;
import org.linagora.LinThumbnail.utils.Constants;
import org.linagora.LinThumbnail.utils.FontUtils;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents all kind of text files
 * 
 * @author sduprey
 */
public class TextResource extends FileResource {
	public Logger logger = LoggerFactory.getLogger(TextResource.class);

	public TextResource(File resource) {
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.linagora.LinThumbnail.FileResource#generateThumbnailImage()
	 * 
	 * The first task code of this method is inspired from the source code of
	 * PDFBox (Apache, Incubator), from the method createPDFFromText from the
	 * class TextToPDF.
	 */
	public BufferedImage generateThumbnailImage() throws IOException {
		PDDocument doc = null;
		PDPage page = null;
		PDType0Font font = null;
		BufferedReader data = null;
		PDPageContentStream contentStream = null;
		FileReader text = null;
		String nextLine = null;
		BufferedImage image = null;

		int fontSize = 17;
		int margin = 15;

		try {
			/**
			 * First task: convert text to pdf page
			 */
			text = new FileReader(this.resource);
			doc = new PDDocument();
			font = PDType0Font.load(doc, FontUtils.getFontFile());
			data = new BufferedReader(text);
			page = new PDPage();
			page.setMediaBox(PDRectangle.A4);
			doc.addPage(page);
			contentStream = new PDPageContentStream(doc, page);

			float height = font.getBoundingBox().getHeight() / 1000;
			height = height * fontSize * 1.05f;
			float y = page.getMediaBox().getHeight() - margin + height;

			contentStream.setFont(font, fontSize);
			contentStream.beginText();
			contentStream.newLineAtOffset(margin, y);

			while ((nextLine = data.readLine()) != null) {
				if (y < margin) // if the page is full
					break;

				if (contentStream == null) {
					throw new IOException("Error:Expected non-null content stream.");
				}

				contentStream.newLineAtOffset(0, -height);
				y -= height;
				contentStream.showText(nextLine.toString());
			}

			if (contentStream != null) {
				contentStream.endText();
				contentStream.close();
			}

			/**
			 * Second task: convert pdf page to image
			 */
			image = new PDFRenderer(doc).renderImageWithDPI(0, Constants.RESOLUTION, ImageType.RGB);
			int maxDim = Math.max(image.getHeight(), image.getWidth());
			if (maxDim > Constants.MAXDIM) {
				image = ImageUtils.scale(image, Constants.MAXDIM);
			}
		} catch (IOException io) {
			throw io;
		} finally {
			if (data != null) {
				data.close();
			}
			if (doc != null) {
				doc.close();
			}
		}
		return image;
	}

	public InputStream generateThumbnailInputStream() throws IOException {
		return ImageUtils.getInputStreamFromImage(generateThumbnailImage(), "png");
	}

}
