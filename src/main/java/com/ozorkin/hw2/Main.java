package com.ozorkin.hw2;


public class Main {
    public static void main(String[] args) {

        double a = 3;
        double b = 4;
        double c = 5;

        if((a+b)>c && (a+c)>b && (b+c)>a) {
            double s= (a+b+c)/2;
            double  area=Math.sqrt(s*(s-a)*(s-b)*(s-c));
            System.out.println("Area of Triangle is: " + area);
        }
        else
            System.out.println("Area of Triangle is: " + 0);
        System.out.println();


        int x = (int)(Math.random()*100);
        System.out.println("First random number is: " + x);
        int y = (int)(Math.random()*100);
        System.out.println("Second random number is: " + y);
        int z = (int)(Math.random()*100);
        System.out.println("Third random number is: " + z);

        System.out.println("The min number is: " +  ((x < y ?  x: y) < z ? (x < y ?  x: y): z));
        System.out.println();

        int random = (int)(Math.random()*100);
        System.out.println("Random number " + random + " is pair? : " + (random%2 == 0));
        System.out.println();

        int decimalNumber = -223;
        System.out.println("DecimalNumber " + decimalNumber + " equals binary number " + toBinary(decimalNumber));

    }

    public static String toBinary(int decimalNumber) {
        String binary = "";
        if (decimalNumber >= 0) {
            while (decimalNumber != 0) {
                binary = (decimalNumber % 2) + binary;
                decimalNumber = decimalNumber / 2;
            }
            return binary;
        }
        return null; // not positive

    }

}