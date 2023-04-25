package LabFunctions;

import LabFunctions.lab1.ThreeNumbers;
import LabFunctions.lab3.Motorcycle;
import LabFunctions.lab3.PassengerCar;
import LabFunctions.lab3.Transport;
import LabFunctions.lab3.Truck;
import LabFunctions.lab4.NameValidator;
import LabFunctions.lab5.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.print("Лабораторные функции загружены!");
        //ThreeNumbers.MenuCheckNumbers();
        //readTransport();
        start();
    }
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




    /**
     * Считывает количество транспортных средств с консоли
     * Строка вида вап4явап, аа4а555
     * @return количество транспортных средств
     * */
    public static int readVehicleCount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество транспортных средств: ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.matches(".*?(\\d+).*")) {
                String numberString = line.replaceAll("[^\\d]+", "");
                int count = Integer.parseInt(numberString);
                if (count > 0) {
                    return count;
                }
            }
            System.out.println("Некорректное количество, попробуйте еще раз: ");
        }
        return 0;
    }

    /**
     * Создаем массив транспортных средств с количеством введенным в консоли
     *
     * @return Транспорт с наибольшей грузоподъемностью
     * */
    public static void readTransport() {
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Введите количество транспортных средств: ");
        int n = readVehicleCount();

        Transport[] transports = new Transport[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Введите тип транспорта (1 - легковой автомобиль, 2 - мотоцикл, 3 - грузовой автомобиль): ");
            int type = scanner.nextInt();
            scanner.nextLine();

            switch (type) {
                case 1:
                    transports[i] = new PassengerCar(4,"LADA_NIVA","A123BC888",120.0,3);

                    break;
                case 2:
                    transports[i] = new Motorcycle("Harley-Davidson", "A123BC777", 120.0, false);
                    break;
                case 3:
                    transports[i] = new Truck("КАМАЗ","A123BC888",120.0,3,true);
                    break;
                default:
                    System.out.println("Неверный тип транспорта!");
                    i--;
                    continue;
            }

            transports[i].init(scanner);
        }

        for (Transport transport : transports) {
            System.out.println(transport);
        }

        // Предполагается, что список транспортных средств хранится в списке transportList
        Transport maxLoadVehicle = null;
        int maxLoad = 0;

        for (Transport vehicle : transports) {
            if (vehicle.getLoadCapacity() > maxLoad) {
                maxLoadVehicle = vehicle;
                maxLoad = vehicle.getLoadCapacity();
            }
        }

        if (maxLoadVehicle != null) {
            System.out.println("Транспортное средство с максимальной грузоподъемностью:\n" + maxLoadVehicle.toString());
        } else {
            System.out.println("Нет транспортных средств в списке.");
        }
    }

}