package mickey;
/**
 * Todo class for todo tasks
 * Extends from Task class
 */
public class Todo extends Task {
    
    public Todo(String description) {
        super(description);
        this.type = TaskTypes.TODO;
    }
    
    /**
     * Returns todo in string 
     */
    @Override
    public String toString() {
        return getTypeLetter() + " " + getStatus() + " " + description;
    }
}