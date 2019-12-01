; Given a stack of N elements, interleave the first half of the stack with the second half reversed
; using only one other queue. This should be done in-place.
;
; Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.
;
; For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3].
; If the stack is [1, 2, 3, 4], it should become [1, 4, 2, 3].
;
; Hint: Try working backwards from the end state.
(ns november19.29
  (:import (clojure.lang PersistentQueue)))

; This is the way you'd do it in clojure. I'll just leave this here because the other one is ugly
(defn task-done-idiomatically [coll n]
  (interleave (take n coll) (reverse (drop n coll))))

; Returns a sequence of rotations of this queue. The queue is rotated by pushing
; the first element to the stack, popping it and adding it to the queue again.
(defn rotate [queue stack]
  (iterate #(conj (pop %) (peek (conj stack (peek %)))) queue))

; Returns a sequence where the nth element is the state of from and to
; when n elements have been transferred from from to to.
(defn transfer [from to]
  (iterate #(vector (pop (% 0)) (conj (% 1) (peek (% 0)))) [from to]))

; The solution as asked by the exercise; since all data is immutable in clojure, it's not really done in place.
; It's more like "only use stack & queue with peek, pop, conj and count"
(defn interleave-half-reversed [stack]
  (let [stack-queue (nth (transfer stack PersistentQueue/EMPTY) (count stack))] ; First, add the whole stack to the queue
    (loop [stack (stack-queue 0) queue (stack-queue 1)]
      (cond
        (empty? queue) stack                                ; If the queue is empty, the stack is in the right order
        (= 1 (count queue)) (conj stack (peek queue))       ; If only one element is left in the queue, just push it to the stack
        :else (let [rotated (nth (rotate queue stack) (dec (count queue)))] ; Else, rotate the queue so that the last element is first and...
                (recur (conj (conj stack (peek rotated)) (peek (pop rotated))) (-> rotated pop pop))))))) ; Push the next two elements from the queue to the stack and repeat

(println (interleave-half-reversed [1 2 3 4 5]))
(println (interleave-half-reversed [1 2 3 4]))

