public abstract class Task {
    protected String description;
    protected boolean isComplete;
    public abstract String toString();

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

   public void markDone() {
        this.isComplete = true;
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
   