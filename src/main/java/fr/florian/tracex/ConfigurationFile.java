package fr.florian.tracex;

import fr.florian.tracex.appenders.ConsoleAppender;
import fr.florian.tracex.exceptions.UnknownPackageException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

class ConfigurationFile {

    public static void readConfiguration(String fileName) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, UnknownPackageException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
        Document document = db.parse(new File(fileName));
        document.getDocumentElement().normalize();

        NodeList loggers = getLoggers(document);
        List<Node> loggerList = getLoggerList(loggers);
        readLogger(loggerList);
    }

    /*
     *      PRIVATE FUNCTION
     */

    private static NodeList getLoggers(Document document) {
        return document.getDocumentElement().getChildNodes();
    }

    public static List<Node> getLoggerList(NodeList nodeList) {
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals("logger")) result.add(node);
        }
        return result;
    }

    private static void readLogger(List<Node> nodes) throws UnknownPackageException {
        for (Node node : nodes) {
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("logger")) {
                    TraceX traceX = parseLogger(node);
                    if (traceX != null) parseAppenders(traceX, node);
                }
            }
        }
    }

    private static TraceX parseLogger(Node node) throws UnknownPackageException {
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nodeChild = nodes.item(i);
            if (nodeChild.getNodeType() == Node.ELEMENT_NODE) {
                if (nodeChild.getNodeName().equals("package")) {
                    String packageName = nodeChild.getTextContent();
                    return new TraceX(packageName);
                }
            }
        }
        return null;
    }

    private static void parseAppenders(TraceX traceX, Node node) {
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nodeChild = nodes.item(i);
            if (nodeChild.getNodeName().equals("appenders")) {
                redirectAppenders(traceX, nodeChild);
            }
        }
    }

    private static void redirectAppenders(TraceX traceX, Node node) {
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nodeChild = nodes.item(i);
            if (nodeChild.getNodeName().equals("console")) {
                new ConsoleAppender(traceX, nodeChild.getChildNodes());
            } else if (nodeChild.getNodeName().equals("mongodb")) {

            } else if (nodeChild.getNodeName().equals("file")) {

            } else if (nodeChild.getNodeName().equals("sql")) {

            }
        }
    }

}
