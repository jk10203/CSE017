import java.util.ArrayList;
import java.util.Scanner;

import EXAMPREP3.HashMapEntry;

import java.io.File;
import java.io.FileNotFoundException;

public class Test{
    public static void main(String[] args) {
        StringComparator sc = new StringComparator();
        HashMapLP<String, String> hmLP = new HashMapLP<>(100000);
        HashMapSC<String, String> hmSC = new HashMapSC<>(100000);
        TreeMap<String, String> treeM = new TreeMap<>(sc);
        ArrayList<HashMapEntry<String, String>> hashbrown = new ArrayList<>(100000);
        ArrayList<MapEntry<String, String>> mapbrown= new ArrayList<>(100000);
        ArrayList<HashMapEntry<String, String>> hashbrown2 = new ArrayList<>(100);
        ArrayList<MapEntry<String, String>> mapbrown2 = new ArrayList<>(100);
       
        readEmails(hashbrown, mapbrown, "emails.txt");
       for(int i = 0; i< hashbrown.size(); i++){
            MapEntry<String, String> mapel = mapbrown.get(i);
            HashMapEntry<String, String> hashel = hashbrown.get(i);


            hmLP.put(mapel.getKey(), mapel.getValue()); //store the word only
            treeM.add(mapel.getKey(), mapel.getValue());
            hmSC.put(hashel.getKey(), hashel.getValue());

        }

       //hashbrown.clear();
        //mapbrown.clear();
        readMail(hashbrown2, mapbrown2, "mailingList.txt");

        int counter = 0;
        int totalHLP = 0;
        int totalHSC = 0; 
        int totalTM = 0;
       
        System.out.println("Testing get()");
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Username", "Tree Map", "HashMapSC", "HashMapLP");

        for(int i =0; i <hashbrown2.size(); i++ ){
       // while (hashbrown != null && mapbrown != null){
            String lime = hashbrown2.get(i).getKey();
            String lemon = mapbrown2.get(i).getKey();
            hmLP.get(lemon);
            hmSC.get(lime);
            treeM.get(lemon);
            //totalHLP += HashMapLP.iterations;
            //totalHSC += HashMapSC.iterations;
            //totalTM += TreeMap.iterations;
            if(i < 20){
                System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", lime, TreeMap.iterations, HashMapSC.iterations, 
                HashMapLP.iterations);
            }
        }

        //searching

    }
    //public boolean searchEmail(MapEntry<String, String> m1, MapEntry<String, String> m2){

    //}
    public static void readEmails(ArrayList<HashMapEntry<String, String>> hmeA,ArrayList<MapEntry<String, String>> meA, String filename){
        File file = new File (filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] stuff = line.split(" ");
                String email = stuff[0];
                String password = stuff[1];
                HashMapEntry<String, String> mo = new HashMapEntry<>(email, password);
                MapEntry<String, String> me = new MapEntry<> (email, password);
                hmeA.add(mo);
                meA.add(me);
                /*hmLP.put(email, password);
                //hmSC = (new HashMapEntry<String, String>(email, password));
                hmSC.put(email, password);
                treeM.add(email, password);*/
            }
            read.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public static void readMail(ArrayList<HashMapEntry<String, String>> hmeA,
                                ArrayList<MapEntry<String, String>> meA, String filename){
        File file = new File (filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] stuff = line.split(" ");
                String email = stuff[0];
                String password = stuff[1];
                HashMapEntry<String, String> mo = new HashMapEntry<>(email, password);
                MapEntry<String, String> me = new MapEntry<> (email, password);
                hmeA.add(mo);
                meA.add(me);
            }
            read.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}