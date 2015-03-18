#!/usr/bin/env python3

# Test suite for solutions of Cracking the code interview

import unittest
from katas.python.ctci import CTCI

class TestCTCISolutions(unittest.TestCase):
    def test_true(self):
        self.assertTrue(True == 1)

    def test_remove_duplicate_in_linkedlist(self):
        self.assertTrue(0)

if __name__ == "__main__":
    unittest.main()
