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
public class row_test {

    double test_row[] = { 3, 3, 4.5, 3};
    double row_length = 13.5;
    int array_length = 4;
    row test_object;    
    
    public row_test() {}
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
        
        test_object = new row(test_row,row_length,array_length);
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
    public void test_get_row(){
        double expected[] = {};
        double result [] = test_object.get_row();
        
        for (int index =0; index < array_length; index++)
            assertEquals(expected[index], result[index], .00001);
    }
    
    public void test_get_row_size(){
        String input = null;
        double expected = row_length;
        double result = test_object.get_row_size();
        
        assertEquals(expected, result, .0001);        
    }
    
    public void test_get_row_length(){
        String input = null;
        double expected = row_length;
        double result = test_object.get_row_length();
        
        assertEquals(expected, result, .0001);
    }
    
    public void test_get_hash(){
        String input = null;
        double expected[] = {  3, 6, 10.5, 13.5 };
        ArrayList result = test_object.get_hash();
        
        // TODO: Create Hash Test
        for(int index = 0; index < array_length; index++){
            assertTrue(result.contains(new Double(expected[index])));
        }
    }
}
