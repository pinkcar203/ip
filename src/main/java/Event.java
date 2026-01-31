public class Event extends Task {
    private String dateFrom; 
    private String dateTo;   
    
    public Event(String description, String from, String to) {
        super(description);
        this.dateFrom = from;
        this.dateTo = to;
    }
    
    @Override
    public String toString() {
        
        return "[E]" + getStatus() + " " + description + " (from " + dateFrom + " to " + dateTo + ")";
    }
    
    public String getDateFrom() {
        return dateFrom;
    }
    
    public String getDateTo() {
        return dateTo;
    }
}