package components.tasks;

import java.util.ArrayList;
import java.util.List;

import exceptions.DongjiEmptyTaskNameException;
import exceptions.DongjiIndexOutOfBoundException;
import exceptions.DongjiParseException;

public class TaskList {
    
    public List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) throws DongjiIndexOutOfBoundException{
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }
        return this.taskList.get(index);
    }

    public Task deleteTask(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        Task deletedTask = this.taskList.remove(index);
        return deletedTask;
        
    }

    public void mark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.taskList.get(index).mark();
    }

    public void unmark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.taskList.get(index).unmark();
    }

    public Task add(Task task) {
        this.taskList.add(task);
        return task;
    }

    public Task addTodo(String input) throws DongjiEmptyTaskNameException {
        String todoName = input.substring(5).trim();
        Task task = new Todo(todoName);
        this.taskList.add(task);
        return task;
    }

    public Task addEvent(String input) throws DongjiEmptyTaskNameException, DongjiParseException {
        input = input.substring(6).trim();

        Task task = Event.fromInputString(input);

        this.taskList.add(task);
        return task;
    }

    public Task addDeadline(String input) throws DongjiEmptyTaskNameException, DongjiParseException {
        input = input.substring(8).trim();

        Task task = Deadline.fromInputString(input);

        this.taskList.add(task);

        return task;
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append((i + 1) + ". " + this.taskList.get(i));
            if (i != this.taskList.size() - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
