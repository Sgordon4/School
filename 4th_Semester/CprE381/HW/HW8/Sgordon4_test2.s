.data
.text
.globl main
main:
	#Start Test
	
	#Start with a simple addi to allow for data dependencies on $t1
	addi $t1, $0, 1		# $t1 <- 1
	
	lui $0, 0			#Noop for $t1
	lui $0, 0			#Noop for $t1
	
	#Another addi that depends on the value in $t1
	addi $t2, $t1, 1	# $t2 <- 2
	
	lui $0, 0			#Noop for $t2
	lui $0, 0			#Noop for $t2
	
	#A subtraction that depends on the value of $t1 and $t2
	sub $t3, $t2, $t1	# $t3 <- 1
	
	#An instruction to ensure $t4 starts at 2. Creates a data dependency
	lui $t4, 2			# $t4 <- 2
	
	lui $0, 0			#Noop for $t3
	
	#A branch instruction that acts as a data hazard, and spawns a control hazard
	beq $t3, $t1, branch
	
	lui $0, 0			#Noop for beq
	lui $0, 0			#Noop for beq
	lui $0, 0			#Noop for beq
	
	#-----------------------------
	#This instruction should not be executed if the branch works correctly
	lui $t4, 0			# $t4 <- 0
	
	
	branch:
	#Slt that should be executed immediately after the branch. Depends on the value of $t4
	slt $t5, $t1, $t4	# $t5 <- 1
	
	#An instruction to ensure $t6 starts at 2. Creates a data dependency
	lui $t6, 2			# $t6 <- 2
	
	#This instruction spawns a control hazard
	j jump
	
	lui $0, 0			#Noop for j
	
	#-----------------------------
	#This instruction should not be executed if the jump works correctly
	lui $t6, 0			# $t6 <- 0
	
	
	jump:
	#Slt that should be executed immediately after the jump. Depends on the value of $t6
	slt $t7, $t1, $t6	# $t7 <- 1
	
	lui $0, 0			#Noop for $t7
	lui $0, 0			#Noop for $t7
	
	#Addi instruction that depends on the value of $t7
	addi $t8, $t7, $t7	# $t8 <- 2
	
	lui $0, 0			#Noop for $t8
	lui $0, 0			#Noop for $t8
	
	#Addi instruction that depends on the value of $t8
	addi $t9, $t8, 1	# $t9 <- 3
	
	lui $0, 0			#Noop for $t9
	lui $0, 0			#Noop for $t9
	
	#Slt instruction that depends on the value of $t8 and $t9
	slt $s0, $t8, $t9	# $s0 <- 1
	
	#Already nooped for $t9
	
	#Sub instruction that depends on the value of $t8 and $t9
	sub $s1, $t9, $t8	# $s1 <- 1
	
	lui $0, 0			#Noop for $s1
	lui $0, 0			#Noop for $s1
	
	#Slt instruction that depends on the value of $s1
	or $s2, $t9, $s1	# $s2 <= 2
	
	
	# Exit program
    li $v0, 10
    syscall