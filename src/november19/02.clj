; Find an efficient algorithm to find the smallest distance (measured in number of words)
; between any two given words in a string.
;
; For example, given words "hello", and "world" and a text content of
; "dog cat hello cat dog dog hello cat world", return 1 because
; there's only one word "cat" in between the two words.

; generic function to calculate smallest distance between two elements in a collection (or nil if not present in this order)
(defn distance [coll element1 element2]
  (let [size (count coll)]
    (loop [index 0 shortest size counting 0]
      (if (>= index size)
        (if (< shortest size) (dec shortest) nil)
        (let [element (nth coll index)]
          (condp = element
            element1 (recur (inc index) shortest 1)
            element2 (if (and (> counting 0) (< counting shortest))
                       (recur (inc index) counting 0)
                       (recur (inc index) shortest 0))
            (recur (inc index) shortest (if (> counting 0) (inc counting) 0))))))))

; splits string into words and calls distance (also distance with the words swapped if no result)
(defn word-distance [str word1 word2]
  (let [words (clojure.string/split str #"\s+") d (distance words word1 word2)]
    (if d d (distance words word2 word1))))

; Tests
(println (word-distance "dog cat hello cat dog dog hello cat world" "hello" "world")) ; 1
(println (word-distance "dog cat hello cat dog dog cat world" "hello" "world")) ; 4
(println (word-distance "dog cat world cat dog dog cat hello" "hello" "world")) ; 4
(println (word-distance "dog cat hello cat dog dog cat" "hello" "world")) ; nil