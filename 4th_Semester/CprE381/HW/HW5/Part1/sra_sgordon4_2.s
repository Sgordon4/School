.data
.text
.globl main
main:
    # Start Test
	
	# Using a value of 7FFFFFFF to ensure shifter uses correct
	# bit for extension
	lui $8, 7FFF
	addi $8, $0, FFFF
	
	# Shifting by an arbitrary 8 bits
	sra $9, $8, 8
    # End Test

    # Exit program
    li $v0, 10
    syscall