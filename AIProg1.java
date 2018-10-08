/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiprog1;

import java.io.*;
import java.util.Stack;

public class AIProg1 {
    private static Node board[][];
    
    
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("C:\\Users\\Chance\\Documents\\NetBeansProjects\\AIProg1\\src\\aiprog1\\openmaze.txt");
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

        //greedyBest(board);
        depthFirst(board);
        
        //This prints the maze, used for debugging.
        for(int i = 0; i < board.length - 1; i++){
            for(int j = 0; j < board[0].length - 1; j++){
                System.out.print(board[i][j].value);
            }
            System.out.println();
        }
    }

    public static void aStar(Node[][] board){
        //f(n)= g(n)+ h(n)
	//
	//
	
    }
    
    public static void depthFirst(Node[][] board){
        //expand deepest unexpanded node
        //Last in, First out.
        
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
        
        Stack frontier = new Stack();
        frontier.push(board[curV][curH]);
        
        int checker = 0;
        boolean done = false;
        while(!done){
            checker++;
            //board[i-1][j] above
            //board[i][j+1] right
            //board[i+1][j+1] below
            //board[i][j-1] left
            
            Node current = (Node)frontier.pop();
            current.mark = true;
            
            /*
            if(!current.value.equals("*") && !current.value.equals("P")){
                current.value = ".";
            }
            */
            
            if(!current.value.equals("*")){
                curV = current.v;
                curH = current.h;
                
                if(checkViable(board, curV + 1, curH)){
                    board[curV+1][curH].path = current.path;
                    board[curV+1][curH].path.push(current);
                    frontier.push(board[curV+1][curH]);
                }
                
                if(checkViable(board, curV, curH + 1)){
                    board[curV][curH+1].path = current.path;
                    board[curV][curH+1].path.push(current);
                    frontier.push(board[curV][curH+1]);
                }
                
                if(checkViable(board, curV - 1, curH)){
                    board[curV-1][curH].path = current.path;
                    board[curV-1][curH].path.push(current);
                    frontier.push(board[curV-1][curH]);
                }
                
                if(checkViable(board, curV, curH - 1)){
                    board[curV][curH-1].path = current.path;
                    board[curV][curH-1].path.push(current);
                    frontier.push(board[curV][curH-1]);
                }
            }
            else{
                while(!current.path.empty()){
                    Node temp = (Node)current.path.pop();
                    temp.value = ".";
                }
                
                done = true;
            }
            
            if(frontier.empty() || checker > 10000){
                done = true;
            }
            
            //done = true;
        }
    }
        
        
    public static void breadthFirst(Node[][] board){
        //expand shallowest unexpanded node
        //First in, First out.
	
	//find starting point
	List<Node> queue = new ArrayList<Node>();
	//queue.add starting point
	/*
	while(!queue.isEmpty()) {
	    Node curr = queue.remove(0);
	    if(goal check)
	    break;

	    board[curr.x, curr.y] = visited;
	    List<Node> neighbors = getNeighbors(board, curr);
	    queue.addALL(neighbors);
	}

	return ...
	
	*/
    }
    public static List<Node> getNeighbors(Node[][] board, Node node) {
	List<Node> neighbors = new ArrayList<Node>();
	//this method needs to find neighbors, ask chance how to go up right and left, looks like he was working on too
	//ints for now
	//left
	if(isOnBoard(board, node.x - 1, node.y)) {
	    neighbors.add(new Node(node.x - 1, node.y));
	}
	//right
	if(isOnBoard(board, node.x + 1, node.y)) {
            neighbors.add(new Node(node.x - 1, node.y));
        }
	//down
	if(isOnBoard(board, node.x, node.y - 1)) {
            neighbors.add(new Node(node.x - 1, node.y));
        }
	//up
	if(isOnBoard(board, node.x, node.y + 1)) {
            neighbors.add(new Node(node.x - 1, node.y));
        }
	return neighbors;
    }

    public static boolean isOnBoard(Node[][] board, int x, int y) {
	// TODO method to make sure node is on board
	return true;
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
        
        int checker = 0;
        while(!done){
            checker++;
            
            board[curV][curH].mark = true;
            if(!board[curV][curH].value.equals("P")){
                board[curV][curH].value = ".";
            }
            
            Node winningNode = board[curV][curH];
            double curValue = 99999;
            //System.out.println("current: " + curValue);
            double checkDist = 0;

            if(curV < board.length - 1){
                if(!board[curV + 1][curH].value.equals("%") && !board[curV + 1][curH].mark){
                    checkDist = distance(curV + 1, curH, endV, endH);
                    System.out.println("down: " + checkDist);
                    if(curValue > checkDist){
                        curValue = checkDist;
                        winningNode = board[curV + 1][curH];
                        //System.out.println("down");
                    }
                    
                    if(curValue == checkDist){
                        int temp = (int)(Math.random() * 10 + 1);
                        if(temp > 5){
                            winningNode = board[curV + 1][curH];
                        }
                    }
                }
            }
            
            if(curH < board[0].length - 1){
                if(!board[curV][curH + 1].value.equals("%") && !board[curV][curH + 1].mark){
                    checkDist = distance(curV, curH + 1, endV, endH);
                    System.out.println("right: " + checkDist);
                    if(curValue > checkDist){
                        curValue = checkDist;
                        winningNode = board[curV][curH + 1];
                        //System.out.println("right");
                    }
                    
                    if(curValue == checkDist){
                        int temp = (int)(Math.random() * 10 + 1);
                        if(temp > 5){
                            winningNode = board[curV][curH + 1];
                        }
                    }
                }
            }
            
            if(curV > 0){
                if(!board[curV - 1][curH].value.equals("%") && !board[curV - 1][curH].mark){
                    checkDist = distance(curV - 1, curH, endV, endH);
                    System.out.println("up: " + checkDist);
                    if(curValue > checkDist){
                        curValue = checkDist;
                        winningNode = board[curV - 1][curH];
                        //System.out.println("up");
                    }
                    
                    if(curValue == checkDist){
                        int temp = (int)(Math.random() * 10 + 1);
                        if(temp > 5){
                            winningNode = board[curV - 1][curH];
                        }
                    }
                }
            }
            
            if(curH > 0){
                if(!board[curV][curH - 1].value.equals("%") && !board[curV][curH - 1].mark){
                    checkDist = distance(curV, curH - 1, endV, endH);
                    System.out.println("left: " + checkDist);
                    if(curValue > checkDist){
                        curValue = checkDist;
                        winningNode = board[curV][curH - 1];
                        //System.out.println("left");
                    }
                                        
                    if(curValue == checkDist){
                        int temp = (int)(Math.random() * 10 + 1);
                        if(temp > 5){
                            winningNode = board[curV][curH - 1];
                        }
                    }
                }
            }

            curV = winningNode.v;
            curH = winningNode.h;
            
            //System.out.println("v: " + winningNode.v + " h: " + winningNode.h);
            
            if(board[curV][curH].value.equals("*")){
                done = true;
            }
            
            if(checker > 10000){
                done = true;
                //System.out.println((int)(Math.random() * 10));
            }
            
            //done = true;
        }
    }
     
    public static boolean checkViable(Node[][] board, int v, int h){
        if(v < board.length - 1 && h < board[0].length - 1 && v > 0 && h > 0){
            if(!board[v][h].mark && !board[v][h].value.equals("%")){
                return true;
            }
        }
        
        return false;
    }
     
    //finds manhattan distance between two points.
    public static double distance(int sV, int sH, int eV, int eH){
        double output = 0;
        
        //manhattan distance
        output = Math.abs(sV - eV) + Math.abs(sH - eH);
        
        return output;
    } 

}
