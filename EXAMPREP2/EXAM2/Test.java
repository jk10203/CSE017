import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Creating ArrayList to hold 7 ToDoLists (one for each day of week)
        ArrayList<ToDoList> weeklyList = new ArrayList<>(7);
        weeklyList.add(new ToDoList("Sunday"));
        weeklyList.add(new ToDoList("Monday"));
        weeklyList.add(new ToDoList("Tuesday"));
        weeklyList.add(new ToDoList("Wednesday"));
        weeklyList.add(new ToDoList("Thursday"));
        weeklyList.add(new ToDoList("Friday"));
        weeklyList.add(new ToDoList("Saturday"));

        // file names holding todoitems to be added to corresponding lists
        String[] files = { "sunday.txt", "monday.txt", "tuesday.txt", "wednesday.txt", "thursday.txt", "friday.txt",
                "saturday.txt" };
        readLists(weeklyList, files);

        System.out.println("Lists with items read in and kept in order within each list by deadline");
        for (ToDoList todolist : weeklyList) {
            System.out.println(todolist);
        }

        insertionSort(weeklyList); // calling generic sort method below that you will define

        System.out.println("List of ToDoLists sorted by Items");
        for (ToDoList todolist : weeklyList) {
            System.out.println(todolist);
        }
    }

    // do not modify. will read from each of the text files to add items to each
    // todolist

    public static void readLists(ArrayList<ToDoList> list, String[] filenames) {
        int i = 0;
        try {
            File f;
            Scanner fScan;
            for (i = 0; i < filenames.length; i++) {
                f = new File(filenames[i]);
                fScan = new Scanner(f);

                ToDoList temp = list.get(i);
                while (fScan.hasNextLine()) {
                    String name = fScan.nextLine();
                    Date d = new Date(fScan.nextLine());
                    String descr = fScan.nextLine();
                    ToDoItem it = new ToDoItem(name, d, descr);
                    temp.addItem(it);
                }
                fScan.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("FNF in readLists: " + i);
            System.exit(1);
        } catch (InvalidDateTimeException e) {
            System.out.println("invalid date/time in readLists: " + i);
            System.exit(1);
        }
    }

    // TODO: define a generic sort method using insertion sort below. Be sure to
    // indicate its time complexity using Big-O notation
    //O(logn)
    //
    public static <E> void insertionSort(List<E> list) {
        //weeklyList

       for(int i=1; i<list.size(); i++){
           int j=i;
           E currentVal = list.get(i);
           
           //E val = list.get(j-1);
           while(j>0 && currentVal.compareTo(list.get(j-1)) < 0){ //the one after is smaller than the before
               //list[j] = list[j-1];
               list.set(j, list.get(j-1));
               j--;
           }
           //list[j] = currentVal;
           list.set(j, currentVal);
       }

    }
}