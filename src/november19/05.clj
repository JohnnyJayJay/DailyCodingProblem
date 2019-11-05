; Given a positive integer n, find the smallest number of squared integers which sum to n.
;
; For example, given n = 13, return 2 since 13 = 3² + 2² = 9 + 4.
;
; Given n = 27, return 3 since 27 = 3² + 3² + 3² = 9 + 9 + 9.
(ns november19.05)

(defn squares [n]
  (loop [possible-square n found []]
    (let [root (Math/sqrt possible-square)]
      (cond
        (= n (apply + found)) found
        (= root (Math/rint root))
        (let [found (into found (repeat (/ (apply - n 0 found) possible-square) possible-square))] ; FIXME implement working algorithm that figures out how many repeats
          (recur (apply - n found) found))
        :else (recur (dec possible-square) found)))))

(defn square-count [n]
  (count (squares n)))

(doseq [number [13 27 52]]
  (println (square-count number)))