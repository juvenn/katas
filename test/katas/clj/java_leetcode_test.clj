(ns katas.clj.java-leetcode-test
  "Testing Leetcode java solutions using clojure"
  (:require [clojure.test :refer :all])
  (:import [katas.java LeetCode ListNode TreeNode UndirectedGraphNode]))

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

(deftest test-count-and-say
  (are [n s] (= s (LeetCode/countAndSay n))
       1 "1"
       2 "11"
       3 "21"
       4 "1211"))

(deftest test-next-say
  (are [s1 s2] (= s2 (-> (StringBuilder. s1)
                         LeetCode/nextSay
                         .toString))
       "1" "11"
       "11" "21"
       "21" "1211"))

(defn to-list-node
  "Build list node from a sequence of integers."
  [[^int n & xs]]
  (if (nil? n) nil
      (let [head (ListNode. n)]
       (reduce (fn [x m]
                 (set! (. x next) (ListNode. m)))
               head xs)
       head)))

(defn from-list-node
  "Return vector from linked list node."
  [^ListNode head]
  (loop [vec []
         x head]
    (if (nil? x) vec
        (recur (conj vec (.val x)) (.next x)))))

(deftest test-to-list-node
  (are [xs] (= xs (-> xs to-list-node
                      from-list-node))
       []
       [2]
       [2 3]
       [2 3 5]
       [2 3 5 7]
       ))

(deftest test-reverse-list
  (are [xs] (= (reverse xs)
               (-> xs
                   to-list-node
                   LeetCode/reverseList
                   from-list-node))
       []
       [2]
       [2 3]
       [2 3 5]
       [2 3 2 5 3 7])
  (are [xs] (= (reverse xs)
               (-> xs
                   to-list-node
                   LeetCode/reverseListRecur
                   from-list-node))
       []
       [2]
       [2 3]
       [2 3 5]
       [2 3 2 5 3 7])
  )


(deftest test-min-window
  (are [s t window] (= window (LeetCode/minWindow s t))
       "acbbaca" "aba" "baca"
       "this is a test string" "tist" "t stri"
       "" "ABC" ""
       "ADOBECODEBANC" "" ""
       "ADOBECODEBANC" "ABC" "BANC"
       "ADOBECODEBANC" "BC" "BEC"
       "ADOBECODEBANC" "ABCC" "CODEBANC"
       "ADOBECODEBANC" "CC" "CODEBANC"
       ))

(defmulti cast!
  "Cast x to specified type."
  (fn [^Class type x] type))
(defmethod cast! Character [_ x] (char x))
(defmethod cast! Character/TYPE [_ x] (char x))
(defmethod cast! Integer [_ x] (int x))
(defmethod cast! Integer/TYPE [_ x] (int x))


