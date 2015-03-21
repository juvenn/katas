#!/usr/bin/env python3

# Test suite for solutions of Cracking the code interview

import unittest
import itertools
import random
from katas.python.ctci import CTCI, LinkedListNode

class TestCTCIRemoveDuplicate(unittest.TestCase):
    '''
    Test remove duplicate items in linked list.

    qn: 2.1
    '''
    def test_true(self):
        self.assertTrue(True == 1)

    def test_non_duplicate_list(self):
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

    def test_duplicates_list(self):
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

class TestCTCIFindNthToLast(unittest.TestCase):
    '''
    Test find nth-to-last item in singly linked list.

    qn: 2.2
    '''
    def test_0_should_find_last_item(self):
        for xs in [[1], range(1, 10), range(1, 1000), range(1, 10000, 10),
                range(10000, 0, -10)]:
            head = LinkedListNode.from_seq(xs)
            val = CTCI.find_nth_to_last(head, 0)
            self.assertEqual(xs[-1], val)

    def test_1_should_find_next_last_item(self):
        for xs in [range(1, 10), range(1, 1000), range(1, 10000, 10),
                range(10000, 0, -10)]:
            head = LinkedListNode.from_seq(xs)
            val = CTCI.find_nth_to_last(head, 1)
            self.assertEqual(xs[-2], val)

    def test_random_positive_nth(self):
        xs = range(1, 10000, 3)
        head = LinkedListNode.from_seq(xs)
        length = len(xs)
        for i in range(0, 100):
            n = random.randrange(0, length)
            val = CTCI.find_nth_to_last(head, n)
            self.assertEqual(xs[-n-1], val)

    def test_minus1_should_find_first_item(self):
        for xs in [[1], range(1, 10), range(1, 1000), range(1, 10000, 10),
                range(10000, 0, -10)]:
            head = LinkedListNode.from_seq(xs)
            val = CTCI.find_nth_to_last(head, -1)
            self.assertEqual(xs[0], val)

    def test_random_nagative_nth(self):
        xs = range(1, 10000, 3)
        head = LinkedListNode.from_seq(xs)
        length = len(xs)
        for i in range(0, 100):
            n = random.randrange(1, length+1)
            val = CTCI.find_nth_to_last(head, -n)
            self.assertEqual(xs[n-1], val)


if __name__ == "__main__":
    unittest.main()
