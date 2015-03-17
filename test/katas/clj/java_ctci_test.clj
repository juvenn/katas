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
  (let [nodes (map #(clojure.lang.Reflector/invokeConstructor klass (into-array [%])) xs)
        head (first nodes)]
    (when head
      (reduce #(joinf %1 %2) head (rest nodes)))
    head)
  )


(deftest test-remove-duplicate
  ^{:qn 2.1}
  (testing "it shall not change a list of all distinct node"
    (are [xs] (= xs (-> (make-linked-list LinkedListNode
                                          #(set! (. %1 next) %2) xs)
                        (doto CTCI/removeDuplicate)
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
                             (doto CTCI/removeDuplicate)
                             seq))
         [] nil
         [1 1] [1]
         [1 2 1] [1 2]
         [1 2 1 1] [1 2]
         [1 2 1 1 1 1] [1 2]
         [1 2 1 2 1 2 3] [1 2 3]
         [1 2 3 3 2 1 1 2 3 4] [1 2 3 4])))
