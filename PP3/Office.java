public class Office extends Room {
    private String owner;
    
    public Office(String number, int capacity, int area, String owner){
        super(number, capacity, area);
        this.owner = owner;
    }
    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }

    public String toString(){
        return String.format("%-10s\t%s\t%-10s\t", "Office", super.toString(), owner);
    }
}
