import java.io.PrintStream;
import java.util.stream.IntStream;

/**
 * Implementation of the optimal (OPT) page replacement
 * algorithm for OS paging.
 *
 * @author Connor P Grady
 * @version 1.0
 *
 * @TODO This algorithm is returning a value of 15 for the test reference string, while a value of 13 is expected.
 */
class OPTReplacement extends Replacement {
    // Counter that keeps track of how many of the reference
    // pages we've already serviced
    private int count = 0;

    // Copy of the reference string for class-level use
    private int[] refString;

    OPTReplacement(int pageFrameCount) {
        super(pageFrameCount);
    }

    /**
     * Inserts the specified page into the page frame.
     *
     * @param pageNumber The page number being inserted into the page frame.
     */
    @Override
    public void insert(int pageNumber) {
        // Increment the count to show we've service a page
        count++;

        if (!IntStream.of(pageFrame).anyMatch(i -> i == pageNumber)) {
            // Specified page number is not in page frame
            // Therefore, we insert the page and this is a fault

            // Increment the fault count
            faultCount++;

            // Determine the optimal position for the page
            pointer = findOptimal();

            // Insert the page in the calculated optimal position
            pageFrame[pointer] = pageNumber;
        }
    }

    /**
     * Inserts all of the specified pages into the page frame
     * and writes the number of faults encountered.
     *
     * @param referenceString The reference pages to be inserted.
     */
    @Override
    public void insertAll(int[] referenceString, PrintStream out) {
        refString = referenceString;

        // Insert each element in the reference string
        // into the page frame
        for(int i : referenceString) insert(i);

        // Write to the provided printstream the fault count
        out.println("OPT faults: " + faultCount);
    }

    /**
     * Determines and returns the index of the optimal page
     * to replace in the page frame.
     * @return Index of optimal page to replace.
     */
    private int findOptimal() {
        // Specifies the distance each page is from
        // being inserted again into the page frame
        int[] distance = new int[pageFrameCount];

        // Initialize each distance to a max
        for (int i = 0; i < distance.length; i++) distance[i] = Integer.MAX_VALUE;

        // Loop through each distance to set it's actual value
        for (int i = 0; i < pageFrameCount; i++) {
            int distanceCount = 0;

            // Loop through each of the remaining reference pages
            for (int j = 0; j < count; j++) {
                // Compare the reference page to the current element in the page frame
                if (refString[j] == pageFrame[i]) {
                    // If matching, this is the distance for this page
                    distance[i] = distanceCount;
                } else {
                    // Increment the distance count
                    distanceCount++;
                }
            }
        }

        // Return the index of the greatest distance
        return getIndexOfMax(distance);
    }

    /**
     * Retrieves the index of the max element in the specified array.
     * @param array The array whose max element's index should be found.
     * @return Index of the max element in the specified array.
     */
    private static int getIndexOfMax(int[] array) {
        int max = 0;

        for (int i = 0; i < array.length; i++) {
            int newNumber = array[i];
            if (newNumber > array[max]) {
                max = i;
            }
        }

        return max;
    }
}
