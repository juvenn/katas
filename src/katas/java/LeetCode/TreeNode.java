
package katas.java;
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

    public List<Integer> inorder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        inorder(this, list);
        return list;
    }

    public void inorder(TreeNode x, List<Integer> list) {
        if (x == null) return;
        inorder(x.left, list);
        list.add(x.val);
        inorder(x.right, list);
    }

    public List<Integer> preorder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        preorder(this, list);
        return list;
    }

    private void preorder(TreeNode x, List<Integer> list) {
        if (x == null) return;
        list.add(val);
        preorder(x.left, list);
        preorder(x.right, list);
    }

    public List<Integer> postorder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        postorder(this, list);
        return list;
    }

    private void postorder(TreeNode x, List<Integer> list) {
        if (x == null) return;
        postorder(x.left, list);
        postorder(x.right, list);
        list.add(x.val);
    }

}
