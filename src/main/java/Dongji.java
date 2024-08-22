import java.util.Scanner;

import components.Task;
import components.TaskList;
import enums.Command;
import exceptions.DongjiEmptyTaskNameException;
import exceptions.DongjiException;
import exceptions.DongjiIndexOutOfBoundException;
import exceptions.DongjiParseException;
import exceptions.DongjiUnknownInstructionException;

public class Dongji {
    private final String SEPARATOR = "-------------------------------";

    private Scanner scanner;
    private TaskList taskList;

    public Dongji() {
        this.greet();

        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();

        executeApplication();
        scanner.close();

        this.exit();
    }

    private void executeApplication() {
        String input = this.scanner.nextLine();

        while (!input.equals("bye")) {
            Command currentCommand = this.parseCommand(input);
            try {
                this.executeCommand(currentCommand, input);
            } catch (DongjiUnknownInstructionException e) {
                this.print(e.getMessage());
            }
            
            input = scanner.nextLine();
        }
    }

    private void executeCommand(Command command, String input) throws DongjiUnknownInstructionException {
        StringBuilder printStringBuilder = new StringBuilder();
        
        switch(command) {
            case LIST:
                String listTaskString = this.listTasks();

                this.addStringsToStringBuilder(printStringBuilder, listTaskString);
                break;
            case DELETE:
                int deleteIndex = this.parseIndex(input);
                String deleteMessage = "";
                try {
                    deleteMessage = this.deleteTask(deleteIndex);
                } catch (DongjiIndexOutOfBoundException e) {
                    deleteMessage = e.getMessage();
                }

                this.addStringsToStringBuilder(printStringBuilder, deleteMessage);
                break;
            case MARK:
                int markIndex = this.parseIndex(input);
                String markMessage = "";
                listTaskString = "";
                try {
                    markMessage = this.mark(markIndex);
                    listTaskString = this.listTasks();
                    
                    this.addStringsToStringBuilder(printStringBuilder, markMessage, "\n", listTaskString);
                }
                catch (DongjiIndexOutOfBoundException e) {
                    markMessage = e.getMessage();

                    this.addStringsToStringBuilder(printStringBuilder, markMessage);
                }
                break;
            case UNMARK:
                int unmarkIndex = this.parseIndex(input);
                String unmarkMessage = "";
                String unmarkListTaskString = "";
                try {
                    unmarkMessage = this.unmark(unmarkIndex);
                    unmarkListTaskString = this.listTasks();

                    this.addStringsToStringBuilder(printStringBuilder, unmarkMessage, "\n", unmarkListTaskString);
                }
                catch (DongjiIndexOutOfBoundException e) {
                    unmarkMessage = e.getMessage();

                    this.addStringsToStringBuilder(printStringBuilder, unmarkMessage);
                }
                break;
            case TODO:
                try {
                    String addTodoMessage = this.addTodo(input);

                    this.addStringsToStringBuilder(printStringBuilder, addTodoMessage);
                } catch (DongjiException e) {
                    printStringBuilder.append(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    String addEventMessage = this.addEvent(input);

                    this.addStringsToStringBuilder(printStringBuilder, addEventMessage);
                } catch (DongjiException e) {
                    printStringBuilder.append(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    String addDeadlineMessage = this.addDeadline(input);

                    this.addStringsToStringBuilder(printStringBuilder, addDeadlineMessage);
                } catch (DongjiException e) {
                    printStringBuilder.append(e.getMessage());
                }
                break;
            default: 
                throw new DongjiUnknownInstructionException(
                        "The command is not recognized! Please provide a valid command");
        }
        this.print(printStringBuilder.toString());
    }

    private Command parseCommand(String input) {
        String command = input.split(" ")[0];

        return Command.valueOf(command.toUpperCase());
    }

    private String deleteTask(int index) throws DongjiIndexOutOfBoundException {
        Task deletedTask = this.taskList.deleteTask(index);
        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + this.taskList.size()
                + " tasks in the list.";
    }

    private String mark(int index) throws DongjiIndexOutOfBoundException {
        this.taskList.mark(index);
        return "Nice! I've marked this task as done:";
    }

    private String unmark(int index) throws DongjiIndexOutOfBoundException {
        this.taskList.unmark(index);
        return "Okay, I've marked this task as not done yet";
    }

    private String addTodo(String input) throws DongjiEmptyTaskNameException {
        Task addedTodo = this.taskList.addTodo(input);
        return "added: " + addedTodo.getName();
    }

    private String addEvent(String input) throws DongjiEmptyTaskNameException, DongjiParseException {
        Task addedEvent = this.taskList.addEvent(input);
        return "added: " + addedEvent.getName();
    }

    private String addDeadline(String input) throws DongjiEmptyTaskNameException, DongjiParseException {
        Task addedDeadline = this.taskList.addDeadline(input);
        return "added: " + addedDeadline.getName();
    }

    private String listTasks() {
        return this.taskList.listTasks();
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
