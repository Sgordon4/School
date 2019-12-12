vlib ./work

vlog -reportprogress 300 -work work Serializer_NBit.v
vsim work.Serializer_NBit



view structure
view signals
view wave

#radix -binary
radix -hex



#Internal signals
add wave -noupdate -divider -height 32 Controls
add wave clk
add wave en
add wave rst


add wave -noupdate -divider -height 32 Selects
add wave Ls
add wave Is
add wave SMCs


add wave -noupdate -divider -height 32 Main-Inputs
add wave Mi
add wave Comma


add wave -noupdate -divider -height 32 Results
add wave MC_Output
add wave Si
add wave SMC_Output


add wave -noupdate -divider -height 32 D-Input
add wave Dff_Output


add wave -noupdate -divider -height 32 Output
add wave Q




#Clock 50ns duty, repeat every 100ns
force clk 0 0, 1 50 -repeat 100


#### Clearing Things ####
force -freeze Comma 2#10100_01110 0
force -deposit en 	1 0
force -deposit rst 	1 0
run 100


##### Commence Test #####
force -deposit rst 	0 0

#Test Comma
force -deposit Mi 	2#11111_111111 0
force -deposit Si	1 0
force -deposit Is	1 0
force -deposit Ls	0 0
run 100


#Test Main Input
force -deposit Mi 	2#10101_01010 0
force -deposit Si	1 0
force -deposit Is	0 0
force -deposit Ls	0 0
run 100


#Test Shift Input
force -deposit Mi 	2#11111_111111 0
force -deposit Si	1 0
force -deposit Is	0 0
force -deposit Ls	1 0
run 100


#Let shifter propogate out
run 500

force -deposit Si	0 0
run 400






















