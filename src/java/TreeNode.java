
package katas;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode (int n) { val = n; }

    public int size() {
        return size(this);
    }

    public int size(TreeNode x) {
        if (x == null) return 0;
        return size(x.left) + size(x.right) + 1;
    }

    public int height() {
        return height(this);
    }

    public int height(TreeNode x) {
        if (x == null) return 0;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public List<Integer> inOrder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        inOrder(this, list);
        return list;
    }

    public void inOrder(TreeNode x, List<Integer> list) {
        if (x == null) return;
        inOrder(x.left, list);
        list.add(x.val);
        inOrder(x.right, list);
    }

    public List<Integer> preOrder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        preOrder(this, list);
        return list;
    }

    private void preOrder(TreeNode x, List<Integer> list) {
        if (x == null) return;
        list.add(val);
        preOrder(x.left, list);
        preOrder(x.right, list);
    }

    public List<Integer> postOrder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        postOrder(this, list);
        return list;
    }

    private void postOrder(TreeNode x, List<Integer> list) {
        if (x == null) return;
        postOrder(x.left, list);
        postOrder(x.right, list);
        list.add(x.val);
    }

}
