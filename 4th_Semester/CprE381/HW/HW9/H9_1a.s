main:

	#As the requirements call for spatial locality in instruction fetching,
	#there should be a minimal amount of jumps or branches.
	#The requirements also call for minimal spatial locality in data accesses, 
	#so all DMem access address calls will be spaced to separated locations.
	
	
	#These sets of instructions do not jump to different instruction locations, 
	#and they keep DMem address locations far apart.
	
	#Setting up DMem initial address and interval
	addi $t0, $0, 0x10010000
	addi $t1, $0, 0x100
	
	#Increase address by 100 to ensure spatial locality won't take effect
	add $t0, $t0, $t1	#$t0 <- 0x10010100
	lw $t2, 0($t0)
	
	add $t0, $t0, $t1	#$t0 <- 0x10010200
	lw $t3, 0($t0)
	
	add $t2, $t2, $t3
	
	
	add $t0, $t0, $t1	#$t0 <- 0x10010300
	lw $t4, 0($t0)
	
	add $t0, $t0, $t1	#$t0 <- 0x10010400
	lw $t5, 0($t0)
	
	sub $t4, $t4, $t5
	
	
	add $t0, $t0, $t1	#$t0 <- 0x10010500
	lw $t6, 0($t0)
	
	add $t0, $t0, $t1	#$t0 <- 0x10010600
	lw $t7, 0($t0)
	
	and $t6, $t6, $t7
	
	
	add $t0, $t0, $t1	#$t0 <- 0x10010700
	lw $t8, 0($t0)
	
	add $t0, $t0, $t1	#$t0 <- 0x10010800
	lw $t9, 0($t0)
	
	or $t6, $t6, $t7
	

	
	slt $s0, $t3, $t4
	slt $s1, $t5, $t6
	
	# Exit program
    	li $v0, 10
    	syscall
