import java.util.ArrayList;

/**
 * Object to Hold Data For Brick House
 * @author david
 */
public class row {
    private double my_row[];
    private int my_length;
    private ArrayList sum_hash = new ArrayList();
    
    public row(double input[], int length){
        my_length = length;
        my_row = new double[my_length];
        System.arraycopy(input, 0, my_row, 0, my_length);
    }
    
    public double[] get_row(){
        return my_row;
    }
    
    public void set_row(double input[]){
        System.arraycopy(input, 0, my_row, 0, my_length);
    }
    
    public void set_row_piece(double input, int index){
        my_row[index] = input;
    }
    
    public double get_row_length(){
        double sum = 0;
        for (int index = 0; index < my_length; index++){
            sum += my_row[index];
        }
        return sum;
    }
    
    public void set_hash(){
        double sum = 0;
        
        for(int index = 1; index < my_length; index++){
            sum += my_row[index];
            sum_hash.add(sum);
        }
    }
    
    public ArrayList get_hash(){
        return sum_hash;
    }
}
