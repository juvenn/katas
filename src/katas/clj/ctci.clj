(ns
  ^{:doc "Solutions to the book Cracking the Coding Interview"}
  katas.clj.ctci)

(defn
  ^{:qn "1.1"
    :doc "Given a string, test if charaters are unique."}
  chars-unique? [s]
  (loop [m     {}
         chars s]
    (let [c   (first chars)
          cnt (get m c 0)]
      (cond
        (nil? c)  true
        (> cnt 0) false
        :else (recur (assoc m c 1) (rest chars))))))
