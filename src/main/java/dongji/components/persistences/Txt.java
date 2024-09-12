package dongji.components.persistences;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import dongji.components.DateTimeData;
import dongji.components.parsers.DateTimeParser;
import dongji.components.tasks.Deadline;
import dongji.components.tasks.Event;
import dongji.components.tasks.Task;
import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;
import dongji.exceptions.DongjiEmptyTaskNameException;

public class Txt implements Persistence {

    private final String FILE_NAME = "dongji.txt";
    private TaskList taskList;

    public Txt(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Exports currently saved tasks to a txt file. If the file does not exist, it
     * will create a new file
     */
    @Override
    public void exportTasks() {
        File dongjiFile = new File(FILE_NAME);
        if (!dongjiFile.exists()) {
            try {
                dongjiFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error opening file writer: " + e.getMessage());
        }

        Iterator<Task> taskListIterator = taskList.iterator();

        try {
            while (taskListIterator.hasNext()) {
                writer.write(convertTaskToTxtRecord(taskListIterator.next()) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        try {
            writer.close();
            System.out.println("Tasks saved successfully. You have " + taskList.size() + " tasks in your list");
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }

    /**
     * Imports tasks from a txt file after checking if the file exists. If the file
     * does not exist, it will return an empty task list
     * 
     * @return TaskList
     */
    @Override
    public TaskList importTasks() {
        File dongjiFile = new File(FILE_NAME);
        if (!dongjiFile.exists()) {
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
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        System.out.println("Saved tasks imported successfully. You have " + taskList.size() + " tasks in your list");
        return this.taskList;
    }

    private Task convertTxtRecordToTask(String taskRecord) {
        String[] taskRecordParts = taskRecord.split(" \\| ");

        assert taskRecordParts.length >= 3;

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
                task = new Event(taskName, DateTimeParser.extractDateTime(eventParts[0]),
                        DateTimeParser.extractDateTime(eventParts[1]));
            }
        } catch (DongjiEmptyTaskNameException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        if (isMarked) {
            task.mark();
        }

        return task;
    }

    private String convertTaskToTxtRecord(Task task) {
        assert task != null;

        StringBuilder sb = new StringBuilder();

        if (task instanceof Todo) {
            sb.append("T | ");
        } else if (task instanceof Deadline) {
            sb.append("D | ");
        } else if (task instanceof Event) {
            sb.append("E | ");
        }

        sb.append(task.isMarked() ? "1 | " : "0 | ");

        sb.append(task.getName());

        if (task instanceof Deadline) {
            sb.append(" | " + DateTimeData.formatDate(((Deadline) task).getDeadline()));
        } else if (task instanceof Event) {
            sb.append(" | " + DateTimeData.formatDate(((Event) task).getEventStart()) + "~"
                    + DateTimeData.formatDate(((Event) task).getEventEnd()));
        }

        return sb.toString();
    }
}
