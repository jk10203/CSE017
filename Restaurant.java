import java.util.ArrayList;
import java.util.Iterator;

public class Restaurant{
    private HashMap<Integer, Order> orders;
    private BST<MenuItem> menu;

    public Restaurant(){
        orders = new HashMap<>();
        menu = new BST<>();
    }
    public Order getOrder(int id){
        return orders.get(id);
    }
    public MenuItem getMenuItem(String name){
        MenuItem m = new MenuItem(name, "", 0.0);
        return menu.get(m);
    }
    public void addOrder(Order o){
        orders.put(o.getID(), o);
    }
    public void cancelOrder(int id){
        orders.remove(id);
    }
    public void addMenuItem(MenuItem m){
        menu.add(m);
    }
    public void removeMenuItem(String name){
        menu.remove(new MenuItem(name, "", 0.0));
    }
    
    public double staffIncome(int staffID){
        ArrayList<Order> allOrders = orders.values();
        double total = 0.0;
        for(Order o: allOrders){
            if(o.getStaffID() == staffID){
                total += o.getTotalOrder(menu.values());
            }
        }
        return total;
    }
    public double totalIncome(){
        ArrayList<Order> allOrders = orders.values();
        double total = 0.0;
        for(Order o: allOrders){
            total += o.getTotalOrder(menu.values());
        }
        return total;
    }
    //complexity: o(n^2)
    public void printSortedMenu(){
        /*The method prints the restaurant menu ranked by popularity 
        (from the most popular to the least popular). The popularity is
         measured by the number of times the menu item is ordered 
         (see sample output for the format). The Bubble sort algorithm is
          provided for you in the file Sort.java. Modify it as you see fit 
          to sort the menu by popularity in descending order.  
          You should not use any sorting algorithm from the Java API.
        */
        ArrayList<Integer> popularity = new ArrayList(11);
        ArrayList<String> foodName = new ArrayList();
        //ArrayList foodName = new ArrayList();
        //ArrayList<HashMapEntry<Integer, Order>> orderArr = orders.toList(); //HAS ALL THE ORDERS
        
        ArrayList orderArr = orders.toList();
     
        int keyCount = 0;
        ArrayList<HashMapEntry<Integer, String>> connectFood = new ArrayList<>(11);
        for(int i=0; i<orderArr.size();i++){
            if(orderArr.get(i)!= null){
                connectFood.get(i).setValue((String)orderArr.get(i));
                connectFood.get(i).setKey(keyCount);
            }
        }
        //ArrayList<String> values =  orders.values();
        for(int j = 0; j<orderArr.size();j++){
            String orderName = connectFood.get(j).getValue();
            if(orderName.equals(menu.get((MenuItem) orderArr.get(j)))){
                connectFood.get(j).setKey(++keyCount);//??
                keyCount = 0;
            }
        }
        Sort.bubbleSort(connectFood);
        
        System.out.printf("%-30s\t%-10s\n", "Item", "Number of times ordered");
        for(int i =0; i<11;i++){
            System.out.printf("%-30s\t%-10d\n", foodName.get(i), popularity.get(i));
        }
    }

}