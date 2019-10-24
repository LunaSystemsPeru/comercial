package clases_varios;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class v21codigoQR {

    public static String get(String xml_file_name, String qr_file_name, String qr_value) {
        String hash = "";
        //	String raya="------------------------------------------------";
        try {

            File fXmlFile = new File(xml_file_name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //	  System.out.println(raya);
            // DigestValue
            NodeList nList_IssueDate = doc.getElementsByTagName("ds:DigestValue");
            Node nNode_IssueDate = nList_IssueDate.item(0);
            System.out.println("" + nNode_IssueDate.getNodeName());
            System.out.println("Generando Codigo QR.");
            hash = nNode_IssueDate.getTextContent();

            String _contenido_hash = nNode_IssueDate.getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

}
