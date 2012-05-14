/**
 * Counter class is used for stop watching the runtime
 * functions exists for starting, stopping and returning runtime
 * @author david
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