/**
 * Implementation of the optimal (OPT) page replacement
 * algorithm for OS paging.
 *
 * @author Connor P Grady
 * @version 1.0
 */
class OPTReplacement extends Replacement {

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
        return 0;
    }

    private int findOptimal() {


        return 1;
    }
}
