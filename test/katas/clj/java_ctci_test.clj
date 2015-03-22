(ns katas.clj.java-ctci-test
  "Testing java CTCI using clojure"
  (:require [clojure.test :refer :all]
            [katas.clj.ctci :as ctci])
  (:import [katas.java CTCI LinkedListNode]
            ))

(deftest test-rotate-square-matrix
  (testing "it should handle small matrix"
    (are [v1 v2] (= v2 (->> (to-array-2d v1)
                            CTCI/rotateSquareMatrix
                            (map vec)))
         [[:a]] [[:a]]
         [[:a :b] [:c :d]] [[:c :a][:d :b]]
         [[:a :b :c] [:d :e :f] [:g :h :i]]
         [[:g :d :a] [:h :e :b] [:i :f :c]]
         ))
  (testing "it should return same as clojure version"
    (are [v] (= (ctci/rotate-matrix v) (->> (to-array-2d v)
                                            CTCI/rotateSquareMatrix
                                            (map vec)))
         [[:a :b :c] [:d :e :f] [:g :h :i]]
         [[:a :b :c] [:f :d :e] [:g :h :i]]
         [[:b :c :a] [:e :d :f] [:h :g :i]]
         [[:a :b :c] [:d :f :e] [:i :h :g]]
         )))


(defn make-linked-list
  "Make a list of node from values in sequence, join them by applying joinf
  on adjacent nodes. `joinf` should take two adjacent nodes, and return the
  second one.

  Examples:

      (make-linked-list SinglyNode
                        #(set! (. %1 next) %2)
                        [1 2 3])

      (make-linked-list DoublyNode
                        #((set! (. %2 prev) %1)
                          (set! (. %1 next) %2))
                        [1 2 3])
  "
  [klass joinf xs]
  (let [nodes (map #(clojure.lang.Reflector/invokeConstructor
                      klass (into-array [%]))
                   xs)
        head (first nodes)]
    (when head (reduce #(joinf %1 %2) head (rest nodes)))
    head))


(defn- test-remove-duplicate-f
  ^{:qn 2.1}
  [removefn]
  (testing "it shall not change a list of all distinct node"
    (are [xs] (= xs (-> (make-linked-list LinkedListNode
                                          #(set! (. %1 next) %2) xs)
                        removefn
                        seq))
         [0]
         [0 1]
         [0 1 2]
         [0 1 2 3]
         [0 1 2 3 4 5]
         [10 9 8 5 4 3 2 0]))
  (testing "it shall remove duplicate items in a list"
    (are [xs xs1] (= xs1 (-> (make-linked-list LinkedListNode
                                               #(set! (. %1 next) %2) xs)
                             removefn
                             seq))
         [] nil
         [1 1] [1]
         [1 2 1] [1 2]
         [1 2 1 1] [1 2]
         [1 2 1 1 1 1] [1 2]
         [1 2 1 2 1 2 3] [1 2 3]
         [1 2 3 3 2 1 1 2 3 4] [1 2 3 4])))

(deftest test-remove-duplicate
  "Test different implementations of remove-duplicate."
  ^{:qn 2.1}
  (test-remove-duplicate-f #(CTCI/removeDuplicate %))
  (test-remove-duplicate-f #(CTCI/removeDuplicateWithoutBuffer %)))

(deftest test-remove-node-in-middle
  "Test remove node in the middle of singly linked list."
  ^{:qn 2.3}
  (testing "it shall raise exception when remove a singluar node"
    (is (thrown? IllegalArgumentException
                 (CTCI/removeNodeInMiddle (LinkedListNode. 42)))))
  (testing "it shall return sequence with one item removed"
    (loop [xs (range 0 4)
           head (make-linked-list LinkedListNode
                                  #(set! (. %1 next) %2) xs)]
      (when (> (count xs) 1)
        (let [n (-> (count xs) dec rand-int)
              xs1 (concat (take n xs) (drop (inc n) xs))
              node (.walk head n)]
          (CTCI/removeNodeInMiddle node)
          (is (= xs1 (seq head)))
          (recur xs1 head))))
    ))
