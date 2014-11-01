#! /usr/bin/python
#

import init
import unittest

from preorder_traversal import TreeNode, Solution


class PreorderTraversalTestCase(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def testPreorderTraversalEmptyTree(self):
        result = self.solution.preorderTraversal(None)
        self.assertEqual(result, [])

    def testPreorderTraversal1(self):
        root = TreeNode(1)
        result = self.solution.preorderTraversal(root)
        self.assertEqual(result, [1])

    def testPreorderTraversal3Balanced(self):
        root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        result = self.solution.preorderTraversal(root)
        self.assertEqual(result, [1,2,3])

    def testPreorderTraversal3Unbalanced(self):
        root = TreeNode(1)
        root.right = TreeNode(2)
        root.right.left = TreeNode(3)
        result = self.solution.preorderTraversal(root)
        self.assertEqual(result, [1,2,3])

    def testPreorderTraversal9(self):
        root = TreeNode(6)
        root.into([2, 1, 4, 3, 5, 7, 9, 8])
        result = self.solution.preorderTraversal(root)
        self.assertEqual(result, [6,2,1,4,3,5,7,9,8])


if __name__ == '__main__':
    unittest.main()
