/*
 * LinShare is an open source filesharing software, part of the LinPKI software
 * suite, developed by Linagora.
 * 
 * Copyright (C) 2017 LINAGORA
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

package org.linagora.LinThumbnail.utils.impl;

import org.linagora.LinThumbnail.postprocessing.Filter;
import org.linagora.LinThumbnail.postprocessing.Resize;
import org.linagora.LinThumbnail.utils.LargeThumbnail;
import org.linagora.LinThumbnail.utils.MediumThumbnail;
import org.linagora.LinThumbnail.utils.PdfThumbnail;
import org.linagora.LinThumbnail.utils.SmallThumbnail;
import org.linagora.LinThumbnail.utils.ThumbnailConfig;
import org.linagora.LinThumbnail.utils.ThumbnailKind;

public class ThumbnailConfigImpl implements ThumbnailConfig {

	protected String defaultImageName;

	protected int maxImageSize;

	protected int resolution;

	protected String absolutePath;

	protected Filter postProcessing;

	protected static ThumbnailKind kind;

	public ThumbnailConfigImpl() {
	}

	public ThumbnailConfigImpl(String defaultImageName, int maxImageSize, int resolution, String absolutePath) {
		this.defaultImageName = defaultImageName;
		this.maxImageSize = maxImageSize;
		this.resolution = resolution;
		this.absolutePath = absolutePath;
		this.postProcessing = new Resize();
	}

	public static ThumbnailConfigImpl getThumbnailConfigFactory(String absolutePath, ThumbnailKind thumbnailEnum) {
		ThumbnailConfigImpl thumbnailconfig = null;
		kind = thumbnailEnum;
		if (ThumbnailKind.SMALL.equals(thumbnailEnum)) {
			thumbnailconfig = new SmallThumbnail(absolutePath);
		} else if (ThumbnailKind.MEDIUM.equals(thumbnailEnum)) {
			thumbnailconfig = new MediumThumbnail(absolutePath);
		} else if (ThumbnailKind.LARGE.equals(thumbnailEnum)) {
			thumbnailconfig = new LargeThumbnail(absolutePath);
		} else if (ThumbnailKind.PDF.equals(thumbnailEnum)) {
			thumbnailconfig = new PdfThumbnail(absolutePath);
		}
		return thumbnailconfig;
	}

	public String getDefaultName() {
		return this.absolutePath + defaultImageName;
	}

	@Override
	public String getDefaultImageName() {
		return defaultImageName;
	}

	public void setDefaultImageName(String defaultImageName) {
		this.defaultImageName = defaultImageName;
	}

	@Override
	public int getMaxImageSize() {
		return maxImageSize;
	}

	@Override
	public void setMaxImageSize(int maxImageSize) {
		this.maxImageSize = maxImageSize;
	}

	@Override
	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	@Override
	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	@Override
	public Filter getPostProcessing() {
		return postProcessing;
	}

	public void setPostProcessing(Filter postProcessing) {
		this.postProcessing = postProcessing;
	}

	@Override
	public ThumbnailKind getKind() {
		return kind;
	}

}
