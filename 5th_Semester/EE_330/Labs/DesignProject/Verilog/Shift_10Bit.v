//------------------------------------
// 10-Bit shift register built from
// synchronous D-Flip-Flops.
// This shifter is Big-Endian.
//
// Sean Gordon
// SGordon4
//------------------------------------
module Shift_10Bit(
    Mi,     // Main-input lines for the shifter
    Si,     // Shift-input
    
    Is,     // Input-select. Select between 'comma' value and Mi
    Ls,     // Load-select.  Select between comma/Mi or Si
    
    clk,    // Clock input
    rst,    // Reset the registers to 0
    en,     // Enable the registers
    
    Q       // Output 
);

    input [9:0]  Mi;
    input        Si;
    
    input        Is;
    input        Ls;
    
    input        clk;
    input        rst;
    input        en;
    
    output [9:0] Q;
    
    wire comma;
    wire Dff_Input[9:0];    // Input of the DFFs
    wire Dff_Output[9:0];   // Output of the DFFs
    
    wire MC_Output[9:0];    // Mi/Comma mux output
    wire SMC_Output[9:0];   // Si/[Mi/Comma] mux output
    
    
    
    
    // Instantiate the DFFs in a vector
    DFF_Sync dff[9:0] (D, clk, rst, en, Q);
    
    
    // Perform the MI/Comma mux calculation
    assign MC_Output = Is ? Mi : comma;
    
    
    //First input of SMC mux is the Si bit
    assign SMC_Output[9] = LS ? MC_Output[9] : Si;
    
    //Generate the rest of the SMC mux
    for(genvar i=8; i>0; i--) begin: SMC_Mux
        assign SMC_Output[i] = LS ? MC_Output[i] : Dff_Output[i-1];
    end
    
    //Check out EEDraw for a text schematic
    
    
    
