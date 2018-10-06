/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiprog1;

import java.io.*; 

public class AIProg1 {
    private static Node board[][];
    
    
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("C:\\Users\\Chance\\Documents\\NetBeansProjects\\AIProg1\\src\\aiprog1\\largemaze.txt");
        BufferedReader br = new BufferedReader(fr);
        
        br.mark(10000); //this lets us reset the buffered reader so we can go through a bunch of times.
        String temp = br.readLine();
        
        int horizontal = temp.length(); //horizontal dimension is just the length of one line
        int vertical = 0;
        while(temp != null){ //calculates the vertical dimension of the maze
            vertical++;
            temp = br.readLine();
        }
        
        br.reset(); //sets reader back to start
        
        vertical++; 
        horizontal++;
        
        //fill the board with values
        Node board[][] = new Node[vertical][horizontal]; 
        for(int i = 0; i < vertical; i++){
            for(int j = 0; j < horizontal; j++){
                String newValue = (char)br.read() + "";
                
                if(!newValue.equals("%") && !newValue.equals(" ") && !newValue.equals("P") && !newValue.equals("*")){ //skips newline character
                    newValue = (char)br.read() + "";
                }
                
                board[i][j] = new Node(newValue, i, j);
                //System.out.print(board[i][j].value);
            }
            //System.out.println();
        }
        
        //This prints the maze, used for debugging.
        
        /*
        for(int i = 0; i < board.length - 1; i++){
            for(int j = 0; j < board[0].length - 1; j++){
                System.out.print(board[i][j].value);
            }
            System.out.println();
        }
        */
        
        greedyBest(board);
    }

    public static void aStar(Node[][] board){
        //f(n)= g(n)+ h(n)
    }
    
    public static void depthFirst(Node[][] board){
        //expand deepest unexpanded node
        //Last in, First out.
    }
        
        
    public static void breadthFirst(Node[][] board){
        //expand shallowest unexpanded node
        //First in, First out.
    }
            
     public static void greedyBest(Node[][] board){
        //find start point 
        int curH = 0;
        int curV = 0;
        for(int i = 0; i < board.length - 1; i++){
            for(int j = 0; j < board[0].length - 1; j++){
                if(board[i][j].value.equals("P")){
                    curH = j;
                    curV = i;
                }
            }
        }
        
        int endH = 0;
        int endV = 0;
        for(int i = 0; i < board.length - 1; i++){
            for(int j = 0; j < board[0].length - 1; j++){
                if(board[i][j].value.equals("*")){
                    endH = j;
                    endV = i;
                }
            }
        }
         
        //h(n)
        //expand the node that has the lowest cost
        
        boolean done = false;
        
        //board[i-1][j] above
        //board[i][j+1] right
        //board[i+1][j+1] below
        //board[i][j-1] left
        
        while(!done){
            board[curV][curH].mark = true;
            
            
            
            done = true;
        }
    }
     
    //finds euclidean distance between two points.
    public static double distance(int sH, int sV, int eH, int eV){
        double output = 0;
        
        //manhattan distance
        output = Math.abs(sV - eV) + Math.abs(sH - eH);
        
        return output;
    }
}
