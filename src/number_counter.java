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
     * Driver is used to create Initial Array of Rows, then calls the shrink
     * to remove the bad ones.  Lastly, the good ones are passed back to the
     * main function
     * 
     * @param len
     * @param base
     * @return 
     */
    public row[] driver(double len, int base){
        // Shrinker might be throwing off the prebuilt results...
            return row_shrinker( row_generator( len, base), len);
	}
    
    /**
     * Shrink takes the array of row objects, and then copies into a temp array
     * only the rows that have the correct length.  Then temp array with the
     * good rows is then returned.
     * 
     * @param input
     * @param length_to_test
     * @return 
     */
    public row[] row_shrinker(row[] input, double length_to_test){
        row temp[] = new row[input.length];
        row output[];
        int counter=0;

        for(int index =0; index < input.length; index++){
            // Normal Running Condition
                if ( input[index].get_row_length() == length_to_test){
                    temp[counter] = input[index];
                    counter++;
                }
            // Case of Having Brick1 leading, which isn't normally generated
            // due to brick1 taking the place of the 0 in the generator
                else if (input[index].get_row_length()+brick1 == length_to_test){
                    double[] temp_double = new double[1+input[index].get_array_length()];
                    temp_double[0] = brick1;
                    System.arraycopy(input[index].get_row(), 0, temp_double, 1, input[index].get_array_length());
                    row temp_row = new row(temp_double, length_to_test,temp_double.length);

                    temp[counter] = temp_row;
                    counter++;
                }
        }

        output = new row[counter];
        final_size = counter;

        for(int index =0; index < counter; index++){
                output[index] = temp[index];			
        }
        return output;
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
        int amount = (int)Math.pow(base, (int)len/brick1+1);
        row output[]; 
        
        // Test if length is greater than the sum, then do normal op
        // Add Condition of {0},{0,0},{0,0,0}... 
        
        if ( len > (brick1+brick2)){
            output = new row[amount];

            for(int before = 0; before < amount; before++){
                //Convert number:base10 to base2
                String temp_string = convert_base(before,base);

                //take output(String) and convert to double[]
                double temp_num[] = convert_amount_and_type(temp_string);

                //create new row object to hold double[]
                row temp_row = new row(temp_num,len,temp_num.length);

                //copy new object in array of objects if correct length
                output[before]=temp_row;
            }
            
            // Test for special case of row consisting of only brick1's,
            // but many of them
            if (len%brick1 == 0){
                int times = (int) (len/brick1);
                double temp[] = new double[times];
                
                // Put case into double[]
                for (int i =0 ; i<times; i++){
                    temp[i] = brick1;
                }
                // Create Row to hold info and push into Row[]
                row temp1 = new row(temp,len,times);
                output[0] = temp1;
            }
        }

        // Otherwise use prebuilt set of potentials
        else{
            output = new row[6];			
            double temp_num[][] = {{brick1,0},{brick2,0},{brick1,brick1},{brick1,brick2},{brick2,brick1},{brick2,brick2}};

            for (int index = 0;index < 6; index++){
                row temp_row = new row(temp_num[index],len,temp_num[index].length);
                output[index] = temp_row;
            }
            
            
        }
        return output;
    }
    
    /**
     * Convert Base will take the number in base-10 that is given and will
     * return a string containing the number in base-2
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
     * this function will convert them to brick1 & brick2
     * 
     * @param input
     * @return 
     */
    public double[] convert_amount_and_type(String input){
        int size = input.length();
        double output[] = new double[size];

        for(int index = 0; index < size; index++){
            double temp = (double)input.charAt(index);
            if(temp == '0')
                temp = brick1;
            else if (temp == '1')
                temp = brick2;
            else
                temp = 0;
            output[index] = temp;
        }

        return output;
    }
}