package Dongji.components.tasks;

import Dongji.exceptions.DongjiEmptyTaskNameException;

public class Todo extends Task {
    public Todo(String name) throws DongjiEmptyTaskNameException {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
