package EXAM2;

import java.util.Scanner;

import Classroom;
import Lab;
import Room;

import java.io.FileNotFoundException;
import java.io.File; 
public class Test{
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        Room[] rooms = new Room[100];
        int count = 0;
        count = readFromFile(rooms, "rooms.txt");
        boolean menuOp = true;
        int choice = 0;
        String roomNumIn = "";

        do{
            try{

                System.out.println("Please choose from the following operations: ");
                System.out.println("1. find a room");
                System.out.println("2. view all rooms");
                System.out.println("3. sort rooms");
                System.out.println("4. Exit the program");
                
                choice = getChoice(input);

                switch(choice){
                    case 1:
                        System.out.println("Please enter a room number (XX-###): ");

                        String garbage = input.nextLine(); //to clear the menu input from before?

                        roomNumIn = input.nextLine(); //getting description from user input
                        System.out.println(" ");//formatting
                        int index = findRoom(rooms, count, roomNumIn); //the return for this method is the index so setting a variable outside of method as that number
                        
                        //checking index values outside of method
                        if (index != -1){ //if index is a number other than -1 --> found
                            System.out.println("Room found: " + rooms[index] + " ");
                        } else if (index == -1){ //-1 is not found (initialized number)
                            System.out.println("Room not found.");
                        }
                        System.out.println(" ");
                        break;
                    case 2:
                        System.out.println(String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t","Type", "Number", "Capacity", "Area", "Owner/Computers"));
                        //20 for desciription and 30 for location because of the long length of the strings that are classified as such
                        printRooms(rooms, count);
                        System.out.println(" "); //for formatting
                        break;

                    case 3:
                        System.out.println("Please enter a room number (XX-###): ");
                        garbage = input.nextLine(); //to clear the menu input from before?
                        roomNumIn = input.nextLine();
                        checkRoomNumber(roomNumIn); //getting description from user input
                        System.out.println(" ");//formatting

                        Room[] foundList = new Room[100]; //in initializing another event array in order to store return array from method
                        foundList = findRooms(rooms, count, roomNumIn); //gives array
                        int counter =0;
                        int counterRoom = 0;
                        for (int i = 0; i<10; i++){ 
                            if (foundList[i] != null){
                                counterRoom++;
                                //going through to see how many rooms were found by using counter
                            }
                        }

                        for (int i = 0; i<counterRoom; i++){
                            if (foundList[i] != null){
                                if (counter == 0 && counterRoom > 1){//to make sure that it doesn't enter this if statement after the first time (using counter)
                                    System.out.println(counterRoom+ " rooms found at " + roomNumIn + "!");//FIXXXXX
                                    System.out.println(String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t","Type", "Number", "Capacity", "Area", "Owner/Computers"));
                                    counter++; 
                                } else if (counter == 0 && counterRoom == 1){ //if only 1 event was found (for grammar issues)
                                    System.out.println(counterRoom + " room found at " + roomNumIn + "!");
                                    System.out.println(String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t","Type", "Number", "Capacity", "Area", "Owner/Computers"));
                                    counter++; //utilizing the same counter as before if statement to make sure only one or the other is entered ONCE
                                }
                                System.out.println(foundList[i] + " "); //displaying the events that are in the array
                            } 
                        }
                        if (counter == 0){ //if the counter is never incremented, means no event was found
                            System.out.println("Room not found.");
                        }
                        System.out.println(" ");
                            
                        break;

                    case 4:
                        System.out.println("Exitting...");
                        menuOp = false; //exits out of loop
                        break; //exits out of switch statement
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(" ");
            }

        } while (menuOp);

    }


    public static void printRooms(Room[] list, int count){
        for(int i = 0; i < count; i++){ //maybe fix?
            if(list[i] == null){
                break;
            }
            System.out.println(list[i] + " ");
        }
    }

    public static int findRoom(Room[] list, int count, String roomNumber){
        int index = -1;
        for(int i = 0; i<count; i++){
            if (roomNumber.compareTo(list[i].getNumber()) == 0){
                index = i;
            }
        }
        return index;
    }
    public static Room[] findRooms(Room[] list, int count, String roomNumber){ //finding by date
        int j = 0;
        Room[] foundList = new Room[100]; //to store the multiple events that are at a date
        for (int i = 0; i<count; i++){
            if (roomNumber.compareTo(list[i].getNumber().toString()) == 0 ){
                foundList[j] = list[i];
                j++;
            } 
        }
        
        return foundList;
    }
    public static void checkRoomNumber(String roomNumber) throws Exception{
        //char[] chars = roomNumber.toCharArray();
        /*for(char c : chars){
            if(!Character.isLetter(c)){
                throw new Exception(""); //change
            }
        }*/
        if (!roomNumber.matches("[A-Z][A-Z][-][1-9][1-9][1-9])")){
            throw new Exception("Invalid room number (XX-###). Please try again.");
        }
    }
    public static int readFromFile(Room[] list, String filename){
        int numRooms = 0;
        File file = new File(filename);
        try{ 
            Scanner fileScanner = new Scanner(file);
            
            while(fileScanner.hasNextLine()) { //while textfile has data to be read run this loop
                //common values for rooms
                String type = fileScanner.nextLine();
                String number = fileScanner.nextLine();
                int capacity = Integer.parseInt(fileScanner.nextLine());
                int area = Integer.parseInt(fileScanner.nextLine());
                if(type.equalsIgnoreCase("office")){ //unique values to appointment
                    String owner = fileScanner.nextLine();
                    list[numRooms] = new Office (number, capacity, area, owner);
                    numRooms++;
                }
                else if(type.equalsIgnoreCase("lab")){ //unique values to meeting
                    int computers = Integer.parseInt(fileScanner.nextLine()); //bc int
                    list[numRooms] = new Lab (number, capacity, area, computers);
                    numRooms++;
                }
                else if(type.equalsIgnoreCase("classroom")){ //unique values to meeting
                    list[numRooms] = new Classroom (number, capacity, area);
                    numRooms++;
                }
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e){ //if file is not found
            System.out.println("Cannot open file \"rooms.txt\""); 
        }           
        return numRooms;

    }
    
    public static int getChoice(Scanner sc){ //method to input validate :D
        int choice;
        int choice2 = 0;
        boolean inVal = true;
        do{
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                if (choice>0 && choice<6){
                    choice2 = choice;
                    inVal = false;
                    break;
                } else {
                    System.out.println("Please enter a valid option. >:(");
                    sc.nextLine();
                }
            } else {
                System.out.println("Please enter a valid option. >:(");
                sc.nextLine();
            }
        } while (inVal);
        return choice2;
    }
}
