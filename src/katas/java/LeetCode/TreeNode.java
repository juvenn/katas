
package katas.java;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode (int n) { val = n; }

    public List<Integer> inorder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        inorderCollect(list);
        return list;
    }

    public void inorderCollect(List<Integer> list) {
        if (left != null) left.inorderCollect(list);
        list.add(val);
        if (right != null) right.inorderCollect(list);
    }
}
