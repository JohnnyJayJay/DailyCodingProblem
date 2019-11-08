; Given a string, return the first recurring character in it, or null if there is no recurring character.
;
; For example, given the string "acbbac", return "b". Given the string "abcdef", return null.
(ns november19.08)

(defn first-recurring [coll]
  (loop [coll coll chars []]
    (let [char (first coll)]
      (if (nil? (some #(= char %) chars))
        (recur (rest coll) (conj chars char))
        char))))

(println (first-recurring "acbbac"))
(println (first-recurring "abcdef"))