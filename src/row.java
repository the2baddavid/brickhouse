import java.util.ArrayList;

/**
 * Object to Hold Data For Brick House
 * @author david
 */
public class row {
    private double my_row[];
    private double my_row_length;
    private int my_array_length;
    private ArrayList sum_hash = new ArrayList();
    
    public row(double input[], double row_length, int array_length){
        my_row = input;
        my_array_length = my_row.length;
        my_row_length = get_row_sum();
        System.arraycopy(input, 0, my_row, 0, my_array_length);
        set_hash();
    }
    
    public double[] get_row(){
        return my_row;
    }
    
    public void set_row(double input[]){
        System.arraycopy(input, 0, my_row, 0, my_array_length);
    }
    
    public void set_row_piece(double input, int index){
        my_row[index] = input;
    }
    
    /**
     * Sums contents of Array, and returns answer
     * @return 
     */
    private double get_row_sum(){
        double sum = 0;
        for (int index = 0; index < my_array_length; index++){
            sum += my_row[index];
        }
        return sum;
    }
    
    public double get_row_length(){
        return my_row_length;
    }
    
    public void set_hash(){
        double sum = 0;
        
        for(int index = 0; index < my_array_length; index++){
            sum += my_row[index];
            sum_hash.add(sum);
        }
    }
    
    public ArrayList get_hash(){
        return sum_hash;
    }
}
