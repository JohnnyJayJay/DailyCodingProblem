; Given a start word, an end word, and a dictionary of valid words, find the shortest transformation
; sequence from start to end such that only one letter is changed at each step of the sequence,
; and each transformed word exists in the dictionary. If there is no possible transformation, return null.
; Each word in the dictionary have the same length as start and end and is lowercase.
;
; For example, given start = "dog", end = "cat", and dictionary = {"dot", "dop", "dat", "cat"},
; return ["dog", "dot", "dat", "cat"].
;
; Given start = "dog", end = "cat", and dictionary = {"dot", "tod", "dat", "dar"},
; return null as there is no possible transformation from dog to cat.
(ns november19.19)

; Returns whether one can be transformed into two, that is, whether they differ in one character max.
(defn transformable? [one two]
  (< (count (filter identity (map not= one two))) 2))


(defn transformations
  ([current end dictionary current-transform]
   (cond
     ; if the current word can be transformed into the end word, return the transform path so far (current-transform) + the end word
     (transformable? current end) (conj current-transform end)
     ; if no more transformations are possible, return an empty vector
     (empty? dictionary) []
     ; else recur for each possible transformation in the dictionary as the current word
     :else (for [possible (filter (partial transformable? current) dictionary)]
             (transformations possible end (disj dictionary possible) (conj current-transform possible)))))
  ([start end dictionary] (apply min-key count (transformations start end dictionary [start])))) ; TODO flatten result

(println (transformations "dog" "cat" #{"dot" "dop" "dat" "cat"} []))