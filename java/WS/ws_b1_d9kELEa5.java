package RMI;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class ws_b1_d9kELEa5 {
    static final String WSDL = "http://36.50.135.242:2221/DataService?wsdl";
    static final String STUDENT = "B23DCCN609", QCODE = "d9kELEa5";
    static final String SOAP = "http://schemas.xmlsoap.org/soap/envelope/";

    static Document parse(InputStream stream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        return factory.newDocumentBuilder().parse(stream);
    }

    static Document download(String url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);
        try (InputStream in = conn.getInputStream()) {
            return parse(in);
        } finally {
            conn.disconnect();
        }
    }

    static String[] parameterNames(List<Document> docs, String operation, String... fallback) {
        for (Document doc : docs) {
            NodeList types = doc.getElementsByTagNameNS("*", "complexType");
            for (int i = 0; i < types.getLength(); i++) {
                Element type = (Element) types.item(i);
                if (!operation.equals(type.getAttribute("name"))) continue;
                NodeList elements = type.getElementsByTagNameNS("*", "element");
                List<String> names = new ArrayList<>();
                for (int j = 0; j < elements.getLength(); j++) {
                    String name = ((Element) elements.item(j)).getAttribute("name");
                    if (!name.isEmpty()) names.add(name);
                }
                if (names.size() == fallback.length) return names.toArray(new String[0]);
            }
            NodeList elements = doc.getElementsByTagNameNS("*", "element");
            for (int i = 0; i < elements.getLength(); i++) {
                Element wrapper = (Element) elements.item(i);
                if (!operation.equals(wrapper.getAttribute("name"))) continue;
                NodeList children = wrapper.getElementsByTagNameNS("*", "element");
                List<String> names = new ArrayList<>();
                for (int j = 0; j < children.getLength(); j++) {
                    String name = ((Element) children.item(j)).getAttribute("name");
                    if (!name.isEmpty()) names.add(name);
                }
                if (names.size() == fallback.length) return names.toArray(new String[0]);
            }
        }
        return fallback;
    }

    static String soapAction(Document wsdl, String operation) {
        NodeList ops = wsdl.getElementsByTagNameNS("http://schemas.xmlsoap.org/wsdl/", "operation");
        for (int i = 0; i < ops.getLength(); i++) {
            Element op = (Element) ops.item(i);
            if (!operation.equals(op.getAttribute("name"))) continue;
            NodeList bindings = op.getElementsByTagNameNS("http://schemas.xmlsoap.org/wsdl/soap/", "operation");
            if (bindings.getLength() > 0) {
                return ((Element) bindings.item(0)).getAttribute("soapAction");
            }
        }
        return "";
    }

    static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }

    static Document call(String endpoint, String namespace, String action,
                         String operation, String[] names, String... values) throws Exception {
        StringBuilder body = new StringBuilder();
        body.append("<m:").append(operation).append(" xmlns:m=\"").append(escape(namespace)).append("\">");
        for (int i = 0; i < values.length; i++) {
            body.append("<").append(names[i]).append(">").append(escape(values[i]))
                    .append("</").append(names[i]).append(">");
        }
        body.append("</m:").append(operation).append(">");
        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<s:Envelope xmlns:s=\"" + SOAP + "\"><s:Body>"
                + body + "</s:Body></s:Envelope>";

        HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);