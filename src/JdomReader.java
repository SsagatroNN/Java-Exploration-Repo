import java.io.File;

import javax.xml.XMLConstants;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class JdomReader 
{
    public static void main(String[] args)
    {
        Document doc = null;

        try 
        {
            SAXBuilder builder = new SAXBuilder();

            builder.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            builder.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            doc = builder.build(new File(args[0]));
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
            throw new RuntimeException(e);
        }
        
        System.out.println(doc.toString());
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
