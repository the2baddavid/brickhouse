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
    private int row_perm[][];
    private double length;
    private int height;
    
   
    public validator(row[] al1, int[][] r1, double l, int h){
        row_array = al1;
        row_perm = r1;
        length = l;
        height = h;
}
    public validator(row[] al1, double l){
        row_array = al1;
        length = l;
}
    
    /**
     * Validate all the rows, using the validate two function for when there
     * are more than two rows. Also needs to get proper rows based on perm
     * 
     * TODO: Probably this function will give me trouble
     * 
     * @param row permutation of rows to check
     */
    public boolean validate_all(int row_perm_to_check){
	int temp[] = row_perm[row_perm_to_check];
                
        // While Still Possible to be a valid row, continue
        for(int index = 1; index < height; index++){
            System.out.println("perm"+(index-1));
            
            // Send indexes of 2 rows to check from the current perm
            if ( validate_two(temp[index-1],temp[index]) == true ){
                //System.out.println("true");
            }
            // Otherwise Exit
            else{
                //System.out.println("false");
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
        
        // Length of Both!
        if(!test_length(index1)){
            System.out.println("index1 bad length"+index1);
            return false;
		}
        if(!test_length(index2)){
            System.out.println("index2 bad length");
            return false;
        }
        System.out.println("good length");
        
        row row1 = row_array[index1]; 
        row row2 = row_array[index2];
        
        ArrayList hash1 = new ArrayList();
        ArrayList hash2 = new ArrayList();
        
        hash1.addAll(row1.get_hash());
        hash2.addAll(row2.get_hash());
        
        // for loop to see if one contains an element of the other
        for (int index = 0; index < hash1.size(); index++){
            // if one does, then the bricks lineup and set is invalid
            if (hash1.contains(hash2.get(index))){
                return false;
            }
        }
        
        return true;
    }
     
     /**
      * test if length is correct!
      * @param index row to check
      * @return if match or not
      */
     public boolean test_length(int index){
         return ( row_array[index].get_row_size() == length);
     }
}
