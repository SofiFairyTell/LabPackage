package LabFunctions.lab3;

import java.util.Scanner;

/**
 * Класс Грузовой автомобиль, наследуется от Транспортного средства.
 * Содержит информацию о марке, номере, скорости, грузоподъемности и наличии прицепа.
 * Если есть прицеп, грузоподъемность увеличивается в два раза.
 */
public class Truck extends Transport {
    private String model;
    private int payload;
    private boolean hasTrailer;

    public Truck(String brand, String number, int speed, int loadCapacity, boolean hasTrailer) {
        this.brand = brand;
        this.number = number;
        this.speed = speed;
        this.hasTrailer = hasTrailer;
        if (hasTrailer) {
            this.loadCapacity = loadCapacity * 2;
        } else {
            this.loadCapacity = loadCapacity;
        }
    }
    @Override
    public void init(Scanner scanner) {
        System.out.print("Enter the model of truck: ");
        model = scanner.nextLine();
        System.out.print("Enter the payload: ");
        payload = scanner.nextInt();
        System.out.print("Enter the load capacity: ");
        loadCapacity = scanner.nextInt();
    }

    public boolean hasTrailer() {
        return hasTrailer;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return "Truck: " + brand + ", " + number + ", " + speed + " km/h, load capacity: " + loadCapacity + " kg, has trailer: " + hasTrailer;
    }

}
