`timescale 1ns/1ps
module decoder_5b_4b_tb();
	reg [4:0] word_in;
	wire [3:0] nibble_out;
	reg [0:0] enable;
	
	decoder_5b_4b dut(.in(word_in), .en(enable), .out(nibble_out));
	
	initial
	begin
		assign enable = 1'b1;
		assign word_in = 5'h04;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h05;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9
		
		assign word_in = 5'h06;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h09;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h0a;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h0b;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9
		
		assign word_in = 5'h0c;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h0d;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h12;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h13;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9
		
		assign word_in = 5'h14;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h15;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h16;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h19;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9
		
		assign word_in = 5'h1a;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign word_in = 5'h1b;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign enable = 1'b0;
		#1
		$display("%b -> %b", word_in, nibble_out);
		#9

		assign enable = 1'b1;
		assign word_in = 5'h1F;
		#1
		$display("%b -> %b", word_in, nibble_out);
	end
endmodule
