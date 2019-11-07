; You are given an N by M matrix of 0s and 1s. Starting from the top left corner,
; how many ways are there to reach the bottom right corner?
;
; You can only move right and down. 0 represents an empty space while 1
; represents a wall you cannot walk through.
;
; For example, given the following matrix:
;
; [[0, 0, 1],
;  [0, 0, 1],
;  [1, 0, 0]]
; Return two, as there are only two ways to get to the bottom right:
;
; - Right, down, down, right
; - Down, right, down, right
; The top left corner and bottom right corner will always be 0.
(ns november19.07)

(defn position [matrix x y]
  (-> matrix (get y []) (get x 1)))

(defn blocked? [matrix x y]
  (= 1 (position matrix x y)))

(defn end? [matrix x y]
    (and (> y (count matrix)) (> x (count (last matrix)))))

; FIXME not working yet
(defn paths
  ([matrix x y n]
   (cond
     (end? matrix x y) 1
     (blocked? matrix x y) 0
     :else (+ n (paths matrix (inc x) y n) (paths matrix x (inc y) n))))
  ([matrix] (paths matrix 0 0 0)))

(println (paths [[0 0 1]
                 [0 0 1]
                 [1 0 0]]))
