
import java.util.Comparator;
public class ComparatorByDeadline implements Comparator<ToDoItem>{ //might have to change to todo
//for each have a generic version that uses object
//Comparable --> int compareTo(o)
//Comparator --> int compare (object o1, object o2) --> use generic one (more robust) which is when you put type Comparator<Contact> above

    public int compare(ToDoItem c1, ToDoItem c2){ 
        
        Date d1 = (Date) c1.getDeadline();
        Date d2 = (Date) c2.getDeadline();
        int out = d1.compareTo(d2);
        return out;
    } 
}