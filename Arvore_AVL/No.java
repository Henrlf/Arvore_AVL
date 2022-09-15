public class No {
    protected int height;
    protected int key;
    protected No left, right;

    public No(int theElement) {
        this(theElement, null, null);
    }

    public No(int theElement, No lt, No rt) {
        key = theElement;
        left = lt;
        right = rt;
        height = 0;
    }
}
