package LabFunctions.lab3;

import java.util.Scanner;

/**
 * Класс, описывающий легковой автомобиль.
 */
public class PassengerCar extends Transport {
    private String model;
    private int seats;
    private String brand;
    private String number;
    private double speed;
    private int loadCapacity;

    public PassengerCar(int wheels, String brand, String number, double speed, int loadCapacity) {
        super.getWheels();
        this.brand = brand;
        this.number = number;
        this.speed = speed;
        this.loadCapacity = loadCapacity;
    }

    // геттеры и сеттеры для свойств

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void init(Scanner scanner) {
        System.out.print("Введите модель автомобиля: ");
        model = scanner.nextLine();
        System.out.print("Введите количество мест: ");
        seats = scanner.nextInt();
        System.out.print("Введите грузоподъемость: ");
        loadCapacity = scanner.nextInt();
    }

    @Override
    public String toString() {
        return "Автомобиль - Модель: " + model + ", Мест: " + seats + ", Грузоподъемность: " + loadCapacity;
    }
}
