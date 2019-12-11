
`timescale 1ns/10ps
module Shift_NBit_tb;
    reg [9:0] Mi;
	reg 	  Si;
	reg Is, Ls;
    reg clk, en, rst;
    wire Q;
    
    localparam period = 5;
    
    Shift_NBit shift(
        .Mi     (Mi),
		.Si     (Si),
		
		.Is     (Is),
		.Ls     (Ls),
		
        .clk    (clk),
        .en     (en),
		.rst	(rst),
		
        .Q      (Q)
    );
    
    //Do basic setup
    initial begin
        clk = 1'b0;
        en  = 1'b1;
        rst = 1'b1;
        repeat(4) #period clk = ~clk;
        
        rst = 1'b0;
        forever #period clk = ~clk; // generate a clock
    end
        
    initial begin
        //Wait for setup to stop before beginning
		Mi = 10'b0000000000;
		Si = 1'b0;
		Is = 1'b0;
		Ls = 1'b0;
		@(negedge rst);
		
		Mi = 10'b1111111111;
		Si = 1'b0;
		Is = 1'b0;
		Ls = 1'b0;
		repeat(1) @(negedge clk);
        
		//Let the shift register propogate out
        repeat(15) @(negedge clk);
        
        //$finish;
    end

endmodule

    
