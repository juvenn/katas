package LeetCode;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class PreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);
        while (!st.empty()) {
            TreeNode x = st.pop();
            result.add(x.val);
            if (x.right != null) st.push(x.right);
            if (x.left  != null) st.push(x.left);
        }
        return result;
    }
}
