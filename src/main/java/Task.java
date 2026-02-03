/**
 * Abstract class for tasks for todo, event and deadline tasks to extend from
 
 */
public abstract class Task {
    protected String description;
    protected boolean isComplete;
    public abstract String toString();
    protected TaskTypes type;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

   public void markDone() {
        this.isComplete = true;
   }

   public TaskTypes getTypeOfTask(){
        return type;
   }

   public String getTypeLetter() {
       switch (type) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
       }
       return "?";
   }


       

   public void markUndone() {
        this.isComplete = false;
   }
   
   public String getStatus() {
        String check;
        if (isComplete) {
            check = "[X]";
        } else {
            check = "[ ]";
        }
        return check;
   }

   public String getDescription(){
       return description;
   }

   public boolean isComplete() {
       return isComplete;
   }
}
   