; Given a string, determine whether any permutation of it is a palindrome.
;
; For example, carrace should return true, since it can be rearranged to form
; racecar, which is a palindrome. daily should return false, since there's no
; rearrangement that can form a palindrome.
(ns november19.06)

(defn occurrences [element coll]
  (count (filter #(= element %) coll)))

; Returns if any permutation of str is a palindrome.
; This is true if the amount of characters that occur an odd number of times is either 1 or 0.
(defn any-palindrome? [str]
  (let [chars (char-array str)]
    (< (count (filter #(odd? (occurrences % chars)) chars)) 2)))

(doseq [word ["racecar" "daily" "ooff" "regallager" "something"]]
  (println word ":" (any-palindrome? word)))