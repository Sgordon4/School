//------------------------------------
// N-Bit deserializer built from
// synchronous D-Flip-Flops.
// This shifter is Big-Endian.
//
// Sean Gordon
// SGordon4
//------------------------------------
module Deserializer_NBit(
    Si,     // Shift-input
    
    clk,    // Clock input
	rst,	// Reset the registers to 0
    en,     // Enable the registers
    
    Q       // Output 
);

	localparam N = 10;

    input			Si;
    
    input        	clk;
	input 			rst;
    input        	en;
    
    output [N-1:0]  Q;
	
    
    //--- DFFs ------------------------------------------------------------
	
	wire [N-1:0] Dff_Inputs;	// Inputs of the DFFs
	wire [N-1:0] Dff_Outputs;	// Outputs of the DFFs
	
	
	//First DFF input is the shift input
	assign Dff_Inputs[N-1] = Si;
	
	genvar i;
	for(i = N-2; i >= 0; i=i-1) begin : DFF_Array
		assign Dff_Inputs[i] = Dff_Outputs[i+1];
	end
	
	
	// Instantiate the DFFs in a vector
	DFF_Sync dff[N-1:0] (
		.D(Dff_Inputs), 
		.clk(clk), 
		.rst(rst), 
		.en(en), 
		.Q(Dff_Outputs)
	);
	
	
	assign Q = Dff_Outputs;
	
	
endmodule
    
    
    
