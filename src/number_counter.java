/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Creates 2d Array counting by given base to given width
 * @author david
 */
public class number_counter{
    /**
     * Create different possibilities for rows
     * Create permutations of rows based on length, with length being unknown
     *
     * @param 
     */    
    public double[][] row_generator(int length, int base){
        int amount = (int)Math.pow(base, length);
        double output[][] = new double[amount][length];

        for(int before = 0; before < amount; before++){
            String temp_string = convert_base(before,base);
            double temp_num[] = convert_amount_and_type(temp_string);
            output[before] = temp_num;
            printit(temp_num,temp_string.length());
        }
        return output;
    }
    
    /**
     * Create possible matches of rows to check
     * 
     * @param length
     * @param base
     * @return 
     */
    static int[][] roper(int length,int base){
        int amount = (int)Math.pow(base, length);
        int output[][] = new int[amount][length];
        
        for(int before = 0; before < amount;before++){
            String temp_string = convert_base(before,base);
            output[before] = convert_type(temp_string);
            printit(output[before],temp_string.length());
        }
        
        return output;
    }

    /**
     * Takes a number of base 10 as input, then converts the number to
     * the specified base and returns it as a string; recursive
     * 
     * @param number what to convert
     * @param base base to convert to
     * @return string containing conversion
     */
    private static String convert_base(int number, int base){
        if (number > 1){
                return convert_base(number/base,base) + "" +number%base;
        }
        else{
                return number%base+"";
        }
    }

    /**
     * For the instance of rows, the bricks are not lengths of 1 and 0, so
     * this function will convert them to 3 & 4.5
     * 
     * @param input
     * @return 
     */
    private static double[] convert_amount_and_type(String input){
        int size = input.length();
        double output[] = new double[size];

        for(int index = 0; index < size; index++){
                double temp = (double)input.charAt(index);
                if(temp == '0')
                        temp = 3;
                else if (temp == '1')
                        temp = 4.5;
                else
                        temp = 0;
                output[index] = temp;
        }

        return output;
    }
    
    /**
     * Simply converts the strings that are output from convert base function
     * into array of integers
     * 
     * @param input
     * @return 
     */
    private static int[] convert_type(String input){
        int size = input.length();
        int output[] = new int[size];
        
        for(int index = 0;index < size; index++){
			String temp = input.charAt(index)+"";
            output[index] = Integer.parseInt(temp);
        }
        
        return output;
    }

    /**
     * Function prints to std out from a double[]
     * 
     * @param input
     * @param size 
     */
    public static void printit(double input[], int size){

        for (int index=0;index < size;index++)
                System.out.print(input[index]);
        System.out.print("\n");
    }
    
    /**
     * Function prints to std-out from an int[]
     * 
     * @param input
     * @param size 
     */
    public static void printit(int input[], int size){

        for (int index=0;index < size;index++)
                System.out.print(input[index]);
        System.out.print("\n");
    }
    
    /**
     * returns number of permutations using powers, base^length
     * 
     * @param length
     * @param base
     * @return 
     */
    public int get_size(int length, int base){
        return (int)Math.pow(base, length);
    }
}