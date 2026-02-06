package mickey;
/**
 * Deadline class for deadline tasks
 * Extends from Task class
 */
public class Deadline extends Task {
    private String dateBy;
    public Deadline (String description, String dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.type = TaskTypes.DEADLINE;
    }

    /**
     * Returns the deadline task in a string format
     */
    @Override
    public String toString() {
        return getTypeLetter() + " " + getStatus() + " " + description + " (by " + dateBy + ")";
    }

    /**
     * Returns the deadline date
     */
    public String getDateBy() {
        return dateBy;
    }
}