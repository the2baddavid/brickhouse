/**
 * Counter class is used for stop watching the runtime
 * functions exists for starting, stopping and returning runtime
 *
 * @author david
 *
 * ~~~	Data Members ~~~
 * start -- Time that the program started (system time)
 * finish -- Time that the program finished (System time)
 *
 * ~~~	Functions ~~~
 * start() -- gets system time and sets the start var
 * end() -- gets the system time and sets the finish var
 * runTime() -- computes program runtime by subtracting finish time from start time
 *
 */
class time_counter{
    
    double start;
    double finish;
    
    /**
     * Start time - Saves current time
     */
    public void start(){
        start = System.currentTimeMillis();
    }
    
    /**
     * Stop time - Saves current time
     */
    public void end(){
        finish = System.currentTimeMillis();
    }
    
    /**
     * Begets runtime by subtracting the start and end times
     * @return 
     */
    public double runTime(){
        return finish-start;
    }    
}
