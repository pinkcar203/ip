public class Deadline extends Task {
    private String dateBy;
    public Deadline (String description, String dateBy) {
        super(description);
        this.dateBy = dateBy;
    }

    @Override
    public String toString() {
        return "[D][" + getStatus() + "] " + description + " (by " + dateBy + ")";
    }

    public String getDateBy() {
        return dateBy;
    }
}