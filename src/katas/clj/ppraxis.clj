(ns katas.clj.ppraxis
  "Exercises from programmingpraxis.com")

(defn cartesian-product
  [xs ys]
  "Take two sequence of distinct integers, generate sequence of cartesian
   products."
  (reduce (fn [res x] (into res (map #(vector x %) ys)))
          []
          xs))

(defn ordered-cartesian-coords
  [n]
  "Ordered cartesian coordinates

   Take an integer n, generate pairs of cartesian coordinates within a
   square bounded by (1,1) and (n,n), in ascending order in product. E.g.:

       (ordered-cartesian-coords 3)
       ;; =>  [[1 1] [1 2] [2 1] [1 3] [3 1] [2 2] [2 3] [3 2] [3 3]]
  "
  (sort-by (fn [[x y]] (* x y))
           (cartesian-product (range 1 (inc n))
                              (range 1 (inc n)))))
