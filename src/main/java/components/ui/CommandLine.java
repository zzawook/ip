package components.ui;

public class CommandLine implements Ui {
    private final String SEPARATOR = "-------------------------------";

    @Override
    public void show(String str) {
        System.out.println(this.wrapWithSeparator(str));
    }

    private String wrapWithSeparator(String input) {
        return SEPARATOR + "\n=> " + input + "\n" + SEPARATOR;
    }

    @Override
    public void showWelcome() {
        System.out.println("Hello! I'm Dongji");
        System.out.println("What can I do for you?");
    }

    @Override
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
