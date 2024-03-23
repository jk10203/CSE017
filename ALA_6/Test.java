/***
 * Class to test stacks and printrequest
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 20, 2023
 *
 * This program was made in order to use stack properties and use the printrequest class to format
 * information from a file.
 */
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    // part 1
    public static void main(String[] args){
        //create stack that will hold numbers
        Stack<Integer> postfixStack = new Stack<>(); //create empty stack
        Scanner input = new Scanner(System.in);
        boolean menuC = true;
        do{
            System.out.println("Enter a postfix expression: ");
            String postfix = input.nextLine();
            //split it using space
            String[] tokens = postfix.split(" ");
            try{
            for(String token: tokens){
                //know if token is number of operation
                if(token.matches("\\d{1,}")){ //one or more digits
                    int operand = Integer.parseInt(token); //pass it into integer
                    postfixStack.push(operand);
                }
                else{ //if not a number, then operation
                    int operand1 = postfixStack.pop();
                    int operand2 = postfixStack.pop();
                    switch(token){
                        case "*":
                            postfixStack.push(operand1 * operand2);
                            break;
                        case "+":
                            postfixStack.push(operand1 + operand2);
                            break;
                        case "-":
                            //operand2 - operand1 bc diff format (for div and sub)
                            postfixStack.push(operand2 - operand1);
                            break;
                        case "/":
                            postfixStack.push(operand2 / operand1);
                            break;
                        default:
                            //System.out.println("Invalid operation in the postfix expression");
                            //replace above with
                            throw new NoSuchElementException();
                    }
                }
            }
            //pop result
            int result = postfixStack.pop();
            if(postfixStack.isEmpty()){
                System.out.println("Result: " + result);
            } else {
                //malformed
                System.out.println("Malformed postfix expression");
            }

            System.out.println("\nDo you want to evaluate another postfix expression? (yes/no): ");
            String choice = input.next();
            switch (choice){
                case "yes":
                    String garbage = input.nextLine();
                    menuC = true; //HOW TO MAKE THIS WORK
                    postfixStack.clear();
                    break;
                case "no" :
                    menuC = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    menuC = false;
            }
                
            } catch (NoSuchElementException e){
                System.out.println("Malformed postfix expression");
            }
        } while (menuC);

//part 2:Using PriorityQueue
    PriorityQueue<PrintRequest> pQueue = new PriorityQueue<>();
    File file = new File("requests.txt");
    try{
        Scanner read = new Scanner(file);
        while (read.hasNext()){
            int id = read.nextInt();
            String group = read.next();
            long size = read.nextLong();
            PrintRequest pr = new PrintRequest(id,group,size);
            pQueue.offer(pr);
        }
        read.close();
    } catch (FileNotFoundException e){
        System.out.print("File not found");

    }
    double printSpeed = 10000;
    double totalTime = 0;
    System.out.println("\nSummary of the printed requests\n---------------------------------------------------------------");
    System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\n", "User ID", "Group", "Size", "Completion time");
    while(!pQueue.isEmpty()){
        PrintRequest pr = pQueue.poll();
        double time = pr.getSize()/printSpeed;//gives time to print request pr
        totalTime += time;
        System.out.printf("%s%s\n", pr, formatTime(time));
    }
    System.out.println("Total Printing Time: " + formatTime(totalTime));
    }
    
    /***
	 * Method to format the time based on printing speed
	 * @param time for the time it takes to print
	 * @return a string that formats the time in d:h:m:s
	 */
    public static String formatTime(double time){ //notworking
        int timeDay = 0;
        int timeHourI= 0;
        int timeMinI = 0;
        int timeSecI = 0;
        double timeSec = (time);
        double timeMin = (timeSec / 60);
        double timeHour = (timeMin / 60);
        double timeDayD = (timeHour / 24);
        if (timeDayD>1){
            timeDay = (int) timeDayD;
            timeHour = timeHour - (timeDay*24);
            timeMin = timeHour*60;
        }
        if (timeHour >1 && timeHour < 24){
            timeHourI = (int) timeHour;
            timeMin = (timeMin) - (timeHourI*60);
            timeSec = timeMin*60;
        }
        if (timeMin > 1 && timeMin < 60){
            timeMinI = (int) timeMin;
            timeSec = timeSec - (timeMinI*60);
        }
        if (timeSec < 60){
            timeSec = 0.5 + timeSec;
            timeSecI = (int) timeSec;
        }
        return String.format("%02d:%02d:%02d:%02d", timeDay, timeHourI, timeMinI, timeSecI);
    }
}
