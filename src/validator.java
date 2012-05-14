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
 * reverse_lookup() - 
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
    
    public long height_handler(){
        long temp = 0;
        reverse_lookup();
        
        // TODO: Swap out new_roper for reverse_lookup
        for(int index = 0; index < row_array.length; index++){
            // Note this is the first row!
            temp += row_array[index].get_height_hash();
        }       
        
        return temp;
    }
    
    /**
     * Reverse lookup -- Function attempts to build the number of possibles
     * from the ground up, by loop through each and adding its respective children,
     * and then doing this again to attempt at removing redundant computations.
     */
    private void reverse_lookup(){
        // Keep rehashing height for all functions until correct height is
        // attained. Keep in mind that the height is already computed to 1,
        // so the first one computed will be height 2
        for(int i1 = 3; i1 < height; i1++){
            // For each Row
            for(int i2 = 0; i2 < row_array.length; i2++){
                // Rolling Sum of the weights that combine to make the new weight for row i2
                long sum = 0;
                
                // Holds the matches of the current row
                int matches[] = row_array[i2].get_matches();
                // Look up and sum the weights of the rows that follow
                for(int i3 = 0 ; i3 < matches.length ; i3++){
                    sum += row_array[matches[i3]].get_height_hash();
                }
                // The sum becomes the new weight for the current hash, i2, and
                // the new height increments, i1
                row_array[i2].set_height_hash(sum,i1);
                // Increment height of hash, to reflect the height of the new weight
            }
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
            row_array[index1].set_height_hash(valid_counter,2);
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
                
                // if at any point the count is greater than 1, then the row is invalid;
                if (count > 1){
                    return false;
                }
            }
        }
        return true;
    }
}
