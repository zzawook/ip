package components;

public class Task {
    private String name;
    private boolean marked;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    private String generateMarkString() {
        return this.marked ? "[X] " : "[ ] ";
    }

    public String toString() {
        return this.generateMarkString() + this.name;
    }
}
