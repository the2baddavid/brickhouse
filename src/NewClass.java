public class NewClass{
	
	public static void main(String[]args){
		int magic_shit[][] = roper(7, 2);
		
	}
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

    private static String convert_base(int number, int base){
        if (number > 1){
                return convert_base(number/base,base) + "" +number%base;
        }
        else{
                return number%base+"";
        }
    }

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
    
    private static int[] convert_type(String input){
        int size = input.length();
        int output[] = new int[size];
        
        for(int index = 0;index < size; index++){
            output[index] = (int)input.charAt(index);
        }
        
        return output;
    }

    public static void printit(double input[], int size){

        for (int index=0;index < size;index++)
                System.out.print(input[index]);
        System.out.print("\n");
    }
    
    public static void printit(int input[], int size){

        for (int index=0;index < size;index++)
                System.out.print(input[index]);
        System.out.print("\n");
    }
    
    public int get_size(int length, int base){
        return (int)Math.pow(base, length);
    }
}
