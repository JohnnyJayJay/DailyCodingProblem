; Implement a stack API using only a heap. A stack implements the following methods:
;
; - push(item), which adds an element to the stack
; - pop(), which removes and returns the most recently added element
;   (or throws an error if there is nothing on the stack)
;
; Recall that a heap has the following operations:
;
; - push(item), which adds a new key to the heap
; - pop(), which removes and returns the max value of the heap

(ns november19.03)

;; Heap implementation

(defrecord HeapEntry [value heap-value]
  Comparable
  (compareTo [_ other] (.compareTo value (:value other))))

(defn swap [coll one two]
  (assoc coll one (get coll two) two (get coll one)))

(defn parent [pos] (/ pos 2))

(defn left-child [pos] (* 2 pos))

(defn right-child [pos] (+ 1 (* 2 pos)))

(defn leaf? [heap pos] (let [size (count heap)]
                         (and (>= pos (/ size 2)) (<= pos size))))

(defn empty-heap [] [Integer/MAX_VALUE])

(defn heapify [heap pos]
  (if (leaf? heap pos)
    heap
    (let [left (left-child pos)
          right (right-child pos)
          curr (get heap pos)
          left-node (get heap left)
          right-node (get heap right)]
      (if (or (< (.compareTo curr left-node) 0) (< (.compareTo curr right-node) 0))
        (if (> (.compareTo left-node right-node) 0)
          (recur (swap heap pos left) left)
          (recur (swap heap pos right) right))
        heap))))

(defn heap-insert [heap value]
  (loop [heap (conj heap value) pos (count heap)]
    (let [parent (parent pos)]
      (if (> (.compareTo (get heap pos) (get heap parent)) 0)
        (recur (swap heap pos parent) parent)
        heap))))

(defn heap-peek [heap] (get heap 1))

(defn heap-pop [heap]
  (into (conj (empty-heap) (last heap)) (drop 2 (pop heap))))

;; Stack implementation (solely based on heap)
(defrecord Stack [heap])

(defn empty-stack [] (->Stack (empty-heap)))

(defn stack-push [stack value]
  (->Stack (heap-insert (:heap stack)
                        (->HeapEntry value (inc (:heap-value (heap-peek (:heap stack))))))))

(defn stack-peek [stack]
  (:value (heap-peek (:heap stack))))

(defn stack-pop [stack]
  (->Stack (heap-pop (:heap stack))))

;; Tests
(def s (-> (empty-stack) (stack-push 100) (stack-push 40) (stack-push 120)))
(dotimes [n 3]
  (println (stack-peek s))
  (def s (stack-pop s)))


