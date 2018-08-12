package com.rwrs.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.transform.ResultTransformer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.StringWriter;
import java.io.WriteAbortedException;

public class XMLUtils {

	// create a xml document and return it
	public static Document createXML(long userId) {
		try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         
	         // root element ODM
	         Element rootElement = doc.createElement("ODM");
	         doc.appendChild(rootElement);
	         
	         // creating attribute for ODM
	         Attr odmXmlnsAttr = doc.createAttribute("xmlns");
	         Attr odmVersionAttr = doc.createAttribute("ODMVersion");
	         Attr odmFileTypeAttr = doc.createAttribute("FileType");
	         Attr odmFileOIDAttr = doc.createAttribute("FileOID");
	         Attr odmCreationTimeAttr = doc.createAttribute("CreationDateTime");
	         
	         // setting attribute to ODM
	         odmXmlnsAttr.setValue("http://www.cdisc.org/ns/odm/v1.3");
	         odmVersionAttr.setValue("1.3");
	         odmFileTypeAttr.setValue("Transactional");
	         odmFileOIDAttr.setValue("Example-1");
	         odmCreationTimeAttr.setValue("2008-01-01T00:00:00");
	         
	         rootElement.setAttributeNode(odmXmlnsAttr);
	         rootElement.setAttributeNode(odmVersionAttr);
	         rootElement.setAttributeNode(odmFileTypeAttr);
	         rootElement.setAttributeNode(odmFileOIDAttr);
	         rootElement.setAttributeNode(odmCreationTimeAttr);
	         

	         // ClinicalData element
	         Element clinicalData = doc.createElement("ClinicalData");
	         rootElement.appendChild(clinicalData);

	         // creating attribute for ClinicalData
	         Attr studyOID = doc.createAttribute("StudyOID");
	         Attr metaDataVersionOID = doc.createAttribute("MetaDataVersionOID");
	         
	         // setting attribute to ClinicalData
	         studyOID.setValue("Mediflex (DEV)");
	         metaDataVersionOID.setValue("1");
	         
	         clinicalData.setAttributeNode(studyOID);
	         clinicalData.setAttributeNode(metaDataVersionOID);
	         
	         
	         // SubjectData element
	         Element subjectData = doc.createElement("SubjectData");
	         clinicalData.appendChild(subjectData);
	         
	         // creating attribute for ClinicalData
	         Attr subjectKey = doc.createAttribute("SubjectKey");
	         Attr transactionType = doc.createAttribute("TransactionType");
	         
	         // setting attribute to ClinicalData
	         subjectKey.setValue(String.valueOf(userId));
	         transactionType.setValue("Insert");
	         
	         subjectData.setAttributeNode(subjectKey);
	         subjectData.setAttributeNode(transactionType);

	         
	         // SiteRef element
	         Element siteRef = doc.createElement("SiteRef");
	         subjectData.appendChild(siteRef);
	         
	         // creating attribute for ClinicalData
	         Attr locationOID = doc.createAttribute("LocationOID");
	         
	         // setting attribute to ClinicalData
	         locationOID.setValue("EvCR0001");
	         
	         siteRef.setAttributeNode(locationOID);
	         
	         return doc;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return null;
	}
	
	
	// takes in a Document class and write it to a file. Return true if the operation is successful
	public static boolean writeDocumentToFile(String file, Document doc) {
		try {
			// write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("/Users/wilson/rwrsData/test.xml"));
	         transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	         
	         return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	// takes in a Document doc and returns a String converted from doc
	public static String xmlToString(Document doc) {
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
	
	
	public static void main(String[] args) {
		Document doc = createXML(101005);
		System.out.println("The stringified xml is: \n" + xmlToString(doc));
	}
	
}
