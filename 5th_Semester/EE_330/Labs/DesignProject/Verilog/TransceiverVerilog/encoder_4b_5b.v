`timescale 1ns/1ps
`default_nettype none
module encoder_4b_5b(in, out);
  input wire [3:0] in;
  output reg [4:0] out;

  always @*
  begin
    case (in)
      4'b0000 : out <= 5'b00100;
      4'b0001 : out <= 5'b00101;
      4'b0010 : out <= 5'b00110;
      4'b0011 : out <= 5'b01001;
      4'b0100 : out <= 5'b01010;
      4'b0101 : out <= 5'b01011;
      4'b0110 : out <= 5'b01100;
      4'b0111 : out <= 5'b01101;
      4'b1000 : out <= 5'b10010;
      4'b1001 : out <= 5'b10011;
      4'b1010 : out <= 5'b10100;
      4'b1011 : out <= 5'b10101;
      4'b1100 : out <= 5'b10110;
      4'b1101 : out <= 5'b11001;
      4'b1110 : out <= 5'b11010;
      4'b1111 : out <= 5'b11011;
    endcase
  end
endmodule
