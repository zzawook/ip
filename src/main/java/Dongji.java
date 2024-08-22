import java.util.Scanner;

import components.Deadline;
import components.Event;
import components.Task;
import components.Todo;

import java.util.List;
import java.util.ArrayList;

public class Dongji {
    private final String SEPARATOR = "-------------------------------";

    private Scanner scanner;
    private List<Task> tasks;

    public Dongji() {
        this.greet();

        this.scanner = new Scanner(System.in);
        this.tasks = new ArrayList<Task>();

        executeApplication();
        scanner.close();

        this.exit();
    }

    private void executeApplication() {
        String input = this.scanner.nextLine();
        while (!input.equals("bye")) {
            StringBuilder printStringBuilder = new StringBuilder();

            if (input.equals("list")) {
                printStringBuilder.append(this.listTasks());
            } 
            else if (input.startsWith("mark")) {
                int index = this.parseIndex(input);
                printStringBuilder.append(this.mark(index));
                printStringBuilder.append("\n");
                printStringBuilder.append(this.listTasks());
            } 
            else if (input.startsWith("unmark")) {
                int index = this.parseIndex(input);
                printStringBuilder.append(this.unmark(index));
                printStringBuilder.append("\n");
                printStringBuilder.append(this.listTasks());
            } 
            else {
                if (input.startsWith("todo")) {
                    printStringBuilder.append(this.addTodo(input));
                } else if (input.startsWith("event")) {
                    printStringBuilder.append(this.addEvent(input));
                } else if (input.startsWith("deadline")) {
                    printStringBuilder.append(this.addDeadline(input));
                }

            }

            this.print(printStringBuilder.toString());
            input = scanner.nextLine();
        }
    }

    private int parseIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private String mark(int index) {
        this.tasks.get(index).mark();
        return "Nice! I've marked this task as done:";
    }

    private String unmark(int index) {
        this.tasks.get(index).unmark();
        return"Okay, I've marked this task as not done yet";
    }

    private String addTodo(String input) {
        String todoName = input.substring(5).trim();
        Task task = new Todo(todoName);
        this.tasks.add(task);
        return "added: " + todoName;
    }

    private String addEvent(String input) {
        String[] splitInput = input.split("/from");

        String eventName = splitInput[0].trim();

        String[] splitDates = splitInput[1].split("/to");
        String eventStart = splitDates[0].trim();
        String eventEnd = splitDates[1].trim();

        Task task = new Event(eventName, eventStart, eventEnd);

        this.tasks.add(task);

        return "added: " + eventName;
    }

    private String addDeadline(String input) {
        String[] splitted = input.split("/by");

        String deadlineName = splitted[0].trim();
        String deadline = splitted[1].trim();

        Task task = new Deadline(deadlineName, deadline);

        this.tasks.add(task);

        return "added: " + deadlineName;
    }

    private String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append((i + 1) + ". " + this.tasks.get(i));
            if (i != this.tasks.size() - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    private void print(String str) {
        System.out.println(this.wrapWithSeparator(str));
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
