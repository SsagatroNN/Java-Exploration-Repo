
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathParser {
    public static void main(String[] args) 
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String fname = args[0];
        Document doc = null;
        try 
        {
            DocumentBuilder db = factory.newDocumentBuilder();
            doc = db.parse(fname);
        } 
        catch (Exception e) 
        {
            System.out.println("Error parsing file: " + e);
            throw new RuntimeException(e);
        }
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = args[1];
        
        try 
        {
            if (expression.equals("//book"))
            {
                getDefaultEval(doc, xPath, expression);
                return;
            }

            NodeList eval = (NodeList) xPath.evaluate(expression, doc, XPathConstants.NODESET);

            if (eval == null)
            {
                // System.out.println("Expression returned null");
                throw new RuntimeException("Expression returned null");
            }

            for (int i = 0; i < eval.getLength(); i++)
            {
                Node node = eval.item(i);                
                node.normalize();
                System.out.println(node.getNodeName() + ": " + node.getTextContent());
            }
            

        } catch (Exception e) 
        {
            System.out.println("Error evaluating expression: " + e);
            throw new RuntimeException(e);
        }
    }

    private static void getDefaultEval(Document doc, XPath xPath, String expression) throws XPathExpressionException {
        NodeList eval = (NodeList) xPath.evaluate(expression, doc, XPathConstants.NODESET);
        
        if (eval == null)
        {
            // System.out.println("Expression returned null");
            throw new RuntimeException("Expression returned null");
        }

        for (int i = 0; i < eval.getLength(); i++)
        {
            Node node = eval.item(i);
            
            node.normalize();
            System.out.println(node.getNodeName() + ": " + node.getAttributes().getNamedItem("id"));
            
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                for (int j = 0; j < node.getChildNodes().getLength(); j++)
                {
                    Node child = node.getChildNodes().item(j);
                    if (child.getNodeType() == Node.ELEMENT_NODE)
                    {
                        System.out.println(child.getNodeName() + ": " + child.getTextContent());
                    }
                }
            }
        }
    }
}
