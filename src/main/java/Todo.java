public class Todo extends Task {
    
    public Todo(String description) {
        super(description);
        this.type = TaskTypes.TODO;
    }
    
    @Override
    public String toString() {
        return getTypeLetter() + " " + getStatus() + " " + description;
    }
}