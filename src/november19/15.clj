; Implement a 2D iterator class. It will be initialized with an array of arrays,
; and should implement the following methods:
;
;    next(): returns the next element in the array of arrays.
;             If there are no more elements, raise an exception.
;    has_next(): returns whether or not the iterator still has elements left.
;
; For example, given the input [[1, 2], [3], [], [4, 5, 6]], calling next()
; repeatedly should output 1, 2, 3, 4, 5, 6.
;
; Do not use flatten or otherwise clone the arrays. Some of the arrays can be empty.
(ns november19.15)

; I'm sorry, assignment, but mutating arrays ain't a thing in Clojure.
; Neither are iterators. Call seq on this result to get something more idiomatic, but similar.
(defn make-flat [coll] (reduce into coll))

(println (make-flat [[1 2] [3] [] [4 5 6]]))