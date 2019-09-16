vlib ./work

#Use VHDL 2008 for internal signal shit
vcom -2008 -work work CentControl.vhd
# Turn off optimizations
vsim -novopt work.CentControl



view structure
view signals
view wave

#radix -binary
radix -hex


add wave -noupdate -divider -height 32 Manuals
add wave initializing
add wave WriteAddr
add wave s_ALUOut

add wave -noupdate -divider -height 32 Inputs
add wave clk
add wave Instr
#add wave WriteData



add wave -noupdate -divider -height 32 Internals
add wave s_ALUOp
add wave s_RegWrite
add wave s_ALUSrc
add wave s_RegDst

#add wave s_ALUOut
#add wave s_WriteData

add wave -noupdate -divider -height 32 Outputs
#add wave Branch
#add wave MemWrite
#add wave MemRead
#add wave MemtoReg
add wave Read1Out
add wave Read2Out
add wave ALUOut
add wave ALUZero
add wave SExtOut




#Clock 50ns duty, repeat every 100ns
force clk 0 0, 1 50 -repeat 100


#### Clearing Things ####
force -deposit Instr 0 0
force -deposit WriteData 0 0



# WriteData is currently unused because dataMem isn't set up, 
# driving s_ALUOut to change input value instead

#### Setting up Regs ####
# $8  <= 1
# $9  <= 2
# $10 <= 3
# $11 <= 4

# As all instructions seem to work, can set regs with those
# initializing was for when they weren't

force initializing 1 0
# Need correct control signals for R-Type, just using "add $0 $0 $0"
force -deposit Instr 		2#000000_00000_00000_00000_00000_100000 0

# $8 <= 1
force -deposit WriteAddr 		16#8 0
force -deposit s_ALUOut 		16#1 1
run 150

# $9 <= 2
force -deposit WriteAddr	 	16#9 0
force -deposit s_ALUOut 		16#2 0
run 100

# $10 <= 3
force -deposit WriteAddr	 	16#A 0
force -deposit s_ALUOut 		16#3 0
run 100

# $11 <= 4
force -deposit WriteAddr	 	16#B 0
force -deposit s_ALUOut 		16#4 0
run 100


force initializing 0 0
force -deposit WriteAddr 0 0
force -deposit s_ALUOut 16#0 0
run 200

#### Actually sending instructions ####


#########################################
# and $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 0100
							# R-Type   9     8     0   -----   and
force -deposit Instr 		2#000000_01001_01000_00000_00000_100100 0

#########################################

run 100

#########################################
# or $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 0101
							# R-Type   9     8     0   -----   or
force -deposit Instr 		2#000000_01001_01000_00000_00000_100101 0

#########################################

run 100

#########################################
# xor $d, $s, $t
# 0000 00ss ssst tttt dddd d--- --10 0110
							# R-Type   8     8     0   -----  xor
force -deposit Instr 		2#000000_01000_01000_00000_00000_100110 0

#########################################

run 100

#########################################
# nor $d, $s, $t
# 0000 00ss ssst tttt dddd d000 00100111
							# R-Type   8     8     0   -----  nor
force -deposit Instr 		2#000000_01000_01000_00000_00000_100111 0

#########################################

run 100





#########################################
# add $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 0000
							# R-Type   8     9     8   -----   add
force -deposit Instr 		2#000000_01000_01001_01000_00000_100000 0

#########################################

run 200

#########################################
# addu $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 0001
							# R-Type   8     9     8   -----  addu
force -deposit Instr 		2#000000_01000_01001_01000_00000_100001 0

#########################################

run 200

#########################################
# sub $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 0010
							# R-Type   8     9     8   -----   sub
force -deposit Instr 		2#000000_01000_01001_01000_00000_100010 0

#########################################

run 200

#########################################
# subu $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 0011
							# R-Type   8     9     8   -----  subu
force -deposit Instr 		2#000000_01000_01001_01000_00000_100011 0

#########################################

run 200





#########################################
# sll $d, $t, h
# 0000 00ss ssst tttt dddd dhhh hh00 0000
							# R-Type -----   9     9     8    sll
force -deposit Instr 		2#000000_00000_01001_01001_01000_000000 0

#########################################

run 200

#########################################
# sra $d, $t, h
# 0000 00ss ssst tttt dddd dhhh hh00 0011
							# R-Type -----   9     9     4    sra
force -deposit Instr 		2#000000_00000_01001_01001_00100_000011 0

#########################################

run 200

#########################################
# srl $d, $t, h
# 0000 00ss ssst tttt dddd dhhh hh00 0010
							# R-Type -----   9     9     4    srl
force -deposit Instr 		2#000000_00000_01001_01001_00100_000010 0

#########################################

run 200





#########################################
# slt $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 1010
							# R-Type   8     9     8   -----  slt
force -deposit Instr 		2#000000_01000_01001_01000_00000_101010 0

#########################################

run 300













