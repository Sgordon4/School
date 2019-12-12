`timescale 1ns/1ps
module encoder_4b_5b_tb();
	reg [3:0] nibble_in;
	wire [4:0] word_out;
	
	encoder_4b_5b dut(.in(nibble_in), .out(word_out));
	
	initial
	begin
		assign nibble_in = 4'b0000;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b0001;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b0010;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b0011;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b0100;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b0101;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b0110;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b0111;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b1000;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b1001;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b1010;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b1011;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b1100;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9
	
		assign nibble_in = 4'b1101;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b1110;
		#1
		$display("%b -> %b", nibble_in, word_out);
		#9

		assign nibble_in = 4'b1111;
		#1
		$display("%b -> %b", nibble_in, word_out);
	end
endmodule
