package Dongji;
import java.util.Scanner;

import Dongji.components.commands.Command;
import Dongji.components.parsers.CommandParser;
import Dongji.components.persistences.Persistence;
import Dongji.components.persistences.Txt;
import Dongji.components.tasks.TaskList;
import Dongji.components.ui.CommandLine;
import Dongji.components.ui.Ui;
import Dongji.exceptions.DongjiParseException;
import Dongji.exceptions.DongjiUnknownInstructionException;

public class Dongji {
    

    private Scanner scanner;
    private TaskList taskList;
    private Persistence persistence;
    private Ui ui;

    public Dongji() {
        this.initialize();
    }

    private void initialize() {
        this.ui = new CommandLine();
        this.taskList = new TaskList();
        this.persistence = new Txt(taskList);
        this.persistence.importTasks();
    }

    private void terminate() {
        this.persistence.exportTasks();
    }

    private void executeApplication() {
        ui.showWelcome();

        this.scanner = new Scanner(System.in);
        
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
        scanner.close();
        this.ui.showGoodbye();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        Dongji dongji = new Dongji();
        dongji.executeApplication();
    }
}
