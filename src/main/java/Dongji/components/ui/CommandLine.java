package Dongji.components.ui;

public class CommandLine implements Ui {
    private final String SEPARATOR = "-------------------------------";

    
    /** 
     * @param str
     */
    @Override
    public void show(String str) {
        System.out.println(this.wrapWithSeparator(str));
    }

    private String wrapWithSeparator(String input) {
        return SEPARATOR + "\n=> " + input + "\n" + SEPARATOR;
    }

    /** 
     * Prints welcome message
     */
    @Override
    public void showWelcome() {
        System.out.println("Hello! I'm Dongji");
        System.out.println("What can I do for you?");
    }

    /** 
     * Prints good bye message
     */
    @Override
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
