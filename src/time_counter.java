/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brickhouse;

/**
 * Counter class is used for stop watching the runtime
 * functions exists for starting, stopping and returning runtime
 * @author david
 */
class time_counter{
    
    double start;
    double finish;
    
    public void start(){
        start = System.currentTimeMillis();
    }
    
    public void end(){
        finish = System.currentTimeMillis();
    }
    
    public double runTime(){
        return finish-start;
    }    
}