public class Lab extends Room{
    private int computers;

    public Lab(String number, int capacity, int area, int computers){
        super(number, capacity, area);
        this.computers = computers;
    }
    public int getComputers(){
        return computers;
    }
    public void setComputers(int c){
        computers = c;
    }

    @Override
    public String toString(){
        return String.format("%-10s\t%s\t%-10f\t", "Lab", super.toString(), computers);
    }
}
