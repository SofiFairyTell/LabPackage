package LabFunctions.lab3;

import java.util.Scanner;

/**
 * Класс, описывающий мотоцикл.
 */
public class Motorcycle extends Transport {
    private String model;
//    private String brand;
//    private String number;
//    private int speed;
    private boolean hasSidecar;
    private int loadCapacity;

    public Motorcycle(String brand, String number, int speed, boolean hasSidecar) {
        this.brand = brand;
        this.number = number;
        this.speed = speed;
        this.hasSidecar = hasSidecar;
        if (hasSidecar) {
            this.loadCapacity = super.getLoadCapacity();
        } else {
            this.loadCapacity = 0;
        }
    }

    public String getBrand() {
        return brand;
    }

    public String getNumber() {
        return number;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    @Override
    public int getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public void init(Scanner scanner) {
        // считываем параметры мотоцикла с консоли
        System.out.print("Введите марку мотоцикла: ");
        this.brand = scanner.next();
        System.out.print("Введите номер мотоцикла: ");
        this.number = scanner.next();
        System.out.print("Введите скорость мотоцикла: ");
        this.speed = scanner.nextInt();
        System.out.print("Наличие коляски (true/false): ");
        this.hasSidecar = scanner.nextBoolean();
        if (hasSidecar) {
            this.loadCapacity = super.getLoadCapacity();
        } else {
            this.loadCapacity = 0;
        }
    }

    @Override
    public String toString() {
        String sidecar = hasSidecar ? "с коляской" : "без коляски";
        return String.format("%s, номер %s, скорость %d км/ч, %s, грузоподъемность %d кг",
                brand, number, speed, sidecar, loadCapacity);
    }
}
