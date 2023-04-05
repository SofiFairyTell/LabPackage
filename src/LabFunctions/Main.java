package LabFunctions;

import LabFunctions.lab1.ThreeNumbers;
import LabFunctions.lab3.Motorcycle;
import LabFunctions.lab3.PassengerCar;
import LabFunctions.lab3.Transport;
import LabFunctions.lab3.Truck;
import LabFunctions.lab4.NameValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.print("Лабораторные функции загружены!");
        //ThreeNumbers.MenuCheckNumbers();
        //readTransport();
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