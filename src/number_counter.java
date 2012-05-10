import java.util.ArrayList;

/**
 * Creates ArrayList of Row Objects
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

    
    /**
     * Used for creating bricks
     * @param b1
     * @param b2 
     */
    public number_counter(double b1, double b2){
        brick1 = b1;
        brick2 = b2;
    }
    
    
    public row[] row_generator(double len, int base){
        int amount = (int)Math.pow(base, (int)len/brick1+1);
        row output[]; 
        
        // Test if length is greater than the sum, true do normal op
        // Add Condition of {0},{0,0},{0,0,0}... 
        
        if ( len > (brick1+brick2)){
            output = new row[amount];

            for(int before = 0; before < amount; before++){
                //System.out.println("before"+before);

                //Convert number:base10 to base2
                String temp_string = convert_base(before,base);
                //System.out.println("base conversion:"+temp_string);

                //take output(String) and convert to double[]
                double temp_num[] = convert_amount_and_type(temp_string);
                //printit(temp_num, temp_num.length);

                //create new row object to hold double[]
                row temp_row = new row(temp_num,len,temp_num.length);

                //copy new object in array of objects if correct length
                output[before]=temp_row;
                //System.out.println(temp_row.get_row());
            }
        }

    // Otherwise use prebuilt set of potentials
    else{
            output = new row[6];			
            double temp_num[][] = {{brick1,0},{brick2,0},{brick1,brick1},{brick1,brick2},{brick2,brick1},{brick2,brick2}};

            for (int index = 0;index < 6; index++){

            /*	System.out.print("before:");
                    printit(temp_num[index],temp_num[index].length);
                    System.out.println(temp_num[index].length); */

                    row temp_row = new row(temp_num[index],len,temp_num[index].length);

            /*	System.out.print("temp:");
                    printit(temp_row.get_row(),temp_num[index].length); */

                    output[index] = temp_row;

            /*	System.out.print("after:");
                    printit(output[index].get_row(),temp_num[index].length); 
                    System.out.println( "length"+temp_row.get_row_length() );	// for debugging	*/
            }
        }
        return output;
    }
    
    /**
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
    
    
    /**
     * Row Gen -- Function prints to std out from a double[]
     * 
     * @param input
     * @param size 
     */
    public static void printit(ArrayList input, int size){

        for (int index=0;index < size;index++)
            System.out.print(input.get(index));
        System.out.print("\n");
    }
    
    /**
     * Mocked up for JUnit Testing...
     * @param input
     * @param size 
     */
    public static void printit(double input[], int size){

        for (int index=0;index < size;index++)
            System.out.print(input[index]);
        System.out.print("\n");
    }
    
    /**
     * returns number of permutations using powers, base^length, however this
     * will be more than the actual, as the actual will only have rows with
     * the correct length, however makes a good starting point
     * 
     * @param length
     * @param base
     * @return 
     */
    public int get_starting_size(int length, int base){
        return (int)Math.pow(base, length);
    }
}