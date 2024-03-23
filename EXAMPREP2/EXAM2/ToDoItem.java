public class ToDoItem implements Comparable<ToDoItem>{
    private String name; 
    private Date deadline;
    private String description; 
    public ToDoItem(String name, Date deadline, String description){
        this.name = name;
        this.deadline = deadline;
        this.description = description;
    }
    public Date getDeadline(){ return deadline;}
    public String getDescription(){ return description;}
    public boolean equals(Object o){ // what makes to items the same? if all three things the same
        ToDoItem t = (ToDoItem) o;
        return this.name.equals(t.name) && this.deadline.compareTo(t.deadline) == 0 && this.description.equals(t.description);
    }
    @Override
    public int compareTo(ToDoItem todo){
        return name.compareTo(todo.name);
    }
    public String toString(){
        return String.format("\t%-25s%-15s%-20s", name, deadline.toString(), description);
    }
}