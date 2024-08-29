package components.persistences;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import components.DateTimeData;
import components.parsers.DateTimeParser;
import components.tasks.Deadline;
import components.tasks.Event;
import components.tasks.Task;
import components.tasks.TaskList;
import components.tasks.Todo;
import exceptions.DongjiEmptyTaskNameException;

public class Txt implements Persistence {

    private final String FILE_NAME = "dongji.txt";
    private TaskList taskList;

    public Txt(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void exportTasks() {
        File dongjiFile = new File(FILE_NAME);
        if (! dongjiFile.exists()) {
            try {
                dongjiFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_NAME);
        }
        catch(IOException e) {
            System.out.println("Error opening file writer: " + e.getMessage());
        }

        Iterator<Task> taskListIterator = taskList.iterator();

        try {
            while (taskListIterator.hasNext()) {
                writer.write(convertTaskToTxtRecord(taskListIterator.next()) + "\n");
            }
        }
        catch(IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        try {
            writer.close();
            System.out.println("Tasks saved successfully. You have " + taskList.size() + " tasks in your list");
        }
        catch(IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }

    public TaskList importTasks() {
        File dongjiFile = new File(FILE_NAME);
        if (! dongjiFile.exists()) {
            System.out.println("No saved tasks found. Starting with empty task list");
            return this.taskList;
        }

        try {
            Scanner scanner = new Scanner(dongjiFile);
            while (scanner.hasNext()) {
                String taskRecord = scanner.nextLine();
                Task task = convertTxtRecordToTask(taskRecord);
                this.taskList.add(task);
            }
            scanner.close();
        }
        catch(IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        System.out.println("Saved tasks imported successfully. You have " + taskList.size() + " tasks in your list");
        return this.taskList;
    }

    private Task convertTxtRecordToTask(String taskRecord) {
        String[] taskRecordParts = taskRecord.split(" \\| ");
        String taskType = taskRecordParts[0];
        boolean isMarked = taskRecordParts[1].equals("1");
        String taskName = taskRecordParts[2];

        Task task = null;
        try {
            if (taskType.equals("T")) {
                task = new Todo(taskName);
            } else if (taskType.equals("D")) {
                DateTimeData deadline = DateTimeParser.extractDateTime(taskRecordParts[3]);
                task = new Deadline(taskName, deadline);
            } else if (taskType.equals("E")) {
                String[] eventParts = taskRecordParts[3].split("~");
                task = new Event(taskName, DateTimeParser.extractDateTime(eventParts[0]), DateTimeParser.extractDateTime(eventParts[1]));
            }
        }
        catch(DongjiEmptyTaskNameException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        if (isMarked) {
            task.mark();
        }

        return task;
    }

    private String convertTaskToTxtRecord(Task task) {
        StringBuilder sb = new StringBuilder();

        if (task instanceof Todo) {
            sb.append("T | ");
        } else if (task instanceof Deadline) {
            sb.append("D | ");
        } else if (task instanceof Event) {
            sb.append("E | ");
        }

        sb.append(task.getIsMarked() ? "1 | " : "0 | ");

        sb.append(task.getName());

        if (task instanceof Deadline) {
            sb.append(" | " + DateTimeData.formatDate(((Deadline) task).getDeadline()));
        } else if (task instanceof Event) {
            sb.append(" | " + DateTimeData.formatDate(((Event) task).getEventStart()) + "~" + DateTimeData.formatDate(((Event) task).getEventEnd()));
        }

        return sb.toString();
    }
}
