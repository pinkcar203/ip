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
        String chatEntry = entry.nextLine();
        String[] taskList = new String[100];
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
                    System.out.println("" + (i+1) + "." + check + " " + taskList[i]);  
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
                    System.out.println(" [X] " + taskList[arrayTask]);
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

            else {
                // add to list, increase counter and echo item
                taskList[taskCount] = chatEntry;
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
