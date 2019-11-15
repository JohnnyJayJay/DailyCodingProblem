; Given an array of integers, return a new array where each element in the new array
; is the number of smaller elements to the right of that element in the original input array.
;
; For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:
;
;    There is 1 smaller element to the right of 3
;    There is 1 smaller element to the right of 4
;    There are 2 smaller elements to the right of 9
;    There is 1 smaller element to the right of 6
;    There are no smaller elements to the right of 1
(ns november19.14)

; I really don't know what to call this function
(defn task [input]
  (for [index (range (count input))]
    (count (filter (partial > (nth input index)) (drop (inc index) input)))))

(println (task [3 4 9 6 1]))