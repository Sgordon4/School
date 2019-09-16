.data
.text
.globl main
main:
    # Start Test
	
	# Using a value of FFFFFFFF to ensure shifter continues sign bit
	lui $8, FFFF
	addi $8, $0, FFFF
	
	# Shifting by an arbitrary 8 bits
	sra $9, $8, 8
    # End Test

    # Exit program
    li $v0, 10
    syscall