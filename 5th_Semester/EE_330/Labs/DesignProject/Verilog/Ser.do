vlib ./work

vcom -93 -work work Shift_NBit.v
# Turn off optimizations
vsim -novopt Shift_NBit



view structure
view signals
view wave

#radix -binary
radix -hex

#Internal signals
add wave -noupdate -divider -height 32 Inputs
add wave clk
add wave en
add wave rst

add wave Mi
add wave Si

add wave Is
add wave Ls

add wave -noupdate -divider -height 32 Internals
add wave we
add wave addr
add wave data

add wave -noupdate -divider -height 32 Outputs
add wave Q




#Clock 50ns duty, repeat every 100ns
force clk 0 0, 1 50 -repeat 100


#### Clearing Things ####
force -deposit en 	1 0
force -deposit rst 	1 0
run 100

force -deposit rst 	0 0
run 100


##### Commence Test #####
force -deposit Mi 	2#00000_00000 0
force -deposit Si	1 0
force -deposit Is	0 0
force -deposit Ls	0 0
run 100

#Let shifter propogate out
run 1000


force -deposit Mi 	2#11111_11111 0
force -deposit Si	1 0
force -deposit Is	0 0
force -deposit Ls	0 0
run 100

#Let shifter propogate out
run 1000






















