
package katas.java;
import java.util.List;
import java.util.ArrayList;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode (int x) { val = x; }

    public List<Integer> seq() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (ListNode x = this; x != null; x = x.next)
            list.add(x.val);
        return list;
    }
}
