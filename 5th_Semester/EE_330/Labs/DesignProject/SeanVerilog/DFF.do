vlib ./work

vlog -reportprogress 300 -work work DFF_Sync.v
vsim work.DFF_Sync


view structure
view signals
view wave

#radix -binary
radix -hex

#Internal signals
add wave -noupdate -divider -height 32 Inputs
add wave clk
add wave rst
add wave en
add wave D

add wave -noupdate -divider -height 32 Outputs
add wave Q




#Clock 50ns duty, repeat every 100ns
force clk 0 0, 1 50 -repeat 100


#### Clearing Things ####
force -deposit en 	1 0
force -deposit rst 	1 0
run 100


##### Commence Test #####
force -deposit rst 	0 0

force -deposit D	1 0
run 100

force -deposit D	0 0
run 100

force -deposit D	1 0
run 100

force -deposit D	1 0
force -deposit en	0 0
run 100

force -deposit D	0 0
run 100

















