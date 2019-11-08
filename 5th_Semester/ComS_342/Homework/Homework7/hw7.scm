(deref (ref 342))
$ 342
(free (ref 342))
(let ((loc (ref 342))) (set! loc 541))
$ $ 541
(let ((loc (ref 540))) (set! loc (deref loc)))
$ 540
