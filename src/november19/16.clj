; Given a list of words, find all pairs of unique indices such that the concatenation of the two words is a palindrome.
;
; For example, given the list ["code", "edoc", "da", "d"], return [(0, 1), (1, 0), (2, 3)].
(ns november19.16)

(defn palindrome-pairs [words]
  (partition
    2
    (flatten
      (let [indices (range (count words))]
        (for [i indices]
          (for [j indices
                :let [concatenated (str (words i) (words j))]
                :when (and (not= i j) (= (seq concatenated) (reverse concatenated)))] ; if the indices aren't the same and string is palindrome
            [i j]))))))                                     ; return pair of both indices

(println (palindrome-pairs ["code" "edoc" "da" "d"]))