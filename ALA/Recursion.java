/***
 * Test Class based on recursion and file/folder finding
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 23, 2023
 * Last Date Modified: February 23, 2023
 *
 * This program was made in order to utilize folder and file properties by looking for a file/folder,
 * a word that occurs in the file, and the size of the file.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Recursion{
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a folder: ");
        String folder = input.nextLine();

        System.out.println("Enter a filename: ");
        String filename = input.nextLine();
        //example is /home/gak425/CSE17/ALA_4

        String found = findFile(folder, filename);
        if(found.equals("")){
            System.out.println("File not found");
        }
        else{
            System.out.println("File found at " + found);
        }

        System.out.println("Enter a folder: ");
        folder = input.nextLine();
        long size = getSize(folder);
        if(size == 0){
            System.out.println("Folder was not found");
        } else {
            if(size > 0 && size < 1000){
                System.out.println("Size: " + (size) + " bytes"); //put conditions to differentiate mega kil giga - bytes
            }
            if(size >=1000 && size < 1000000){
                System.out.println("Size: " + (size/1000.0) + " kilobytes"); //put conditions to differentiate mega kil giga - bytes
            }
            if(size >=1000000 && size < 1000000000){
                System.out.println("Size: " + (size/1000000.0) + " megabytes"); //put conditions to differentiate mega kil giga - bytes
            }
            else{
                System.out.println("Size: " + (size/1000000000.0) + " gigabytes"); //put conditions to differentiate mega kil giga - bytes
            }
            //example: /home/gak425/CSE17/ALA_4/Recursion.java
        }

        System.out.println("Enter a folder: ");
        folder = input.nextLine();

        System.out.println("Enter a word: ");
        String word = input.nextLine();
        findWord(folder, word);


       /* File file = new File (folder); //creates file object to access properties of folder
        if (!file.exists()){ //if file associated w obj does NOT exist
            System.out.println("Folder not found");
        }
        if (file.isFile()){ //asks, is textfile? / folder is the name of a file, not a folder
            System.out.println(folder + " is a filename, not a folder");
        }
        if (file.isDirectory()){//it is directory
            System.out.println(folder + " found");
            File[] files = file.listFiles();//returns list of files and folders as an array of file objects
            for(int i =0; i<files.length(); i++){
                if(files[i].isFile()){
                    System.out.println("File: " + files[i].getAbsolutePath());
                }
                if(files[i].isDirectory()){
                    System.out.println("Subfolder: " + files[i].getAbsolutePath());
                }
            }
        }*/

    }
    /***
	 * Method to find if a file exists
     * @param   folder for the name of the folder to be searched
	 * @param   filename for the file to be searched
     * @param   word for the word to search for
	 * @return if it was found or not
	 */
    public static String findFile(String folder, String filename){
        //recursively go into sub folders (hierarchy)
        //to access files/folders we use class file (file object)
        File file = new File (folder);
        if(!file.exists()){
            return "";
        }
        if(file.isFile()){
            return "";
        }
        if (file.isDirectory()){ //it is a folder
            File[] files = file.listFiles(); //getting the contents of the folder
            for (int i =0; i<file.length(); i++){
                if(files[i].isFile()){ //it is a file
                    if (files[i].getName().equals(filename)){
                        return files[i].getAbsolutePath();
                    }
                }
                if(files[i].isDirectory()){ //it is a folder
                    //RECURSIVE CALL
                    String found = findFile(files[i].getAbsolutePath(), filename); //recursive case
                    //if it returns an empty string = we did not find it (in the whole hierarchy of that folder) 
                    //--> need to go to next folder
                    if (!found.equals("")){
                        return found;
                    }       
                }
            }
        }
        return ""; //to silence
    }
    /***
	 * Method to search for a word in a final
	 * @param   folder for the folder to be searched
	 * @return the length of the file
	 */
    public static long getSize(String folder){
        File file = new File(folder);
        long size = 0;
        if (!file.exists()){
            return 0;
        }
        if (file.isFile()){
            return file.length(); //returns the size of the file
        }
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i =0; i < files.length; i++){
                if (files[i].isFile()){
                    size += files[i].length();
                }
                if (files[i].isDirectory()){ //actually subfolder
                    //RECURSIVE PART --> returns size of folder and adds
                    size += getSize(files[i].getAbsolutePath());
                }
            }
        }
        return 0; // to silence
    }
    /***
	 * Method to search for a word in a folder of files
	 * @param   folder for the folder to be searched
     * @param   word for the word to search for
	 * no return value
	 */
    public static void findWord(String folder, String word){
        File file = new File(folder);
        if(!file.exists()){
            System.out.println("Folder or File not found.");
        }
        if (file.isFile()){ //is a file
            int count = searchWord(file.getAbsolutePath(), word); //look for word inside file
            if ( count !=0){
                System.out.println(word + " appears " + count + "times in " + file.getAbsolutePath());
            }
        } if (file.isDirectory()){
            File[]files = file.listFiles();
            for(File f: files){
                if (f.isFile()){
                    searchWord(file.getAbsolutePath(), word);
                    int count = searchWord(file.getAbsolutePath(), word); //look for word inside file
                    if ( count !=0){
                        System.out.println(word + " appears " + count + "times in " + file.getAbsolutePath());
                    }
                }
                if (f.isDirectory()){
                    findWord(f.getAbsolutePath(), word); //recursive case
                }
            }
        }
    }
    /***
	 * Method to search for a word in a file
	 * @param   filename for the file to be searched
     * @param   word for the word to search for
	 * @return the number of times that word appears
	 */
    public static int searchWord(String filename, String word){
        File file = new File(filename);
        int count  = 0;
        try{
            Scanner read = new Scanner(file);
            while (read.hasNextLine()){ 
                String line = read.nextLine();
                //looking for anywhere the word appears (ex. text --> testable / testing)
                //contains --> returns true but need how many
                //you can split using "test" and subtract 1 from it maybe(?) --> not alwatys accurate for word appearing at end of line
                //you can use indexOf
                int index = line.indexOf(word); //first occurrence of word
                while (index != -1){ //-1 did not find the word
                    count++;
                    index = line.indexOf(word, index+1); //looking for next occurrence
                }

            }
            read.close();
        }
        catch(FileNotFoundException e){
            return 0; 
        }
        
        return count; 
    }
}