import java.io.PrintStream;
import java.util.stream.IntStream;

/**
 * Implementation of the first in, first out (FIFO) page replacement
 * algorithm for OS Paging.
 *
 * @author Connor P Grady
 * @version 1.0
 */
class FIFOReplacement extends Replacement {

    /**
     * Initializes a new instance of the FIFO page
     * replacement algorithm with the specified page frame count.
     * @param pageFrameCount The count of page frames in the system.
     */
    FIFOReplacement(int pageFrameCount) {
        super(pageFrameCount);
    }

    /**
     * Inserts the specified page into the page frame.
     * @param pageNumber The page number being inserted into the page frame.
     */
    @Override
    public void insert(int pageNumber) {
        if (!IntStream.of(pageFrame).anyMatch(i -> i == pageNumber)) {
            // Specified page number is not in page frame
            // Therefore, we insert the page and this is a fault

            // Increment faultCount
            faultCount++;

            // Insert the page into the position of the
            // first added element for the frame position
            pageFrame[pointer] = pageNumber;

            // Set pointer to the new "first in" location
            pointer = (pointer + 1) % pageFrameCount;
        }

        // Otherwise, this is a hit and we do nothing
    }

    /**
     * Inserts all of the specified pages into the page frame
     * and writes the number of faults encountered.
     * @param referenceString The reference pages to be inserted.
     */
    @Override
    public void insertAll(int[] referenceString, PrintStream out) {
        // Insert each element in the reference string
        // into the page frame
        for(int i : referenceString) insert(i);

        // Write to the provided printstream the fault count
        out.println("FIFO faults: " + faultCount);
    }
}
