import java.util.stream.IntStream;

/**
 * Implementation of the last recently used (LRU) page replacement
 * algorithm for OS paging.
 *
 * @author Connor P Grady
 * @version 1.0
 */
class LRUReplacement extends Replacement {
    private int[] ages;

    LRUReplacement(int pageFrameCount) {
        super(pageFrameCount);
        this.ages = new int[pageFrameCount];
    }

    /**
     * Inserts the specified page into the page frame.
     *
     * @param pageNumber The page number being inserted into the page frame.
     */
    @Override
    public void insert(int pageNumber) {
        // Increment each age
        for (int i = 0; i < pageFrameCount; i++) ages[i]++;

        if (!IntStream.of(pageFrame).anyMatch(i -> i == pageNumber)) {
            // Specified page number is not in page frame
            faultCount++;
            pointer = getIndexOfMaxAge();
            pageFrame[pointer] = pageNumber;
            ages[pointer] = 0;
        } else {
            // Increment the age of each matching page
            for (int i = 0; i < pageFrameCount; i++) {
                if (pageFrame[i] == pageNumber) ages[i] = 0;
            }
        }
    }

    /**
     * Inserts all of the specified pages into the page frame
     * and returns the number of faults encountered.
     *
     * @param referenceString The reference pages to be inserted.
     * @return The number of faults encountered.
     */
    @Override
    public int insertAll(int[] referenceString) {
        for(int i : referenceString) insert(i);

        return faultCount;
    }

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
