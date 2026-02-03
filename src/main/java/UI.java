/**
 * Class that handles messages displayed to the user
 */
import java.util.Scanner;

public class UI {
    private Scanner entry;
    private static final String line = "------------------------------------------";

    public UI(){
        this.entry = new Scanner(System.in);
    }
    

    public void welcomeLogo() {
        String logo = "( o.o )\n"  // animal face
                    + "  >^< ";      
        
        System.out.println(line);
        System.out.println(" Hello! I'm Mickey\n" + logo + "\n");
        System.out.println(" What can I do for you?");
        System.out.println(line);
    }

    public void sayBye() {
        System.out.println(line);
        System.out.println(" Chat says bye. Hope to see you again soon! :D ");
        System.out.println(line);
    }

    public String userInput() {
        return entry.nextLine().trim();
    }

    public void nextLine() {
        System.out.println(line);
    }

    public void allTaskList () {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
    }

    public void showInvalidTaskNumber() {
        System.out.println("Please choose a task number within the list.");
    }

    public void showMarked() {
        System.out.println("Niceeee! I've marked this task as done:");
    }

     public void showUnmarked() {
        System.out.println("Okie, I've marked this task as not done yet:");
    }

    public void showDeleted(String task, int taskCount) {
        System.out.println("Okie, I've removed this task:");
        System.out.println(" " + task);
        if (taskCount == 0) {
            System.out.println(" Now you have no tasks in the list.");
        } else if (taskCount == 1) {
            System.out.println(" Now you have " + taskCount + " task in the list.");
        }
        else {
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
        }
    }

    public void showNumberFormatError() {
        System.out.println("Oops, please use the task number for selection.");
    }

     public void showEmptyTodoError() {
        System.out.println("Please enter a description of your todo item");
    }

    public void showNoTask(){
        System.out.println("You are done with all your tasks! Good job!");
    }

     public void showTaskAdded(String taskString, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + taskString);
        if (taskCount == 1) {
            System.out.println(" Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
        }
    }

    public void showDeadlineEmptyError() {
        System.out.println("Oops please specify the item and deadline");
    }

    public void showDeadlineMissingDateError() {
        System.out.println("Remember to enter the deadline dateee!");
    }

    public void showDeadlineNoDescriptionError() {
        System.out.println("Hmmm there seems to be no description..");
    }

    public void showEventEmptyError() {
        System.out.println("You havent entered any event details yet!");
    }

    public void showEventMissingDatesError() {
        System.out.println("You havent entered any event dates yet...");
    }

    
    public void showTask(int index, String task) {
        System.out.println(index + "." + task);
    }

    public void showMarkedTask(String description, String details) {
        if (details != null) {
            System.out.println("[X] " + description + " " + details);
        } else {
            System.out.println(" [X] " + description);
        }
    }

     public void showUnmarkedTask(String description) {
        System.out.println(" [ ] " + description);
    }
    
    
    public void close() {
        entry.close();
    }
}