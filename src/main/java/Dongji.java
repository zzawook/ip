import java.util.Scanner;

public class Dongji {
    private final String SEPARATOR = "-------------------------------";

    private Scanner scanner;

    public Dongji() {
        this.greet();

        this.scanner = new Scanner(System.in);
        executeApplication();
        scanner.close();

        this.exit();
    }

    private void executeApplication() {
        String input = this.scanner.nextLine();
        while (!input.equals("bye")) {
            this.echo(input);
            input = scanner.nextLine();
        }
    }

    private void echo(String input) {
        System.out.println(this.wrapWithSeparator(input));
    }

    private String wrapWithSeparator(String input) {
        return SEPARATOR + "\n=> " + input + "\n" + SEPARATOR;
    }

    private void greet() {
        System.out.println(this.SEPARATOR);
        System.out.println("Hello! I'm Dongji\nWhat can I do for you?");
        System.out.println(this.SEPARATOR);
    }

    private void exit() {
        System.out.println(this.SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(this.SEPARATOR);
    }

    public static void main(String[] args) {
        Dongji dongji = new Dongji();
    }

}
