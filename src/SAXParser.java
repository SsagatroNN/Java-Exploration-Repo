import javax.xml.parsers.SAXParserFactory;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.input.sax.SAXHandler;

/*
 * The great thing about SAX is the use of a handler which inherits from DefaultHandler and can be used to handle XML data. 
 */

public class SAXParser 
{
    public static void main(String[] args) 
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        String fname = args[0];
        SAXHandler handler = new SAXHandler();
        try {
            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            parser.parse(fname, handler);    
        } catch (Exception e) {
            System.out.println("Error: " + e);
            throw new RuntimeException(e);
        }

        System.out.println("Parsed XML successfully");
        Document doc = handler.getDocument();

        doc.getRootElement();

        for (org.jdom2.Element i : doc.getRootElement().getChildren())
        {
            i.getAttributes().forEach((Attribute j) -> {
                System.out.println(j.getName() + ": " + j.getValue());
            });

            i.getChildren().forEach((org.jdom2.Element j) -> {
                System.out.println(j.getName() + ": " + j.getValue());
            });
            System.out.println();
        }
    }    
}
