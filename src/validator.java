import java.util.ArrayList;

/*****************************  Validator   ************************************
********************************************************************************
 * Validator is used to attain correct number of possible sets, by knowing the
 * number of valid rows, which rows can follow them, and what the desired height
 * of the puzzle is.
 * 
 * ~~~~~~~ Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * row[] row_array - holds all of the rows of correct length
 * height - the height of the puzzle
 * 
 * ~~~~~~~ Functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * validator() - constructor, that simply copies the height and the rows in
 * 
 * height handler - loops through each row, passing it into the new_roper
 * function, which will use height for how often to recurse.  The loop also sums
 * all the returned answers, which are the number of possible solutions.
 * 
 * new_roper() - Function that recurses based on height.  The function 
 * returns the number of possible solutions for each row, which is multiplied
 * by the number each respective row has of possible solutions based on the row
 * number.
 * 
 * validate_all() - Purpose of the function is to find out which rows can
 * legally follow a given row.  The function does this by way of calling the
 * validate_two function and tracking the results.  It then takes the results
 * and copies them into each respective row for use by the new_roper function.
 * 
 * validate_two() - Function takes in two indexes, which belong to rows in the
 * row array.  The function then pulls each of their respective hashes that
 * contain the brick sums in them and checks for collisions, with only one being
 * allowed which will be the row length.
 * 
 * 
*******************************************************************************/

/**
 * Used for attaining number of correct responses
 * @author david
 */
public class validator {
    
    private row[] row_array;
    private int height;
   
    public validator(row[] al1, int h){
        row_array = al1;
        height = h;
}
    
    public int height_handler(){
        int temp = 0;
        
        for(int index = 0; index < row_array.length; index++){
            // Note this is the first row!
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
        
        // Checks Rows Beyond the second
        if (height > 2){
            int temp = 0;
            for(int index=0; index < num_matches; index++){
                temp += new_roper(height-1, matches[index]);                
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
        
        // check hash2 for elements in hash1
        int count =0;
        for (int index = 0; index < hash1.size() ; index++){
            // if one does, then the bricks lineup and set is invalid
            if (hash2.contains(hash1.get(index))){
                count++;
            }
        }
        
        // check hash1 for elements in hash2
        for (int index = 0; index < hash2.size() ; index++){
            // if one does, then the bricks lineup and set is invalid
            if (hash1.contains(hash2.get(index))){
                count++;
            }
        }
        
        // Unless there are only 2 collisions, either the rows are wrong length
        // or they line up on the same brick
        if (count != 2){
            return false;
        }
        else{
            return true;
        }
    }
}
