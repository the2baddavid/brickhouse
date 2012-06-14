import java.util.ArrayList;

/**
 * Object to Hold Data For Brick House
 * @author david
 *
 * ~~~	Data Members ~~~
 *	my_row[] -- Holds the bricks that are being used to create row
 *	my_row_length -- total length of the row based on brick sizes
 *	my_array_length -- number of bricks per row, or length of array holding the brick information
 *
 *	matches[] -- rows that can legally be used with THIS row
 *	sum_hash -- similar to my_row_length but holds sums of rows at each brick end for determining matches
 *
 *	height_weight -- number of solutions for the row based on the current height and matches
 * 	height_of_hash -- height of cached weight for the row (cached number of solutions)
 *
 * ~~~ 	Functions ~~~~
 */
public class row {
	private double my_row[];
	private double my_row_length;
	private int my_array_length;

	// Used for determining Sets of 2
	private int matches[];
	private ArrayList sum_hash = new ArrayList();

	// Used to make set higher than 2
	private long height_weight = 0;
	private long height_of_hash = 0;
    
	/**
	* Constructor
	* @param input --
	* @param row_length --
	* @param array_length --
	*/
	public row(double input[], double row_length, int array_length){
		my_row = input;
		my_array_length = my_row.length;
		my_row_length = get_row_sum();
		System.arraycopy(input, 0, my_row, 0, my_array_length);
		set_hash();
	}
    
    /**
     * Returns the blocks used to create the row
     * @return 
     */
    public double[] get_row(){
        return my_row;
    }
    
    /**
     * Sums contents of Array, and returns answer.  Used for determining if
     * THIS row is the correct length so that it can be otherwise discarded.
     * Private function, that is used only when the constructor is called.
     * 
     * @return sum of array
     */
    private double get_row_sum(){
        double sum = 0;
        for (int index = 0; index < my_array_length; index++){
            sum += my_row[index];
        }
        return sum;
    }
    
    /**
     * Returns the length of the row, from the block lengths combined.  Not to
     * be confused with get_array_length()
     * 
     * @return sum
     */
    public double get_row_length(){
        return my_row_length;
    }
    
    /**
     * Returns the number of elements, or blocks that make up the row
     * @return # of Blocks
     */
    public int get_array_length(){
        return my_array_length;
    }
    
    /**
     * Create hash that holds all the sums.  What the Hash does, is it adds 
     * each next block length to the preceding ones, allowing us to do determine
     * if the row matches block ends with another row by way of comparison.
     */
    private void set_hash(){
        double sum = 0;
        
        for(int index = 0; index < my_array_length; index++){
            sum += my_row[index];
            sum_hash.add(new Double(sum));
        }
    }
    
    /**
     * Return hash of block sums. What the Hash does, is it adds 
     * each next block length to the preceding ones, allowing us to do determine
     * if the row matches block ends with another row by way of comparison.
     * @return 
     */
    public ArrayList get_hash(){
        return sum_hash;
    }
    
    /**
     * Function is used to set the array which contains the indexes for Row
     * Array of rows that can legally follow the respective row. 
     * 
     * @param temp
     * @param size 
     */
    public void set_matches(int[] temp, int size){
        matches = new int[size];
        System.arraycopy(temp, 0, matches, 0, size);
    }
    
    /**
     * Gives the array that holds the indexes for Row_Array of the rows,
     * which can legally follow the respective row, for the purpose of
     * computing possible solutions to varying heights.
     * 
     * @return array of legal preceding rows
     */
    public int[] get_matches(){
        return matches;
    }
    
    /**
     * Get the number of matches, which is the number of rows that can legally
     * follow the respective one.
     * 
     * @return 
     */
    public int get_match_length(){
        return matches.length;
    }
    
    /**
     * Function sets both the new height of the hash, or the sum of preceding
     * rows.  Said another way, it holds the number of possible solutions for
     * the respective row, at the height specified by the height_of_hash.
     * 
     * @param hash
     * @param new_height 
     */
    public void set_height_hash(long hash, long new_height){
        height_weight = hash;
        height_of_hash = new_height;
    }
    
    /**
     * Gets the current weight of the hash, which is to say the possible
     * solutions for the given height for the given row.
     * 
     * @return 
     */
    public long get_height_hash(){
        return height_weight;
    }
    
    /**
     * gets the height of the current hash weight, or how high the wall that
     * supports the current row is.
     * 
     * @return 
     */
    public long get_height_of_hash(){
        return height_of_hash;
    }
}
