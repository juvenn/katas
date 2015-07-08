(ns ppraxis-test
  "Test for programmingpraxis.com exercises."
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [ppraxis :as pprax]))

(deftest test-ordered-cartesian-coords
  (testing "it generates pairs for simple cases"
    (are [x y] (= y (pprax/ordered-cartesian-coords x))
         1 [[1 1]]
         2 [[1 1] [1 2] [2 1] [2 2]]
         3 [[1 1] [1 2] [2 1] [1 3] [3 1] [2 2] [2 3] [3 2] [3 3]]
         4 [[1 1] [1 2] [2 1] [1 3] [3 1] [1 4] [2 2] [4 1]
            [2 3] [3 2] [2 4] [4 2] [3 3] [3 4] [4 3] [4 4]])))

(defspec first-of-ordered-cartesian-coords-is-1-1
  (prop/for-all [n gen/s-pos-int]
                (= [1 1] (first (pprax/ordered-cartesian-coords n)))))

(defspec last-of-ordered-cartesian-coords-is-n-n
  (prop/for-all [n gen/s-pos-int]
                (= [n n] (last (pprax/ordered-cartesian-coords n)))))

(defspec ordered-cartesian-coords-have-n-squared-elements
  (prop/for-all [n gen/s-pos-int]
                (= (* n n) (count (pprax/ordered-cartesian-coords n)))))

(defspec ordered-cartesian-coords-should-be-idempotent-on-sort
  (prop/for-all [n gen/s-pos-int]
                (let [pairs (pprax/ordered-cartesian-coords n)]
                  (= pairs (sort-by (fn [[x y]] (* x y)) pairs)))))
