/**
 * Base definition of a page replacement algorithm.
 *
 * @author Connor P Grady
 */
abstract class Replacement {
    public int pageFrameCount;
    public int faultCount;
    public int[] pageFrame;
    public int pointer;

    Replacement(int pageFrameCount) {
        this.pageFrameCount = pageFrameCount;
        this.pageFrame = new int[this.pageFrameCount];
    }

    /**
     * Inserts the specified page into the page frame.
     * @param pageNumber The page number being inserted into the page frame.
     */
    public abstract void insert(int pageNumber);

    /**
     * Inserts all of the specified pages into the page frame
     * and returns the number of faults encountered.
     * @param referenceString The reference pages to be inserted.
     * @return The number of faults encountered.
     */
    public abstract int insertAll(int[] referenceString);
}
