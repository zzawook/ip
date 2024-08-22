package components;

import exceptions.DongjiEmptyTaskNameException;

public abstract class Task {
    private String name;
    private boolean marked;

    public Task(String name) throws DongjiEmptyTaskNameException{
        if (name.trim().length() == 0) {
            throw new DongjiEmptyTaskNameException("Task name cannot be empty! Please provide a task name");
        }
        this.name = name;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    public String getName() {
        return this.name;
    }

    private String generateMarkString() {
        return this.marked ? "[X] " : "[ ] ";
    }

    public String toString() {
        return this.generateMarkString() + this.name;
    }
}
