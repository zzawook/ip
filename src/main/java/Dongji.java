import java.util.Scanner;

import components.Parser;
import components.Ui;
import components.commands.Command;
import components.tasks.TaskList;
import exceptions.DongjiUnknownInstructionException;

public class Dongji {
    

    private Scanner scanner;
    private TaskList taskList;
    private Ui ui;

    public Dongji() {
        this.ui = new Ui();
        ui.showWelcome();

        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();

        executeApplication();
        scanner.close();

        this.ui.showGoodbye();
    }

    private void executeApplication() {
        String input = this.scanner.nextLine();
        Parser parser = new Parser(this.taskList);

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

            ui.show(currentCommand.execute());
            
            input = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        new Dongji();
    }
}
