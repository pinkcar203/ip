package mickey;
/**
 * Class for parsing user input and extracting information needed
 */
public class Parser {
    
    /**
     * Extracts the first word of input as the command
     * @param chatEntry the user input
     * @return the command
     */
    
    public static String getCommand(String chatEntry) {
        String[] firstWord = chatEntry.trim().split("\\s+");
        return firstWord[0];

    }

    /**
     * Retreived the index in the input
     */
    
    public static int getByIndex(String chatEntry) {
        int byIndex = chatEntry.indexOf("/by");
        return byIndex;
    }
    
    
    public static int getFromIndex(String chatEntry) {
        int fromIndex = chatEntry.indexOf("/from");
        return fromIndex;
    }
    
    public static int getToIndex(String chatEntry) {
        int toIndex = chatEntry.indexOf("/to");
        return toIndex;
    }

    /**
     * Extracts the task number from the input
     * @param chatEntry the user input
     * @return the task number
     */
    public static int getCompletedTask(String chatEntry) {
        String[] parts = chatEntry.trim().split("\\s+");
        //add error handling when just entering "mark"
        if (parts.length < 2) {
            throw new NumberFormatException("No task number provided");
        }
        int completedTask = Integer.parseInt(parts[1]);
        return completedTask;
    }
    public static int getDeletedTask(String chatEntry){
        String[] parts = chatEntry.trim().split("\\s+");
        int deletedTask = Integer.parseInt(parts[1]);
        return deletedTask;
    }
   
    public static String getTodoDescription(String chatEntry) {
        String description = chatEntry.substring(5);
        return description;
    }
    
    /**`
     * Extracts the description and deadline date from input
     * @param chatEntry user input
     * @return the description and deadline date
     */  
    
    public static String[] getDeadline(String chatEntry) {
        int byIndex = chatEntry.indexOf("/by");
        String description = chatEntry.substring(9, byIndex);
        String by = chatEntry.substring(byIndex + 4);
        return new String[]{description, by};
    }
    
    /**
     * Extracts the description and event date range from the input
     * @param chatEntry user input
     * @return the description and  date range
     */
    
    public static String[] getEvent(String chatEntry) {
        int fromIndex = chatEntry.indexOf("/from");
        int toIndex = chatEntry.indexOf("/to");
        String description = chatEntry.substring(6, fromIndex);
        String dateFrom = chatEntry.substring(fromIndex + 6, toIndex);
        String dateTo = chatEntry.substring(toIndex + 4);
        return new String[]{description, dateFrom, dateTo};
    }
    
    
    
}