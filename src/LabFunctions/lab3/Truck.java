package LabFunctions.lab3;

import java.util.Scanner;

/**
 * Класс, описывающий грузовик.
 */
public class Truck extends Transport {
    private String model;
    private int payload;

    @Override
    public void init(Scanner scanner) {
        System.out.print("Enter the model of truck: ");
        model = scanner.nextLine();
        System.out.print("Enter the payload: ");
        payload = scanner.nextInt();
        System.out.print("Enter the load capacity: ");
        loadCapacity = scanner.nextInt();
    }

    @Override
    public String toString() {
        return "Truck - Model: " + model + ", Payload: " + payload + ", Load capacity: " + loadCapacity;
    }
}
