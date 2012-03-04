public class TreapExtend
{
    // Insert tuple z into treap T according to 
    // binary search tree requirements.
    public static void treapInsert(Treap T, Treap z)
    {
        Treap y = null;
        Treap x = T.root;
        while(x != null)
        {
            y = x;
            if(z.key < x.key)
            {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.p = y;
        if(y==null)
        {
            T.root = z;
        } else if(z.key < y.key)
        {
            y.left = z;
        } else {
            y.right = z;
        }
        while(z != T.root && z.prio < z.p.prio)
        {
            if(z == z.p.left)
            {
                treapRightRotate(T,z.p);
            } else {
                treapLeftRotate(T,z.p);
            }
        }
    }

    // Rotate tuple up to the left
    public static void treapLeftRotate(Treap T, Treap x)
    {
        Treap y = x.right;
        x.right = y.left;
        if(y.left != null)
        {
            y.left.p = x;
        }
        y.p = x.p;
        if(x.p == null)
        {
            T.root = y;
        } else if(x==x.p.left)
        {
            x.p.left = y;
        } else {
            x.p.right = y;
        }
        y.left = x;
        x.p = y;
    }

    // Rotate tuple up to the right
    public static void treapRightRotate(Treap T, Treap x)
    {
        Treap y = x.left;
        x.left = y.right;
        if(y.right != null)
        {
            y.right.p = x;
        }
        y.p = x.p;
        if(x.p == null)
        {
            T.root = y;
        } else if(x==x.p.left)
        {
            x.p.left = y;
        } else {
            x.p.right = y;
        }
        y.right = x;
        x.p = y;
    }

    public static void inorderTreeWalk(Treap x)
    {
        if(x!= null)
        {
            inorderTreeWalk(x.left);
            System.out.println(x.key);
            inorderTreeWalk(x.right);
        }
    }

    // Test code for unit.
    public static void main(String[] args)
    {
        Treap node1 = new Treap(5,5);
        node1.root = node1;
        treapInsert(node1.root, new Treap(7,2));
        treapInsert(node1.root, new Treap(3,1));
        inorderTreeWalk((new Treap(1,1)).root);
    }
}
