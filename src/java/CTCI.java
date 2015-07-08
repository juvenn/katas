/**
 * Solutions to Cracking the coding interview
 *
 * Tests are in Clojure and put in the module `katas.clj.java-ctci-test`.
 **/
package katas.java;
import java.util.HashSet;

public class CTCI {

    /**
     * Rotate square matrix in 90 degree
     *
     * qn: 1.4
     **/
    public static Object[][] rotateSquareMatrix(Object[][] m) {
        assert m.length == m[0].length;

        /*
         * Take a square rectangle out of matrix, e.g.:
         *
         *    1 2 3 4
         *    d     6
         *    c     7
         *    b a 9 8
         *
         * A rotation of global matrix would move those entries into each
         * other only on the square, and all exchanges happen only on the
         * rectangle.
         *
         * Assume a clock-wise rotation, entry 2 would move to 6, 6 -> 9, 9 ->
         * c, c -> 2, which forms a cycle:
         *
         *    2 -> 6 -> 9 -> c -> 2
         *    1 -> 4 -> 8 -> b -> 1
         *
         * The algorithm goes from outer rectangle into inner ones, and rotates
         * them iteratively.
         *
         */
        for (int x = 0; x < (m.length / 2); x++) {
            int num_columns = m.length    - x;
            int offset      = num_columns - 1;
            for (int i = 1; i < num_columns; i++) {
                // start from (x, x+i), exchange entries in cycle
                Object temp_val = m[x][x+i];
                m[x][x+i]               = m[x+offset-i][x];
                m[x+offset-i][x]        = m[x+offset][x+offset-i];
                m[x+offset][x+offset-i] = m[x+i][x+offset];
                m[x+i][x+offset]        = temp_val;
            }
        }
        return m;
    }

    /**
     * Remove duplicate elements in linked list.
     *
     * qn: 2.1
     */
    public static LinkedListNode<Integer> removeDuplicate(LinkedListNode<Integer> head) {
        if (head == null) return head;
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode<Integer> nav = head;
        set.add(nav.val);

        while (nav.next != null) {
            if (set.contains(nav.next.val)) {
                nav.next = nav.next.next;
            } else {
                set.add(nav.next.val);
                nav = nav.next;
            }
        }
        return head;
    }

    /**
     * Remove duplicate elements in linked list.
     *
     * This version does not use additional buffer.
     *
     * qn: 2.1
     */
    public static LinkedListNode<Integer> removeDuplicateWithoutBuffer(LinkedListNode<Integer> head) {
        if (head == null) return head;
        LinkedListNode<Integer> nav = head;
        while (nav.next != null) {
            LinkedListNode<Integer> nav2 = head;
            while (nav2.val != nav.next.val) { nav2 = nav2.next; }
            if (nav2 != nav.next) {
                nav.next = nav.next.next;
            } else {
                nav = nav.next;
            }
        }
        return head;
    }

    /**
     * Remove node in the middle of singly linked list.
     *
     * It is not possible to remove last node.
     *
     * qn: 2.3
     */
    public static void removeNodeInMiddle(LinkedListNode<Integer> node) {
        if (node == null || node.next == null)
            throw new IllegalArgumentException("Cannot remove last node.");
        LinkedListNode<Integer> nav = node;
        while (nav.next.next != null){
            nav.val = nav.next.val;
            nav = nav.next;
        }
        nav.val = nav.next.val;
        nav.next = null;

    }
}
