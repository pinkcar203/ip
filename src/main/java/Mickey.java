import java.util.Scanner;

public class Mickey {
    public static void main(String[] args) {
        String logo = "( o.o )\n"  // animal face
                    + "  >^< ";      
        
        System.out.println("------------------------------------------");
        System.out.println(" Hello! I'm Mickey\n" + logo + "\n");
        System.out.println(" What can I do for you?");
        System.out.println("------------------------------------------");
        
        Scanner entry = new Scanner(System.in);
        String chatEntry = entry.nextLine().trim();
        String[] taskList = new String[100];
        char[] taskType = new char[100];  
        String[] taskDetails = new String[100];  
        int taskCount = 0;
        boolean[] isComplete = new boolean[100];
                    
        while (!chatEntry.equals("bye")) {

            String[] firstWord = chatEntry.trim().split("\\s+"); 
            System.out.println("------------------------------------------");
            
            if (chatEntry.equals("list") || chatEntry.equals("to-do")) {
                System.out.println("------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                // show all tasks 
                for (int i = 0; i < taskCount; i++) {
                    String check;
                    if (isComplete[i]) {
                        check = "[X]";
                    } else {
                        check = "[ ]";
                    }
                    String typeTag = "[" + taskType[i] + "]";
                    String task = typeTag + check + " " + taskList[i];                  
                    if (taskDetails[i] != null) {
                        task += " " + taskDetails[i];
                    }
                    
                    System.out.println((i+1) + "." + task);  
                }
            } 
            //possible errors: if task number more than task count; negative task number
            else if (firstWord[0].equals("mark")){
                try {
                    String[] parts = chatEntry.trim().split("\\s+");
                    int taskComplete = Integer.parseInt(parts[1]);
                    int arrayTask = taskComplete - 1;
                    if (arrayTask < 0 || arrayTask >= taskCount) {
                        System.out.println("Please choose a task number within the list.");
                    }

                    else {
                    isComplete[arrayTask] = true;
                    System.out.println("Niceeee! I've marked this task as done:");
                    if (taskDetails[arrayTask] != null) {
                        System.out.println("[X] " + taskList[arrayTask] + " " + taskDetails[arrayTask]);
                    } else {
                        System.out.println(" [X] " + taskList[arrayTask]);
                    }
                    }
                } 
                catch (NumberFormatException err) {
                    System.out.println("Oops, please use the task number for selection.");
                }
            } 
            else if (firstWord[0].equals("unmark")){
                try {
                    String[] parts = chatEntry.trim().split("\\s+");
                    int taskComplete = Integer.parseInt(parts[1]);
                    int arrayTask = taskComplete - 1;
                    if (arrayTask < 0 || arrayTask >= taskCount) {
                        System.out.println("Please choose a task number within the list.");
                    }
                    else {
                    isComplete[arrayTask] = false;
                    System.out.println("Okie, I've marked this task as not done yet:");
                    System.out.println(" [ ] " + taskList[arrayTask]);
                    }
                } 
                catch (NumberFormatException err) {
                    System.out.println("Oops, please use the task number for selection.");
                }

            }
            else if (firstWord[0].equals("todo")) {
                // todo: task description
                if (chatEntry.length() <= 4) {
                    System.out.println("Please enter a description of your todo item");
                } else {
                    String description = chatEntry.substring(5);
                    taskList[taskCount] = description;
                    taskType[taskCount] = 'T';
                    taskDetails[taskCount] = null;
                    isComplete[taskCount] = false;
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  [T][ ] " + description);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                }
            }
            else if (firstWord[0].equals("deadline")) {
                // cuts off at /by
                int byIndex = chatEntry.indexOf("/by");
                if (chatEntry.length() <= 8){
                    System.out.println("Oops please specify the item and deadline");
                }
                else if (byIndex == -1) {
                    System.out.println("Remember to enter the deadline dateee!");
                }
                else if (byIndex <= 10){
                    System.out.println("Hmmm there seems to be no description..");
                }
                else {
                    String description = chatEntry.substring(9, byIndex);
                    String by = chatEntry.substring(byIndex + 4);
                    taskList[taskCount] = description;
                    taskType[taskCount] = 'D';
                    taskDetails[taskCount] = "(by:" + by + ")";
                    isComplete[taskCount] = false;
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  [D][ ] " + description + " (by: " + by + ")");
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                }
            }

            
            else if (firstWord[0].equals("event")) {
                int fromIndex = chatEntry.indexOf("/from");
                int toIndex = chatEntry.indexOf("/to");
                if (chatEntry.length() <= 5) {
                    System.out.println("You havent entered any event details yet!");
                }
                else if (fromIndex == -1 || toIndex == -1){
                    System.out.println("You havent entered any event dates yet...");
                }
                else {
                String description = chatEntry.substring(6, fromIndex);
                String from = chatEntry.substring(fromIndex + 6, toIndex);
                String to = chatEntry.substring(toIndex + 4);
                taskList[taskCount] = description;
                taskType[taskCount] = 'E';
                taskDetails[taskCount] = "(from " + from + " to " + to + ")";
                isComplete[taskCount] = false;
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                System.out.println("  [E][ ] " + description + " (from " + from + "to " + to + ")");
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                }
            }
            else {
                // todo echo
                taskList[taskCount] = chatEntry;
                taskType[taskCount] = 'T';
                taskDetails[taskCount] = null;
                isComplete[taskCount] = false;
                taskCount++;
                System.out.println(" added: " + chatEntry);
            }
            
            System.out.println("------------------------------------------");
            chatEntry = entry.nextLine();
        }
        
        System.out.println("------------------------------------------");
        System.out.println(" Chat says bye. Hope to see you again soon! :D ");
        System.out.println("------------------------------------------");
        entry.close();

    }
}
