package LabFunctions.lab5;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import javax.xml.stream.XMLOutputFactory;

import javax.xml.stream.XMLStreamWriter;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

public class ParseSAX {
    protected String content;
    protected boolean flag = false;
    protected ArrayList<City> citieslist = new ArrayList<City>();
    protected String[] strMas = new String[6];
    public City cityList;

    public ArrayList<City> getCitiesList() {
        return citieslist;
    }

    private final DefaultHandler handler = new DefaultHandler() {
        String tag = "";
        String id = "";

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            tag = qName;
            if (tag.equalsIgnoreCase("City"))
                id = attributes.getValue("Code");
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (tag.equalsIgnoreCase("country")) {
                strMas[0] = new String(ch, start, length);
            } else if (tag.equalsIgnoreCase("name")) {
                strMas[1] = new String(ch, start, length);
            } else if (tag.equalsIgnoreCase("mayor")) {
                strMas[2] = new String(ch, start, length);
            } else if (tag.equalsIgnoreCase("population")) {
                strMas[3] = new String(ch, start, length);
            } else if (tag.equalsIgnoreCase("area")) {
                strMas[4] = new String(ch, start, length);
            } else if (tag.equalsIgnoreCase("qualityOfLife")) {
                strMas[5] = new String(ch, start, length);
            }
            if (strMas[0] != null && strMas[1] != null && strMas[2] != null && strMas[3] != null && strMas[4] != null
                    && strMas[5] != null && !id.equals("")) {
                City cityLists = new City(Integer.parseInt(id), strMas[0], strMas[1], strMas[2],
                        Integer.parseInt(strMas[3]), Double.parseDouble(strMas[4]), Integer.parseInt(strMas[5]));
                Arrays.fill(strMas, null);
                citieslist.add(cityLists);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            tag = "";
        }
    };

    private final DefaultHandler handlerSearch = new DefaultHandler() {
        String tag = "";
        String id = "";

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            tag = qName;
            if (tag.equalsIgnoreCase("City"))
                id = attributes.getValue("Code");
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (id.equalsIgnoreCase(content)) {
                if (tag.equalsIgnoreCase("country") && strMas[0] == null) {
                    strMas[0] = new String(ch, start, length);
                } else if (tag.equalsIgnoreCase("name") && strMas[1] == null) {
                    strMas[1] = new String(ch, start, length);
                } else if (tag.equalsIgnoreCase("mayor") && strMas[2] == null) {
                    strMas[2] = new String(ch, start, length);
                } else if (tag.equalsIgnoreCase("population") && strMas[3] == null) {
                    strMas[3] = new String(ch, start, length);
                } else if (tag.equalsIgnoreCase("area") && strMas[4] == null) {
                    strMas[4] = new String(ch, start, length);
                } else if (tag.equalsIgnoreCase("qualityOfLife") && strMas[5] == null) {
                    strMas[5] = new String(ch, start, length);
                }
                if (strMas[0] != null && strMas[1] != null && strMas[2] != null && strMas[3] != null && strMas[4] != null && strMas[5] != null && !id.equals("")) {
                    cityList = new City(Integer.parseInt(id), strMas[0], strMas[1], strMas[2], Integer.parseInt(strMas[3]),
                            Double.parseDouble(strMas[4]), Integer.parseInt(strMas[5]));
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (tag.equalsIgnoreCase(""))
                flag = false;
            if (tag.equalsIgnoreCase("country") ||
                    tag.equalsIgnoreCase("name") ||
                    tag.equalsIgnoreCase("mayor") ||
                    tag.equalsIgnoreCase("population") ||
                    tag.equalsIgnoreCase("area") ||
                    tag.equalsIgnoreCase("qualityOfLife"))
                tag = "";
        }
    };

    public City searchSaxDocument(String filePath, String content) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            this.content = content;
            saxParser.parse(new File(filePath), handlerSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public ArrayList<City> readerSaxDocument(String filePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this.handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.citieslist;
    }

    //В качестве аргумента принимает путь файла, в который нужно записать
    public void writeSaxDocument(String filePath) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(filePath));
            writer.writeStartDocument();
            writer.writeCharacters("\n");
            writer.writeStartElement("root");
            writer.writeCharacters("\n");
            writer.writeStartElement("font");
            writer.writeAttribute("code", "1");
            writer.writeCharacters("TimesNewRoman\n");
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}