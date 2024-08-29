package Dongji.components.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Dongji.exceptions.DongjiIndexOutOfBoundException;

public class TaskList {

    public List<Task> taskList;

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
     * @return Iterator<Task>
     */
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    /**
     * Returns the task at the specified index. Throws an DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or greater than the size of the task list)
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
     * Removes the task at the specified index. Throws an DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or greater than the size of the task list)
     * 
     * @param index
     * @return Task
     * @throws DongjiIndexOutOfBoundException
     */
    public Task deleteTask(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        Task deletedTask = this.taskList.remove(index);
        return deletedTask;

    }

    
    /** 
     * Marks the task at the specified index. Throws an DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or greater than the size of the task list)
     * 
     * @param index
     * @throws DongjiIndexOutOfBoundException
     */
    public void mark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.taskList.get(index).mark();
    }

    
    
    /** 
     * Unmarks the task at the specified index. Throws an DongjiIndexOutOfBoundException if the index is invalid (i.e. less than 0 or greater than the size of the task list)
     * 
     * @param index
     * @throws DongjiIndexOutOfBoundException
     */
    public void unmark(int index) throws DongjiIndexOutOfBoundException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DongjiIndexOutOfBoundException("Index out of bound! Please provide a valid index of task");
        }

        this.taskList.get(index).unmark();
    }

    
    /** 
     * Adds the task to the task list
     * 
     * @param task
     * @return Task
     */
    public Task add(Task task) {
        this.taskList.add(task);
        return task;
    }

    
    /** 
     * Returns a string representation of the task list
     * 
     * @return String
     */
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
