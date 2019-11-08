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

; Returns the element of the matrix that is at the specified x-y position, or 1 if the position is out of bounds
(defn position [matrix x y]
  (-> matrix (get y []) (get x 1)))

; Whether there is a 1 at the specified position in the matrix
(defn blocked? [matrix x y]
  (= 1 (position matrix x y)))

; Returns whether x-y represents the very last position of the given matrix (bottom right corner)
(defn end? [matrix x y]
    (and (= y (dec (count matrix))) (= x (dec (count (last matrix))))))

; Recursively calculates the amount of paths in a matrix that lead from a starting position x-y
; to the very last position (bottom right corner). 1's represent borders, 0's ways to pass through.
(defn paths
  ([matrix x y n]
   (cond
     (blocked? matrix x y) 0 ; If there is a border at this position, return 0
     (end? matrix x y) 1 ; If this position is the last position, return 1
     :else (+ n (paths matrix (inc x) y n) (paths matrix x (inc y) n)))) ; Else: the amount of paths is increased by the amount of paths starting from one position below the current position and one position right of the current position
  ([matrix] (paths matrix 0 0 0))) ; Overload that starts at the top left corner with 0 paths found so far

(println (paths [[0 0 1]
                 [0 0 1]
                 [1 0 0]]))
