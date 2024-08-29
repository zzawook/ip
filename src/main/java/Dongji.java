import java.util.Scanner;

import components.commands.Command;
import components.parsers.CommandParser;
import components.persistences.Persistence;
import components.persistences.Txt;
import components.tasks.TaskList;
import components.ui.CommandLine;
import components.ui.Ui;
import exceptions.DongjiParseException;
import exceptions.DongjiUnknownInstructionException;

public class Dongji {
    

    private Scanner scanner;
    private TaskList taskList;
    private Persistence persistence;
    private Ui ui;

    public Dongji() {
        this.ui = new CommandLine();
        this.taskList = new TaskList();
        this.persistence = new Txt(taskList);
        this.persistence.importTasks();
        ui.showWelcome();

        this.scanner = new Scanner(System.in);

        executeApplication();
        scanner.close();
        this.persistence.exportTasks();

        this.ui.showGoodbye();
    }

    private void executeApplication() {
        String input = this.scanner.nextLine();
        CommandParser parser = new CommandParser(this.taskList);

        while (!input.equals("bye")) {
            Command currentCommand = null;
            try {
                currentCommand = parser.parseToCommand(input);
            }
            catch(DongjiUnknownInstructionException e) {
                ui.show(e.getMessage());
                input = scanner.nextLine();
                continue;
            }
            catch(DongjiParseException e) {
                ui.show(e.getMessage());
                input = scanner.nextLine();
                continue;
            }

            ui.show(currentCommand.execute());
            
            input = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        new Dongji();
    }
}
