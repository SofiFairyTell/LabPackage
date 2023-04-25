package LabFunctions.lab5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
