/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_search;

/**
 *
 * @author Asadullah Monsur
 */
public class Scheduling implements Runnable {

    int ar[][];
    int x;
    int y;
    
    public Scheduling(int a[][],int x, int y) {
    ar = a;
    this.x = x;
    this.y=y;

    }

    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
