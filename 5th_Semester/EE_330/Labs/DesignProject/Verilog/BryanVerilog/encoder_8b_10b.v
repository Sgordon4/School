`timescale 1ns/1ps
`default_nettype none
module encoder_8b_10b(word_in, long_word_out);
  input wire [7:0] word_in;
  output reg [9:0] long_word_out;

  wire [9:0] temp;

  encoder_4b_5b high_nibble(word_in[7:4], temp[9:5]);
  encoder_4b_5b low_nibble(word_in[3:0], temp[4:0]);

  always @*
  begin
    long_word_out <= temp;
  end
endmodule
