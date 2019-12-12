//------------------------------------
// N-Bit serializer built from
// synchronous D-Flip-Flops.
// This shifter is Big-Endian.
//
// Sean Gordon
// SGordon4
//------------------------------------
module Serializer_NBit(
    Mi,     // Main-input lines for the shifter
	Comma,	// Comma input lines for the shifter
    Si,     // Shift-input
    
    Is,     // Input-select. Select between 'Comma' value and Mi
    Ls,     // Load-select.  Select between Comma/Mi or Si
    
    clk,    // Clock input
    en,     // Enable the registers
	rst,	// Reset the registers to 0
    
    Q       // Output 
);

	localparam N = 10;

    input [N-1:0] 	Mi;
	input [N-1:0]   Comma;
    input			Si;
    
    input        	Is;
    input        	Ls;
    
    input        	clk;
    input        	en;
	input 			rst;
    
    output		 	Q;
    
	
    wire [N-1:0] Dff_Output;   // Output of the DFFs
    
    wire [N-1:0] MC_Output;    // Mi/Comma mux output
    wire [N-1:0] SMC_Output;   // Si/[Mi/Comma] mux output
	wire 		 SMCs;		   // SMC select bit
	
    
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
    assign MC_Output = Is ? Comma : Mi;
    
    
	//--- SCM Mux ---------------------------------------------------------
	
    //First input of SMC mux is the Si bit
    assign SMC_Output[N-1] = Ls ? Si : MC_Output[N-1];
    
    //Generate the rest of the SMC mux
	assign SMCs = ((!Is) && Ls);
	
	genvar i;
    for(i = N-2; i >= 0; i=i-1) begin: SMC_Mux
        assign SMC_Output[i] = SMCs ? Dff_Output[i+1] : MC_Output[i];
    end
	
	
	assign Q = Dff_Output[0];
	
endmodule
    
    
    
