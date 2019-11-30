; Given a stack of N elements, interleave the first half of the stack with the second half reversed
; using only one other queue. This should be done in-place.
;
; Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.
;
; For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3].
; If the stack is [1, 2, 3, 4], it should become [1, 4, 2, 3].
;
; Hint: Try working backwards from the end state.
(ns november19.29)

; This is the way you'd do it in clojure. I'll just leave this here because the other one is ugly
(defn task-done-idiomatically [coll n]
  (interleave (take n coll) (reverse (drop n coll))))

; TODO real solution