#! /usr/bin/python
#
# Definition for a  binary tree node
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    # Build binary search tree with a list of values
    def into(self, vals):
        for v in vals:
            self.insert(v)

    def insert(self, val):
        if val <= self.val:
            if self.left: self.left.insert(val)
            else        : self.left = TreeNode(val)
        elif val > self.val:
            if self.right: self.right.insert(val)
            else         : self.right = TreeNode(val)

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def preorderTraversal(self, root):
        if root is None: return []
        result = []
        stack  = []
        stack.append(root)
        while stack:
            x = stack.pop()
            result.append(x.val)
            if x.right: stack.append(x.right)
            if  x.left: stack.append(x.left)
        return result

    # recursively traverse the tree
    def recurPreTraverse(self, list, node):
        if node:
            list.append(node.val)
            self.recurPreTraverse(list, node.left)
            self.recurPreTraverse(list, node.right)
