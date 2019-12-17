//------------------------------------
// Transceiver built for EE330's
// design project.
//
// Sean Gordon
// SGordon4
//------------------------------------
module Transceiver(
    In,     // Transceiver input
	
	Is, 	// Input-select. Select between 'Comma' value and In
	Ls,     // Load-select. Select either loading Comma/In or shifting registers
			// Also used to disable encoders so that they hold state for loading
    
    clk,    // Clock input
	rst,	// Reset the registers to 0
    en,     // Enable the registers
    
    Out     // Output 
);


	localparam Comma = 10'b1010001110;


	input [7:0] 	In;
    
    input        	Is;
    input        	Ls;
    
    input        	clk;
    input        	en;
	input 			rst;
    
    output [7:0] 	Out;
	
	
	//Wires --------------------
	
	wire [9:0] w_encCombo;		//Combination of encoders 1 and 2
	
	wire w_serialOut;			//Output of serializer
	wire [9:0] w_deserialOut;	//Output of deserializer
	
	wire w_commaDetect;			//Output of comma detector
	wire w_upCounter;			//Output of 4-bit up counter
	
	//--------------------------
	
	
	// First Half ===========================================
	
	encoder_4b_5b encoder1 (
		.in		(In[7:4]), 
		.out	(w_encCombo[9:5])
	);
	encoder_4b_5b encoder2 (
		.in		(In[3:0]), 
		.out	(w_encCombo[4:0])
	);
	
	Serializer_NBit serializer (
		.Mi		(w_encCombo),
		.Comma	(Comma),
		.Si		(1'b1),
		.Is		(Is),
		.Ls		(Ls),
		
		.clk	(clk),
		.en		(en),
		.rst	(rst),
		
		.Q		(w_serialOut)
	);
	
	//=======================================================
	
	// Second Half ==========================================
	
	Deserializer_NBit deserializer (
		.Si		(w_serialOut),
		
		.clk	(clk),
		.en		(en),
		.rst	(rst),
		
		.Q		(w_deserialOut)
	);
	
	decoder_5b_4b decoder1 (
		.in		(w_deserialOut[9:5]), 
		.out	(Out[7:4]),
		.en		(w_upCounter)
	);
	decoder_5b_4b decoder2 (
		.in		(w_deserialOut[4:0]), 
		.out	(Out[3:0]),
		.en		(w_upCounter)
	);
	
	comma_detector commaDetect (
		.pi		(w_deserialOut), 
		.det	(w_commaDetect)
	);
	
	countToTen upCounter (
		.clk	(clk), 
		//.rst	(w_commaDetect || w_upCounter),
		.rst	(w_commaDetect),
		.cnt_of_10	(w_upCounter)
	);
	
	//=======================================================
	
endmodule
	