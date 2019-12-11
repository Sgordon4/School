//------------------------------------
// 10-Bit shift register built from
// synchronous D-Flip-Flops.
// This shifter is Big-Endian.
//
// Sean Gordon
// SGordon4
//------------------------------------
module Shift_NBit(
    Mi,     // Main-input lines for the shifter
    Si,     // Shift-input
    
    Is,     // Input-select. Select between 'comma' value and Mi
    Ls,     // Load-select.  Select between comma/Mi or Si
    
    clk,    // Clock input
    en,     // Enable the registers
	rst,	// Reset the registers to 0
    
    Q       // Output 
);

	localparam N = 10;

    input [N-1:0] 	Mi;
    input			Si;
    
    input        	Is;
    input        	Ls;
    
    input        	clk;
    input        	en;
	input 			rst;
    
    output		 	Q;
    
    wire comma;
    //wire Dff_Input[N-1:0];    // Input of the DFFs
    wire [N-1:0] Dff_Output;   // Output of the DFFs
    
    wire [N-1:0] MC_Output;    // Mi/Comma mux output
    wire [N-1:0] SMC_Output;   // Si/[Mi/Comma] mux output
    
    
	
	assign comma = 1010001110'b1;
    
    //--- DFFs ------------------------------------------------------------
	
    // Instantiate the DFFs in a vector
    DFF_Sync dff[N-1:0] (
		.D(SMC_Output), 
		.clk(clk), 
		.rst(rst), 
		.en(en), 
		.Q(Dff_Output)
	);
    
    
	//--- MI/Comma Mux ----------------------------------------------------
	
    // Perform the MI/Comma mux calculation
    assign MC_Output = Is ? Mi : comma;
    
    
	//--- SCM Mux ---------------------------------------------------------
	
    //First input of SMC mux is the Si bit
    assign SMC_Output[N-1] = Ls ? MC_Output[N-1] : Si;
    
    //Generate the rest of the SMC mux
	genvar i;
    for(i = N-2; i >= 0; i=i-1) begin: SMC_Mux
        assign SMC_Output[i] = (Is && (!Ls)) ? MC_Output[i] : Dff_Output[i+1];
    end
	
	
	
	assign Q = Dff_Output[0];
endmodule
	
    //Check out EEDraw for a text schematic
    
    
    
