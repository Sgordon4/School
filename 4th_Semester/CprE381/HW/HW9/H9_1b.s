main:

	#As the requirements call for temporal locality in data fetching,
	#there should be frequent accesses to the same addresses.
	#The requirements also call for minimal temporal locality in instruction fetching, 
	#so there should be few, if any, jumps or branches to previous instructions.
	
	
	#These sets of instructions do not jump to previous instruction locations, 
	#and they access the same memory locations multiple times.
	
	#Setting up DMem initial address and interval
	addi $t0, $0, 0x10010000
	addi $t1, $0, 0x10010200
	
	#Continuously access the same locations in DMem to work with temporal locality
	lw $t2, 0($t0)
	lw $t3, 0($t1)
	
	add $t2, $t2, $t3
	
	
	lw $t4, 0($t0)
	lw $t5, 0($t1)
	
	sub $t4, $t4, $t5
	
	
	lw $t6, 0($t0)
	lw $t7, 0($t1)
	
	and $t6, $t6, $t7
	
	
	lw $t8, 0($t0)
	lw $t9, 0($t1)
	
	or $t8, $t8, $t9
	
	
	lw $s0, 0($t0)
	lw $s1, 0($t1)
	
	nor $s0, $s0, $s1
	

	
	slt $s2, $t2, $t4
	slt $s3, $t6, $t8
	slt $s4, $s1, $t9
	
	# Exit program
    	li $v0, 10
    	syscall