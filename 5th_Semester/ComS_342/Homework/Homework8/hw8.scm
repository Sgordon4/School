//RefExp:
			
// Incorrect Type Error
$(ref: num #t)
//$ Type error: The Ref expression expect type number, found bool in (ref #t)

// Correct Type
$(ref: num 1)
//$ loc:1

//======================
AssignExp:
		  
//Setup
$(define refCorrect: Ref num (ref: num 1))
		  
// Nested Type Error
$(set! refCorrect #t)
//$ Type error: The inner type of the reference type is number the rhs type is bool in (set! refCorrect #t)

// LHS Type Error
$(set! 1 1)
//$ Type error: The lhs of the assignment expression expect a reference type found number in (set! 1.0 1.0)

// Correct Type
$(set! refCorrect 2)
//$ 2

//===================================================

//ListExp:
		  
// Invalid Type Error
$(list: num 1 #t)
//$ Type error: The 1 expression should have type number, found bool in (list 1.0 #t )

// Correct Type
$(list: num 1 2)
//$ (1 2)

//======================
	
//CdrExp:
		  
// Invalid Type Error
$(cdr 1)
//$ Type error: The cdr expect an expression of type Pair, found number in (cdr 1.0)

// Correct Type
$(cdr (list: num 1 2))
//$ (2)

//===================================================

//CompoundArithExp:
		  
// Invalid Type Error
$(+ 1 #t)
//$ Type error: expected num found bool in (+ 1.0 #t )

// Correct Type
$(+ 1 2)
//$ 3

//===================================================

//BinaryComparator:
		  
// Invalid Type1 Error
$(< #t 2)
//$ Type error: The first argument of a binary expression should be num Type, found bool in (< #t 2.0)

// Invalid Type2 Error
$(< 1 #t)
//$ Type error: The second argument of a binary expression should be num Type, found bool in (< 1.0 #t)

// Correct Type
$(< 1 2)
//$ #t

//===================================================

//IfExp:
		  
// Invalid Condition Error
$(if 1 2 3)
//$ Type error: The condition should have boolean type, found number in (if 1.0 2.0 3.0)

// Invalid Exp Type Error
$(if #t 1 #t)
//$ Type error: The then and else expressions should have the same type, then has type number else has type bool in (if #t 1.0 #t)

// Correct Type
$(if #t 1 2)
//$ 1

//===================================================

//LetExp:
		  
// Invalid Variable Error
$(let ((x: num #t)) x)
//$ Type error: The declared type of the 0 let variable and the actual type mismatch, expect number type, found bool in (let ( (x #t)) x )

// Internal Error (testing with CompoundArithExp)
$(let ((x: num 1)) (+ #t x))
//$ Type error: expected num found bool in (+ #t x )

// Correct Type
$(let ((x: num 1)) x)
//$ 1

//===================================================

//CallExp:

//Setup
$(define identity: (num -> num) (lambda (x: num) x))
		  
// Expected Function Error
$(1)
//$ Type error: Expect a function type in the call expression, found number in (1.0 )

// Invalid Exp Type Error
$(identity #t)
//$ Type error: The expected type of actual parameter 0 is number, found bool in (identity #t )

// Correct Type
$(identity 1)
//$ 1

//===================================================
