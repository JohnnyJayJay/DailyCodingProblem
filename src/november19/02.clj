; Find an efficient algorithm to find the smallest distance (measured in number of words)
; between any two given words in a string.
;
; For example, given words "hello", and "world" and a text content of
; "dog cat hello cat dog dog hello cat world", return 1 because
; there's only one word "cat" in between the two words.
(ns november19.02)

; generic function to calculate smallest distance between two elements in a collection (or nil if not present in this order)
(defn distance [coll element1 element2]
  (let [size (count coll)]
    (loop [index 0 shortest size counting 0]
      (if (>= index size)
        (if (< shortest size) (dec shortest) nil)
        (let [element (nth coll index)]
          (condp = element
            element1 (recur (inc index) shortest 1) ; if first element is found, begin counting
            element2 (if (and (> counting 0) (< counting shortest)) ; if second element is found,...
                       (recur (inc index) counting 0) ; ...the first value already occurred and this distance is smaller than the shortest one so far: reset counter and recur with this distance as the new shortest
                       (recur (inc index) shortest 0)) ; ...but neither of the other conditions apply, reset counter and stick with the shortest so far
            (recur (inc index) shortest (if (> counting 0) (inc counting) 0)))))))) ; Ignore other elements, but increase distance counter if already counting

; splits string into words and calls distance (also distance with the words swapped if no result)
(defn word-distance [str word1 word2]
  (let [words (clojure.string/split str #"\s+") d (distance words word1 word2)]
    (if d d (distance words word2 word1))))

; Tests
(doseq [phrase ["dog cat hello cat dog dog hello cat world"
                "dog cat hello cat dog dog cat world"
                "dog cat world cat dog dog cat hello"
                "dog cat hello cat dog dog cat"]]
  (println (word-distance phrase "hello" "world")))