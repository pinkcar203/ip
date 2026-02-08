package mickey.util;


import mickey.task.Task;
import mickey.task.Todo;
import mickey.task.Deadline;
import mickey.task.Event;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * FileSaver class for saving and loading tasks 
 */

public class FileSaver {
    private static final String SEPARATOR = " | ";
    private Path filePath;

    /**
     * Constructor for FileSaver
     */
    
    public FileSaver(String filePathString) {
        this.filePath = Paths.get(filePathString);
    }

     /**
     * Creates file if not there
     */
    private void createIfItDoesntExist(Path filePath) {
    //Get the parent folder
    Path parentDirectory = filePath.getParent();
    
    // check if parent folder is null 
    if (parentDirectory == null) {
        return;  // No parent folder needed, exit early
    }
    
    // check if the folder already exists
    boolean folderExists = Files.exists(parentDirectory);
    
    // create if it doesn't exist
    if (!folderExists) {
        try {
            Files.createDirectories(parentDirectory);
            System.out.println("Created folder: " + parentDirectory);
        } catch (IOException e) {
            System.out.println("Unable to create folder: " + e.getMessage());
        }
    }
}
    
    /**
     * Loads tasks from file
     * @return list of tasks
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        
        createIfItDoesntExist(filePath);
        
        if (!Files.exists(filePath)) {
            return tasks;
        }
        
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading task from file: " + e.getMessage());
        }
        
        return tasks;
    }
    
    /**
     * Save tasks to file
     */
    public void saveTasks(ArrayList<Task> tasks) {
        createIfItDoesntExist(filePath);
        
        try {
            List<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(convertTaskToFileFormat(task));
            }
            //creates file if not there
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Error saving task to file: " + e.getMessage());
        }
    }
    
   
    /**
     * Converts task for file input
     */
    private String convertTaskToFileFormat(Task task) {
        String taskType = getTaskTypeCode(task);
        String completionStatus = task.getIsComplete() ? "1" : "0";
        String description = task.getDescription();
        String additionalDetails = getAdditionalTaskDetails(task);
        
        return taskType + SEPARATOR + completionStatus + SEPARATOR 
                + description + additionalDetails;
    }
    /**
     * Gets task type character
     * @param task the task to get the type of
     * @return the task type character
     */
    
    private String getTaskTypeCode(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        }
        return "?";
    }
    /**
     * Gets additional details
     * @param task the task to get the additional details of
     * @return the additional details
     */
    
    private String getAdditionalTaskDetails(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return SEPARATOR + deadline.getDateBy().toString();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return SEPARATOR + event.getDateFrom().toString() + SEPARATOR + event.getDateTo().toString();
        }
        return "";
    }
    /**
     * Parses task from line
     * @param line the line to parse the task 
     * @return the task
     */
    
    private Task parseTaskFromLine(String line) {
        try {
            String[] components = line.split(" \\| ");
            
                    
            String taskType = components[0].trim();
            boolean isCompleted = components[1].trim().equals("1");
            String description = components[2].trim();
            
            Task task = createTaskFromComponents(taskType, components, description);
            
            //handle corrupted data too
            if (task != null && isCompleted) {
                task.markDone();
            }
            
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted line: " + line);
            return null;
        }
    }
    /**
     * Constructs task from components
     * @param taskType the type of task
     * @param components the components of the task
     * @param description the description of the task
     * @return the task
     */
    
    private Task createTaskFromComponents(String taskType, String[] components, 
        String description) {
        switch (taskType) {
        case "T":
            return new Todo(description);
        case "D":
            if (components.length > 3) {
                String deadlineBy = components[3].trim();
                LocalDate deadlineDate = LocalDate.parse(deadlineBy);
                return new Deadline(description, deadlineDate);
            }
            break;
        case "E":
            if (components.length > 4) {
                String eventFrom = components[3].trim();
                String eventTo = components[4].trim();
                LocalDateTime eventFromDate = LocalDateTime.parse(eventFrom);
                LocalDateTime eventToDate = LocalDateTime.parse(eventTo);
                return new Event(description, eventFromDate, eventToDate);
            }
            break;
        }
        return null;
    }
}