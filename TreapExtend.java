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
            // Check if value is already in treap,
            // if it is then drop insert function.
            if(z.key == x.key) return;
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

    // Rotate tuple up to the left. T is any node in tree
    // and x is parent of node that is to be raised.
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

    // Rotate tuple up to the right. T is any node in tree
    // and x is parent of node to be raised.
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

    public static Treap treapSearch(Treap x, int k)
    {
        if(x == null || k == x.key)
        {
            return x;
        }
        if(k < x.key)
        {
            return treapSearch(x.left,k);
        } else {
            return treapSearch(x.right,k);
        }
    }

    public static void treapTransplant(Treap T, Treap u, Treap v)
    {
        if(u.p == null)
        {
            T.root = v;
        } else if(u == u.p.left)
        {
            u.p.left = v;
        } else {
            u.p.right = v;
        }
        if(v != null)
        {
            v.p = u.p;
        }
    }

    public static Treap treapMinimum(Treap x)
    {
        while(x.left != null)
        {
            x = x.left;
        }
        return x;
    }

    public static void treapDelete(Treap T, Treap z)
    {
        Treap y;
        if(z.left == null)
        {
            y = z.right;
            treapTransplant(T,z,z.right);
        } else if(z.right == null)
        {
            y = z.left;
            treapTransplant(T,z,z.left);
        } else {
            y = treapMinimum(z.right);
            if(y.p != z)
            {
                treapTransplant(T,y,y.right);
                y.right = z.right;
                y.right.p = y;
            }
            treapTransplant(T,z,y);
            y.left = z.left;
            y.left.p = y;
        }
        // Treap prio fixdown
        treapFixDown(y);
    }

    public static void treapFixDown(Treap x)
    {
        if(x.left == null && x.right == null) return;
        if(x.left == null && x.right.prio < x.prio)
        {
            treapLeftRotate(x.root, x);
        } else {return;}
        if(x.right == null && x.left.prio < x.prio)
        {
            treapRightRotate(x.root, x);
        } else {return;}
        if(x.prio < java.lang.Math.min(x.left.prio, x.right.prio)) return;
        if(x.left.prio < x.right.prio)
        {
            treapRightRotate(x.root, x);
            treapFixDown(x);
        } else {
            treapLeftRotate(x.root, x);
            treapFixDown(x);
        }
    }

    // Test code for unit.
    public static void main(String[] args)
    {
        Treap node1 = new Treap(5,5);
        node1.root = node1;
        treapInsert(node1.root, new Treap(7,8));
        treapInsert(node1.root, new Treap(3,7));
        Treap search = treapSearch(node1.root, 5);
        treapDelete(search.root, search);
        System.out.println(node1.root.key);
    }
}
