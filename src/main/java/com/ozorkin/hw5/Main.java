package com.ozorkin.hw5;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        findMaxElement();
        System.out.println("----------------------------");
        swapEvenIndexElements();
        System.out.println("----------------------------");
        growingArray();
        System.out.println("----------------------------");
        biggerAverage();
        System.out.println("----------------------------");
        bubbleSort();
    }

    private static void bubbleSort() {
        final Random random = new Random();
        final int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(numbers));
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    isSorted = false;
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    System.out.println(Arrays.toString(numbers));
                }
            }
            System.out.println(Arrays.toString(numbers));
        }

    }

    public static void biggerAverage() {
        int[] array1 = new int[5];
        int[] array2 = new int[5];
        Random random = new Random();
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < array1.length; i++) {
            array1[i] = random.nextInt(0, 5);
            sum1 += array1[i];
        }
        System.out.println("First array: " + Arrays.toString(array1));

        for (int i = 0; i < array2.length; i++) {
            array2[i] = random.nextInt(0, 5);
            sum2 += array2[i];
        }
        System.out.println("Second array: " + Arrays.toString(array2));

        double avg1 = (double) sum1 / (array1.length + 1);
        double avg2 = (double) sum2 / (array2.length + 1);

        System.out.println("Average of first array: " + avg1);
        System.out.println("Average of second array: " + avg2);

        if (avg1 > avg2) {
            System.out.println("Average of first array " + avg1 + " is more than second " + avg2);
        } else if (avg1 < avg2) {
            System.out.println("Average of second array " + avg2 + " is more than first " + avg1);
        } else System.out.println("Average is equal");
    }

    public static String growingArray() {
        int[] ints = new int[4];
        Random random = new Random();
        String isGrowing = "This array is growing";
        String notGrowing = "This array is not growing";

        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(10, 99);
        }
        System.out.println(Arrays.toString(ints));
        for (int j = 0; j < ints.length - 1; j++) {
            if (ints[j + 1] < ints[j]) {
                System.out.println(notGrowing);
                return notGrowing;
            }
        }
        System.out.println(isGrowing);
        return isGrowing;
    }

    public static void swapEvenIndexElements() {
        int[] ints = new int[8];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(1, 10);
        }
        System.out.println("Array before swap: " + Arrays.toString(ints));

        for (int j = 1; j < ints.length; j += 2) {
            ints[j] = 0;
        }
        System.out.println("Arra after swap: " + Arrays.toString(ints));
    }

    public static void findMaxElement() {
        int[] ints = new int[12];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(-15, 15);
        }
        int max = Integer.MIN_VALUE;
        System.out.println(Arrays.toString(ints));

        for (int anInt : ints) {
            if (anInt > max) {
                max = anInt;
            }
        }
        System.out.println("Max element is: " + max);

        for (int k = ints.length - 1; k > 0; k--) {
            if (ints[k] == max) {
                System.out.println("Index of max element is :" + k);
                break;
            }
        }
    }
}
