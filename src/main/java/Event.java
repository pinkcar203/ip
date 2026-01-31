public class Event extends Task {
    private String dateFrom; 
    private String dateTo;   
    
    public Event(String description, String from, String to) {
        super(description);
        this.dateFrom = from;
        this.dateTo = to;
        this.type = TaskTypes.EVENT;
    }
    
    @Override
    public String toString() {
        
        return getTypeLetter()+ " " + getStatus() + " " + description + " (from " + dateFrom + "to " + dateTo + ")";
    }
    
    public String getDateFrom() {
        return dateFrom;
    }
    
    public String getDateTo() {
        return dateTo;
    }
}