public class Deadline extends Task {
    private String dateBy;
    public Deadline (String description, String dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.type = TaskTypes.DEADLINE;
    }

    @Override
    public String toString() {
        return getTypeLetter() + " " + getStatus() + " " + description + " (by " + dateBy + ")";
    }

    public String getDateBy() {
        return dateBy;
    }
}