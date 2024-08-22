import java.util.Scanner;

import components.Deadline;
import components.Event;
import components.Task;
import components.Todo;
import exceptions.DongjiEmptyTaskNameException;
import exceptions.DongjiException;
import exceptions.DongjiIndexOutOfBoundException;
import exceptions.DongjiParseException;
import exceptions.DongjiUnknownInstructionException;

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
                String listTaskString = this.listTasks();

                this.addStringsToStringBuilder(printStringBuilder, listTaskString);
            } 
            else if (input.startsWith("delete")) {
                int index = this.parseIndex(input);
                String deleteMessage = "";
                try {
                    deleteMessage = this.deleteTask(index);
                } catch (DongjiIndexOutOfBoundException e) {
                    deleteMessage = e.getMessage();
                }

                this.addStringsToStringBuilder(printStringBuilder, deleteMessage);
            }
            else if (input.startsWith("mark")) {
                int index = this.parseIndex(input);
                String markMessage = "";
                String listTaskString = "";
                try {
                    markMessage = this.mark(index);
                    listTaskString = this.listTasks();
                }
                catch (DongjiIndexOutOfBoundException e) {
                    markMessage = e.getMessage();
                }

                this.addStringsToStringBuilder(printStringBuilder, markMessage, "\n", listTaskString);
            } 
            else if (input.startsWith("unmark")) {
                int index = this.parseIndex(input);
                String unmarkMessage = "";
                String listTaskString = "";
                try {
                    unmarkMessage = this.unmark(index);
                    listTaskString = this.listTasks();
                }
                catch (DongjiIndexOutOfBoundException e) {
                    unmarkMessage = e.getMessage();
                }

                this.addStringsToStringBuilder(printStringBuilder, unmarkMessage, "\n", listTaskString);
            } 
            else {
                try {
                    if (input.startsWith("todo")) {
                        String addTodoMessage = this.addTodo(input);

                        this.addStringsToStringBuilder(printStringBuilder, addTodoMessage);
                    } else if (input.startsWith("event")) {
                        String addEventMessage = this.addEvent(input);

                        this.addStringsToStringBuilder(printStringBuilder, addEventMessage);
                    } else if (input.startsWith("deadline")) {
                        String addDeadlineMessage = this.addDeadline(input);

                        this.addStringsToStringBuilder(printStringBuilder, addDeadlineMessage);
                    }
                    else {
                        throw new DongjiUnknownInstructionException(
                                "The command is not recognized! Please provide a valid command");
                    }
                } catch (DongjiException e) {
                    printStringBuilder.append(e.getMessage());
                }
            }

            this.print(printStringBuilder.toString());
            input = scanner.nextLine();
        }
    }

    private String deleteTask(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        Task deletedTask = this.tasks.remove(index);
        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + this.tasks.size()
                + " tasks in the list.";
    }

    private String mark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.tasks.get(index).mark();
        return "Nice! I've marked this task as done:";
    }

    private String unmark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.tasks.get(index).unmark();
        return "Okay, I've marked this task as not done yet";
    }

    private String addTodo(String input) throws DongjiEmptyTaskNameException {
        String todoName = input.substring(5).trim();
        Task task = new Todo(todoName);
        this.tasks.add(task);
        return "added: " + todoName;
    }

    private String addEvent(String input) throws DongjiEmptyTaskNameException, DongjiParseException {
        input = input.substring(6).trim();

        Task task = Event.fromInputString(input);

        this.tasks.add(task);

        return "added: " + task.getName();
    }

    private String addDeadline(String input) throws DongjiEmptyTaskNameException, DongjiParseException {
        input = input.substring(8).trim();

        Task task = Deadline.fromInputString(input);

        this.tasks.add(task);

        return "added: " + task.getName();
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

    private String wrapWithSeparator(String input) {
        return SEPARATOR + "\n=> " + input + "\n" + SEPARATOR;
    }

    private int parseIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private void addStringsToStringBuilder(StringBuilder sb, String... s) {
        for (String str : s) {
            sb.append(str);
        }
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
        new Dongji();
    }
}
