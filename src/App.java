import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    static ArrayList<Integer> plSpots =  new ArrayList<Integer>();
    static ArrayList<Integer> cpuSpots =  new ArrayList<Integer>();    
    

    public static void main(String[] args) {
        char [] [] gameBoard = {
                    {' ', '|', ' ', '|', ' '}, 
                    {'-', '+', '-', '+', '-'}, 
                    {' ', '|', ' ', '|', ' '}, 
                    {'-', '+', '-', '+', '-'}, 
                    {' ', '|', ' ', '|', ' '}};

        printgb(gameBoard);

        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enger your placement (1-9):");
            int plSpot = scan.nextInt();
            while(plSpots.contains(plSpot) || cpuSpots.contains(plSpot)){
                System.out.println("Spot taken, Enter new placement");
                plSpot = scan.nextInt();
            }
       
            markPlace(gameBoard, plSpot, "player");
            String victory = checkWin();
            if(victory.length() > 0){
                System.out.println(victory);
                break;
           }

            Random rand =  new Random();
            int cpuSpot =  rand.nextInt(9)+1;
            while(plSpots.contains(cpuSpot) || cpuSpots.contains(cpuSpot)){
                cpuSpot = rand.nextInt(9)+1;
            }
            markPlace(gameBoard, cpuSpot, "cpu");
            

            printgb(gameBoard);  

            victory = checkWin();
            if(victory.length() > 0){
                 System.out.println(victory);
                 break;
            }
           

        }

    }

    public static void printgb(char[][] gameBoard){
         for(char[] row: gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void markPlace(char [][] gameBoard, int spot, String user){
        char symbol = ' ';
        if (user.equals("player")){
            symbol = 'X';
            plSpots.add(spot);
        } else if (user.equals("cpu")){
            symbol = 'O';
            cpuSpots.add(spot);
        };
        switch(spot){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
            }
    }

    public static  String checkWin(){
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        
        List leftCol = Arrays.asList(1,4,7);
        List middleCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);

        List topLeftDiag = Arrays.asList(1,5,9);
        List topRightDiag = Arrays.asList(3,5,7);

        List<List> winValid = new ArrayList<List>();
        winValid.add(topRow);
        winValid.add(middleRow);
        winValid.add(bottomRow);
        winValid.add(leftCol);
        winValid.add(middleCol);
        winValid.add(rightCol);
        winValid.add(topLeftDiag);
        winValid.add(topRightDiag);

        for(List l: winValid){
            if(plSpots.containsAll(l)){
                return "You've Won!";
            } else if(cpuSpots.containsAll(l)){
                return "CPU Won..boo!";
            } else if(plSpots.size() + cpuSpots.size() == 9){
                return "It's a draw.";
            }
        }

        return "";
    }
}
