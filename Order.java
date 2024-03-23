import java.util.Iterator;
import java.util.ArrayList;

public class Order{
    private int id;
    private int staffID;
    private LinkedList<String> orderItems;

    public Order(int id, int sID){
        this.id = id;
        staffID = sID;
        orderItems = new LinkedList<>();
    }
    public int getID(){ 
        return id;
    }
    public int getStaffID(){
        return staffID;
    }
    public LinkedList<String> getOrderItems(){
        return orderItems;
    }
    
    public void addOrderItem(String name){
        orderItems.add(name);
    }
    public boolean cancelMenuItem(String name){
        return orderItems.remove(name);
    }
    public void setID(int id){
        this.id = id;
    }
    public void setStaffID(int id){
        this.staffID = id;
    }
    public String toString(){
        return String.format("%-10d\t%-10d\n%s", id, staffID, orderItems);
    }
    public double getTotalOrder(ArrayList<MenuItem> menu){
        /*The method accepts a menu (list of name, description, 
        and price) and returns the total money charged for the menu items in 
        this order. Note that the parameter list contains the menu of the 
        restaurant with the prices while the class Order contains the list 
        orderItems with only the names of the menu items ordered.
 */     double orderTotal = 0;
        Iterator iterOrder = orderItems.iterator();
        while(iterOrder.hasNext()){
            String orderCurrent = (String) iterOrder.next();
            for(int i =0; i< menu.size(); i++){
                if (orderCurrent.compareTo(menu.get(i).getName()) ==0){
                    orderTotal += menu.get(i).getPrice();
                    break;
                }
            }
        }
        return orderTotal;
    }
}