(ns katas.clj.java-leetcode-test
  "Testing Leetcode java solutions using clojure"
  (:require [clojure.test :refer :all])
  (:import [katas.java LeetCode]))

(deftest test-sort-colors
  (are [xs ys] (= ys (let [nums (int-array xs)]
                       (do (LeetCode/sortColors nums)
                           (vec nums)))) 
       [1] [1]
       [1 0] [0 1]
       [2 0] [0 2]
       [2 0 1 2] [0 1 2 2]
       [2 2 2 2] [2 2 2 2]))

(deftest test-sort-colors-one-pass
  (are [xs ys] (= ys (let [nums (int-array xs)]
                       (do (LeetCode/sortColors3Way nums)
                           (vec nums)))) 
       [1] [1]
       [1 0] [0 1]
       [2 0] [0 2]
       [2 2 2 2] [2 2 2 2]
       [2 0 1 2] [0 1 2 2]))
