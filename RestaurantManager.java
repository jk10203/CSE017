import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class RestaurantManager{
    public static void main(String[] args){
        Restaurant myRestaurant = new Restaurant();
        readMenu(myRestaurant, "menu.txt");
        readOrders(myRestaurant, "orders.txt");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter staff ID: ");
        int staffID = keyboard.nextInt();
        checkStaffID(staffID);
        System.out.printf("Staff %d contribution to the income: $%.2f\n", staffID, myRestaurant.staffIncome(staffID));
        System.out.printf("\nTotal Restaurant Income: $%.2f\n", myRestaurant.totalIncome());
        System.out.println("\nRestaurant meals ranked by popularity: ");
        myRestaurant.printSortedMenu();
    }
    
    public static void readOrders(Restaurant r, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                int id = Integer.parseInt(read.nextLine());
                int staffID = Integer.parseInt(read.nextLine());
                Order o = new Order(id, staffID);
                int nItems = Integer.parseInt(read.nextLine());
                for(int i=0; i<nItems; i++){
                    String item = read.nextLine();
                    o.addOrderItem(item);
                }
                r.addOrder(o);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    public static void readMenu(Restaurant r, String filename){
        /*Implement the method readMenu to read the menu from the file menu.txt 
        and add the menu items to the restaurant data structure menu. The input 
        file contains the three attributes of different menu items. Each attribute 
        is on a separate line (name, description, and price).
 */
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String name = read.nextLine();
                String description = read.nextLine();
                double price = Double.parseDouble(read.nextLine());
                MenuItem men = new MenuItem(name, description, price);
                r.addMenuItem(men);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

    }

    public static void checkStaffID(Integer staffID){
        /*Implement the method checkStaffID which checks if the parameter staffID 
        is valid and throws an InvalidIDException if it is not. A staff id is valid 
        if it contains exactly 9 digits. Use a regular expression and the method 
        matches() to validate the staff ID. The method should declare throwing an 
        exception of type InvalidIDException.
        
 */
        try{
            String s = "";
            s = staffID.toString();
            //s = Integer.parseString(staffID);
    
            if(s.matches("\\d{9}")){
        
            } else {
                throw new InvalidIDException();
            }
        } catch (InvalidIDException e){
            System.out.println(e.getMessage());
        }
    }
}