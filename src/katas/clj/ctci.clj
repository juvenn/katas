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

(defn anagram?
  "Are two strings anagram?

  String A is said to be anagram of string B, if B is a rearranging of
  characters from A.
  "
  ^{:qn "1.4"}
  [s1 s2]
  (if (not= (count s1) (count s2)) false
    ; key-index counting should be equal
    (let [incf #(assoc %1 %2 (inc (get %1 %2 0)))]
      (= (reduce incf {} s1)
         (reduce incf {} s2)))
    ))

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

(defn zeroify-matrix
  "For every zero entry in matrix m, set entire row and column to 0"
  ^{:qn "1.7"}
  [m]
  (let [cols (count (first m))
        v (flatten m)
        zero-entries (->> v (map-indexed vector)
                          (filter #(= 0 (second %1)))
                          (map (fn [[i _]]
                                 [(quot i cols) (rem i cols)])))
        zero-rows (set (map first zero-entries))
        zero-cols (set (map second zero-entries))
        to-zero? #(or (contains? zero-rows (quot %1 cols))
                      (contains? zero-cols (rem  %1 cols)))]
    (->> v
         (map-indexed #(if (to-zero? %1) 0
                         %2))
         (partition cols))))

(defn is-rotation?
  "Test if a string is rotation of the other."
  ^{:qn "1.8"}
  [s1 s2]
  (let [s (str s1 s1)]
    (if (= (.length s1) (.length s2)) (.contains s s2)
      false)))
