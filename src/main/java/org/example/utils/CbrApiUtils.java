package org.example.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static org.example.utils.DateUtils.formatDateCbr;

public abstract class CbrApiUtils {

    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public static String buildUrl(Date date) {
        return "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + formatDateCbr(date);
    }

    public static Document getContent(Date date) {
        try {
            String urlString = buildUrl(date);
            URL url = new URL(urlString);
            try (InputStream is = url.openStream()) {
                DocumentBuilder builder = factory.newDocumentBuilder();
                return builder.parse(is);
            } catch (IOException e) {
                throw new RuntimeException("I/O exception occurred while accessing to CBR API");
            } catch (SAXException e) {
                throw new RuntimeException("Internal error occurred (unable to parse XML" +
                        " answer of CBR API)");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Internal error occurred (invalid CBR API URL)");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Internal error occurred (XML document builder" +
                    " not instantiated)");
        }
    }

}
