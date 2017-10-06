LinThumbnail 2.0
================

LinThumbnail is a free software. This makes it possible to have a thumbnail (little image for preview) for a wide document formats.

Dependences
-----------
 * Apache PDFBox - Java PDF Library
   http://pdfbox.apache.org/
   Apache License v2.0 - http://www.apache.org/licenses/LICENSE-2.0

 * Artofsolving JODConverter - Java OpenDocument Converter
   http://www.artofsolving.com/opensource/jodconverter
   GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html

 * QOS.ch SL4J - Simple Logging Facade for Java
   http://www.slf4j.org/
   MIT license - http://www.slf4j.org/license.html

Requirements :
-------------
JODConverter automates conversions between different document formats using OpenOffice.org or LibreOffice. You should have LibreOffice or openoffice installed.

The minimum version of LibreOffice is : 4.2.8.

Linthumbnail require also java 7 .

Supported formats
-----------------
Linthumbnail can generate a thumbnails for a lot of document formats :

 * openDocument (ODP, ODT, ODS, ODG)

 * MicrosoftOffice (DOC, PPT, XLS) and (DOCX, PPTX, XLSX)

 * And more (PDF, HTML, TXT, XML, XSLT, ... )

Importants
---------

The service libreOffice/OpenOffice will need to be enabled to convert differents documents,
JodConverter uses inter-process communication TCP socket, on port 2002.

if the application is crashed or disabled you will need to verify if the process is free, otherwise you need to kill him first.

    Search for process like :
        - ps aux | grep port=2002
    And kill them using :
        - sudo kill -9 (ID)
