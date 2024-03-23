import java.util.List;
import java.util.Iterator;
import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;

public class ToDoList implements Comparable<ToDoList>{
    private List<ToDoItem> thingsToDo; 
    private String name;
    public static int numTDL = 0;

    public ToDoList(String name){
        this.name = name;
        if(numTDL %  3 == 0){
            thingsToDo = new Stack<>();
        } else if (numTDL % 3 == 1){
            thingsToDo = new LinkedList<>();
        } else {
            thingsToDo = new ArrayList<>();
        }
        numTDL++;
    }

    public void addItem(ToDoItem it ){
        thingsToDo.add(it);
        thingsToDo.sort(new ComparatorByDeadline()); //this will cause a compiler error until class ComparatorByDeadline is defined.
    }

    //TODO: define compareTo below to compare two ToDoLists element by element using iterators.
    //BIG O(N)
    public int compareTo(ToDoList otherToDo){
        /*Define the natural ordering for ToDoLists by writing a definition of compareTo() that will compare 
        two ToDoLists element by element based on name, and return the difference between the first two ToDoItems 
        that are not the same. Similar to the compareTo() definition in String, return 0 if the lists are equal in 
        length and have items with identical names. If all elements are the same, the shorter List is considered “smaller”.
            You must use an iterator to do this.
            */


            //

        Iterator iter1 = thingsToDo.iterator();
        Iterator iter2 = this.thingsToDo.iterator();
        ToDoItem el1 = (ToDoItem) iter1.next();
        ToDoItem el2 = (ToDoItem) iter2.next();
        //ToDoItem name1 = el1.getName();
        //ToDoItem name2 = el2.getName();
        boolean boo = true;

        if (this.thingsToDo.size() == thingsToDo.size()){
            for(int j = 0; j< this.thingsToDo.size(); j++){
                if (this.thingsToDo.contains(el1) != true){
                    boo = false;
                }
            }
            if (boo == true){
                return 0;
            }
        } else {
            while(iter1.hasNext()){
                if (iter2.hasNext()){
                    if (el2.compareTo(el1) == 0){
                        return 0;
                    } else if (el2.compareTo(el1) != 0){
                        return el2.compareTo(el1);
                    }

                } 
            }
        }
       
       // return this.getName().compareTo(c.getName());
    }


    //TODO: define toString() to use recursion & an iteratorin order to return the elements held in thingsToDo
    //TIME COMPLEXITY - BIG O(N)
    public String toString(){
    /*Define a toString() method that is recursive and utilizes an iterator to return a String that includes each ToDoItem from thingsToDo on a new line. 
    */
        Iterator iter = thingsToDo.iterator();
        ToDoList ToDoList1 = (ToDoList) iter.next(); //CHECK THIS IF WRONG
        return toString(iter, ToDoList1);   
       
    }
    public String toString(Iterator iter, ToDoList el){
        if(iter.hasNext()){
        System.out.println(el);  //might have to do .toString?
        return toString(iter, el); // el might have to be like next or something
        } else {
            return "";
        }
    }


    
}