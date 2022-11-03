package com.ozorkin.hw4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input some text to find first and last char");
        String string = bufferedReader.readLine();
        System.out.println("First element of sentence: " + string.charAt(0));
        System.out.println("Last element of sentence: " + string.charAt(string.length()-1));

        System.out.println("Does sentence contains piece of text?");
        System.out.println("Write sentence");
        String letter = bufferedReader.readLine();
        System.out.println("Write piece of text which you want to find in: ");
        String text = bufferedReader.readLine();
        System.out.println(includesText(letter,text));

        System.out.println("Check if sentence contains similar end");
        System.out.println("Write sentence");
        String newLetter = bufferedReader.readLine();
        System.out.println("Write the end of the text which you want to compare:");
        String end = bufferedReader.readLine();
        System.out.println(includesSubstring(newLetter,end));


        System.out.println("Let's compare 2 sentences");
        System.out.println("Write first sentence:");
        String firstSentence = bufferedReader.readLine();
        System.out.println("Write second sentence:");
        String secondSentence = bufferedReader.readLine();
        System.out.println("First sentence equals second ?: " + firstSentence.equalsIgnoreCase(secondSentence));


        System.out.println("Second sentence contains first word of first sentence?");
        System.out.println("Write first sentence:");
        String firstSentenceWord = bufferedReader.readLine();
        System.out.println("Write second sentence:");
        String secondSentenceWord = bufferedReader.readLine();
        String[] firstWord = firstSentenceWord.split(" ");
        System.out.println("Second sentence contains first word of first sentence ?: " + secondSentenceWord.startsWith(firstWord[0]));

    }
    public static boolean  includesText(String string, String text) {
         int endSubstring = string.indexOf(text);

        return endSubstring >= 0;
    }
    public static boolean includesSubstring (String string, String end) {
        String compare = string.substring(string.length()-end.length());

        return compare.equalsIgnoreCase(end);
    }
}
