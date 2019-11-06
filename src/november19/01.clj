; You are given n numbers as well as n probabilities that sum up to 1.
; Write a function to generate one of the numbers with its corresponding probability.
;
; For example, given the numbers [1, 2, 3, 4] and probabilities [0.1, 0.5, 0.2, 0.2],
; your function should return 1 10% of the time, 2 50% of the time, and 3 and 4 20% of the time.
;
; You can generate random numbers between 0 and 1 uniformly.

(ns november19.01)

(defn choose [numbers probabilities]
  (let [r (rand)]
    (loop [index 0 total 1] ; Loop through the probabilities and see if the random number is in bounds of 1 - probabilities so far
      (let [bound (- total (nth probabilities index))]
        (if (> r bound) (nth numbers index) (recur (inc index) bound))))))

; inputs
(def numbers '(1 2 3 4))
(def probabilities '(0.1 0.5 0.2 0.2))

; helper function to count the occurrences of a single element in a collection
(defn occurrences [element coll]
  (count (filter #(= element %) coll)))

; Call choose with the inputs n times and print the results (number : count - ratio)
(let [n 10000
      result (take n (repeatedly #(choose numbers probabilities)))
      parts (map #(occurrences % result) numbers)]
  (println "Total:" n)
  (dotimes [i (count numbers)]
    (let [part (nth parts i)]
      (println (nth numbers i) ":" part "-" (double (/ part n))))))