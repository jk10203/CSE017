/***
 * Class to create the Country Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 20, 2023
 *
 * This program was made in order to establish a way to order a Country's properties including its
 * name, carbonEmission and carbonCapita for a series of years. There also exists methods to get certain
 * emission values for certain years and ways to manipulate the ArrayList of the carbonEmission and carbonCapita in
 * order to add new values. There also exists ways to compare two countries based on names.
 */
import java.util.ListIterator;
public class Country{
    private String name;
    private ArrayList<Pair<Integer,Double>> carbonEmission;
    private ArrayList<Pair<Integer, Double>> carbonCapita;
    
    /***
	 * Default constructor
	 * @param name for the name of the Country
     * Intitializes name to parameter, and initializes the arraylist 
     * carbonEmission and carbonCapita
	 */
    //BIG O NOTATION: O(1)
    public Country(String name){
        this.name = name;
        //new ArrayList<>(cap)
        carbonEmission = new ArrayList<Pair<Integer,Double>>();
        carbonCapita =  new ArrayList<Pair<Integer,Double>>();
    }
    /***
	 * Getter for the name
	 * no parameters
	 * @return	the value of the data member name
	 */
    //BIG O NOTATION: O(1)
    public String getName(){
        return name;
    }
    /***
	 * Setter for the name
	 * @param	date to set the date member name
	 * no return value
	 */
    //BIG O NOTATION: O(1)
    public void setName(String name){
        this.name = name;
    }
    /***
	 * Method to add an element to carbonEmission array
	 * @param year for the year of the carbon emission
     * @param tons for the amount of carbon emissions
	 * no return value
	 */
    //BIG O NOTATION: O(1)
    public void addCarbonEmission(int year, double tons){
        Pair pear = new Pair(year, tons);
        carbonEmission.add(pear);
    }
    /***
	 * Method to add an element to carbonCapita array
	 * @param year for the year of the carbon emission
     * @param tons for the amount of carbon emissions per capita
	 * no return value
	 */
    //BIG O NOTATION: O(1)
    public void addCarbonCapita(int year, double tons){
        Pair pear = new Pair(year, tons);
        carbonCapita.add(pear);
    }
    /***
	 * Getter for the emission value
	 * @param year for the year of the carbon emission you want to get
	 * @return	the iterator that points to that year
	 */
    //BIG O NOTATION: O(N)
    public ListIterator<Pair<Integer,Double>> getEmission(int year){
            ListIterator <Pair<Integer, Double>> iter = carbonEmission.listIterator();
            while(iter.hasNext()){
                Pair<Integer, Double> P = iter.next();
                if(P.getFirst() == year){
                    iter.previous();
                    return iter;
                }
            }
            return null;
    }
    /***
	 * Getter for the emission per capita value
	 * @param year for the year of the carbon emission per capita you want to get
	 * @return	the iterator that points to that year
	 */
    //BIG O NOTATION: O(N)
    public ListIterator<Pair<Integer,Double>> getCapita(int year){
        //ListIterator capIt = new ListIterator (carbonCapita); ??????
        
        ListIterator<Pair<Integer, Double>> iter = carbonCapita.listIterator();
            while(iter.hasNext()){
                Pair<Integer, Double> P = iter.next();
                if(P.getFirst() == year){
                    iter.previous();
                    return iter;
                }
            }
            return null;
    }
    /***
	 * Method to get the Country's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    //BIG O NOTATION: O(1)
    public String toString(){
        return String.format("%-15s\t%-10d\t%-10d\t", name, carbonEmission.toString(), carbonCapita.toString() );
    }
    /***
	 * Method to compare names of the country
	 * @param o for the country object
	 * @return a boolean if the name of the country equals another name of another country
	 */
    //BIG O NOTATION: O(1)
    public boolean equals(Object o){
        if(o instanceof Country){
            Country cuntry = (Country) o;
            return this.getName().equals(cuntry.getName());
        }
        return false;
    }
    
}