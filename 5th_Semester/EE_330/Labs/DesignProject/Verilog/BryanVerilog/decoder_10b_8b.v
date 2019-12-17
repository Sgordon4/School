`timescale 1ns/1ps
`default_nettype none
module decoder_10b_8b(in, en, out);
  input wire [9:0] in;
  output reg [7:0] out;
  input wire en;

  wire [7:0] temp;

  decoder_5b_4b high_half(in[9:5], en, temp[7:4]);
  decoder_5b_4b low_half(in[4:0], en, temp[3:0]);

  always @*
  begin
    out <= temp;
  end
endmodule
