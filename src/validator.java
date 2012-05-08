
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
    private ArrayList row_array = new ArrayList();
    private int row_perm[][];
    
    // TODO: Constructor
    public validator(ArrayList al1, int[][] r1){
        row_array.addAll(al1);
        row_perm = r1;
}
    
    /**
     * Validate all the rows, using the validate two function for when there
     * are more than two rows. Also needs to get proper rows based on perm
     * 
     * @param row permutation of rows to check
     */
    public boolean validate_all(int row_perm_to_check, int height){
        boolean verify = true;
        
        // While Still Possible to be a valid row, continue
        for(int index = 1; index < height; index++){
            if ( validate_two(index-1,index) ){
                // Continue
            }
            // Otherwise Exit
            else{
                return false;
            }
        }
        // If it makes it through, then it is valid
        return true;
    }
    
    /**
     * Takes two arrays, and checks them for collisions in the sense of having
     * two brick ends at the same spot, validates Two Rows as being compatible,
     * via an hash table.
     * 
     * @param array1
     * @param array2
     * @return whether the rows are valid together
     */
     private boolean validate_two(int index1, int index2){
        boolean cont = true;
        
        row row1 = (row) row_array.get(index1); 
        row row2 = (row) row_array.get(index2);
        
        ArrayList hash1 = new ArrayList();
        ArrayList hash2 = new ArrayList();
        
        hash1.addAll(row1.get_hash());
        hash2.addAll(row2.get_hash());

        // for loop to see if one contains an element of the other
        for (int index = 0; index < row1.get_row_size(); index++){
            // if one does, then the bricks lineup and set is invalid
            if (hash1.contains(hash2.get(index))){
                return false;
            }
        }
        
        return true;
    }    
}
