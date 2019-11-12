; Given a list of words, return the shortest unique prefix of each word. For example, given the list:
;
; - dog
; - cat
; - apple
; - apricot
; - fish
;
; Return the list:
;
; - d
; - c
; - app
; - apr
; - f
(ns november19.11)

(defn unique-prefixes [str-list]
  (for [string str-list]
    (loop [prefix "" remaining string]
      (if (empty? remaining) ; If the string itself isn't a unique prefix, return nil
        nil
        (if (= 1 (count (filter #(.startsWith % prefix) str-list))) ; If only one string (this string) starts with the prefix
          prefix
          (recur (str prefix (first remaining)) (rest remaining))))))) ; else, continue by checking for this prefix + the next character

(println (unique-prefixes ["dog" "cat" "apple" "apricot" "fish"]))