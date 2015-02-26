(ns katas.clj.java-ctci-test
  "Testing java CTCI using clojure"
  (:require [clojure.test :refer :all]
            [katas.clj.ctci :as ctci])
  (:import (katas.java CTCI)))

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
