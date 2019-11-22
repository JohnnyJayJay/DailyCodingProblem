; Given a string s and a list of words words, where each word is the same length,
; find all starting indices of substrings in s that is a concatenation of every word in words exactly once.
;
; For example, given s = "dogcatcatcodecatdog" and words = ["cat", "dog"],
; return [0, 13], since "dogcat" starts at index 0 and "catdog" starts at index 13.
;
; Given s = "barfoobazbitbyte" and words = ["dog", "cat"],
; return [] since there are no substrings composed of "dog" and "cat" in s.
;
; The order of the indices does not matter.
(ns november19.21)

; returns all possible orders for a given collection, i.e. a sequence of n! sequences
; that all represent a different order of the initial collection.
; E.g.: (possible-orders [1 2 3]) => ((1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1))
(defn possible-orders
  ([start remaining]                                      ; Helper function overload for recursion
   (if (seq remaining)
     (for [element remaining]
       (let [orders (possible-orders element (filter (partial not= element) remaining))] ; all possible orders for the remaining elements without the current element
         (if (some? start)
           (cons start orders)
           orders)))                                        ; If no start value given, just return the orders
     [start]))                                              ; If no remaining values, return the start value (as a vector, to work with cons)
  ([coll] (partition (count coll) (flatten (possible-orders nil (vec coll)))))) ; begin recursive possible-orders for a collection and reformat the result to be a sequence of sequences (no more nesting)

; returns the indices in string of substrings that are a combination of the given parts
(defn substr-indices [string parts]
  (filter (partial <= 0) (map #(.indexOf string %) (map (partial apply str) (possible-orders parts))))) ; get all possible-orders, concatenate each, find substring index and filter out the non-existent ones

(println (substr-indices "dogcatcatcodecatdog" ["cat" "dog"]))
(println (substr-indices "barfoobazbitbyte" ["dog" "cat"]))