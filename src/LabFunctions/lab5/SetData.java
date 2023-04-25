package LabFunctions.lab5;

import java.util.Scanner;

public class SetData {

    public String setValue()
    {
        while (true)
        {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            if(str.length()>0)
            {
                return str;
            }
            else
            {
                System.out.println("Пустые значения не допустимы");
            }
        }
    }

    public String[] setData()
    {
        String[] strings = new String[6];
        System.out.println("Введите данные о городе:");
        System.out.println("Введите Страну:");
        strings[0] = setValue();
        System.out.println("Введите Город:");
        strings[1] = setValue();
        System.out.println("Введите ФИО мэра города");
        strings[2] = setValue();
        System.out.println("Введите количество населения");
        strings[3] = setValue();
        System.out.println("Введите площадь");
        strings[4] = setValue();
        System.out.println("Введите числовой показатель уровня жизни (от 1 до 5)");
        strings[5] = setValue();
        return strings;
    }



    public City setNewCity(int Code)
    {
        var strings = this.setData();
        return new City(Code + 1, strings[0], strings[1],strings[2],Integer.parseInt(strings[3]),
                    Double.parseDouble(strings[4]), Integer.parseInt(strings[5]));
    }

    public City setNewCity(int size, String country,String name,String mayor, int population, double area,int qualityOfLife)
    {
        return new City(size + 1, country,name,mayor, population, area,qualityOfLife);
    }

    public void changeCity(String filePath)
    {
        var sax = new ParseSAX();
        var cities = sax.readerSaxDocument(filePath);
        System.out.println("Введите id города:");
        var searchId = Common.getID();
        var strings = this.setData();
        boolean flag = false;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getCode() == searchId) {
                cities.set(i, new City(searchId,
                        strings[0], strings[1], strings[2],
                        Integer.parseInt(strings[3]), Double.parseDouble(strings[4]), Integer.parseInt(strings[5])));
                flag = true;
                break;
            }
        }
        if (flag) {
            var dom = new ParseDOM(filePath);
            dom.setDomNodes(cities);
        } else {
            System.out.println("Такого города нет!");
        }
    }

    public void deleteCity(String filePath)
    {
        var sax = new ParseSAX();
        var cities = sax.readerSaxDocument(filePath);
        System.out.println("Введите код города:");
        var searchId = Common.getID();
        boolean flag = false;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getCode() == searchId) {
                cities.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            var dom = new ParseDOM(filePath);
            dom.setDomNodes(cities);
        } else {
            System.out.println("Такого города нет!");
        }
    }
    public void deleteEvent(String filePath, int searchId)
    {
        var sax = new ParseSAX();
        var events = sax.readerSaxDocument(filePath);

        boolean flag = false;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getCode() == searchId) {
                events.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            var dom = new ParseDOM(filePath);
            dom.setDomNodes(events);
        } else {
            System.out.println("Такого города нет!");
        }
    }

}