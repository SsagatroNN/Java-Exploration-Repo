import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DomParser 
{
    public static void main(String[] args) 
    {
        String fname = args[0];
        Document doc = null;
        try 
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true); // To avoid XXE Injection, 
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(fname);
            doc = db.parse(file);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            throw new RuntimeException(e);
        }

        if (doc == null)
            throw new RuntimeException("Document is null");

        Element root = doc.getDocumentElement();
        doc.getDocumentElement().normalize();
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++)
        {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) children.item(i);
                System.out.println(element.getTagName() + " : " + element.getAttributes().getNamedItem("id"));
            }
            for (int j = 0; j < children.item(i).getChildNodes().getLength(); j++)
            {
                Node node = root.getChildNodes().item(i).getChildNodes().item(j);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    System.out.println(element.getTagName() + " : " + element.getTextContent());
                }
            }
        }
    
    }
}
