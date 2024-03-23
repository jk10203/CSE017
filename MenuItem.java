public class MenuItem implements Comparable<MenuItem> {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String descr, double price){
        this.name = name;
        this.description = descr;
        this.price = price;
    }
    public String getName(){ return name;}
    public String getDescription(){ return description;}
    public double getPrice(){ return price;}

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public String toString(){
        return String.format("%-10s\t%-20s\t%-10.2f", name, description, price);
    }
    public int compareTo(MenuItem s){
        if (this.getName() == s.getName()){
            return 0;
        } else if (this.getName().compareTo(s.getName())<0){ //might have to flip
            return -1;
        } else {
            return 1;
        }
    }
}
