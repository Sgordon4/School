//---------------------------------------
//
// Boolean function for lab 12
// Implementing function:
// !ABC + A!BC +AB!C
//
//---------------------------------------

module boolean_function(
	i_A,
	i_B,
	i_C,
	o_F
);

	input i_A, i_B, i_C;
	output o_F;
	wire s_gate1, s_gate2, s_gate3;

	and (s_gate1, !i_A, i_B, i_C);
	and (s_gate2, i_A, !i_B, i_C);
	and (s_gate3, i_A, i_B, !i_C);

	or  (o_F, s_gate1, s_gate2, s_gate3);
endmodule
