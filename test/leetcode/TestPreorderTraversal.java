
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;

import java.util.List;
import java.util.ArrayList;
import LeetCode.TreeNode;
import LeetCode.PreorderTraversal;

public class TestPreorderTraversal {
    private PreorderTraversal solution = new PreorderTraversal();

    @Test
    public void testTraverseEmptyTree() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        List<Integer> result = this.solution.preorderTraversal(null);
        assertEquals(result, expected);
    }

    @Test
    public void testTraverseOneElementTree() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        List<Integer> result = this.solution.preorderTraversal(new TreeNode(1));
        assertEquals(result, expected);
    }

    @Test
    public void testTraverseTreeElementTree() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        TreeNode root   = new TreeNode(1);
        root.right      = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = this.solution.preorderTraversal(root);
        assertEquals(result, expected);
    }

    @Test
    public void testTraverseFourElementTree() {
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(4);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        TreeNode root   = new TreeNode(1);
        root.left       = new TreeNode(4);
        root.right      = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        List<Integer> result = this.solution.preorderTraversal(root);
        assertEquals(result, expected);
    }
}
