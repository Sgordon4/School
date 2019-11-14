
`timescale 1 ns/10 ps
module boolean_function_tb;

	reg i_A, i_B, i_C;
	wire o_F;

	localparam period = 20;
	
	boolean_function DUT(.i_A(i_A), .i_B(i_B), .i_C(i_C), .o_F(o_F));

	initial
	begin
		i_A = 0; 
		i_B = 0; 
		i_C = 0;
		#period;

		i_A = 0; 
		i_B = 0; 
		i_C = 1;
		#period;

		i_A = 0; 
		i_B = 1; 
		i_C = 0;
		#period;

		i_A = 0; 
		i_B = 1; 
		i_C = 1;
		#period;

		i_A = 1; 
		i_B = 0; 
		i_C = 0;
		#period;

		i_A = 1; 
		i_B = 0; 
		i_C = 1;
		#period;

		i_A = 1; 
		i_B = 1; 
		i_C = 0;
		#period;

		i_A = 1; 
		i_B = 1; 
		i_C = 1;
		#period;
	end
endmodule
