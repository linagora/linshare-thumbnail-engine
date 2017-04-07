package org.linagora.LinThumbnail.formats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.linagora.LinThumbnail.FileResource;
import org.linagora.LinThumbnail.utils.Constants;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentResource extends FileResource {
	public Logger logger = LoggerFactory.getLogger(DocumentResource.class);

	public DocumentResource(File resource) {
		this.resource = resource;
	}

	/**
	 * 1) Convert to PDF 2) Convert to PNG
	 */
	@Override
	public BufferedImage generateThumbnailImage() throws IOException {
		OfficeManager officeManager;
		BufferedImage image = null;
		PDDocument document = null;
		String outputFormat = "pdf";
		int maxDim = 0;

		officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
		try {
			officeManager.start();

			// First convert to PDF
			File inputFile = this.resource;
			File outputFile = File.createTempFile("thumbnail", this.resource.getName() + "conv." + outputFormat);
			OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
			converter.convert(inputFile, outputFile);

			// Second convert to PNG
			document = PDDocument.load(new FileInputStream(outputFile));
			image = new PDFRenderer(document).renderImageWithDPI(0, Constants.RESOLUTION, ImageType.RGB);
			maxDim = Math.max(image.getHeight(), image.getWidth());
			if (maxDim > Constants.MAXDIM) {
				image = ImageUtils.scale(image, Constants.MAXDIM);
			}
			document.close();
		} finally {
			officeManager.stop();
		}
		return image;
	}

	@Override
	public InputStream generateThumbnailInputStream() throws IOException {
		return ImageUtils.getInputStreamFromImage(generateThumbnailImage(), "png");
	}

}
