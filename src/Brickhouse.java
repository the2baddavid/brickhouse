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

/** 
 * 
 * @author david
 */
public class Brickhouse {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
/******************************************************************************/
/*************************   Let's get started   ******************************/
/******************************************************************************/
        //  Structure Size
        double height;
        double length;
        
        // Brick Sizes -- Brick1 MUST be smaller!
        double brick1 = 3;
        double brick2 = 4.5;

        // Row information
        int row_number;
        row row_array[];
                
        // Start Clock
        time_counter count = new time_counter();
        count.start();
        
        // Other Important Info
        long correct;
        
        // Check number of args, print usage if wrong
        if(args.length != 2){
            System.out.println("usage: Brickhouse [length][height]");
            System.exit(1);
        }
        
        // Set Input
        length = Double.parseDouble(args[0]);
        height = Double.parseDouble(args[1]);
        
        
/******************************************************************************/ 
/*****************************  Handle Length    ******************************/ 
/******************************************************************************/ 
        
        // Create Rows and Trim to Only Correct Ones
        number_counter row_create = new number_counter(brick1, brick2);
        row_array = row_create.row_generator(length,2);
        row_number = row_array.length;
                
/******************************************************************************/ 
/*****************************  Handle Height    ******************************/ 
/******************************************************************************/ 
        
        // Find which rows can follow which, if Height > 1
        if (height > 1){
            // Create Validator Object to Pass All Row Perms to
            validator validate = new validator(row_array,(int)height);
            validate.validate_all();
            
            // Now that the correct following rows are known, find the number
            // of possible correct sets
            correct = validate.height_handler();            
        }
        // Otherwise, all rows of correct length are correct
        else{
            correct = row_number;
        }
        
/******************************************************************************/ 
/********************* Get run time and finish up    **************************/ 
/******************************************************************************/ 
/******************************************************************************/ 
      
        count.end();
        System.out.print(correct);
        System.out.println("\nRun Time:"+count.runTime()+" ms");
    }
}
