/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class number_counter_test {
    ArrayList row_array;
    int row_number;
    double length = 2;
    number_counter row_create;
    double brick1 = 3;
    double brick2 = 4.5;
    
    public number_counter_test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        row_create = new number_counter(brick1, brick2);
        row_array = row_create.row_generator((int)length,2);
        row_number = row_array.size();
        
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void convert_amount_type_test(){
        String input1 = "0";
        double expected[] = {brick1};
        double result[] = row_create.convert_amount_and_type(input1);
        
        row_create.printit(result, result.length);
        
        for(int index = 0; index < expected.length;index++)
            assertEquals(expected[index], result[index], .0001);
    }
    
    @Test
    public void row_gen_test(){
        // initialize counter
        // generate rows
   
        int power = (int) Math.pow(2,2);
        // for loop
        // get array for each row in arraylist
        // print array        
        
    }
}
