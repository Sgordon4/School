`timescale 1ns/1ps
`default_nettype none
module dff_up_counter_4b(clk, rst, cnt_of_10);
  input wire clk;
  input wire rst;
  output reg cnt_of_10;

  reg [3:0] count;

  always @ (posedge clk)
  if(rst) begin
    count <= 4'b0000;
  end
  else begin
    count <= count + 1;
    if(count == 4'b1001) begin
      count <= 4'b0000;
      cnt_of_10 <= 1;
    end
    else begin
      cnt_of_10 <= 0;
    end
  end
endmodule
