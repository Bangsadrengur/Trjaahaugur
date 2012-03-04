// Treap is a node that has a left and right child,
// a parent, a common root that needs to be 
// initialized externally, a common nil pointer 
// that needs to be initialized externally, an 
// integer key and an integer priority value.
public class Treap
{
    public Treap left;
    public Treap right;
    public Treap p;
    // public static Treap nil;
    public static Treap root;
    public int key;
    public int prio;

    public Treap(int key, int prio)
    {
        left = null;
        right = null;
        p = null;
        this.key = key;
        this.prio = prio;
    }
}

