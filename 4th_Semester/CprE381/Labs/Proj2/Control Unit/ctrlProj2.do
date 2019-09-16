vlib ./work

vcom -93 -work work Control.vhd
# Turn off optimizations
vsim -novopt work.Control



view structure
view signals
view wave

radix -binary

#Internal signals
add wave -noupdate -divider -height 32 Inputs
add wave Opcode
add wave Funct 

add wave -noupdate -divider -height 32 Internals

add wave -noupdate -divider -height 32 Outputs
add wave ALUCtrl
add wave Jump
add wave Branch
add wave MemWrite
add wave MemRead
add wave RegWrite
add wave MemtoReg
add wave ALUSrc
add wave RegDst



force -deposit ALUCtrl		0 0
force -deposit Jump			0 0
force -deposit Branch		0 0
force -deposit MemWrite		0 0
force -deposit MemRead		0 0
force -deposit RegWrite		0 0
force -deposit MemtoReg		0 0
force -deposit ALUSrc		0 0
force -deposit RegDst		0 0





############ Add ############
# Add
force -deposit Opcode 000000 0
force -deposit Funct  100000 0
run 100

# Addi (Funct unused)
force -deposit Opcode 001000 0
force -deposit Funct  000000 0
run 100

# Lw (Funct unused)
force -deposit Opcode 100011 0
force -deposit Funct  000000 0
run 100

# Lui (Funct unused)
force -deposit Opcode 001111 0
force -deposit Funct  000000 0
run 100

# Sw (Funct unused)
force -deposit Opcode 101011 0
force -deposit Funct  000000 0
run 100


############ Addu ###########
# Addu
force -deposit Opcode 000000 0
force -deposit Funct  100001 0
run 100

# Addiu (Funct unused)
force -deposit Opcode 001001 0
force -deposit Funct  000000 0
run 100


############ Subu ###########
# Subu
force -deposit Opcode 000000 0
force -deposit Funct  100011 0
run 100

#----------------------------

############ And ############
# And
force -deposit Opcode 000000 0
force -deposit Funct  100100 0
run 100

# Andi (Funct unused)
force -deposit Opcode 001100 0
force -deposit Funct  000000 0
run 100


############# Or ############
# Or
force -deposit Opcode 000000 0
force -deposit Funct  100101 0
run 100

# Ori (Funct unused)
force -deposit Opcode 001101 0
force -deposit Funct  000000 0
run 100


############# Nor ###########
# Nor
force -deposit Opcode 000000 0
force -deposit Funct  100111 0
run 100


############# Xor ###########
# Xor
force -deposit Opcode 000000 0
force -deposit Funct  100110 0
run 100

#----------------------------

############# Sll ###########
# Sll
force -deposit Opcode 000000 0
force -deposit Funct  000000 0
run 100

# Srl
force -deposit Opcode 000000 0
force -deposit Funct  000010 0
run 100

# Sra
force -deposit Opcode 000000 0
force -deposit Funct  000011 0
run 100


############# Slt ###########
# Slt
force -deposit Opcode 000000 0
force -deposit Funct  101010 0
run 100

# Sltu
force -deposit Opcode 000000 0
force -deposit Funct  101011 0
run 100

# Slti (Funct unused)
force -deposit Opcode 001010 0
force -deposit Funct  000000 0
run 100

# Sltiu (Funct unused)
force -deposit Opcode 001011 0
force -deposit Funct  000000 0
run 100































































