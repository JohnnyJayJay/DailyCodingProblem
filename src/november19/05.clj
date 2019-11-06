; Given a positive integer n, find the smallest number of squared integers which sum to n.
;
; For example, given n = 13, return 2 since 13 = 3² + 2² = 9 + 4.
;
; Given n = 27, return 3 since 27 = 3² + 3² + 3² = 9 + 9 + 9.
(ns november19.05)

(defn is-int [double]
  (= double (Math/rint double)))

; Return one possible representation of n as a sum of squares
(defn simple-squares [n]
  (loop [possible-square n found []]
    (let [root (Math/sqrt possible-square)]
      (if
        (is-int root)
        (let [found (into found (repeat (/ (apply - n 0 found) possible-square) possible-square))] ; add square as often as possible to the found squares
          (if (= n (apply + found)) found (recur (apply - n found) found))) ; look for the next square or return if the ones found suffice
        (recur (dec possible-square) found)))))

; Return the "best" possible representation of n as a sum of squares
(defn min-squares [n]
  (loop [max-square n possible-representations []] ; try for different max. squares
    (let [root (Math/sqrt max-square)]
      (cond
       (or (< (apply min 4 (map count possible-representations)) 4) (< (* max-square 4) n)) ; if less than 4 squares have already been found or if a 4 square representation isn't possible anymore
       (apply min-key count possible-representations) ; return the representation with the least squares
       (is-int root)
       (recur (Math/pow (dec root) 2) ; recur with the next square number...
              (conj possible-representations (cons max-square (simple-squares (- n max-square))))) ; ...and one square representation with the current max-square added to the possible ones
       :else (recur (dec max-square) possible-representations)))))

(defn square-count [n]
  (count (min-squares n)))

(doseq [number [13 27 52]]
  (println (square-count number)))