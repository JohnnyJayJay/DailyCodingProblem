; Given a tree where each edge has a weight, compute the length of the longest path in the tree.
;
; For example, given the following tree:
;
;   a
;  /|\
; b c d
;    / \
;   e   f
;  / \
; g   h
;
; and the weights: a-b: 3, a-c: 5, a-d: 8, d-e: 2, d-f: 4, e-g: 1, e-h: 1,
; the longest path would be c -> a -> d -> f, with a length of 17.
;
; The path does not have to pass through the root, and each node can have any amount of children.
(ns november19.09)

(defrecord Node [parent? weight children])

(defn without-child [node child]
  (assoc node :children (remove #(identical? child %) (:children node))))

(defn leaves [tree]
  (loop [updated tree found []]
    (let [children (:children updated)]
      (if (seq children)
        (let [child (first children)]
          (recur (without-child updated child)
                 (into found (leaves child))))
        found))))

(defn longest-path-down [root total-weight]
  (let [children (:children root)]
    (if (seq children)
      (apply
        max
        (for [child children]
          (let [weight (+ total-weight (:weight child))]
            (max weight (longest-path-down child weight)))))
      total-weight)))

; The time complexity for this is stupidly large, but it handles all possible cases (including negative weight)
(defn longest-path
  ([node previous total-weight]
   (let [parent (:parent node)]
     (if parent
       (let [weight (:weight node)]
         (max
           (longest-path parent node (+ weight total-weight))
           (longest-path-down (without-child node previous) total-weight)))
       (longest-path-down (without-child node previous) total-weight))))
  ([tree] (apply max (longest-path tree nil 0) (for [child (:children tree)] (longest-path child nil 0)))))
