#!/usr/bin/env python3

# Test suite for solutions of Cracking the code interview

import unittest
import itertools
from katas.python.ctci import CTCI, LinkedListNode

class TestCTCISolutions(unittest.TestCase):
    def test_true(self):
        self.assertTrue(True == 1)

    def test_remove_duplicate_in_linkedlist(self):
        _lists = [
                [1],
                range(1, 10),
                range(1, 100),
                range(1, 1000),
                range(1, 10000, 10)
                ]
        for xs in _lists:
            head = LinkedListNode.from_seq(xs)
            result_head = CTCI.remove_duplicate_in_linkedlist(head)
            self.assertSequenceEqual(xs,list(result_head))

        _lists = [
                ([1, 1], [1]),
                ([1, 2, 1], [1, 2]),
                ([1, 2, 1, 2], [1, 2]),
                ([1, 2, 1, 2, 1, 3], [1, 2, 3]),
                ([1, 1, 1, 1, 1, 1], [1]),
                ([1, 3, 2, 2, 3, 3], [1, 3, 2])
                ]
        for xs1, xs2 in _lists:
            head = LinkedListNode.from_seq(xs1)
            result_head = CTCI.remove_duplicate_in_linkedlist(head)
            self.assertSequenceEqual(xs2,list(result_head))


if __name__ == "__main__":
    unittest.main()
