public class Classroom extends Room {
    
    public Classroom(String number, int capacity, int area){
        super(number, capacity, area);
    }

    @Override
    public String toString(){
        return String.format("%-10s\t%s\t", "Classroom", super.toString());
    }
}
