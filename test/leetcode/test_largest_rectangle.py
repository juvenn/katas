#! /usr/bin/python
#

import init
import unittest
import time

from largest_rectangle import Solution

class LargestRectangleTestCase(unittest.TestCase):
    def setUp(self):
        self.start    = time.time()
        self.solution = Solution()

    def tearDown(self):
        dt = time.time() - self.start
        print "%s: %.3f" % (self.id(), dt)

    def test_empty_histogram(self):
        result = self.solution.largestRectangle([])
        self.assertEqual(result, 0)

    def test_one_histogram(self):
        result = self.solution.largestRectangle([6])
        self.assertEqual(result, 6)

    def test_two_histogram(self):
        result = self.solution.largestRectangle([6,8])
        self.assertEqual(result, 12)

    def test_multiple_histogram(self):
        result = self.solution.largestRectangle([2,1,5,6,2,3])
        self.assertEqual(result, 10)

    # [0 .. 10]
    def test_range11_histogram(self):
        result = self.solution.largestRectangle(range(11))
        self.assertEqual(result, 5 * 6)

    # [10 .. 0]
    def test_reversed_range11_histogram(self):
        result = self.solution.largestRectangle(range(10,-1,-1))
        self.assertEqual(result, 6 * 5)

    # [0 .. 10000]
    def test_range10k_histogram(self):
        result = self.solution.largestRectangle(range(10001))
        self.assertEqual(result, 5000 * 5001)

    def test_range_ordered_histogram(self):
        ends = [pow(10,x) + 1 for x in range(1, 5)]
        for hi in ends:
            result = self.solution.largestRectangle(range(hi))
            self.assertEqual(result, hi / 2 * (hi / 2 + 1))

    # def test_reverse_range_histogram(self):
    #     ends = [pow(10,x) + 1 for x in range(1, 5)]
    #     for hi in ends:
    #         result = self.solution.largestRectangle(range(hi-1, -1, -1))
    #         self.assertEqual(result, hi / 2 * (hi / 2 + 1))

class PartitionByMinTestCase(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()
        self.start    = time.time()

    def tearDown(self):
        dt = time.time() - self.start
        print "%s: %.3f" % (self.id(), dt)

    def test_partition_all_item(self):
        vals = [7, 6, 8, 3, 4, 2, 3, 6, 9, 10]
        (minv, parts) = self.solution.partitionByMin(vals, 0, 10)
        self.assertEqual(minv, 2)
        self.assertEqual(parts, [[0, 5], [6, 10]])

    def test_partition_left(self):
        vals = [7, 6, 8, 3, 4, 2, 3, 6, 9, 10]
        (minv, parts) = self.solution.partitionByMin(vals, 0, 5)
        self.assertEqual(minv, 3)
        self.assertEqual(parts, [[0, 3], [4, 5]])

    def test_partition_consecutive(self):
        vals = [7, 6, 8, 3, 4, 2, 3, 6, 9, 10]
        (minv, parts) = self.solution.partitionByMin(vals, 4, 5)
        self.assertEqual(minv, 4)
        self.assertEqual(parts, [])

    def test_partition_one_item(self):
        vals = [7, 6, 8, 3, 4, 2, 3, 6, 9, 10]
        (minv, parts) = self.solution.partitionByMin(vals, 4, 4)
        self.assertEqual(minv, 4)
        self.assertEqual(parts, [])

    def test_partition_many(self):
        vals = [7, 3, 8, 3, 4, 3, 7, 6, 3, 10]
        (minv, parts) = self.solution.partitionByMin(vals, 0, 10)
        self.assertEqual(minv, 3)
        self.assertEqual(parts, [[0, 1], [2, 3], [4, 5], [6, 8], [9, 10]])

if __name__ == '__main__':
    unittest.main()
