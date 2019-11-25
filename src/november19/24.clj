; You are given a starting state start, a list of transition probabilities for a Markov chain,
; and a number of steps num_steps. Run the Markov chain starting from start for num_steps
; and compute the number of times we visited each state.
;
; For example, given the starting state a, number of steps 5000, and the following transition probabilities:
;
; [
;   ('a', 'a', 0.9),
;   ('a', 'b', 0.075),
;   ('a', 'c', 0.025),
;   ('b', 'a', 0.15),
;   ('b', 'b', 0.8),
;   ('b', 'c', 0.05),
;   ('c', 'a', 0.25),
;   ('c', 'b', 0.25),
;   ('c', 'c', 0.5)
; ]
; One instance of running this Markov chain might produce { 'a': 3012, 'b': 1656, 'c': 332 }.
(ns november19.24)

; Chooses the next state for a given collection of transition probabilities based on RNG.
; Basically the same algorithm as used in 01.clj -
; keep subtracting probabilities from an initial total of 1 until the random number
; (between 0 and 1) is bigger than that
(defn next-state [state transitions]
  (let [r (rand)]
    (loop [total 1 remaining (filter #(= (first %) state) transitions)]
      (let [transition (first remaining) bound (- total (last transition))]
        (if (> r bound) (nth transition 1) (recur bound (rest remaining)))))))

(defn state-visits
  ([current steps transitions visits]                         ; for recursion
   (if (zero? steps)                                        ; return result if 0 steps are left
     visits
     ; recur with random next state, decreased steps and increased visits of current state
     (recur (next-state current transitions) (dec steps) transitions (update visits current inc))))
  ([start steps transitions]
   (state-visits start steps transitions (zipmap (distinct (map first transitions)) (repeat 0))))) ; map all possible states to 0 for the initial visits map

(println (state-visits :a 5000 [[:a :a 0.9]
                                [:a :b 0.075]
                                [:a :c 0.025]
                                [:b :a 0.15]
                                [:b :b 0.8]
                                [:b :c 0.05]
                                [:c :a 0.25]
                                [:c :b 0.25]
                                [:c :c 0.5]]))