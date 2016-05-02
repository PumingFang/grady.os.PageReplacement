import java.util.Random;
import java.util.Scanner;

/**
 * Main driver for executing and testing the implementation
 * of FIFO, LRU, and OPT page replacement algorithms for OS paging.
 *
 * @author Connor P Grady
 * @version 1.0
 */
public class Driver {
    // Default reference string length
    private static final int REFERENCE_STRING_LENGTH = 10;

    // Constant reference string used for test purposes
    // Taken from Operating Systems Concepts 9th Edition, Exercise 9.21 (Page 453)
    // This way, I can be confident of what the result should be
    private static final int[] TEST_REFERENCE_STRING = { 7, 2, 3, 1, 2, 5, 3, 4, 6, 7, 7, 1, 0, 5, 4, 6, 2, 3, 0, 1 };

    public static void main(String[] args) {
        System.out.println("UWW CS 424 | Spring 2016 | Program 4");
        System.out.println("OS Page Replacement Algorithms\n");

        System.out.print("Please enter the number of page frames (1 - 7) OR you may also enter the string 'test' to use a predefined reference string and frame size of 3, matching Exercise 9.21 from the book: ");

        // Create the Scanner on system input
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        int[] referenceString;
        int numPageFrames = 0;

        if (input.equals("test")) {
            // Use the test reference string
            referenceString = TEST_REFERENCE_STRING;
            numPageFrames = 3;
        } else {
            // Get the number of page frames from input
            numPageFrames = Integer.parseInt(input);

            // Insure user input is between 1 and 7, inclusive
            while (numPageFrames > 7 || numPageFrames < 1) {
                System.out.print("ERROR: The number of page frames must be in the range 1 - 7. Please re-enter the number of page frames: ");
                numPageFrames = scanner.nextInt();
            }

            // Generate a random page reference string
            referenceString = generateReferenceString();
        }

        // Create a collection of replacement algorithms
        // that will be executed. This is where using the
        // abstract class is useful
        Replacement[] algorithms = new Replacement[]{ new FIFOReplacement(numPageFrames), new LRUReplacement(numPageFrames), new OPTReplacement(numPageFrames) };

        // Execute all of the replacement algorithms
        for (Replacement alg : algorithms) {
            alg.insertAll(referenceString, System.out);
        }
    }

    /**
     * Generates and returns a random page reference String
     * whose page numbers range from 0 to 9.
     * @return Array of integers representing the reference String.
     */
    private static int[] generateReferenceString() {
        Random random = new Random();

        int[] referenceString = new int[REFERENCE_STRING_LENGTH];

        for (int i = 0; i < referenceString.length; i++) {
            referenceString[i] = random.nextInt(9);
        }

        return referenceString;
    }
}
