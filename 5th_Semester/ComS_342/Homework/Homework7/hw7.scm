$ (deref (ref 342))
342
$ (free (ref 342))
$ (let ((loc (ref 342))) (set! loc 541))
541
$ (let ((loc (ref 540))) (set! loc (deref loc)))
540




$ (define alias (ref 1))
$ (let ((b alias)) b)
loc:0
$ (define alias2 (deref (ref 1)))
$ (let ((c alias2)) c)
1




$ (define pairNode (lambda (fst snd)(lambda(op) (if op fst snd))))
$ (define node (lambda (x) (pairNode x (pairNode (ref 0) (ref 0)))))
$ (define value (lambda (n) (n #t)))
$ (define left  (lambda (n) ((n #f) #t)))
$ (define right (lambda (n) ((n #f) #f)))
$ (define add (lambda (p which c)(if which (set! (left p) c)(set! (right p) c))))
$ (define root ( node 1 ))
$ (add root #t ( node 2 ))
(lambda ( op ) (if op fst snd))
$ (add root #f ( node 3 ))
(lambda ( op ) (if op fst snd))
$ (add (deref (left root)) #f (node 4))
(lambda ( op ) (if op fst snd))
$ (add (deref (right root)) #t (node 5))
(lambda ( op ) (if op fst snd))
$ (add (deref (right root)) #f (node 6))
(lambda ( op ) (if op fst snd))
$ (value root)
1
$ (value (deref (left root)))
2
$ (value (deref (right root)))
3
$ (value (deref (right (deref (left root)))))
4
$ (value (deref (left (deref (right root)))))
5
$ (value (deref (right (deref (right root)))))
6