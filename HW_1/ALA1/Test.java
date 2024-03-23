/***
 * Class to model the entity Person
 * @author Joy Kim
 * @version 0.1
 * Date of creation: January 24, 2023
 * Last Date Modified: January 24, 2023
 *
 * This program was made in order to test the parent class Person and its subclasses Employee, Student, and Faculty
 * by making a Person array that is filled with information on 4 different people. After the information of each person is
 * organized, 2 methods are created to display the array and sort the array in alphabetical order. So the array is 
 * first displayed then displayed again this time showing the sorted version.
 */
import java.util.Scanner;
public class Test{
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        Person[] people = new Person[4]; //creates array of Person called people

        //initializing people array with a Person, Student, Employee, and Faculty
        people[0] = new Person ("Helen Brown", "222 10th Street Bethlehem", "610-334-2288", "hbrown@gmail.com");
        people[1] = new Student ("Paul Leister", "972 4th Street Allentown", "610-331-7177", "pleister@gmail.com", 12345, "CSE");
        people[2] = new Employee ("Beth Down", "234 Main Street Philadelphia", "484-222-4433", "bdown@gmail.com", 33442, "Systems Administrator", 75000.00);
        people[3] = new Faculty ("Mark Jones", "21 Orchid Street Bethlehem", "610-333-2211", "mjones@gmail.com", 22222, "Faculty", 100000.00, "Associate Professor");

        //first printing the original array
        System.out.println("Original Array: ");
        printArray(people);

        selectionSort(people); //utilizing selection sort to sort the names

        System.out.println("\nSorted Array: ");
        //printing the now sorted array of people
        printArray(people);

    
    }
    public static void printArray(Person[] list){
        for (int i =0; i<4; i++){ //for(Person p: list){ ??
            System.out.println(list[i] + " "); //needs the " " in orded to use toString indirectly
        }
    }     
    public static void selectionSort(Person[] list){
    		for(int i=0; i<list.length; i++){
      		    int minIndex = i;
                for(int j=i+1; j<list.length; j++){    
                    String name1 = list[j].getName();
                    String name2 = list[minIndex].getName(); 
                    if(name1.compareTo(name2)<0){ //if name1 appears before name2 (negative)
                            minIndex=j;
                    }
                }
                Person temp = list[i]; //temp must be of type Person in order to avoid errors of mismatch with the list array
                list[i] = list[minIndex]; //if minIndex = j, then its switching the one after i to be equal to i
                list[minIndex] = temp; //now the value of i+1 (j) is equal to what i used to equal (so that it is successfully sorted)
            }
}


}