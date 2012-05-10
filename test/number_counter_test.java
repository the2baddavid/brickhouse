/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author david
 */
public class number_counter_test {
    ArrayList row_array;
    int row_number;
    double length = 2;
    number_counter row_create;
    row_counter rower;
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
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void convert_amount_type_test(){
        String input1 = "0";
        double expected[] = {brick1};
        double result[] = row_create.convert_amount_and_type(input1);
                
        for(int index = 0; index < expected.length;index++)
            assertEquals(expected[index], result[index], .0001);
        
        String input2 = "01";
        double expected2[] = {brick1,brick2};
        double result2[] = row_create.convert_amount_and_type(input2);
                
        for(int index = 0; index < expected2.length;index++)
            assertEquals(expected2[index], result2[index], .0001);
    }
    
    @Test
    public void convert_base_test(){
        int input = 20;
        String expected = "10100";
        String result = row_create.convert_base(input, 2);
        
        assertTrue(expected.equals(result));
    }
    
    @Test
    public void convert_type_test(){
        String input = "01011";
        byte expected[] = {0,1,0,1,1};
        byte result[] = rower.convert_type(input,input.length());
        
        assertArrayEquals(expected, result);        
    }
    
    @Test
    public void roper_test(){
        // length =2
        // base =2 
        byte expected[][] = {{0,0},{0,1},{1,0},{1,1}};
        byte result[][] = rower.roper(2,2);
        
        for(int index = 0; index < 4; index++){
            assertArrayEquals(expected, result);
        }
    }
}
