;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname next-dir) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define backtrack?
  (lambda (startpos initpos) (equal? startpos initpos)))


(define (next-dir curpos endpos preblocks)
  (local
    ((define next-dir (next-dir-to-move curpos endpos)))
    (if (list-contains? preblocks (list curpos next-dir))
        (change-dir curpos preblocks)
        next-dir)))

;; next-dir-to-move : Position Position -> Direction
;; GIVEN:
;;          1. the cuurent position of the robot,
;;          2. the target position that robot is supposed to reach
;; RETURNS: direction for next step
;; EXAMPLES: see tests below

;; STRATEGY: using cases on curpos and endpos
(define (next-dir-to-move curpos endpos)
  (cond
    [(and (<= (pos-x endpos) (pos-x curpos)) (>= (pos-y endpos) (pos-y curpos))) "ne"]
    [(and (>= (pos-x endpos) (pos-x curpos)) (>= (pos-y endpos) (pos-y curpos))) "se"]
    [(and (>= (pos-x endpos) (pos-x curpos)) (<= (pos-y endpos) (pos-y curpos))) "sw"]
    [(and (<= (pos-x endpos) (pos-x curpos)) (<= (pos-y endpos) (pos-y curpos))) "nw"]))

;; pos-x : Position -> Integer
;; GIVEN:   a Position
;; RETURNS: first element from the list which represents the column of the robot
(define (pos-x lst)
  (first lst))

;; pos-y : Position -> Integer
;; GIVEN:   a Position
;; RETURNS: second element from the list which represents the row of the robot
(define (pos-y lst)
  (second lst))

;; move-dir : Move -> Direction
;; GIVEN:   a Move
;; RETURNS: first element from the list which represents direction of the move
(define (move-dir mv)
  (first mv))

;; move-step : Move -> PosInt
;; GIVEN:   a Move
;; RETURNS: second element from the list which represents step for the move
(define (move-step mv)
  (second mv))