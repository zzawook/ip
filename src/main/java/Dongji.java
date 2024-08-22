import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Dongji {
    private final String SEPARATOR = "-------------------------------";

    private Scanner scanner;
    private List<String> tasks;

    public Dongji() {
        this.greet();

        this.scanner = new Scanner(System.in);
        this.tasks = new ArrayList<String>();

        executeApplication();
        scanner.close();

        this.exit();
    }

    private void executeApplication() {
        String input = this.scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.listTasks();
            } else {
                this.addTask(input);
            }

            input = scanner.nextLine();
        }
    }

    private void addTask(String task) {
        this.tasks.add(task);
        System.out.println(this.wrapWithSeparator("added: " + task));
    }

    private void listTasks() {
        System.out.println(this.SEPARATOR);

        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }

        System.out.println(this.SEPARATOR);
    }

    // private void echo(String input) {
    // System.out.println(this.wrapWithSeparator(input));
    // }

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
