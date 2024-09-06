package Dongji.components.ui;

import java.util.Scanner;

import Dongji.Dongji;

public class CommandLine {
    private final String SEPARATOR = "-------------------------------";
    private Scanner scanner;
    private Dongji dongji;

    public CommandLine() {
        this.scanner = new Scanner(System.in);
        this.dongji = new Dongji();
    }

    
    /** 
     * Reads the user input from the command line and returns it as a String
     * 
     * @return String
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Launches the command line interface for Dongji
     */
    public void launch() {
        showWelcome();
        String input = readInput();
        while (!input.equals("bye")) {
            this.show(dongji.getResponse(input));
            input = readInput();
        }
        showGoodbye();
        dongji.terminate();
        close();
    }
    
    /** 
     * Prints the given String str after wrapping with separator
     * 
     * @param str
     */
    private void show(String str) {
        System.out.println(this.wrapWithSeparator(str));
    }

    private String wrapWithSeparator(String input) {
        return SEPARATOR + "\n=> " + input + "\n" + SEPARATOR;
    }

    /** 
     * Prints welcome message
     */
    private void showWelcome() {
        System.out.println("Hello! I'm Dongji");
        System.out.println("What can I do for you?");
    }

    /** 
     * Prints good bye message
     */
    private void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void close() {
        scanner.close();
    }
}
