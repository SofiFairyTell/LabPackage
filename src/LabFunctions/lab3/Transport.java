package LabFunctions.lab3;

import java.util.Scanner;

/**
 * Абстрактный класс, описывающий транспортное средство.
 */
public abstract class Transport {
    protected int loadCapacity;
    private int wheels;

    /**
     * Считывает параметры с консоли.
     * @param scanner объект Scanner для считывания параметров
     */
    public abstract void init(Scanner scanner);

    /**
     * Возвращает грузоподъемность.
     * @return грузоподъемность
     */
    public int getLoadCapacity() {
        return loadCapacity;
    }
    // геттеры и сеттеры для свойств

    public int getWheels() {
        return wheels;
    }
    /**
     * Возвращает строковое представление объекта.
     * @return строковое представление объекта
     */
    @Override
    public abstract String toString();
}

