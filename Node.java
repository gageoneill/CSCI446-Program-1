/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiprog1;

import java.util.Stack;

/**
 *
 * @author Chance
 */
public class Node {
    public String value; //string value stored, could be " ", %, P, or *
    public int h; //height position
    public int v; //vertical position
    //h and v are just to make things more convenient when figuring out where the node is.
    public boolean mark;
    //public String path; //the path so far.
    public Stack path; //the path so far.
    
    public Node(String iValue, int iV, int iH){
        value = iValue;
        h = iH;
        v = iV;
        mark = false;
        path = new Stack();
    }
    
    //finds manhattan distance between two points.
    public double distance(int eV, int eH){
        double output = 0;
        
        //manhattan distance
        output = Math.abs(v - eV) + Math.abs(h - eH);
        
        return output;
    }
}