(defn array2d
  "Convert collection of collection into 2-dimensional array with optional type.
   The default is Object, and the inner collection must have consistent length.

  See also: clojure.core.to-array-2d.
  "
  ([coll] (array2d Object coll))
  ([^Class type ^java.util.Collection coll]
   (let [[x :as xs] (seq coll)
         len (count (seq x))
         ret (make-array type (. coll (size)) len)]
    (loop [i 0 xs xs]
      (when-let [[x & ys] xs]
        (assert (= len (count x)))
        (doseq [[k v] (map-indexed #(vector %1 %2) x)]
          (aset ret i k (cast! type v)))
        (recur (inc i) ys)))
    ret))
  )

(deftest test-num-islands
  (are [a-map n] (= n (let [grid (array2d Character/TYPE a-map)]
                         (LeetCode/numIslands grid)))
       ["0"] 0
       ["1"] 1
       ["00"
        "00"] 0
       ["00"
        "01"] 1
       ["10"
        "01"] 2
       ["1001100"] 2
       ["11110"
        "11010"
        "11000"
        "00000"] 1
       ["11000"
        "11000"
        "00100"
        "00011"] 3
 ))

(deftest test-max-subarray
  (are [xs sum] (= sum (LeetCode/maxSubArray (int-array xs)))
       [] 0
       [1] 1
       [1 2 3] 6
       [2 0 1 1] 4
       [-3 -2 -1] -1
       [-1 -2 -3 -1] -1
       [-2 1 -3 4 -1 2 1 -5 4] 6
       [8 -19 5 -4 20] 21
       )
  (are [xs sum] (= sum (LeetCode/maxSubArray3 (int-array xs)))
       [] 0
       [8 -19 5 -4 20] 21))

(deftest test-minimum-hp
  (are [xs hp] (= hp (let [grid (array2d Integer/TYPE xs)]
                       (LeetCode/calculateMinHP grid)))
       [[0]] 1
       [[1]] 1
       [[9]] 1
       [[-2 4]] 3
       [[-2 -3 3]
        [-5 -10 1]
        [10 30 -5]] 7))

(deftest test-sorted-array-to-bst
  (is (nil? (LeetCode/sortedArrayToBST (int-array []))))
  (are [xs rootval] (= rootval (-> xs
                                   int-array
                                   LeetCode/sortedArrayToBST
                                   (.. val)))
       [1] 1
       [1 2] 2
       [1 2 3] 2
       [1 2 3 4] 3
       [1 2 3 4 5] 3
       [1 2 3 4 5 6] 4
       ))

(defn seq-to-listnode
  [[^int x & xs]]
  (when x
    (let [head (ListNode. x)]
      (reduce #(set! (. %1 next) (ListNode. %2)) head xs)
      head))
  )

(deftest test-sorted-list-to-bst
  (testing "tree size should match"
    (are [xs] (= (count xs) (-> xs
                                seq-to-listnode
                                LeetCode/sortedListToBST
                                .size))
         (range 1)
         (range 7)
         (range 10)
         (range 20)
         (range 100)
         (range 133)
         (range 203)
         ))
  (testing "tree height should be between log2(N) and log3(N)"
    (are [xs] (let [n (count xs)
                    hi (/ (Math/log n) (Math/log 2))
                    lo (/ (Math/log n) (Math/log 3))
                    h (-> xs
                          seq-to-listnode
                          LeetCode/sortedListToBST
                          .height)]
                (or (>= h lo) (<= h hi)))
         (range 1)
         (range 7)
         (range 10)
         (range 20)
         (range 100)
         (range 133)
         (range 203)
         )))

(defn tree
  "Build binary tree from nested collection of integers.

  E.g. (tree '(2 (1 3))) => 1->2<-3
       (tree '(2 (1 nil))) => 1->2
       (tree '(2 (nil 3))) => 2<-3
       (tree '(2 (nil 5 (4 6)))) => 2<-(4->3<-6)
  "
  [[x & children]]
  (cond
    (nil? x) nil
    (empty? children) (TreeNode. x)
    :else
    (let [[[lv v & pair]] children                       ;; left value
            rv (if (coll? v) (first pair) v)             ;; right value
            lc (if (coll? v) v [])                       ;; left children
            rc (if (coll? v) (second pair) (first pair)) ;; right children
            node (tree [x])]
      (set! (. node left)  (tree [lv lc]))
      (set! (. node right) (tree [rv rc]))
      node)
    ))

(deftest test-tree
  (are [xs res] (= res (-> xs tree .inOrder))
       [2] [2]
       [2 [1 3]] [1 2 3]
       [2 [nil 3]] [2 3]
       [2 [1 nil]] [1 2]
       [2 [nil 5 [4 6]]] [2 4 5 6]
       [4 [2 [1 3] 7 [6 9]]] [1 2 3 4 6 7 9]))

(deftest test-invert-binary-tree
  (testing "it should invert simple trees"
    (are [coll xs] (= xs (-> coll tree
                             (LeetCode/invertTree)
                             .inOrder))
         [2] [2]
         [2 [1 3]] [3 2 1]
         [2 [nil 3]] [3 2]
         [2 [1 nil]] [2 1]
         [2 [nil 5 [4 6]]] [6 5 4 2]
         [4 [2 [1 3] 7 [6 9]]] [9 7 6 4 3 2 1]))
  (testing "double invert should be idempotent"
    (are [coll xs] (= xs (-> coll tree
                             LeetCode/invertTree
                             LeetCode/invertTree
                             .inOrder))
         [2 [1 3]] [1 2 3]
         [2 [nil 3]] [2 3]
         [2 [1 nil]] [1 2]
         [2 [nil 5 [4 6]]] [2 4 5 6]
         [4 [2 [1 3] 7 [6 9]]] [1 2 3 4 6 7 9])))

(deftest test-hamming-weight
  (are [n weight] (= weight (LeetCode/hammingWeight n))
       0 0
       1 1
       2 1
       3 2
       4 1
      -1 32 
      -2 31
      -3 31
      -4 30
      Integer/MAX_VALUE 31
      Integer/MIN_VALUE 1
       ))

;; FIX: how to transform graph to and from clojure data structure
(deftest test-clone-graph
  (is (nil? (LeetCode/cloneGraph nil)))
  (is (= 42 (-> (UndirectedGraphNode. 42)
                LeetCode/cloneGraph
                .label))))

(defn compute-area
  "Wrapper for java computeArea"
  [a b c d e f g h]
  (LeetCode/computeArea a b c d e f g h))

(deftest test-compute-area
  (are [xs area] (= area (apply compute-area xs))
       [0 0 5 4
        3 -2 6 3] 29
       [0 0 5 4
        3 -2 6 0] 26
       [0 0 5 4
        3 -2 5 3] 24
        [-1500000001 0 -1500000000 1
         1500000000 0 1500000001 1] 2
        ))

(deftest test-find-first-missing-positive
  (are [xs min] (= min (-> xs int-array
                           LeetCode/findFirstMissingPositive))
       [0] 1
       [1] 2
       [2] 1
       [1 2 0] 3
       [3 4 -1 1] 2
       [2 4 6 8 5 3 1] 7
       (range 1 100) 100))

(deftest test-atoi
  (testing "valid number"
    (are [s n] (= n (LeetCode/myAtoi s))
         "0" 0
         "42" 42
         "+42" 42
         "001" 1
         "-001" -1
         "402" 402
         "2147483647" 2147483647
         "-2147483648" -2147483648
         ))
  (testing "invalid number"
    (are [s n] (= n (LeetCode/myAtoi s))
         "" 0
         "2h4" 2
         "2-4" 2
         "2 4" 2))
  (testing "integer overflow"
    (are [s n] (= n (LeetCode/myAtoi s))
         "2147483648" 2147483647
         "-2147483649" -2147483648
         "9999999999" 2147483647
         ))
  )

(deftest test-find-repeated-dna-sequences
  (are [s xs] (= xs (LeetCode/findRepeatedDnaSequences s))
       "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT" ["AAAAACCCCC" "CCCCCAAAAA"]
       ))

