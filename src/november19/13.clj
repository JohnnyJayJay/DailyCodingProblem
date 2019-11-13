; You are given an array of length n + 1 whose elements belong to the set {1, 2, ..., n}.
; By the pigeonhole principle, there must be a duplicate. Find it in linear time and space.
(ns november19.13)

(defn find-duplicate [coll]
  (- (apply + coll) (apply + (range (count coll))))) ; sum of all numbers in coll - sum of all integers from 1 to n = duplicate

(println (find-duplicate [1 2 3 4 7 5 6 7 8 9]))