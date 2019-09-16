.data
.text
.globl main
main:
    # Start Test
	
	# Using a value of 0 to ensure shifter doesn't just slap a 1 on
	lui $8, 0000
	addi $8, $0, 0000
	
	# Shifting by an arbitrary 8 bits
	sra $9, $8, 8
	
    # End Test

    # Exit program
    li $v0, 10
    syscall
