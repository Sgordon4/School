`timescale 1ns/1ps
module up_counter_4b_tb();
reg reset;
reg clk;
wire enable;

dff_up_counter_4b dut(clk, reset, enable);

initial begin
  clk=0;
     forever #10 clk = ~clk;  
end 

initial begin 
  #10;
  reset=1;
  #10;
  reset=0;
  #130;
  reset=1;
  #10
  reset=0;
end 
endmodule 
