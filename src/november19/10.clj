; Given a 32-bit integer, return the number with its bits reversed.
;
; For example, given the binary number 1111 0000 1111 0000 1111 0000 1111 0000,
; return 0000 1111 0000 1111 0000 1111 0000 1111.
(ns november19.10)

(defn reversed-bits [num base]
  (loop [original num reversed 0]
    (if (zero? original)
      reversed
      (recur (quot original base) (+ (* reversed base) (mod original base))))))

(println (Integer/toBinaryString (reversed-bits 2r11110000111100001111000011110000 2))) ; omits zeros at the beginning

