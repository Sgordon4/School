`timescale 1ns/1ps
`default_nettype none
module decoder_5b_4b(in, en, out);
  input wire [4:0] in;
  output reg [3:0] out;
  input wire en;

  always @*
  begin
    if(en)
      begin
      case (in)
        5'h04   : out <= 4'b0000;
        5'h05   : out <= 4'b0001;
        5'h06   : out <= 4'b0010;
        5'h09   : out <= 4'b0011;
        5'h0a   : out <= 4'b0100;
        5'h0b   : out <= 4'b0101;
        5'h0c   : out <= 4'b0110;
        5'h0d   : out <= 4'b0111;
        5'h12   : out <= 4'b1000;
        5'h13   : out <= 4'b1001;
        5'h14   : out <= 4'b1010;
        5'h15   : out <= 4'b1011;
        5'h16   : out <= 4'b1100;
        5'h19   : out <= 4'b1101;
        5'h1a   : out <= 4'b1110;
        5'h1b   : out <= 4'b1111;
        default : out <= 4'bzzzz;
      endcase
      end
    else
      out <= 4'bzzzz;
  end
endmodule
