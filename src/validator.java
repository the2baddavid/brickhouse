
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
     * are more than two rows;  When only one, validate everything, Create 
     * based on Height of structure If height is equal to 1, then return
     * all rows as valid.
     * 
     * check rows specified in combo(row perm) or the row array
     * 
     * @param row permutation of rows to check
     */
    static boolean validate_all(int combo_to_verify[]){
        boolean verify = true;
        
        for(int index = 1; index < height && verify == true; index++){
            if (validate_two( row_array[combo_to_verify[index -1]], row_array[combo_to_verify[index]])){
                verify = true;
            }
            else{
                verify = false;
            }
        }
        
        return verify;
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
    static boolean validate_two(double array1[], double array2[]){
        boolean cont = true;
        double sum1[] = new double[rowLength];
        double sum2[] = new double[rowLength];
        Hashtable valid = new Hashtable();
        
        // Start Summing
        sum1[0] = array1[0];
        sum2[0] = array2[0];
        
        // Loop through the rest
        for(int index = 1; index < rowLength; index++){
            sum1[index] = sum1[index-1] + array1[index];
            sum2[index] = sum2[index-1] + array2[index];
        }
        
        // Check for Collisions
        for(int index = 0; index < rowLength && cont == true; index++){
            valid.put(sum1[index], new Double(index));
            if (valid.containsValue(sum2[index])){
                cont = false;
                break; // TODO Might cause issues?
            }
        }
        
        return cont;
    }
    
    
    /**
     * Adds up all the elements in the array, then returns the answer;
     * Used verify that the permutation of row is the correct length
     * 
     * @param test
     * @return Returns the Sum
     */
    static double array_adder(double[] test){
        double sum = 0;
        
        for(int index = 0; index < rowLength; index++){
            sum += test[index];
        }
        
        return sum;
    }
    
    /**
     * Recursive function is used to create permutations of possible combinations
     * of single rows.
     * 
     * However, as there are only 2 possible values the behavior is not all that 
     * different from binary counting permutation
     * 
     * @param old Array in progress, to be copied for more possibilities
     * @param value value to be added to the array
     * @param index current position in the array
     */
    static void builder(double old[], double value, int index){
        // Unique Instance of the Array, and copy current to it
        double test[] = new double[rowLength];
        System.arraycopy(old,0,test,0,rowLength);
        
        if(index > 0){
            test[index-1] = value;
        }
                
        //  Test if we are at the end && that it is a valid answer
        if(index == 0 && (array_adder(test) == length)){
            // If valid, add the array to the list of possibles
            System.arraycopy(test, 0, row_array[row_number], 0, rowLength);
            row_number++;
        }
        //  Otherwise keep going!
        else{
            builder(test, brick1, (index-1));
            builder(test, brick2, (index-1));
        }
    }
}
