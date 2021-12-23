package net.tracex;

import net.tracex.appenders.ConsoleAppender;
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

    public static void readConfiguration(String fileName) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, ClassNotFoundException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
        Document document = db.parse(new File(fileName));
        document.getDocumentElement().normalize();

        NodeList loggers = document.getDocumentElement().getChildNodes();
        //for each "logger"
        for (int i = 0; i < loggers.getLength(); i++) {
            //for each "configuration"
            TraceX traceX = null;
            List<Node> loggerList = getChildNodes(loggers.item(i), "configuration");
            for (Node nodeTemp : loggerList) {
                traceX = parseTraceX(nodeTemp);
            }
            //if traceX != null, then configure each appenders
            if (traceX != null) {
                List<Node> appenderList = getChildNodes(loggers.item(i), "appenders");
                for (Node n : appenderList) {
                    parseAppenders(traceX, n);
                }
            }
        }
    }

    /*
     *      PRIVATE FUNCTION
     */

    private static TraceX parseTraceX(Node node) throws ClassNotFoundException {
        String className = getNodeString(node, "class");
        String appName = getNodeString(node, "name");
        String appVersion = getNodeString(node, "version");
        TraceX traceX = TraceX.getInstance(className);
        if (appName != null) traceX.setAppName(appName);
        if (appVersion != null) traceX.setAppVersion(appVersion);
        return traceX;
    }

    private static void parseAppenders(TraceX traceX, Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeTemp = nodeList.item(i);
            if (nodeTemp.getNodeType() == Node.ELEMENT_NODE) {
                String appenderName = nodeTemp.getNodeName();
                if (appenderName.equals("console")) {
                    traceX.registerListener(new ConsoleAppender(traceX, nodeTemp));
                } else if (appenderName.equals("mongodb")) {

                } else if (appenderName.equals("file")) {

                }
            }
        }
    }

    /*
     *      XML UTILS
     */

    private static List<Node> getChildNodes(Node node, String name) {
        List<Node> result = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeChild = nodeList.item(i);
            if (nodeChild.getNodeName().equals(name)) result.add(nodeChild);
        }
        return result;
    }

    private static String getNodeString(Node node, String name) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeChild = nodeList.item(i);
            if (nodeChild.getNodeType() == Node.ELEMENT_NODE) {
                if (nodeChild.getNodeName().equals(name)) return nodeChild.getTextContent();
            }
        }
        return null;
    }

}
