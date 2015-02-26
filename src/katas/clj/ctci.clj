(ns
  ^{:doc "Solutions to the book Cracking the Coding Interview"}
  katas.clj.ctci)

(defn chars-unique?
  "Test if characters in string are unique."
  ^{:qn "1.1"}
  [s]
  (loop [counts {}
         chars  s]
    (let [c   (first chars)
          cnt (get counts c 0)]
      (cond
        (nil? c)  true
        (> cnt 0) false
        :else (recur (assoc counts c (inc cnt)) (rest chars))))))

(defn reverse-str
  "Reverse c-style string, including null character."
  ^{:qn "1.2"}
  [s]
  (loop [res ()
         chars s]
    (if (empty? chars) (apply str res)
      (recur (conj res (first chars))
             (rest chars)))))

(defn remove-duplicate
  "Remove dulicate characters in a string."
  ^{:qn "1.3"}
  [s]
  (loop [chars  s
         res    []
         counts {}]
    (let [c (first chars)
          cnt (get counts c 0)]
      (if (nil? c) (apply str res)
        (recur (rest chars)
               (if (> cnt 0) res (conj res c))
               (assoc counts c (inc cnt)))))))

(defn anagrams?
  "Are two strings anagrams?"
  ^{:qn "1.4"}
  [s1 s2]
  (if (= (count s1) (count s2))
    (= s1 (reverse-str s2))
    false))

(defn encode-space
  "Encode space as '%20' in a string."
  ^{:qn "1.5"}
  [s]
  (apply str (map #(if (= \space %) "%20" %) s)))

(defn rotate-matrix
  "Rotate matrix 90 degrees clockwise."
  ^{:qn "1.6"}
  [m]
  (apply map (fn [& more] (reverse (vec more))) m))
