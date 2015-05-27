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

(deftest test-zig-zag-convert
  (are [ins rows outs] (= outs (LeetCode/zigZagConvert ins rows))
       "zigzagtictoe" 1 "zigzagtictoe"
       "zigzagtictoe" 2 "zgatcoizgite"
       "paypalishiring" 3 "pahnaplsiigyir"
       "zig" 3 "zig"
       "zigz" 3 "zizg"
       "zigza" 3 "zaizg"
       "zigzag" 3 "zaizgg"
       "zigzagz" 3 "zaizggz"
       "zigzagzi" 3 "zaizgigz"
       "zigzagzig" 3 "zagizgigz"
       ))

(deftest test-zig-zag-convert-rows
  (are [ins rows outs] (= outs (LeetCode/zigZagConvertRows ins rows))
       "zigzagtictoe" 1 "zigzagtictoe"
       "zigzagtictoe" 2 "zgatcoizgite"
       "paypalishiring" 3 "pahnaplsiigyir"
       "zig" 3 "zig"
       "zigz" 3 "zizg"
       "zigza" 3 "zaizg"
       "zigzag" 3 "zaizgg"
       "zigzagz" 3 "zaizggz"
       "zigzagzi" 3 "zaizgigz"
       "zigzagzig" 3 "zagizgigz"
       ))


(deftest test-two-sum
  (are [nums sum tuple] (= tuple
                           (-> (int-array nums)
                               (LeetCode/twoSum sum)
                               vec))
       [] 0 []
       [2 7 11 15] 0 []
       [-2 7 2 15] 0 [1 3]
       [2 7 11 15] 10 []
       [2 7 11 15] 22 [2 4]
       [2 7 11 15] 9 [1 2]))

(deftest test-two-sum-linear
  (are [nums sum tuple] (= tuple
                           (-> (int-array nums)
                               (LeetCode/twoSumLinear sum)
                               vec))
       [] 0 []
       [3 2 4] 6 [2 3]
       [2 7 11 15] 0 []
       [-2 7 2 15] 0 [1 3]
       [2 7 11 15] 10 []
       [2 7 11 15] 22 [2 4]
       [2 7 12 15] 9 [1 2]))

(deftest test-reverse-integer
  (are [n m] (= m (LeetCode/reverseInteger n))
       0    0
       12   21
       120  21
       102  201
       123  321
       -123 -321
       1463847412  2147483641
       2147447412  2147447412
       2147447413  0
       2147483647  0
       -2147483648 0
       1534236469  0
       -1534236469 0
       ))

(deftest test-is-int-palindrome
  (are [n bool] (= bool (LeetCode/isPalindrome n))
       0 true
       11 true
       12 false
       131 true
       1221 true
       1000021 false
       1463847412 false
       2147447412 true
       2147347412 false
       2147483647 false
       )
  (are [n bool] (= bool (LeetCode/isPalindrome2 n))
       0 true
       11 true
       12 false
       131 true
       1221 true
       1000021 false
       1463847412 false
       2147447412 true
       2147347412 false
       2147483647 false
       )
  )
