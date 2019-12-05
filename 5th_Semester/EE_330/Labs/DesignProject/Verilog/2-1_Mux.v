//------------------------------------
// 2 to 1 multiplexor.
//
// Sean Gordon
// SGordon4
//------------------------------------
 module  2to1_Mux(
     i_A      , // Mux first input
     i_B      , // Mux Second input
     S        , // Sect input
     o_F      // Mux output
 );

     input i_A, i_B, S ;
     output o_F;
     wire  o_F;

     assign o_F = (S) ? i_B : i_A;
 
 endmodule
