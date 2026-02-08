package mickey;


import mickey.task.Task;
import mickey.task.Todo;
import mickey.task.Deadline;
import mickey.task.Event;
import mickey.task.TaskList;
import mickey.util.UI;
import mickey.util.Parser;
import mickey.util.FileSaver;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for chatbot Mickey
 * Handles user input and task management
 */

public class Mickey {
    private TaskList tasks;
    private UI ui;
    private int taskCount;
    private FileSaver saver;
    private ArrayList<String> quotes;
    
    /**
     * New mickey instance with empty list, UI and task count
     */
    public Mickey(String filePath) {
    this.ui = new UI();
    this.saver = new FileSaver(filePath);   
    this.tasks = new TaskList(saver.loadTasks());         
    this.taskCount = tasks.size();
    this.quotes = saver.loadQuotes();
    }

    /**
     * Main method for chatbot
     */

    public static void main(String[] args)  {
        new Mickey("./data/mickey.txt").run();
    }

    private void saveTask() {
        saver.saveTasks(tasks.getAllTasks());
    }

    /**
     * To run the chatbot
     */


    public void run() {
        ui.welcomeLogo();
        String userInput = ui.userInput();
        
        while (!userInput.equals("bye")) {
            String command = Parser.getCommand(userInput);
            ui.nextLine();
            
            if (userInput.equals("list") || userInput.equals("to-do")) {
                handleListCommand();
            } else if (command.equals("mark")) {
                handleMarkCommand(userInput);
            } else if (command.equals("unmark")) {
                handleUnmarkCommand(userInput);
            } else if (command.equals("todo")) {
                handleTodoCommand(userInput);
            } else if (command.equals("deadline")) {
                handleDeadlineCommand(userInput);
            } else if (command.equals("event")) {
                handleEventCommand(userInput);
            } else if (command.equals("delete")) {
                handleDeleteCommand(userInput);
            } else if (command.equals("due")) {
                handleDueCommand(userInput);
            } else if (command.equals("cheer")) {
                handleCheerCommand();
            }
<<<<<<< HEAD
            else if (command.equals("find")) {
                handleFindCommand(userInput);
            }
=======

>>>>>>> branch-A-Cheer
            else {
                handleEchoCommand(userInput);
            }
            
            ui.nextLine();
            userInput = ui.userInput();
        }
        
        ui.sayBye();
        ui.close();
    }
    
    /**
     * Shows all tasks in the list
     */
    private void handleListCommand() {
        if (taskCount == 0) {
            ui.showNoTask();
        } else {
            ui.allTaskList();
            for (int i = 0; i < taskCount; i++) {
                Task currentTask = tasks.getTask(i);
                int displayIndex = i + 1;
                ui.showTask(displayIndex, currentTask.toString());
            }
        }
    }

    /**
     * Handles the find command
     * @param userInput the user input containing the keyword to search 
     * @return the matching tasks
     */

