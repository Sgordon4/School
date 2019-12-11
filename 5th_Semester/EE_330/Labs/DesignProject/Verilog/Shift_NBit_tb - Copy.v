
`timescale 1ns/10ps
module Shift_4it_tb;
    reg D;
    reg clk, rst, en;
    wire Q;
    
    localparam period = 5;
    
    Shift_4Bit shift(
        .D      (D),
        .clk    (clk),
        .rst    (rst),
        .en     (en),
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
        D = 0;
        @(negedge rst);
        
        D = 1;
        repeat(1) @(negedge clk);
        D = 0;
        repeat(1) @(negedge clk);
        D = 1;
        repeat(1) @(negedge clk);
        D = 1;
        repeat(1) @(negedge clk);
        
        D = 0;
        repeat(1) @(negedge clk);
        D = 1;
        repeat(1) @(negedge clk);
        D = 1;
        repeat(1) @(negedge clk);
        D = 1;
        repeat(1) @(negedge clk);
        
        //Let the shift register propogate out
        D = 0;
        repeat(1) @(negedge clk);
        D = 0;
        repeat(1) @(negedge clk);
        D = 0;
        rst = 1;            //Show reset works
        repeat(1) @(negedge clk);
        D = 0;
        repeat(1) @(negedge clk);
        
        
        //$finish;
    end

endmodule

    
