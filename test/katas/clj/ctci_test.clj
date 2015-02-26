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

(deftest test-reverse-str
  ^{:qn "1.2"}
  (testing "it should return empty string"
    (is (= "" (ctci/reverse-str ""))))
  (testing "it should reverse small strings"
    (is (= "h" (ctci/reverse-str "h")))
    (is (= "he" (ctci/reverse-str "eh")))
    (is (= "hel" (ctci/reverse-str "leh")))
    (is (= "hell" (ctci/reverse-str "lleh")))
    (is (= "hello" (ctci/reverse-str "olleh")))
    ))

(deftest test-remove-duplicate
  ^{:qn "1.3"}
  (testing "it should return empty string"
    (is (= "" (ctci/remove-duplicate ""))))
  (testing "it should handle small strings"
    (is (= "h" (ctci/remove-duplicate "h")))
    (is (= "he" (ctci/remove-duplicate "he")))
    (is (= "hel" (ctci/remove-duplicate "hel")))
    (is (= "hel" (ctci/remove-duplicate "hell")))
    (is (= "oleh" (ctci/remove-duplicate "olleh")))
    (is (= "helo" (ctci/remove-duplicate
                    "hellohellohellohello")))
    (is (= "helo" (ctci/remove-duplicate "hello")))))

(deftest test-anagrams?
  ^{:qn "1.4"}
  (testing "two empty strings should return true"
    (is (= true (ctci/anagrams? "" "")))
    )
  (testing "strings with different length should return false"
    (are [s1 s2] (= false (ctci/anagrams? s1 s2))
         "h"     ""
         "he"    "h"
         "hel"   "he"
         "hell"  "hel"
         "hello" "hell"
         ))
  (testing "string and its reverse should return true"
    (are [s] (ctci/anagrams? s (apply str (reverse s)))
         "h"
         "he"
         "hel"
         "hell"
         "hello"
         ))
  )

(deftest test-encode-space
  ^{:qn "1.5"}
  (testing "it should return itself for string without space"
    (are [s] (= s (ctci/encode-space s))
         ""
         "h"
         "he"
         "hel"
         "hell"
         "hello"))
  (testing "it should replace all spaces with '%20'"
    (are [s1 s2] (= s2 (ctci/encode-space s1))
         " " "%20"
         "a " "a%20"
         "a b" "a%20b"
         "a b " "a%20b%20"
         "a  b" "a%20%20b"
         "a b c " "a%20b%20c%20"
         )))

(deftest test-rotate-matrix
  ^{:qn "1.6"}
  (testing "it should handle small matrix"
    (are [m1 m2] (= m2 (ctci/rotate-matrix m1))
         [[:a]] [[:a]]
         [[:a :b] [:c :d]] [[:c :a][:d :b]]
         [[:a :b :c] [:d :e :f] [:g :h :i]]
         [[:g :d :a] [:h :e :b] [:i :f :c]]))
  (testing ""))
