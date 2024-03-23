/***
 * Class to process print requests
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 20, 2023
 *
 * This program was made in order to establish elements of a print request such as the id, group, and size.
 */

public class PrintRequest implements Comparable<PrintRequest>{
    private int id;
    private String group;
    private long size;

    /***
	 * Default constructor
	 * No parameters
	 * Initializes id and size to the string 0 and group to "batch"
	 */
    public PrintRequest(){
        id = 0;
        group = "batch";
        size = 0;
    }
    /***
	 * Constructor with 3 parameters
	 * @param	id for the id number
	 * @param	gr for group name
	 * @param	size for the size of the group
	 */
    public PrintRequest(int id, String gr, long size){
        this.id = id;
        group = gr;
        this.size = size;
    }
    /***
	 * Getter for the ID 
	 * @param	no parameters
	 * @return	the value of the data member id
	 */
    public int getID(){
        return id;
    }
    /***
	 * Getter for the group
	 * @param	no parameters
	 * @return	the value of the data member group
	 */
    public String getGroup(){
        return group;
    }
    /***
	 * Getter for the size
	 * @param	no parameters
	 * @return	the value of the data member size
	 */
    public long getSize(){
        return size;
    }
    /***
	 * Setter for the ID
	 * @param	id to set the data member id
	 * no return value
	 */
    public void setID(int id){
        this.id = id;
    }
    /***
	 * Setter for the group
	 * @param	group to set the data member group
	 * no return value
	 */
    public void setGroup(String group){
        this.group = group;
    }
    /***
	 * Setter for the size of the group
	 * @param	size to set the data member size
	 * no return value
	 */
    public void setSize(long size){
        this.size = size;
    }

    /***
	 * Method to get the printrequest information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        long temp = size;
        String out = "";
        if(size>1000000000){
            temp = temp/1000000000;
            out = temp + "GB";
        } else if (size>1000000){
            temp = temp / 1000000;
            out = temp + "MB";
        }else if (size > 1000){
            temp = temp / 1000;
            out = temp + "KB";
        } else{
            out = size + "Bytes";
        }
        return String.format("%-10d\t%-10s\t%-10s\t", id, group, out);
    }
    /***
	 * Method to get the priority
	 * @param no parameters
	 * @return the index to determine priority of the group
	 */
    private int getPriority(){
        String[] priorities = {"root", "admin", "user", "batch"};
        for(int i =0; i< priorities.length; i++){
            if(this.group.equals(priorities[i])){
                return i; //return index
            }
        }
        return -1;
    }
    /***
	 * Method to compare the priority of two groups
	 * @param pr for the PrintRequest object
	 * @return a value based on if the first printrequest is equal, bigger than, 
     * or smaller than the second printrequest
	 */
    public int compareTo(PrintRequest pr){
        return this.getPriority() - pr.getPriority();
    }
}
