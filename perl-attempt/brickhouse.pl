package brickhouse;

/*
Your niece was given a set of blocks for her birthday, and she has decided to build a panel using
3”×1” and 4.5”×1" blocks. For structural integrity, the spaces between the blocks must not line up
in adjacent rows. For example, the 13.5”×3” panel below is unacceptable, because some of the
spaces between the blocks in the first two rows line up (as indicated by the dotted line).

There are 2 ways in which to build a 7.5”×1” panel, 2 ways to build a 7.5”×2” panel, 4 ways to
build a 12”×3” panel, and 7958 ways to build a 27”×5” panel. How many different ways are there
for your niece to build a 48”×10” panel? The answer will fit in a 64-bit integer. Write a program to
calculate the answer.

The program should be non-interactive and run as a single-line command which takes two
command-line arguments, width and height, in that order. Given any width between 3 and 48 that
is a multiple of 0.5, inclusive, and any height that is an integer between 1 and 10, inclusive, your
program should calculate the number of valid ways there are to build a wall of those dimensions.
Your program’s output should simply be the solution as a number, with no line-breaks or white
spaces.

Your program will be judged on how fast it runs and how clearly the code is written. We will be
running your program as well as reading the source code, so anything you can do to make this
process easier would be appreciated.

Send the source code and let us know the value that your program computes, your program’s
running time, and the kind of machine on which you ran it.
 */

import java.util.Hashtable;

/**
 *
 * @author david
 */
public class Brickhouse {

    /**
     * @param args the command line arguments
     */
    $height;
    $length;
    
    $rowLength = 100;
    $numberOfRows = 1000;
    
    $row_perm_index = 0;
    $row_perm[][];
    
    $row_number = 0;
    @row_array[$numberOfRows][$rowLength];
    
    static double brick1 = 3;
    static double brick2 = 4.5;
    
    public static void main(String[] args) {
       
        # Start Clock
        time_counter count = new time_counter();
        count.start();
        
        # variables to return
        double correct = 0;
        double runtime;
        
        # Set Input
        height = Double.parseDouble(args[0]);
        length = Double.parseDouble(args[1]);
        
        # Create Permutations 
       /* double test[] = new double[rowLength];
        builder(test, brick1, (rowLength));       
        builder(test, brick2, (rowLength));*/
        
        # Find permutations of rows
        number_counter nc = new number_counter(2,(int)height);
        row_perm = nc.get_all();
        row_perm_index = nc.get_size();
        
        # Take Valid Rows and Find Matches
        for(int index = 0; index < row_perm_index; index++){
            if (validate_all(row_perm[index])){
                correct ++;
            }
        }
        
        # Get run time and finish up
        count.end();
        runtime = count.runTime();
        
        System.out.println("Number of Correct Answers"+correct);
        System.out.println("Run Time:"+runtime);
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
        
        if(height == 1){
            return true;
        }
        
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
        
        # Start Summing
        sum1[0] = array1[0];
        sum2[0] = array2[0];
        
        # Loop through the rest
        for(int index = 1; index < rowLength; index++){
            sum1[index] = sum1[index-1] + array1[index];
            sum2[index] = sum2[index-1] + array2[index];
        }
        
        # Check for Collisions
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
        # Unique Instance of the Array, and copy current to it
        double test[] = new double[rowLength];
        System.arraycopy(old,0,test,0,rowLength);
        
        if(index > 0){
            test[index-1] = value;
        }
                
        #  Test if we are at the end && that it is a valid answer
        if(index == 0 && (array_adder(test) == length)){
            // If valid, add the array to the list of possibles
            System.arraycopy(test, 0, row_array[row_number], 0, rowLength);
            row_number++;
        }
        #  Otherwise keep going!
        else{
            builder(test, brick1, (index-1));
            builder(test, brick2, (index-1));
        }
    }
}
