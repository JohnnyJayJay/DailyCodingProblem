; Given a linked list and a positive integer k, rotate the list to the right by k places.
;
; For example, given the linked list 7 -> 7 -> 3 -> 5 and k = 2, it should become 3 -> 5 -> 7 -> 7.
;
; Given the linked list 1 -> 2 -> 3 -> 4 -> 5 and k = 3, it should become 3 -> 4 -> 5 -> 1 -> 2.
(ns november19.26)

(defn rotate [k coll]
  (apply concat (reverse (split-at k coll))))

(println (rotate 2 '(7 7 3 5)))
(println (rotate 3 '(1 2 3 4 5)))