; Determine whether there exists a one-to-one character mapping from one string s1 to another s2.
;
; For example, given s1 = abc and s2 = bcd, return true since we can map a to b, b to c, and c to d.
;
; Given s1 = foo and s2 = bar, return false since the o cannot map to two characters.
(ns november19.25)

(defn mapping-possible? [one two]
  (= (count (distinct one)) (count (distinct two)) (count (zipmap one two))))

(println (mapping-possible? "abc" "bcd"))
(println (mapping-possible? "foo" "bar"))
(println (mapping-possible? "foo" "baa"))