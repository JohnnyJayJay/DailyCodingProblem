; Write a function to flatten a nested dictionary. Namespace the keys with a period.
;
; For example, given the following dictionary:
;
; {
;     "key": 3,
;     "foo": {
;         "a": 5,
;         "bar": {
;             "baz": 8
;         }
;     }
; }

; it should become:
;
; {
;     "key": 3,
;     "foo.a": 5,
;     "foo.bar.baz": 8
; }

; You can assume keys do not contain dots in them, i.e. no clobbering will occur.
(ns november19.22)

(defn flatten-dict [dict context]
  (apply
    hash-map                                                ; construct new hash map from key-value pairs
    (flatten
      (map #(if (map? (% 1)) (vec (flatten-dict (% 1) (% 0))) %) ; if nested map: flatten with key as new context
           (map #(if context (assoc % 0 (str context "." (% 0))) %) ; change key to context.key if context is present
                (seq dict))))))                             ; key-value pairs

(println (flatten-dict {"key" 3
                        "foo" {"a" 5
                               "bar" {"baz" 8}}} nil))
