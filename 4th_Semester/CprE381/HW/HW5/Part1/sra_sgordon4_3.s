.data
.text
.globl main
main:
    # Start Test
	
	# Using a value of 80000000 to ensure shifter uses correct
	# bit for extension
	lui $8, 8000
	addi $8, $0, 0000
	
	# Shifting by an arbitrary 8 bits
	sra $9, $8, 8
    # End Test

    # Exit program
    li $v0, 10
    syscall