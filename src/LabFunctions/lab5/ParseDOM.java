package LabFunctions.lab5;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class ParseDOM
{
    private final String filePath;
    public ParseDOM(String filepath)
    {
        this.filePath = filepath;
    }

    public void getDOMNodes() {
        File xmlFile = new File(this.filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            // теперь XML полностью загружен в память
            //вернули корневой элемент документа и нормализовали
            document.getDocumentElement().normalize();
            System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());
            // получаем узлы с именем Language
            NodeList nodeList = document.getElementsByTagName("Language");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // если узел является элементом
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("\nЭлемент "+element.getNodeName());

                    // получаем список дочерних
                    NodeList childNodesList = element.getChildNodes();
                    for (int j = 0; j < childNodesList.getLength(); j++) {
                        Node childNode = childNodesList.item(j);
                        // если узел является элементом
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println(childNode.getNodeName()+": "+ childNode.getTextContent());
                        }
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void setDomNodes(ArrayList<City> cityList) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();// создаем пустой объект Document
            // создаем корневой элемент
            Element rootElement = doc.createElement("CityLists");
            doc.appendChild(rootElement);
            // добавляем корневой элемент в объект Document
            for(int i = 0; i < cityList.size(); i++){
                rootElement.appendChild(getCity(doc, cityList.get(i).getCode(),cityList.get(i).getCountry(), cityList.get(i).getName(),
                        cityList.get(i).getMayor(), cityList.get(i).getPopulation(),
                        cityList.get(i).getArea(), cityList.get(i).getQualityOfLife()));
            }
            doc.getDocumentElement().normalize();
//            //создаем объект TransformerFactory для преобразования документа в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // установка параметров форматирования для красивого вывода
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            //получение исходного кода готового документа
            DOMSource source = new DOMSource(doc);
            //создание объекта для записи - файл
            var prop = new ParseProperties();
            var catalog = prop.readCatalogRoot();
            StreamResult file = new StreamResult(new File(catalog + "\\file.xml"));

            //запись данных
            transformer.transform(source, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод для создания нового узла XML-файла
    private static Node getCity(Document doc, int Code, String Country, String Name, String Mayor, int population,
                                 double area, int QualityOfLife)
    {
        Element language = doc.createElement("City");
        language.setAttribute("id", Code+""); // устанавливаем атрибут id
        // создаем элементы name и age
        language.appendChild(getLanguageElements(doc, "country", Country));
        language.appendChild(getLanguageElements(doc, "name", Name));
        language.appendChild(getLanguageElements(doc, "mayor", Mayor));
        language.appendChild(getLanguageElements(doc, "population", String.valueOf(population)));
        language.appendChild(getLanguageElements(doc, "area", String.valueOf(area)));
        language.appendChild(getLanguageElements(doc, "qualityOfLife", String.valueOf(QualityOfLife)));
        return language;
    }

    // метод для создания одного узла
    private static Node getLanguageElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}