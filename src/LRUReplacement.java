import java.io.PrintStream;
import java.util.stream.IntStream;

/**
 * Implementation of the last recently used (LRU) page replacement
 * algorithm for OS paging.
 *
 * @author Connor P Grady
 * @version 1.0
 */
class LRUReplacement extends Replacement {
    // Collection of ages, each one pertaining
    // to a index-matched page in the page frame
    private int[] ages;

    /**
     * Initializes a new instance of the LRU page replacement
     * algorithm with the specified page frame count.
     * @param pageFrameCount The count of page frames in the system.
     */
    LRUReplacement(int pageFrameCount) {
        super(pageFrameCount);
        this.ages = new int[pageFrameCount];
    }

    /**
     * Inserts the specified page into the page frame.
     * @param pageNumber The page number being inserted into the page frame.
     */
    @Override
    public void insert(int pageNumber) {
        // Increment age of all pages
        // Each insertion is an age for all pages
        for (int i = 0; i < pageFrameCount; i++) ages[i]++;

        if (!IntStream.of(pageFrame).anyMatch(i -> i == pageNumber)) {
            // Specified page number is not in page frame
            // Therefore, we insert the page and this is a fault

            // Increment faultCount
            faultCount++;
            // Set point to the position of the oldest page
            pointer = getIndexOfMaxAge();
            // Insert the new page into the position of the oldest page
            pageFrame[pointer] = pageNumber;
            // Reset the age of the new page
            ages[pointer] = 0;
        } else {
            // Page is in the page frame
            // Therefore, this is a hit and we do not re-add it
            // We do, however, reset the age of the page
            for (int i = 0; i < pageFrameCount; i++) {
                if (pageFrame[i] == pageNumber) ages[i] = 0;
            }
        }
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
        out.println("LRU faults: " + faultCount);
    }

    /**
     * Retrieves the index of the oldest element in the page frame.
     * @return Index of the oldest element in the page frame.
     */
    private int getIndexOfMaxAge() {
        int maxAge = 0;

        for (int i = 0; i < ages.length; i++) {
            int newNumber = ages[i];
            if (newNumber > ages[maxAge]) {
                maxAge = i;
            }
        }

        return maxAge;
    }
}
