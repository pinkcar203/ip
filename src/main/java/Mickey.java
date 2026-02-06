package mickey;
/**
 * Main class for chatbot Mickey
 * Handles user input and task management
 */

import java.util.ArrayList;

public class Mickey {
    private ArrayList<Task> tasks;
    private UI ui;
    private int taskCount;
    private FileSaver saver;
    
    /**
     * New mickey instance with empty list, UI and task count
     */
    public Mickey(String filePath) {
    this.ui = new UI();
    this.saver = new FileSaver(filePath);   
    this.tasks = saver.loadTasks();         
    this.taskCount = tasks.size();
    }

    public static void main(String[] args)  {
        new Mickey("./data/mickey.txt").run();
    }

    private void saveTask() {
        saver.saveTasks(tasks);
    }


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
            } else {
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
                Task currentTask = tasks.get(i);
                int displayIndex = i + 1;
                ui.showTask(displayIndex, currentTask.toString());
            }
        }
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
                Task selectedTask = tasks.get(taskIndex);
                tasks.remove(taskIndex);
                taskCount--;
                saveTask();
                ui.showDeleted(selectedTask.toString(), taskCount);
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
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
                Task selectedTask = tasks.get(taskIndex);
                selectedTask.markDone();
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
                Task selectedTask = tasks.get(taskIndex);
                selectedTask.markUndone();
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
            tasks.add(newTodo);
            taskCount++;
            saveTask();
            ui.showTaskAdded(newTodo.toString(), taskCount);
        }
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
            String[] deadlineDetails = Parser.getDeadline(userInput);
            String description = deadlineDetails[0];
            String dateBy = deadlineDetails[1];
            Deadline newDeadline = new Deadline(description, dateBy);
            tasks.add(newDeadline);
            taskCount++;
            saveTask();
            ui.showTaskAdded(newDeadline.toString(), taskCount);
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
            String[] eventDetails = Parser.getEvent(userInput);
            String description = eventDetails[0];
            String dateFrom = eventDetails[1];
            String dateTo = eventDetails[2];
            Event newEvent = new Event(description, dateFrom, dateTo);
            tasks.add(newEvent);
            taskCount++;
            saveTask();
            ui.showTaskAdded(newEvent.toString(), taskCount);
        }
    }
    /**
     * Adds as a Todo task default
     * @param userInput contains the description of task
     */
    
    private void handleEchoCommand(String userInput) {
        Todo echoTask = new Todo(userInput);
        tasks.add(echoTask);
        taskCount++;
        saveTask();
        ui.showTaskAdded(echoTask.toString(), taskCount);
    }
    
    
}