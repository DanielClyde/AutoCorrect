/**
 * This class is a skew heap
 * has merge, insert, and deleteMax functionality
 * keeps nodes sorted based on priority
 * Follows the pattern of the author's LeftistHeap
 * @author Danny Clyde
 * @param <AnyType>
 */
public class SkewHeap<AnyType extends Comparable<? super AnyType>> {
    private SkewNode<AnyType> root;    // root
    /**
     * Constructs the skew heap
     */
    public SkewHeap( ) {
        root = null;
    }

    /**
     * Internal method to merge two roots.
     * Deals with deviant cases and calls recursive merge1.
     */
    private SkewNode<AnyType> mergePartOne( SkewNode<AnyType> a, SkewNode<AnyType> b ) {
        if( a == null ) return b;
        if( b == null ) return a;
        if( a.element.compareTo( b.element ) < 0 ) {
            return mergePartTwo( a, b );
        }
        else {
            return mergePartTwo( b, a );
        }
    }

    /**
     * Internal method to merge two roots.
     * Assumes trees are not empty, and h1's root contains smallest item.
     */
    private SkewNode<AnyType> mergePartTwo( SkewNode<AnyType> a, SkewNode<AnyType> b ) {
        if( a.left == null ) {
            a.left = b;
        }
        else {
            a.right = mergePartOne( a.right, b );
            swapChildren( a );
        }
        return a;
    }

    /**
     * Swap the children of the given node
     * @author Danny Clyde
     */
    private static <AnyType> void swapChildren( SkewNode<AnyType> t ) {
        SkewNode<AnyType> temp = t.left;
        t.left = t.right;
        t.right = temp;
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * @param x the item to insert.
     */
    public void insert( AnyType x ) {
        root = mergePartOne( new SkewNode<AnyType>( x ), root );
    }


    /**
     * Remove the item with highest priority from the heap
     * in the instance of a heap of Terms, this is the term in the queue with
     * highest frequency
     * @return the node with highest priority
     */
    public AnyType deleteMax( ) {
        if( isEmpty( ) )
            throw new NullPointerException( );

        AnyType max = root.element;
        root = mergePartOne( root.left, root.right );

        return max;
    }

    /**
     * See if the heap is empty
     * @return true if empty, false if not
     * @author Danny Clyde
     */
    public boolean isEmpty( ) {
        return root == null;
    }


    private static class SkewNode<AnyType> {
        AnyType              element;      // The data in the node
        SkewNode<AnyType> left;         // Left child
        SkewNode<AnyType> right;        // Right child

        SkewNode( AnyType theElement ) {
            this( theElement, null, null );
        }

        SkewNode( AnyType theElement, SkewNode<AnyType> lt, SkewNode<AnyType> rt ) {
            element = theElement;
            left    = lt;
            right   = rt;
        }
    }

    public static void main( String [ ] args ) {
        int numItems = 100;
        SkewHeap<Integer> h  = new SkewHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems ) {
            h.insert( i );
        }

        for( i = 1; i < numItems; i++ )
            if( h.deleteMax( ) != i )
                System.out.println( "Oops! " + i );
    }
}

