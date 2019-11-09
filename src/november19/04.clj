; Given a list of elements, find the majority element, which appears
; more than half the time (> floor(len(lst) / 2.0)).
;
; You can assume that such element exists.
;
; For example, given [1, 2, 1, 1, 3, 4, 0], return 1.
(ns november19.04)

(defn majority-element [coll]
  (key (apply max-key val (frequencies coll)))) ; map coll's values to their occurrences in col and find the max value

(println (majority-element [1 2 1 1 3 4 0]))