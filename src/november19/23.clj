; This is not a DailyCodingProblem task, but I came across the question elsewhere and thought
; my solution to it might fit nicely into this collection.
;
; Given a list of options, generate all possible ordered combinations of n consecutive options.
; E.g. options = [1 2] and n = 3 should result in 8 combinations:
; ((1 1 1) (1 1 2) (1 2 1) (1 2 2) (2 1 1) (2 1 2) (2 2 1) (2 2 2))
(ns november19.23)

(defn make-roots [n options]
  (if (pos? n)
    (for [option options]
      {:option option :children (make-roots (dec n) options)})
    ()))

(defn combinations
  ([root]
   (let [children (:children root)
         option (:option root)]
     (if (seq children)
       (apply
         concat
         (map (fn [child] (map (fn [path] (conj path option)) (combinations child))) children))
       (list (list option)))))
  ([n & options] (apply concat (map combinations (make-roots n options)))))

(println (combinations 3 1 2))