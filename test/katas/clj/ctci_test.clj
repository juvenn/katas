(ns katas.clj.ctci-test
  (:require [clojure.test :refer :all]
            [katas.clj.ctci :as ctci]))

(deftest test-chars-unique?
  (testing "it should return true on empty string"
    (is (= true (ctci/chars-unique? ""))))
  (testing "it should work for normal strings"
    (is (= true (ctci/chars-unique? "h")))
    (is (= true (ctci/chars-unique? "he")))
    (is (= true (ctci/chars-unique? "hel")))
    (is (= false (ctci/chars-unique? "hell")))
    (is (= false (ctci/chars-unique? "hello")))
    ))
