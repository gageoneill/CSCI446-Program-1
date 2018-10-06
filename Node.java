/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiprog1;

/**
 *
 * @author Chance
 */
public class Node {
    public String value; //string value stored, could be " ", %, P, or *
    public int h; //height position
    public int v; //vertical position
    public boolean mark;
    
    public Node(String iValue, int iV, int iH){
        value = iValue;
        h = iH;
        v = iV;
        mark = false;
    }
    
    //h and v are just to make things more convenient when figuring out where the node is.
}
