package mickey;
/**
 * Event class for event tasks
 * Extends from task class
 */
public class Event extends Task {
    private String dateFrom; 
    private String dateTo;   
    
    public Event(String description, String from, String to) {
        super(description);
        this.dateFrom = from;
        this.dateTo = to;
        this.type = TaskTypes.EVENT;
    }
    /**
     * Returs event in string format
     */
    @Override
    public String toString() {
        
        return getTypeLetter()+ " " + getStatus() + " " + description + " (from " + dateFrom + "to " + dateTo + ")";
    }
    
    /**
     * Returns event start date
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * Returns event end date
     */
    
    public String getDateTo() {
        return dateTo;
    }
}