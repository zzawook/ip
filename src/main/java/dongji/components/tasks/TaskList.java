package dongji.components.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import dongji.exceptions.DongjiIndexOutOfBoundException;

/**
 * Represents a list of tasks. Wrapper of List<Task/>
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns the size of the task list
     * 
     * @return int
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns an iterator for the task list
     * 
     * @return Iterator<Task/>
     */
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    /**
     * Returns the task at the specified index. Throws an
     * DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or
     * greater than the size of the task list)
     * 
     * @param index
     * @return Task
     * @throws DongjiIndexOutOfBoundException
     */
    public Task get(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }
        return this.taskList.get(index);
    }

    /**
     * Removes the task at the specified index. Throws an
     * DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or
     * greater than the size of the task list)
     * 
     * @param index
     * @return Task
     * @throws DongjiIndexOutOfBoundException
     */
    public Task deleteTask(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        int initSize = this.taskList.size();
        Task deletedTask = this.taskList.remove(index);

        assert deletedTask != null;
        assert !this.taskList.contains(deletedTask);
        assert this.taskList.size() == initSize - 1;

        return deletedTask;
    }

    /**
     * Marks the task at the specified index. Throws an
     * DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or
     * greater than the size of the task list)
     * 
     * @param index
     * @throws DongjiIndexOutOfBoundException
     */
    public void mark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.taskList.get(index).mark();
        assert this.taskList.get(index).isMarked();
    }

    /**
     * Unmarks the task at the specified index. Throws an
     * DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or
     * greater than the size of the task list)
     * 
     * @param index
     * @throws DongjiIndexOutOfBoundException
     */
    public void unmark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.taskList.get(index).unmark();
        assert !this.taskList.get(index).isMarked();
    }

    /**
     * Adds the task to the task list
     * @param task
     * @return Task
     */
    public Task add(Task task) {
        int initSize = this.taskList.size();
        this.taskList.add(task);

        assert this.taskList.size() == initSize + 1;
        assert this.taskList.contains(task);
        return task;
    }

    public Stream<Task> stream() {
        return this.taskList.stream();
    }

    /**
     * Returns a string representation of the task list
     * 
     * @return String
     */
    public String listTasks() {
        if (this.taskList.size() == 0) {
            return "No tasks in the list!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append(i + 1 + ". " + this.taskList.get(i).toString() + "\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
