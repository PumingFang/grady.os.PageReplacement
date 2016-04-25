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
    private static final int REFERENCE_STRING_LENGTH = 10;
    private static final int[] TEST_REFERENCE_STRING = { 7, 2, 3, 1, 2, 5, 3, 4, 6, 7, 7, 1, 0, 5, 4, 6, 2, 3, 0, 1 };

    public static void main(String[] args) {
        System.out.println("UWW CS 424 | Spring 2016 | Program 4");
        System.out.println("OS Page Replacement Algorithms\n");

        System.out.print("Please enter the number of page frames (1 - 7): ");

        // Create the Scanner on system input
        Scanner scanner = new Scanner(System.in);

        // Get the number of page frames from input
        int numPageFrames = scanner.nextInt();

        // Insure user input is between 1 and 7, inclusive
        while (numPageFrames > 7 || numPageFrames < 1) {
            System.out.println("ERROR: The number of page frames must be in the range 1 - 7. Please re-enter the number of page frames: ");
            numPageFrames = scanner.nextInt();
        }

        // Generate a random page reference string
        int[] referenceString = TEST_REFERENCE_STRING; //generateReferenceString();

        Replacement fifoReplacement = new FIFOReplacement(numPageFrames);
        System.out.println("FIFO Faults: " + fifoReplacement.insertAll(referenceString));

        Replacement lruReplacement = new LRUReplacement(numPageFrames);
        System.out.println("LRU Faults: " + lruReplacement.insertAll(referenceString));

        Replacement optReplacement = new OPTReplacement(numPageFrames);
        System.out.println("OPT Faults: " + optReplacement.insertAll(referenceString));
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
