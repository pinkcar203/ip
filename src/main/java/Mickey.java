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
                    
        while (!chatEntry.equals("bye")) {
            System.out.println("------------------------------------------");
            if (chatEntry.equals("list") || chatEntry.equals("to-do")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i+1) + ". " + taskList[i]);
                }            
                } else {
                //add to list, increase counter and echo item
                taskList[taskCount] = chatEntry;
                taskCount++;
                System.out.println(" added: " + chatEntry);
            }
            
            System.out.println("------------------------------------------");
            chatEntry = entry.nextLine();
        }
        
        System.out.println("------------------------------------------");
        System.out.println(" Bye. Hope to see you again soon! :D ");
        System.out.println("------------------------------------------");
        entry.close();
        
    }
}
