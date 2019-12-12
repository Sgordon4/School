vlib ./work

## Just compile everything lol ##
vlog -reportprogress 300 -work work comma_detector.v
vlog -reportprogress 300 -work work countToTen.v
vlog -reportprogress 300 -work work decoder_5b_4b.v
vlog -reportprogress 300 -work work DFF_Sync.v
vlog -reportprogress 300 -work work Deserializer_NBit.v
vlog -reportprogress 300 -work work encoder_4b_5b.v
vlog -reportprogress 300 -work work Serializer_NBit.v

vlog -reportprogress 300 -work work Transceiver.v
vsim work.Transceiver



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


add wave -noupdate -divider -height 32 Input
add wave In
add wave w_encCombo


add wave -noupdate -divider -height 32 Serializer
add wave Comma
add wave -position insertpoint  \
sim:/Transceiver/serializer/Dff_Output
add wave w_serialOut


add wave -noupdate -divider -height 32 Second-Half
add wave w_deserialOut
add wave w_commaDetect
add wave w_upCounter
add wave -position insertpoint  \
sim:/Transceiver/upCounter/rst
add wave -position insertpoint  \
sim:/Transceiver/upCounter/count




add wave -noupdate -divider -height 32 Output
add wave Out




#Clock 50ns duty, repeat every 100ns
force clk 0 0, 1 50 -repeat 100


#### Clearing Things ####
#Setting In to 0xCC to point out that we're loading a 'comma'
force -deposit In 	2#00110_01100 0
force -deposit en 	1 0
force -deposit rst 	1 0
run 100


##### Commence Test #####
force -deposit rst 	0 0


#Load Comma
force -deposit Is	1 0
force -deposit Ls	0 0
run 100

#And send it over
force -deposit Is	0 0
force -deposit Ls	1 0
run 900


############## IMPORTANT #############
# Make sure when we load we do it on
# the 1000ns mark
# (run 900 -> load, run 100 -> repeat)


#Load Main Input
force -deposit In 	2#10101_01010 0
force -deposit Is	0 0
force -deposit Ls	0 0
run 100

#And send it over
force -deposit Is	0 0
force -deposit Ls	1 0
run 900



#Load Main Input-1
force -deposit In 	2#10101_01001 0
force -deposit Is	0 0
force -deposit Ls	0 0
run 100

#And send it over
force -deposit Is	0 0
force -deposit Ls	1 0
run 1000



#### Test Comma Reset ####


#Load Main Input
force -deposit In 	2#10101_01010 0
force -deposit Is	0 0
force -deposit Ls	0 0
run 100

#And send part of it over
force -deposit Is	0 0
force -deposit Ls	1 0
run 400



#Load Comma
#Setting In to 0xCC to point out that we're loading a 'comma'
force -deposit In 	2#00110_01100 0
force -deposit Is	1 0
force -deposit Ls	0 0
run 100

#And send it over
force -deposit Is	0 0
force -deposit Ls	1 0
run 900



#Load Main Input-1
force -deposit In 	2#10101_01001 0
force -deposit Is	0 0
force -deposit Ls	0 0
run 100

#And send it over
force -deposit Is	0 0
force -deposit Ls	1 0
run 900




#Get that last bit
run 100





















