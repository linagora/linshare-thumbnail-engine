/*
 * LinShare is an open source filesharing software, part of the LinPKI software
 * suite, developed by Linagora.
 * 
 * Copyright (C) 2010-2017 LINAGORA
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version, provided you comply with the Additional Terms applicable for
 * LinShare software by Linagora pursuant to Section 7 of the GNU Affero General
 * Public License, subsections (b), (c), and (e), pursuant to which you must
 * notably (i) retain the display of the “LinShare™” trademark/logo at the top
 * of the interface window, the display of the “You are using the Open Source
 * and free version of LinShare™, powered by Linagora © 2009–2017. Contribute to
 * Linshare R&D by subscribing to an Enterprise offer!” infobox and in the
 * e-mails sent with the Program, (ii) retain all hypertext links between
 * LinShare and linshare.org, between linagora.com and Linagora, and (iii)
 * refrain from infringing Linagora intellectual property rights over its
 * trademarks and commercial brands. Other Additional Terms apply, see
 * <http://www.linagora.com/licenses/> for more details.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License and
 * its applicable Additional Terms for LinShare along with this program. If not,
 * see <http://www.gnu.org/licenses/> for the GNU Affero General Public License
 * version 3 and <http://www.linagora.com/licenses/> for the Additional Terms
 * applicable to LinShare software.
 */

package org.linagora.LinThumbnail.formats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jodconverter.office.OfficeException;
import org.linagora.LinThumbnail.FileResource;
import org.linagora.LinThumbnail.utils.Constants;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.linagora.LinThumbnail.utils.ThumbnailConfig;
import org.linagora.LinThumbnail.utils.ThumbnailKind;
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

	@Override
	public File generateThumbnailFile(ThumbnailConfig thumbnail) throws IOException {
		if (ThumbnailKind.PDF.equals(thumbnail.getKind())) {
			return null;
		}
		PDDocument document = null;
		BufferedImage image = null;
		File imageThumbnail = null;
		try {
			imageThumbnail = File.createTempFile("file", "thumbnail");;
			imageThumbnail.deleteOnExit();
			document = PDDocument.load(this.resource);
			image = new PDFRenderer(document).renderImageWithDPI(0, thumbnail.getResolution(), ImageType.RGB);
			image = thumbnail.getPostProcessing().apply(image);
			ImageIO.write(image, Constants.THMB_DEFAULT_FORMAT, imageThumbnail);
		} catch (IOException e) {
			logger.debug("Failed to generate thumbnail file", e);
			thumbnailClean(imageThumbnail);
			throw e;
		} finally {
			if (document != null) {
				document.close();
			}
		}
		return imageThumbnail;
	}

	@Override
	public InputStream generateThumbnailInputStream() throws IOException, OfficeException {
		return ImageUtils.getInputStreamFromImage(generateThumbnailImage(), "png");
	}

	@Override
	public Boolean needToGeneratePDFPreview() {
		return false;
	}

}
