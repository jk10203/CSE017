/***
 * Class to create the Test Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 20, 2023
 *
 * This program was made in order to test the modified ArrayList and LinkedList classes by using a variety of 
 * operations that manipulates Country objects and the pair objects within the country objects. Modified
 * iterators from the classes are also used in order to search for a country or the highest/lowest emission value.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.List;
/**
    Class to test the modified classes ArrayList and LinkedList
*/
public class Test{
    public static void main(String[] args){
        LinkedList<Country> countries = new LinkedList<>();
        readFile(countries, "emissions.txt");
        System.out.println("List read from the file: " + countries.size() + " countries");
        // Finding a country
        Country c = new Country("United States of America");
        ListIterator<Country> listIter = findCountry(countries, c);
        if(listIter == null){
            System.out.println(c.getName() + " not found.");
        }
        else{
            System.out.println(c.getName() + " found.");
        
            //moving to the next country
            if(listIter.hasNext()){
                System.out.println("Next country: " + listIter.next().getName());
            }
            //moving to the previous country
            listIter.previous();
            listIter.previous();
            listIter.previous();
            if(listIter.hasPrevious()){
                System.out.println("Previous country: " + listIter.previous().getName());
            }
            c = listIter.next();
            c = listIter.next();
            c = listIter.next();
        
            // Get carbon emission in 2015
            int year = 2015;
            ListIterator<Pair<Integer,Double>> emissionIter = c.getEmission(year);
            if(emissionIter == null){
                System.out.println("No carbon emission found for the year " + year);
            }
            else{
                System.out.println("Carbon emission of " + c.getName() + " in " + 
                               year + " = " + emissionIter.next().getSecond() + " tons");
            }
            // Get carbom emission for the 3 previous years
            emissionIter.previous();
            int index = 0;
            System.out.println("\nCarbon emission of " + c.getName() + " for the previous three years:");
            while(index < 3 && emissionIter.hasPrevious()){
                Pair<Integer,Double> pair = emissionIter.previous();
                System.out.println("\t" + pair.getFirst() + " = " + pair.getSecond() + " tons");
                index++;
            }
            // Get Carbon emission per capita in 2015
            ListIterator<Pair<Integer,Double>> capitaIter = c.getCapita(year);
            if(capitaIter == null){
                System.out.println("\nNo carbon emission per capita found for the year " + year);
            }
            else{
                System.out.println("\nCarbon emission per capita of " + c.getName() + " in " + 
                               year + " = " + capitaIter.next().getSecond() + " tons per capita");
            }
            System.out.println("\nCarbon emission per capita of " + c.getName() + " for the next three years:");
        
            // Get carbon emission per capita for the next 3 years
            index = 0;
            while(index < 3 && capitaIter.hasNext()){
                Pair<Integer,Double> pair = capitaIter.next();
                System.out.println("\t" + pair.getFirst() + " = " + pair.getSecond() + " tons per capita");
                index++;
            }
        }
        // Determine the countries with extreme carbon emissions in 2015 and 2019
        c = extremeEmission(countries, 2015, true, true);
        if(c != null)
            System.out.println("\nHighest Carbon Emission in 2015: " + c.getName() + " " + 
                                c.getEmission(2015).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2015, false, true);
        if(c != null)
            System.out.println("Lowest Carbon Emission in 2015: " + c.getName() + " " + 
                                c.getEmission(2015).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2019, true, true);
        if(c != null)
            System.out.println("\nHighest Carbon Emission in 2019: " + c.getName() + " " + 
                                c.getEmission(2019).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2019, false, true);
        if(c != null)
            System.out.println("Lowest Carbon Emission in 2019: " + c.getName() + " " + 
                                c.getEmission(2019).next().getSecond() + " tons");

        // Determine the countries with extreme carbon emissions per capita in 2015 and 2019
        c = extremeEmission(countries, 2015, true, false);
        if(c != null)
            System.out.println("\nHighest Carbon Emission (per capita) in 2015: " + c.getName() + " " + 
                                c.getCapita(2015).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2015, false, false);
        if(c != null)
            System.out.println("Lowest Carbon Emission (per capita) in 2015: " + c.getName() + " " + 
                                c.getCapita(2015).next().getSecond() + " tons");

        c = extremeEmission(countries, 2019, true, false);
        if(c != null)
            System.out.println("\nHighest Carbon Emission (per capita) in 2019: " + c.getName() + " " + 
                                c.getCapita(2019).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2019, false, false);
        if(c != null)
            System.out.println("Lowest Carbon Emission (per capita) in 2019: " + c.getName() + " " + 
                                c.getCapita(2019).next().getSecond() + " tons");
        
    }
    /**
        Read the carbon emission data from filename
        @param list where data will be stored
        @param filename where data will be read from
     */  
    public static void readFile(LinkedList<Country> list, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            String line = read.nextLine();
            String[] tokens = line.split(",");
            String country = tokens[0];
            int year = Integer.parseInt(tokens[1]);
            double tons = Double.parseDouble(tokens[2]);
            Country c = new Country(country);
            while(read.hasNextLine()){
                c.addCarbonEmission(year, tons);
                line = read.nextLine();
                tokens = line.split(",");
                String country_new = tokens[0];
                int year_new = Integer.parseInt(tokens[1]);;
                tons = Double.parseDouble(tokens[2]);
                int index = 1;
                while(country.equals(country_new) && year!=year_new && read.hasNextLine()){
                    c.addCarbonEmission(year_new, tons);
                    index++;
                    line = read.nextLine();
                    tokens = line.split(",");
                    country_new = tokens[0];
                    year_new = Integer.parseInt(tokens[1]);;
                    tons = Double.parseDouble(tokens[2]);
                }
                if(year == year_new){
                    for(int i=0; i<index; i++){
                        c.addCarbonCapita(year_new, tons);
                        if(i<index-1){
                            line = read.nextLine();
                            tokens = line.split(",");
                            country_new = tokens[0];
                            year_new = Integer.parseInt(tokens[1]);;
                            tons = Double.parseDouble(tokens[2]);
                        }
                    }
                }
                if(read.hasNextLine()){
                    list.add(c);
                    line = read.nextLine();
                    tokens = line.split(",");
                    country_new = tokens[0];
                    year_new = Integer.parseInt(tokens[1]);;
                    tons = Double.parseDouble(tokens[2]);
                    c = new Country(country_new);
                    year = year_new;
                    country = country_new;
                }
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0);
        }
    }
    /**
        Search method in the linked list
        @param ll the linked list being searched
        @param c the country searching for
        @return a ListIterator object pointing to the found country, null if no country was no found
        BIG O NOTATION: O(N)
    */
    public static ListIterator<Country> findCountry(LinkedList<Country> ll, Country c){
        //returns a ListIterator to the country found in ll, null if no country was found
       /* listIter is empty */
        int count = 0;
        int index = 0;
        ListIterator<Country> listCountry =  ll.listIterator();
        boolean boo = false;
        while(listCountry.hasNext() && (boo == false)){
            Country contry = listCountry.next();
            if (contry.getName().equals(c.getName())){ 
              boo = true;
              break;  
            }
            count++;
            index = count;
        }
        //System.out.println(index);
        if (index >= 0){ //index is less than zero?
            ListIterator<Country> listCount =  ll.listIterator(index+1); //listCount is null
            return listCount;
        } else {
            return null;
        }
    }
    /**
        Find the country with extreme carbon emission
        @param ll the linked list of countries
        @param year the year at which the extreme values are extracted
        @param minMax true for finding the highest emission, false for the lowest emission
        @param type true for carbon emissions in tons, false for carbon emission in tons per capita
        @return the country with the extreme emission at the given year
        
        BIG O NOTATION: O(N^2) ??? (bc getemission or getCapita inside while loop)
        The big O notation for extreme emissions should be O(N^2) this is because .getEmission and .getCapita 
        both iterate through the arraylist of countries once completely in the Country Class. Thus, because we 
        have a while loop which will run for the entirety of the list of countries making it o(n) complexity, we also
        call .getEmission and get.Capita inside the while loop. This effectively makes this method O(N^2) as it is basically
        iterating through the list of countries within each loop (like 2 for loops). And because minMax can either be
        true or false and not both, we consider only the code when minMax is either true or false separately and not together.
     */
    public static Country extremeEmission(LinkedList<Country> ll, int year,
                                          boolean minMax, boolean type){
        /*returns the country with the highest (minMax = true) or the lowest (minMax = false) 
        carbon emissions in tons (type = true) or carbon emission per capita (type = false) at 
        the given year.*/
        
        //iterator for emission or by capita and take first as max and you iteratom if 
        //theres anything greater 

        // highest carbon emission
        if (minMax == true){
            ListIterator<Country> iter = ll.listIterator();
            Country max = (Country) iter.next(); //first element of ll
            Country c = max;
            Country cc = max;
            ListIterator<Pair<Integer, Double>> iterMax = max.getEmission(year); //n
            ListIterator<Pair<Integer, Double>> iterCapMax = max.getCapita(year);//n
            Pair<Integer, Double> whichMax = iterMax.next(); 
            Pair<Integer, Double> whichCMax = iterCapMax.next();
            while(iter.hasNext()){ //n worst case
                max = iter.next();
                iterMax = max.getEmission(year);//n
                iterCapMax = max.getCapita(year);//n
                Pair<Integer, Double> whichMax2 = iterMax.next();
                Pair<Integer, Double> whichCMax2 = iterCapMax.next();
                if(whichMax2.getSecond() > whichMax.getSecond()){ //second
                    whichMax = whichMax2;
                    c = max; //basically keeping track of which country  is largest
                }
                if(whichCMax2.getSecond() > whichCMax.getSecond()){ //second
                    whichCMax = whichCMax2;
                    cc = max; //basically keeping track of which country  is largest
                }
            }
            
            if (type == true){ //tons
                return c;
            } else if (type == false){ //per capita
                return cc;
            }
        }
        //MIN
        else if (minMax == false){
            ListIterator<Country> iter2 = ll.listIterator();
            Country min = (Country) iter2.next(); //first element of ll
            Country c = min;
            Country cc = min;
            ListIterator<Pair<Integer, Double>> iterMin = min.getEmission(year);
            ListIterator<Pair<Integer, Double>> iterCapMin = min.getCapita(year);
            Pair<Integer, Double> whichMin = iterMin.next(); 
            Pair<Integer, Double> whichCMin = iterCapMin.next();
            while(iter2.hasNext()){
                min = iter2.next();
                iterMin = min.getEmission(year);
                iterCapMin = min.getCapita(year);
                Pair<Integer, Double> whichMin2 = iterMin.next();
                Pair<Integer, Double> whichCMin2 = iterCapMin.next();
                if(whichMin2.getSecond() < whichMin.getSecond()){ //second
                    whichMin = whichMin2;
                    c = min; //basically keeping track of which country  is largest
                }
                if(whichCMin2.getSecond() < whichCMin.getSecond()){ //second
                    whichCMin = whichCMin2;
                    cc = min; //basically keeping track of which country  is largest
                }
            }
            if (type == true){ //tons
                return c;
            } else if (type == false){ //per capita
                return cc;
            }
          
        }

        /*ListIterator iter = ll.listIterator();
        ListIterator iter2 = ll.listIterator();
        Country max =  (Country) iter.next();
        Country maxCap = (Country) iter2.next();
        ListIterator<Pair<Integer,Double>> iter1 = max.getEmission(year);
        ListIterator<Pair<Integer,Double>> iter22 = max.getEmission(year);
        ListIterator<Pair<Integer,Double>> iter1W = maxCap.getCapita(year);
        ListIterator<Pair<Integer,Double>> iter22W = maxCap.getCapita(year);

        int nextVV = iter22.next().getFirst();
        int nextWW = iter22W.next().getFirst();
        for(int i =0; i< ll.size(); i++){ //getting max for get emission
            int nextV = iter1.next().getFirst();
            nextVV = iter22.next().getFirst(); //next element
            Country m = (Country)iter.next();
                if (nextV<nextVV){
                    max = m;
                }
            int nextW = iter1W.next().getFirst();
            nextWW = iter22W.next().getFirst();
            Country mc = (Country) iter2.next();
                if (nextW<nextWW){
                    maxCap = mc;
                }
    
        }*/
        /*ListIterator iterL = ll.listIterator();
        ListIterator iter2L = ll.listIterator();
        Country min =  (Country) iterL.next();
        Country minCap = (Country) iter2L.next();
        ListIterator<Pair<Integer,Double>> iter1L = min.getEmission(year);
        ListIterator<Pair<Integer,Double>> iter22L = minCap.getEmission(year);
        
        Iterator iterL = ll.iterator();
        Iterator iterL2 = ll.iterator();
        Country min = (Country)iterL.next();
        Country minCap = (Country)iterL2.next();
        for(int i =0; i< ll.size(); i++){ //getting max for get emission
            Country nextLV = (Country) iterL.next();
            Country nextLVV = (Country) iterL2.next();
            if (max.getEmission(year)>nextLV.getEmission(year)){
                min = nextLV.getEmission(year);
            }
            if (maxCap.getCapita(year)>nextLVV.getCapita(year)){
                minCap = nextLVV.getCapita(year);
            }
        }*/
            
        
        /*
         else if (minMax == false){ //lowest carbon emission
            if (type == true){ //tons
                return min;
            } else if (type == false){ //per capita
                return minCap;
            }
        }*/
        return null;
    }
}