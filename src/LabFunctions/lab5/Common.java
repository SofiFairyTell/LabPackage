package LabFunctions.lab5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Common {
    public static int getID() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Введенно некоректное значение!");
            System.out.print("Введите значение повторно: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static Date DateParser(String date_str) {
        Date date_parsed = null;
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date_parsed = dateFormat.parse(date_str);
        } catch (ParseException e) {
            System.out.println("Не удалось извлечь дату " + dateFormat);
        }
        return date_parsed;
    }

    public static String DateToString(Date date) {
        String date_parsed = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        date_parsed = dateFormat.format(date);
        return date_parsed;
    }

    /**
     * Создание XML файла по умолчанию
     * @param filePath
     * @return Сообщение об успешном создании файла
     */
    public static String CreateDefaultXML(String filePath) {
        // создаем объект класса File для указанного пути
        File file = new File(filePath);

        try {
            // создаем новый файл
            if (file.createNewFile()) {
                System.out.println("Файл создан: " + file.getAbsolutePath());

                // создаем объект класса FileWriter для записи в файл
                FileWriter writer = new FileWriter(file);

                // записываем в файл нужную строку
                String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<CityLists>\n" +
                        "    <City id=\"31\">\n" +
                        "        <country>Россия</country>\n" +
                        "        <name>Белгород</name>\n" +
                        "        <mayor>Гладков</mayor>\n" +
                        "        <population>339978</population>\n" +
                        "        <area>153</area>\n" +
                        "        <qualityOfLife>5</qualityOfLife>\n" +
                        "    </City>\n" +
                        "</CityLists>";

                writer.write(xmlString); // записываем строку в файл
                writer.flush(); // очищаем буфер и закрываем FileWriter
                writer.close();

                System.out.println("Данные записаны в файл: " + file.getAbsolutePath());
            } else {
                System.out.println("Файл уже существует: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
        return filePath;
    }

    /**
     * Вывод команд меню
     *
     */
    public static int choiceMenu() {
        System.out.println("Выберите с чем работать: \n" +
                "1. XML\n" +
                "2. БД\n" +
                "3. Конвертировать данные из XML в БД\n" +
                "4. Конвертировать данные из БД в XML\n" +
                "5. Завершить работу.");
        int type = 0;
        while (true) {
            type = Common.getID();
            if (type >= 1 && type <= 5) {
                return type;
            }
            System.out.println("Можно ввести только 1,2,3,4,5");
        }
    }

    /**
     * Выполнение работ с базой данных и XML
     */
    public static void start() {
        SetData set = new SetData();
        var prop = new ParseProperties();
        var catalog = prop.readCatalogRoot();
        File dir = new File(catalog);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = catalog + "\\file.xml";
        //создаим файл по умолчанию
        filePath = Common.CreateDefaultXML(filePath);
        int type = choiceMenu();
        if (type == 3) {
            var sax = new ParseSAX();
            var parsing = new Parse(sax.readerSaxDocument(filePath));
            parsing.parseXMLtoDB();
            start();
        }
        if (type == 4) {
            var sax = new ParseSAX();
            var dom = new ParseDOM(filePath);
            ArrayList<City> citiesSAX = sax.getCitiesList();
            var parsing = new Parse(citiesSAX,dom);
            parsing.parseDBtoXML();
            start();
        }
        if (type == 5) return;
        while (true) {
            System.out.println("Выберите действие: \n" +
                    "1. Вывести всё содержимое\n" +
                    "2. Найти содержимое по параметру\n" +
                    "3. Добавить новую запись\n" +
                    "4: Изменить запись\n" +
                    "5: Удалить запись\n" +
                    "6: Возврат в верхнее меню\n" +
                    "0: Завершить работу\n");
            var choice = Common.getID();
            switch (choice) {
                case 1: {
                    if (type == 1) {
                        var sax = new ParseSAX();
                        var citiesList = sax.readerSaxDocument(filePath);
                        if (citiesList.size() > 0) {
                            for (City cityList : citiesList) {
                                System.out.println(cityList.toString());
                            }
                        }
                    } else if (type == 2) {
                        var mySqlObj = new ParseSQL();
                        var result = mySqlObj.workDataBase(choice);
                        try {
                            while (result.next()) {
                                City cityList = new City(
                                        result.getInt("id"),
                                        result.getString("country"),
                                        result.getString("name"),
                                        result.getString("mayor"),
                                        result.getInt("population"),
                                        result.getDouble("area"),
                                        result.getInt("qualityOfLife")
                                );

                                System.out.println(cityList.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case 2: {
                    if (type == 1) {
                        var sax = new ParseSAX();
                        String content = "";
                        System.out.print("Выберите содержимое поиска (id):\n");
                        Scanner scanner = new Scanner(System.in);
                        content = scanner.nextLine();
                        var event = sax.searchSaxDocument(filePath, content);
                        System.out.println(event != null ? event.toString() : "Такого города нет!");
                    } else
                    if (type == 2) {
                        var mySqlObj = new ParseSQL();
                        var result = mySqlObj.workDataBase(choice);
                        try {
                            while (result.next()) {
                                City cityList = new City(
                                        result.getInt("id"),
                                        result.getString("country"),
                                        result.getString("name"),
                                        result.getString("mayor"),
                                        result.getInt("population"),
                                        result.getDouble("area"),
                                        result.getInt("qualityOfLife")
                                );
                                System.out.println(cityList.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case 3: {
                    if (type == 1) {
                        var sax = new ParseSAX();
                        var citiesList = sax.readerSaxDocument(filePath);

                        var newCity = set.setNewCity(citiesList.size());
                        citiesList.add(newCity);
                        var dom = new ParseDOM(filePath);
                        dom.setDomNodes(citiesList);
                    } else if (type == 2) {
                        var mySqlObj = new ParseSQL();
                        mySqlObj.workDataBase(choice);
                    }
                }
                break;
                case 4: {
                    if (type == 1) {
                        set.changeCity(filePath);
                    } else if (type == 2) {
                        var mySqlObj = new ParseSQL();
                        mySqlObj.workDataBase(choice);
                    }
                }
                break;
                case 5: {
                    if (type == 1) {
                        set.deleteCity(filePath);
                    } else if (type == 2) {
                        var mySqlObj = new ParseSQL();
                        mySqlObj.workDataBase(choice);
                    }
                }
                break;
                case 6: {
                    start();
                }
                break;
                case 0: {
                    return;
                }
            }
        }
    }
}
