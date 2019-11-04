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

(defrecord HeapEntry [value key]
  Comparable
  (compareTo [_ other] (.compareTo key (:key other))))

(defn swap [coll one two]
  (assoc coll one (nth coll two) two (nth coll one)))

(defn parent [pos] (int (/ pos 2)))

(defn left-child [pos] (* 2 pos))

(defn right-child [pos] (+ 1 (* 2 pos)))

(defn leaf? [heap pos] (let [size (count heap)]
                         (and (>= pos (/ size 2)) (<= pos size))))

(defn empty-heap [] [(->HeapEntry nil Long/MAX_VALUE)])

(defn heapify [heap pos]
  (if (leaf? heap pos)
    heap
    (let [left (left-child pos)
          right (right-child pos)
          size (count heap)]
      (if (< left right size)
        (let [curr (nth heap pos)
              left-node (nth heap left)
              right-node (nth heap right)]
          (if (or (< (.compareTo curr left-node) 0) (< (.compareTo curr right-node) 0))
            (if (> (.compareTo left-node right-node) 0)
              (recur (swap heap pos left) left)
              (recur (swap heap pos right) right))
            heap))
        heap))))

(defn heap-insert [heap value]
  (loop [pos (count heap) heap (conj heap value)]
    (let [parent (parent pos)]
      (if (> (.compareTo (nth heap pos) (nth heap parent)) 0)
        (recur parent (swap heap pos parent))
        heap))))

(defn heap-peek [heap] (nth heap 1))

(defn heap-pop [heap]
  (heapify (into (conj (empty-heap) (last heap)) (drop 2 (pop heap))) 1))

;; Stack implementation (solely based on heap)
(defrecord Stack [heap])

(defn is-empty [stack] (<= (count (:heap stack)) 1))

(defn empty-stack [] (->Stack (empty-heap)))

(defn stack-push [stack value]
  (->Stack
    (heap-insert
      (:heap stack)
      (->HeapEntry value (if (is-empty stack) 0 (inc (:key (heap-peek (:heap stack)))))))))

(defn stack-peek [stack]
  (:value (heap-peek (:heap stack))))

(defn stack-pop [stack]
  (->Stack (heap-pop (:heap stack))))

;; FIXME Tests prints 100 infinitely
(loop [s (-> (empty-stack) (stack-push 100) (stack-push 40) (stack-push 120))]
  (println (stack-peek s))
  (if (is-empty s) nil (recur (stack-pop s))))



