vlib ./work

vcom -93 -work work tb_SimplifiedMIPSProcessor.vhd
# Turn off optimizations
vsim -novopt work.tb_SimplifiedMIPSProcessor


view structure 
view signals
view wave

radix -binary

#Internal signals
add wave -noupdate -divider -height 32 Inputs
add wave A
add wave B
add wave i_S

add wave -noupdate -divider -height 32 Internals
add wave uAdd
add wave sAdd
add wave uSub
add wave sSub

add wave -noupdate -divider -height 32 Outputs
add wave o_F
add wave Cout



#### Actually sending instructions ####


#########################################
# and $d, $s, $t
# 0000 00ss ssst tttt dddd d000 0010 0100
							# R-Type   9     8     0   -----   and
force -deposit Instr 		2#000000_01001_01000_00000_00000_100100 0

#########################################

run 100