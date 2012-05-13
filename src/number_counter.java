/**
 * Creates Array of Row Objects
 * 
 * @author david
 */

public class number_counter{
    /**
     * Create different possibilities for rows
     * Create permutations of rows based on length, with length being unknown
     * Uses row[] object to house data, which is all nested into array of rows
     *
     * @param len
     * @param base
     * @return row_array 
     */    
    
    double brick1;
    double brick2;
    int final_size;
    
    /**
     * Used for creating bricks
     * @param b1
     * @param b2 
     */
    public number_counter(double b1, double b2){
        brick1 = b1;
        brick2 = b2;
    }   

    /**
        * Get the final size, which is the size of the array after it has been
        * shrunk to only good inputs.
        * 
        * @return 
        */
    public int get_shrunk_size(){
        return final_size;
    }

    /**
     * generate array containing all possible rows, then return the array of
     * of the row objects
     * 
     * @param len
     * @param base
     * @return 
     */
    public row[] row_generator(double len, int base){
        // temp holding, and initial array size
        int amount = (int)Math.pow(base, (int)len/brick1+1);
        row temp[] = new row[amount];
        
        // final holding array and size iterator
        row output[];
        int size = 0;
        
        int start = (int) (len / brick2);
        int end = (int) (len / brick1);

        if (len%brick1 != 0){   // make sure we dont miss anything!
            end ++;
        }

        // --   MAIN GENERATOR  --  For General Use
        // for loop creates all possibilities of a certain length, with the
        // length increasing from the minimux possible length, to the max
        for(int index = start; index <= end; index++){
            int max = (int) Math.pow(2,index);
            
            // for each length from index1 from min to max, count 0 to max
            for( int index2 = 0; index2 < max ; index2++ ){
                
                // Create and set temp variables
                String temp_string = convert_base(index2, 2);
                double temp_double[] = convert_amount_and_type(temp_string, index);
                row temp_row = new row(temp_double, len,temp_double.length);

                // Copy New Row into Temp Holder for later, and iterate size if correct length
                if (temp_row.get_row_length() == len){
                    temp[size] = temp_row;
                    size++;
                }
            }                
        }

        // Copy temp array into final for returning
        output = new row[size];
        final_size = size;
        System.arraycopy(temp, 0, output, 0, size);
        
        return output;
    }
    
    /**
     * Convert Base will take the number in base-10 that is given and will
     * return a string containing the number in specified base
     * 
     * @param number
     * @param base
     * @return 
     */
    public String convert_base(int number, int base){
        if (number > 1){
            return convert_base(number/base,base) + "" +number%base;
        }
        else{
            return number%base+"";
        }
    }
    
    /**
     * Row gen -- For the instance of rows, the bricks are not lengths of 1 and 0, so
     * this function will convert them to brick1 & brick2. Also
     * 
     * @param input
     * @return 
     */
    public double[] convert_amount_and_type(String input, int array_length){
        int size = input.length();
        double output[] = new double[array_length];

        // initialize all values in output to brick1
        for(int index = 0; index < array_length; index++){
            output [ index ] = brick1;
        }
        
        // then where brick2 is needed, change output to brick2
        for(int index = 0; index < size; index++){
            double temp = (double)input.charAt(index);
            
            if (temp == '1'){
                temp = brick2;
                output[index + (array_length - size)] = temp;
            }
        }

        return output;
    }
}