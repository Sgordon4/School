vlib ./work

vlog -reportprogress 300 -work work Deserializer_NBit.v
vsim work.Deserializer_NBit



view structure
view signals
view wave

#radix -binary
radix -hex



#Internal signals
add wave -noupdate -divider -height 32 Controls
add wave clk
add wave rst
add wave en


add wave -noupdate -divider -height 32 Shift-Input
add wave Si


add wave -noupdate -divider -height 32 Output
add wave Q




#Clock 50ns duty, repeat every 100ns
force clk 0 0, 1 50 -repeat 100


#### Clearing Things ####
force -deposit en 	1 0
force -deposit rst 	1 0
run 100


##### Commence Test #####
force -deposit rst 	0 0

#Test Shift Input
force -deposit Si	1 0
run 100

force -deposit Si	0 0
run 100

force -deposit Si	1 0
run 100

force -deposit Si	0 0
run 100

force -deposit Si	1 0
run 100

force -deposit Si	1 0
run 100

force -deposit Si	0 0
run 100

force -deposit Si	1 0
run 100


#Allow deserializer to propagate
run 900






















