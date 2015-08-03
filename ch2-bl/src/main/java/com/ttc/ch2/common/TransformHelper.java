package com.ttc.ch2.common;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class TransformHelper {
	private StreamSource xstlSource = null;
	private StreamSource xmlSource = null;
	private Writer outWriter = null;

	public TransformHelper(Writer w, Reader xml, Reader xslt) {
		outWriter = w;
		xstlSource = new StreamSource(xml);
		xmlSource = new StreamSource(xslt);
	}

	public StringBuffer transform(StringBuffer xml, File xslt) throws TransformerException {
		xstlSource = new StreamSource(xslt);
		xmlSource = new StreamSource(new StringReader(xml.toString()));
		outWriter = new StringWriter();
		return ((StringWriter) transform()).getBuffer();
	}

	protected Writer transform() throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer(xstlSource);
		Result rs = new StreamResult(outWriter);
		transformer.transform(xmlSource, rs);
		return outWriter;
	}
	
	


public static String documentToString(Document doc) {
    try {
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
    } catch (Exception ex) {
        throw new RuntimeException("Error converting to String", ex);
    }
}


	public static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(
					xmlStr)));
			return doc;
		} catch (Exception e) {
			throw new RuntimeException("Error converting to Document", e);
		}
	}



	//getery settery
	public Writer getOutWriter() {
		return outWriter;
	}

	public void setOutWriter(Writer outWriter) {
		this.outWriter = outWriter;
	}

}
