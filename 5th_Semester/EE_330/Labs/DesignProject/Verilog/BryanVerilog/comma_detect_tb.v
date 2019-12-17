`timescale 1ns/1ps
`default_nettype none
module comma_detect_tb();
  reg [9:0] in;
  wire [0:0] out;

  comma_detector dut(.pi(in), .det(out));

  initial
  begin
    assign in = 10'b0000011111;
    #1
    $display("detected? %b (should be 0)", out);
    #9

    assign in = 10'b1111111111;
    #1
    $display("detected? %b (should be 0)", out);
    #9

    assign in = 10'b0000000000;
    #1
    $display("detected? %b (should be 0)", out);
    #9

    assign in = 10'b1010001110;
    #1
    $display("detected? %b (should be 1)", out);
  end
endmodule
