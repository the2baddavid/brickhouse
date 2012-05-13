// Fix all output
import java.util.ArrayList;

/*
 * TODO: Test the 2 row checking
 
 * TODO: Fix validation functions
*/

/**
 *
 * @author david
 */
public class validator {
    //TODO: Add Private Fields
    private row[] row_array;
    private int height;
   
    public validator(row[] al1, double l, int h){
        row_array = al1;
        height = h;
}
    public validator(row[] al1, double l){
        row_array = al1;
}
    
    public int height_handler(){
        int temp = 0;
        
        for(int index = 0; index < row_array.length; index++){
            temp += new_roper(height, index);
        }       
        
        return temp;
    }
    
    /**
     * Recursive function which should calculate all possible combinations of
     * rows based on a given height
     * 
     * @param height
     * @param row_index
     * @return 
     */
    private int new_roper(int height, int row_index){
        int num_matches = row_array[row_index].get_match_length();
        int matches[] = row_array[row_index].get_matches();
        
        if (height > 1){
            int temp =0;
            for(int index=0; index < num_matches; index++){
                temp += new_roper(height-1, matches[index])*num_matches;                
            }
            return temp;
        }
        else{
            return num_matches;
        }
    }
    
    
    
    /**
     * for each row in row_array, find all valid rows that can follow it.  In
     * other words, for row number 5 which rows can be below it, and store the
     * matches in the row object.
     * 
     * @return void, as all the matches should be stored within the row objects
     * in the row array
     */
    public void validate_all(){
	
        for (int index1 = 0; index1 < row_array.length; index1++){
            
            int valid[] = new int[row_array.length];
            int valid_counter =0;
            
            for (int index2 = 0; index2 < row_array.length; index2++){
                if (validate_two(index1,index2)){
                    valid[valid_counter] = index2;
                    valid_counter++;
                }
            }
            
            row_array[index1].set_matches(valid, valid_counter);
        }
    }
    
    /**
     * Takes two arrays, and checks them for collisions in the sense of having
     * two brick ends at the same spot, validates Two Rows as being compatible,
     * via an hash table.
     * 
     * @param index of first array to match against
     * @param index of second array to match against
     * @return whether the rows are valid together
     */
     private boolean validate_two(int index1, int index2){
        boolean cont = true;
        
        row row1 = row_array[index1]; 
        row row2 = row_array[index2];
        
        ArrayList hash1 = new ArrayList();
        ArrayList hash2 = new ArrayList();
        
        hash1.addAll(row1.get_hash());
        hash2.addAll(row2.get_hash());
        
        // for loop to see if one contains an element of the other
        int count =0;
        for (int index = 0; index < hash1.size() ; index++){
            // if one does, then the bricks lineup and set is invalid
            if (hash2.contains(hash1.get(index))){
                count++;
            }
        }
        
        // Should fix the false positive issue with ends
        if (count > 1){
            return false;
        }
        else{
            return true;
        }
    }
}
