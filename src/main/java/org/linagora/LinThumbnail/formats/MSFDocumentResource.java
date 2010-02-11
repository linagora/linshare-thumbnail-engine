package org.linagora.LinThumbnail.formats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

import org.linagora.LinThumbnail.FileResource;
import org.linagora.LinThumbnail.utils.FileUtils;
import org.linagora.LinThumbnail.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormatRegistry;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * Represents Microsoft Document Format files (before ooxml)
 * 
 * @author sduprey
 */
public class MSFDocumentResource extends FileResource {
	public Logger logger = LoggerFactory.getLogger(MSFDocumentResource.class);

	public MSFDocumentResource(File resource) {
		this.resource = resource;
	}
	
	public InputStream generateThumbnailInputStream() throws IOException {
		int port = SocketOpenOfficeConnection.DEFAULT_PORT;
		String inputFormat = FileUtils.getExtension(this.resource);
        String outputFormat = "";
        InputStream stream = null;
        
        if (inputFormat.equalsIgnoreCase("doc")) {
        	outputFormat = "odt";
        }
        else if (inputFormat.equalsIgnoreCase("xls")) {
        	outputFormat = "ods";        	
        }
        else if (inputFormat.equalsIgnoreCase("ppt")) {
        	outputFormat = "odp";        	
        }
        else return null;
        
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(port);
		
        DocumentFormatRegistry registry= new DefaultDocumentFormatRegistry();

        try {
            connection.connect();
        } catch (ConnectException officeNotRunning) {
            logger.error("ERROR: connection failed. Please make sure OpenOffice.org is running and listening on port " + port + ".");
            return null;
        }
        try {
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection, registry);
            File inputFile = this.resource;
            File outputFile = File.createTempFile("thumbnail", this.resource.getName()+"conv."+outputFormat);
            converter.convert(inputFile, outputFile);
            
            FileResource fr = new OpenDocumentResource(outputFile);
            stream = fr.generateThumbnailInputStream();
            outputFile.deleteOnExit();
        } finally {
            connection.disconnect();
        }
        return stream;
	}

	public BufferedImage generateThumbnailImage() throws IOException {
		return ImageUtils.getBufferedImageFromInputStream(generateThumbnailInputStream());
	}
}
