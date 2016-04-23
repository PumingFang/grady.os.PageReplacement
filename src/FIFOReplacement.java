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
    public void insert(int pageNumber) {
        if (!IntStream.of(pageFrame).anyMatch(i -> i == pageNumber)) {
            // Specified page number is not in page frame
            faultCount++;
            pageFrame[pointer] = pageNumber;
            pointer = (pointer + 1) % pageFrameCount;
        }
    }

    public int insertAll(int[] referenceString) {
        for(int i : referenceString) insert(i);

        return faultCount;
    }
}