    private void handleFindCommand(String userInput) {
        if (userInput.length() <= 4) {
            System.out.println("Please enter the keyword to search by");
            return;
        }
        String keyword = Parser.getKeywordToSearch(userInput);
        ArrayList<Task> matchResults = new ArrayList<>();

        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchResults.add(task);
            }
        }
        ui.showKeywordResults(matchResults);
    }

    /**
     * Deletes a task from task list
     * @param userInput the user input containing the task number to be deleted
     */
    
    private void handleDeleteCommand(String userInput) {
        try {
            int taskNumber = Parser.getDeletedTask(userInput);
            int taskIndex = taskNumber - 1;
            
            if (taskIndex < 0 || taskIndex >= taskCount) {
                ui.showInvalidTaskNumber();
            } else {
                Task selectedTask = tasks.deleteTask(taskIndex);
                taskCount--;
                saveTask();
                ui.showDeleted(selectedTask.toString(), taskCount);
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
    }

    /**
     * Handles the cheer command
     * @return the random quote
     */

    private void handleCheerCommand() {
        if (quotes.isEmpty()) {
            System.out.println("Keep goingggggg!");
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.size());
        String randomQuote = quotes.get(randomIndex);
        System.out.println(randomQuote);
    }

    /**
     * Marks a task as done
     * @param userInput containing the task number to be marked as done
     */
    
    private void handleMarkCommand(String userInput) {
        try {
            int taskNumber = Parser.getCompletedTask(userInput);
            int taskIndex = taskNumber - 1;
            
            if (taskIndex < 0 || taskIndex >= taskCount) {
                ui.showInvalidTaskNumber();
            } else {
                tasks.markTask(taskIndex);
                Task selectedTask = tasks.getTask(taskIndex);
                saveTask();
                ui.showMarked();
                System.out.println(" " + selectedTask.toString());
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
    }
    /**
     * Marks a task as incomplete
     * @param userInput contains the task number to be marked as incomplete
     */
    
    private void handleUnmarkCommand(String userInput) {
        try {
            int taskNumber = Parser.getCompletedTask(userInput);
            int taskIndex = taskNumber - 1;
            
            if (taskIndex < 0 || taskIndex >= taskCount) {
                ui.showInvalidTaskNumber();
            } else {
                tasks.unmarkTask(taskIndex);
                Task selectedTask = tasks.getTask(taskIndex);
                saveTask();
                ui.showUnmarked();
                System.out.println(" " + selectedTask.toString());
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
    }

    /**
     * Adds a todo task to the task list
     * @param userInput contains the description of the task
     */
    private void handleTodoCommand(String userInput) {
        if (userInput.length() <= 4) {
            ui.showEmptyTodoError();
        } else {
            String description = Parser.getTodoDescription(userInput);
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);
            taskCount++;
            saveTask();
            ui.showTaskAdded(newTodo.toString(), taskCount);
        }
    }
    /**
     * Commans to show tasks due on a specific date
     * @param userInput contains the date to show tasks due
     */
    private void handleDueCommand(String userInput) {
        try{
            LocalDate date = Parser.getDateInFormat(userInput);
            ArrayList<Task> dueTasks = new ArrayList<>();
            for (Task task : tasks.getAllTasks()) {
                if (taskIsSameDate(task, date)) {
                    dueTasks.add(task);
                }
            }
            ui.showDueTasks(dueTasks, date);
        } catch (DateTimeParseException e) {
            ui.showInvalidDate();
        } 
    }

    /**
     * Checks if a task is due on a specific date
     * @param task the task
     * @param date the date to check
     * @return true if the task is due on the date and false if not due
     *
     */

    private boolean taskIsSameDate(Task task, LocalDate date) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getDateBy().equals(date);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            LocalDate eventDate = event.getDateFrom().toLocalDate();
            return eventDate.equals(date);
        }
        return false;
    }


    /**
     * Adds a deadline task
     * @param userInput contains the description and deadline date of the task
     */
    
    private void handleDeadlineCommand(String userInput) {
        int byIndex = Parser.getByIndex(userInput);
        
        if (userInput.length() <= 8) {
            ui.showDeadlineEmptyError();
        } else if (byIndex == -1) {
            ui.showDeadlineMissingDateError();
        } else if (byIndex <= 10) {
            ui.showDeadlineNoDescriptionError();
        } else {
            try {
                Object[] deadlineDetails = Parser.getDeadline(userInput);
                String description = (String) deadlineDetails[0];
                LocalDate dateBy = (LocalDate) deadlineDetails[1];
                Deadline newDeadline = new Deadline(description, dateBy);
                tasks.addTask(newDeadline);
                taskCount++;
                saveTask();
                ui.showTaskAdded(newDeadline.toString(), taskCount);
            } catch (DateTimeParseException e) {
                ui.showInvalidDate();
            }
        }
    }
    
    /**
     * Adds an event task
     * @param userInput contains the description and event date range of the task
     */
    private void handleEventCommand(String userInput) {
        int fromIndex = Parser.getFromIndex(userInput);
        int toIndex = Parser.getToIndex(userInput);
        
        if (userInput.length() <= 5) {
            ui.showEventEmptyError();
        } else if (fromIndex == -1 || toIndex == -1) {
            ui.showEventMissingDatesError();
        } else {
            try {
                Object[] eventDetails = Parser.getEvent(userInput);
                String description = (String) eventDetails[0];
                LocalDateTime dateFrom = (LocalDateTime) eventDetails[1];
                LocalDateTime dateTo = (LocalDateTime) eventDetails[2];
                Event newEvent = new Event(description, dateFrom, dateTo);
                tasks.addTask(newEvent);
                taskCount++;
                saveTask();
                ui.showTaskAdded(newEvent.toString(), taskCount);
            } catch (DateTimeParseException e) {
                ui.showInvalidDate();
            }
        }
    }
    /**
     * Adds as a Todo task default
     * @param userInput contains the description of task
     */
    
    private void handleEchoCommand(String userInput) {
        Todo echoTask = new Todo(userInput);
        tasks.addTask(echoTask);
        taskCount++;
        saveTask();
        ui.showTaskAdded(echoTask.toString(), taskCount);
    }
    
    
}