package CompareScreens;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BatchFileHandler {

	private static final String DEFAULT_FILE_NAME = "Batch4Tests.xml";
	private static final String UNIQUE = "unique";
	private static final String IDENTIFIERS = "identifiers";
	private String path = null;

	public BatchFileHandler() throws SAXException, IOException, ParserConfigurationException {
		this("");
	}

	public BatchFileHandler(String filePath) throws SAXException, IOException, ParserConfigurationException {
		this.path = filePath + DEFAULT_FILE_NAME;
	}

	private void createNewFile() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(this.path, "UTF-8");

		String initialFile = 
				"<tests>\n" + 
				"	<identifiers>\n" + 
				"	</identifiers>\n" + 
				"	<batch-id>" + generateRandomBatchId() + "</batch-id>\n" + 
				"</tests>";

		writer.println(initialFile);
		writer.close();
	}

	private String generateRandomBatchId() {
		return UUID.randomUUID().toString();
	}

	private org.w3c.dom.Document getDoc() throws ParserConfigurationException, SAXException, IOException{
		File fXmlFile = new File(this.path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		return dBuilder.parse(fXmlFile);
	}
	
	private Node getIdentifiersNode(org.w3c.dom.Document doc) throws ParserConfigurationException, SAXException, IOException
	{
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(IDENTIFIERS).item(0);
	}
	
	public void addIdentifier(String identifier) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		File fXmlFile = new File(this.path);
		Document doc;
		Node identifiersNode;
		
		if (!fXmlFile.exists()) {
			createNewFile();
		} else {
			doc = getDoc();
			identifiersNode = getIdentifiersNode(doc);

			Set<String> identifiersSet = getIdentifiersSet(identifiersNode);

			if (identifiersSet.contains(identifier)) {
				createNewFile();
			}
		}
		
		doc = getDoc();
		identifiersNode = getIdentifiersNode(doc);
		
		Element newElement = doc.createElement(UNIQUE);
		newElement.setTextContent(identifier);

		identifiersNode.appendChild(newElement);

		save(doc);
	}

	private void save(Document doc)
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(this.path);
		transformer.transform(source, result);
	}

	private Set<String> getIdentifiersSet(Node identifiersNode) throws ParserConfigurationException, SAXException, IOException{
		
		NodeList identifiersNodes = identifiersNode.getChildNodes();
		
		Set<String> uniqueIdentifiers = new HashSet<String>();
		
		for (int i=0; i < identifiersNodes.getLength(); i++){
				String nodeName = identifiersNodes.item(i).getNodeName();
				
				if (nodeName.toLowerCase().equals(UNIQUE)){
					uniqueIdentifiers.add(identifiersNodes.item(i).getTextContent());
				}
		}
		
		return uniqueIdentifiers;
	}
	
	public String getBatchId() throws ParserConfigurationException, SAXException, IOException {

		org.w3c.dom.Document doc = getDoc();
		doc.getDocumentElement().normalize();
		Node batchIdNode = doc.getElementsByTagName("batch-id").item(0);

		return batchIdNode.getTextContent();
	}

}
