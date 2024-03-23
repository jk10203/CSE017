public class Staff{
    private int id;
    private String name;

    public Staff(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getID(){ return id;}
    public String getName(){return name;}

    public void setID(int id){this.id = id;}
    public void setName(String name){ this.name = name;}

    public String toString(){
        return String.format("%-10d\t%-20s", id, name);
    }
}