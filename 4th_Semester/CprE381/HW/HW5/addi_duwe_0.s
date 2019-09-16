.data
.text
.globl main
main:
    # Start Test
    addi $1, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $2, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $3, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $4, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $5, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $6, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $7, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $8, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $9, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $10, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $11, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $12, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $13, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $14, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $15, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $16, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $17, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $18, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $19, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $20, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $21, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $22, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $23, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $24, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $25, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $26, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $27, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $28, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $29, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $30, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    addi $31, $0, 0     # verify that one can clear registers and 0+0 works in the ALU   
    # End Test

    # Exit program
    li $v0, 10
    syscall
