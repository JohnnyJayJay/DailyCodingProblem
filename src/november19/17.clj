; Given an N by N matrix, rotate it by 90 degrees clockwise.
;
; For example, given the following matrix:
;
; [[1, 2, 3],
;  [4, 5, 6],
;  [7, 8, 9]]
; you should return:
;
; [[7, 4, 1],
;  [8, 5, 2],
;  [9, 6, 3]]
; Follow-up: What if you couldn't use any extra space?
(ns november19.17)

(defn rotate [matrix, n]
  (let [indices (range n)                                   ; 0..n
        reversed (reverse indices)]             ; n..0
    (apply vector (for [column indices]                     ; iterate over columns
                    (apply vector (for [row reversed] ((matrix row) column))))))) ; iterate over reversed rows

;; Follow-up is not directly possible in Clojure

(println (rotate [[1 2 3]
                  [4 5 6]
                  [7 8 9]] 3))