`timescale 1ns/1ps
`default_nettype none
module comma_detector(pi, det);
  input wire [9:0] pi;
  output reg [0:0] det;

  always @*
  begin
    if(pi == 10'b1010001110)
      begin
      det <= 1'b1;
      end
    else
      det <= 1'b0;
  end
endmodule
