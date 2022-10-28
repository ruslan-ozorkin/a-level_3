package com.ozorkin.hw1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1:");
        String name ="Ruslan";
        String surname="Ozorkin";
        System.out.println(name + " " + surname);
        System.out.println("--------------");
        System.out.println("Task 2:");
        int y = 5;
        for (int i = 0; i <= 10; i++) {
            System.out.println("Step - " + i + " value: " + y);
            y=y+2;
        }
        System.out.println("--------------");

        System.out.println("Task 3:");
        for (int i = 0; i <= 10; i++) {
            if (i==3) {
                continue;
            }
            if (i==6) {
                break;
            }
            System.out.println("Step " + i);

        }
    }
}