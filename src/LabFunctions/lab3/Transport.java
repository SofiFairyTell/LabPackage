package LabFunctions.lab3;

import java.util.Scanner;

/**
 * Абстрактный класс, описывающий транспортное средство.
 */
public abstract class Transport {
    protected int loadCapacity; //грузоподъемность
    private int wheels; //к-во колес
    protected String brand;//бренд
    protected String number;//номер
    protected double speed;//скорость

    /**
     * Считывает параметры с консоли.
     *
     * @param scanner объект Scanner для считывания параметров
     */
    public abstract void init(Scanner scanner);

    /**
     * Возвращает грузоподъемность.
     *
     * @return грузоподъемность
     */
    public int getLoadCapacity() {
        return loadCapacity;
    }
    // геттеры и сеттеры для свойств

    public int getWheels() {
        return wheels;
    }

    public double getSpeed() {
        return speed;
    }

    /**
     * Возвращает строковое представление объекта.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Transport{" +
                "brand='" + brand + '\'' +
                ", number='" + number + '\'' +
                ", speed=" + speed +
                '}';
    }

}

