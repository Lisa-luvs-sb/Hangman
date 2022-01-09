package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

// Author: Lisa Martin

public class Hangman {
    public static int turns = 6;


    public static void manBeingHanged(String u, boolean f) {
        if (turns == 5 && !f) {
            System.out.println("\n\nSorry there is no (" + u + ") in the word.\n");
            System.out.println("From a far now hanging on a pole is a head.");
        }
        if (turns == 4 && !f) {
            System.out.println("\n\nSorry there is no (" + u + ") in the word.\n");
            System.out.println("The head now has a body.");
        }
        if (turns == 3 && !f) {
            System.out.println("\n\nSorry there is no (" + u + ") in the word.\n");
            System.out.println("The body now has an arm.");
        }
        if (turns == 2 && !f) {
            System.out.println("\n\nSorry there is no (" + u + ") in the word.\n");
            System.out.println("The body now has two arms.");
        }
        if (turns == 1 && !f) {
            System.out.println("\n\nSorry there is no (" + u + ") in the word.\n");
            System.out.println("The body now has two arms and a leg.");
        }
        if (turns == 0 && !f) {
            System.out.println("\n\nSorry there is no (" + u + ") in the word.\n");
            System.out.println("The body now has two arms and two legs.");
        }
    }

    public static void outputIntro() {
        System.out.println("\n|********************************|");
        System.out.println("|*** Welcome to Java Hangman! ***|");
        System.out.println("|********************************|\n");
        System.out.println("The word that you'll be guessing is programming related.\n");
        System.out.println("Enter (yes) to play or (no) to quit:");
    }

    public static void main(String[] args) throws Exception {
        boolean finished = false;
        int timesGuessed = 0;
        String play;

        File wordFile = new File("C:\\Users\\Lisa\\IdeaProjects\\Hangman\\wordTextFile.txt");
        Scanner loadWords = new Scanner(wordFile);

        ArrayList<String> words = new ArrayList<>();
        // Add words from text file into an ArrayList
        while (loadWords.hasNextLine()) {
            words.add(loadWords.nextLine());
        }
        // Randomly select the word to guess
        String pickWord = words.get((int)(Math.random() * words.size()));
        // Convert the chosen word into a char array
        char[] wordArray = pickWord.toCharArray();
        // Create an array to hold the letters guessed
        char[] recordAnswer = new char[wordArray.length];
        // Until the letter is found fill space with char
        for (int i = 0; i < wordArray.length; i++) {
            recordAnswer[i] = '*';
        }

        Scanner input = new Scanner(System.in);
        String usersGuess;
        // welcome prompt
        outputIntro();
        // does the user want to play?
        play = input.next();

        if (play.equals("yes")) {
            // Run while the word has not been guessed or out of tries.
            while (finished == false) {

                // Changes prompt for everytime after the 1st guess
                if (timesGuessed >= 1) {
                    System.out.println("\nGuess another letter: ");
                } else {
                    System.out.println("\nGuess a single letter: ");
                    timesGuessed++;
                }
                usersGuess = input.next().toLowerCase();
                //Input Validation
                while (!usersGuess.matches("[A-Za-z]{1}")) {
                    System.out.println("Incorrect input. Please enter a letter:");
                    usersGuess = input.next();
                }

                boolean found = false;
                for (int i = 0; i < wordArray.length; i++) {
                    if (usersGuess.charAt(0) == wordArray[i]) {
                        recordAnswer[i] = wordArray[i];
                        found = true;
                    }
                }
                if (found) {
                    System.out.println("\n(" + usersGuess + ") is in the word.\n");
                }

                if (!found) {
                    turns--;
                }

                boolean done = true;
                for (int i = 0; i < recordAnswer.length; i++) {
                    if(recordAnswer[i] == '*') {
                        System.out.print(" _");
                        done = false;
                    } else {
                        System.out.print(" " + recordAnswer[i]);
                    }
                }

                manBeingHanged(usersGuess, found);

                if (done) {
                    System.out.println("\nYou found the word!");
                    finished = true;
                }

                if (turns <= 0) {
                    System.out.println("\nOh NO! You've hung a man. GAME OVER\n");
                    finished = true;
                    System.out.println("The word was (" + pickWord + ")");
                }

              }

            } else {
            System.out.println("\nOkay bye.");
        }
    }
}
