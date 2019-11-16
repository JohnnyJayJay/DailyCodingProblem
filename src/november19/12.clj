; Given an arithmetic expression in Reverse Polish Notation, write a program to evaluate it.
;
; The expression is given as a list of numbers and operands. For example: [5, 3, '+'] should return 5 + 3 = 8.
;
; For example, [15, 7, 1, 1, '+', '-', '/', 3, '*', 2, 1, 1, '+', '+', '-'] should return 5,
; since it is equivalent to ((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1)) = 5.
;
; You can assume the given expression is always valid.
(ns november19.12)

; rpn: Reverse Polish Notation
(defn rpn [expression]                                      ; expression is a vector of numbers and operators
  (if (= 1 (count expression))                              ; if there's only one element, return that ([x] => x)
    (first expression)
    (recur (loop [remaining (range (count expression))]     ; else recur with a modified expression, where the first occurrence of [number number operator] is evaluated
             (let [index (first remaining)
                   eval-begin (- index 2)
                   eval-end (inc index)
                   element (expression index)]
               (if (ifn? element)
                 (into                                      ; replace [number number operator] with the evaluated value
                   (conj (subvec expression 0 eval-begin) (eval (rseq (subvec expression eval-begin eval-end))))
                   (subvec expression eval-end))
                 (recur (rest remaining))))))))             ; if not an operator, continue iterating

(println (rpn [5 3 +]))
(println (rpn [15 7 1 1 + - / 3 * 2 1 1 + + -]))