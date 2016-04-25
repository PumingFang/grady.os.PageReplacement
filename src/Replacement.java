import java.io.PrintStream;

/**
 * Base definition of a page replacement algorithm.
 *
 * @author Connor P Grady
 */
abstract class Replacement {
    int pageFrameCount;
    int faultCount;
    int[] pageFrame;
    int pointer;

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
     * and writes the number of faults encountered.
     * @param referenceString The reference pages to be inserted.
     */
    public abstract void insertAll(int[] referenceString, PrintStream out);
}
