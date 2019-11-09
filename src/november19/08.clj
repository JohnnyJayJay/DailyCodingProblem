; Given a string, return the first recurring character in it, or null if there is no recurring character.
;
; For example, given the string "acbbac", return "b". Given the string "abcdef", return null.
(ns november19.08)

(defn first-recurring [coll]
  (loop [coll coll elements (hash-set)]
    (let [element (first coll)]
      (if (or (elements element) (nil? element))
        element
        (recur (rest coll) (conj elements element))))))

(println (first-recurring "acbbac"))
(println (first-recurring "abcdef"))