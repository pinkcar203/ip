public class Parser {
    
    
    public static String getCommand(String chatEntry) {
        String[] firstWord = chatEntry.trim().split("\\s+");
        return firstWord[0];
    }
    
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

    public static int getCompletedTask(String chatEntry) {
        String[] parts = chatEntry.trim().split("\\s+");
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
    
    
    public static String[] getDeadline(String chatEntry) {
        int byIndex = chatEntry.indexOf("/by");
        String description = chatEntry.substring(9, byIndex);
        String by = chatEntry.substring(byIndex + 4);
        return new String[]{description, by};
    }
    
    
    public static String[] getEvent(String chatEntry) {
        int fromIndex = chatEntry.indexOf("/from");
        int toIndex = chatEntry.indexOf("/to");
        String description = chatEntry.substring(6, fromIndex);
        String dateFrom = chatEntry.substring(fromIndex + 6, toIndex);
        String dateTo = chatEntry.substring(toIndex + 4);
        return new String[]{description, dateFrom, dateTo};
    }
    
    
    
}