package mickey;

import java.util.Scanner;

public class Mickey {
    public static void main(String[] args) {
        String logo = "( o.o )\n"    // animal face
                    + "  >^< ";      
        
        System.out.println("------------------------------------------");
        System.out.println(" Hello! I'm Mickey\n" + logo + "\n");
        System.out.println(" What can I do for you?");
        System.out.println("------------------------------------------");
        
        Scanner entry = new Scanner(System.in);
        String chatEntry = entry.nextLine();
                
        while (!chatEntry.equals("bye")) {
            System.out.println("------------------------------------------");
            System.out.println("   " + chatEntry); //repeats it
            System.out.println("------------------------------------------");
            chatEntry = entry.nextLine();
        }
        
        System.out.println("------------------------------------------");
        System.out.println(" Bye. Hope to see you again soon! :D ");
        System.out.println("------------------------------------------");
        entry.close();
    }
}
