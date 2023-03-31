package LabFunctions.lab3;

import java.util.Scanner;

/**
 * Класс, описывающий мотоцикл.
 */
public class Motorcycle extends Transport {
    private String model;
    private int maxSpeed;

    @Override
    public void init(Scanner scanner) {
        System.out.print("Enter the model of motorcycle: ");
        model = scanner.nextLine();
        System.out.print("Enter the maximum speed: ");
        maxSpeed = scanner.nextInt();
        System.out.print("Enter the load capacity: ");
        loadCapacity = scanner.nextInt();
    }

    @Override
    public String toString() {
        return "Motorcycle - Model: " + model + ", Max speed: " + maxSpeed + ", Load capacity: " + loadCapacity;
    }
}
