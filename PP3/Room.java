

public abstract class Room implements Comparable{
    private String number;
    private int capacity;
    private int area;

    protected Room(String number, int capacity, int area){
        this.number = number;
        this.capacity = capacity;
        this.area = area;
    }
    public String getNumber(){
        return number;
    }
    public int getCapacity(){
        return capacity;
    }
    public int getArea(){
        return area;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public void setArea(int area){
        this.area = area;
    }
    @Override 
    public String toString(){
        return String.format("%-10s\t%-10f\t%-10f",number, capacity, area);
    }
    public int compareTo(Room r){
        if(this.getCapacity() == r.getCapacity()){
            return 0;
        }
        if(this.getCapacity() > r.getCapacity()){
            return 1; //if the first capacity is bigger than 2nd capacity
        }
        if(this.getCapacity() < r.getCapacity()){
            return -1;//if the first capacity is smaller than 2nd capacity
        }
        return -1;
    }


}