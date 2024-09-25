package dongji.components.persistences;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dongji.components.tasks.TaskList;
import dongji.components.tasks.Todo;
import dongji.exceptions.DongjiEmptyTaskNameException;

public class TxtTest {

    @TempDir
    Path tempDir;

    private Txt txt;
    private TaskList taskList;

    @BeforeEach
    public void setup() {
        // Initialize Txt with the temporary directory path
        taskList = new TaskList();
        txt = new Txt(taskList);
    }

    @AfterAll
    private static void deleteExportedFile() {
        File exportedFile = new File("dongji.txt");
        if (exportedFile.exists()) {
            exportedFile.delete();
        }
    }

    private void clearExportedFile() {
        File exportedFile = new File("dongji.txt");
        if (exportedFile.exists()) {
            try {
                Files.writeString(exportedFile.toPath(), "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void populateExportedFile() {
        File exportedFile = new File("dongji.txt");
        if (exportedFile.exists()) {
            try {
                Files.writeString(exportedFile.toPath(), "T | 0 | Test Task\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                exportedFile.createNewFile();
                Files.writeString(exportedFile.toPath(), "T | 0 | Test Task\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testExportTasks() {
        try {
            this.taskList.add(new Todo("Test Task"));
        } catch (DongjiEmptyTaskNameException e) {
            e.printStackTrace();
            assert false;
        }

        assertDoesNotThrow(() -> {
            txt.exportTasks();
        });

        File exportedFile = new File("dongji.txt");
        assertTrue(exportedFile.exists());

        // Verify the contents of the file
        String content = "";
        try {
            content = Files.readString(exportedFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }

        assertTrue(content.contains("Test Task"));
    }

    @Test
    public void testImportTasks() {
        this.populateExportedFile();

        assertDoesNotThrow(() -> {
            TaskList taskList = txt.importTasks();
            assertEquals(1, taskList.size());
            assertEquals("Test Task", taskList.get(0).getName());
        });
    }
    @Test
    public void testExportTasksWithEmptyList() {
        assertDoesNotThrow(() -> {
            txt.exportTasks();
        });

        File exportedFile = new File("dongji.txt");
        assertTrue(exportedFile.exists());

        // Verify the contents of the file
        String content = "";
        try {
            content = Files.readString(exportedFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }

        assertTrue(content.isEmpty());
    }

    @Test
    public void testImportTasksWithEmptyFile() {
        this.clearExportedFile();

        assertDoesNotThrow(() -> {
            TaskList taskList = txt.importTasks();
            assertEquals(0, taskList.size());
        });
    }

    @Test
    public void testImportTasksWithNonExistentFile() {
        // Ensure the file does not exist
        File nonExistentFile = new File("dongji.txt");
        if (nonExistentFile.exists()) {
            nonExistentFile.delete();
        }

        assertDoesNotThrow(() -> {
            TaskList taskList = txt.importTasks();
            assertEquals(0, taskList.size());
        });
    }
}
