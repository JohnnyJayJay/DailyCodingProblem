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

; returns the given node without the given child (useful to "block" certain ways down a tree)
(defn without-child [node child]
  (assoc node :children (remove #(identical? child %) (:children node))))

; Starting from any node, work all possible ways down, not limited to leaf nodes as the last nodes
(defn longest-path-down [root total-weight]
  (let [children (:children root)]
    (if (seq children)
      (apply
        max ; if children available, return the maximum value of the longest-path-down from all children
        (for [child children]
          (let [weight (+ total-weight (:weight child))]
            (max weight (longest-path-down child weight))))) ; stop at this node if the total weight is larger than any further path
      total-weight))) ; if at leaf, return the total weight acquired along the way

; The time complexity for this is stupidly large, but it handles all possible cases (including negative weight)
(defn longest-path
  ([node previous total-weight] ; node: node to work up from; previous: node in last recursion; total-weight: weight acquired so far
   (let [parent (:parent node)]
     (if parent ; if not at tree root
       (let [weight (:weight node)]
         (max ; find the bigger value of going back down now or going further up (recursively)
           (longest-path parent node (+ weight total-weight))
           (longest-path-down (without-child node previous) total-weight)))
       (longest-path-down (without-child node previous) total-weight)))) ; if at root start going back down
  ([tree] (apply max (longest-path tree nil 0) (for [child (:children tree)] (longest-path child))))) ; for all nodes, find the longest path that starts with them and from that extract the longest one
