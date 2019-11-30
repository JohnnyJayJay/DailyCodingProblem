; Given a string, split it into as few strings as possible such that each string is a palindrome.
;
; For example, given the input string racecarannakayak, return ["racecar", "anna", "kayak"].
;
; Given the input string abc, return ["a", "b", "c"].
(ns november19.30)

(defn is-palindrome [coll]
  (= (seq coll) (reverse coll)))

(defn next-palindrome [coll]
  (loop [current [] best [] remaining coll]                 ; loop thought the coll and find the longest possible palindrome that starts with the first element
    (if (empty? remaining)
      best
      (let [next (conj current (first remaining))]
        (recur
          next
          (if (and (is-palindrome next) (> (count next) (count best))) next best) ; swap best fit so far with current if it's a palindrome and longer than the other
          (rest remaining))))))

(defn split-palindromes [string]
  (loop [remaining string results []]
    (if (empty? remaining)
      results
      (let [pal (next-palindrome remaining)]                ; get next palindrome in string
        (recur (drop (count pal) remaining) (conj results (apply str pal))))))) ; conj it to results and remove it from the remaining elements

(println (split-palindromes "racecarannakayak"))
(println (split-palindromes "abc"))