//------------------------------------
// 4-Bit shift register built from
// synchronous D-Flip-Flops
//
// Sean Gordon
// SGordon4
//------------------------------------
module Shift_4Bit(
    D,
    clk,
    rst,
    Q
);
    input D, clk, rst;
    output Q;
    wire s_D1_2, s_D2_3, s_D3_4;

    DFF_Sync DFF1(
    .D      (D),
    .clk    (clk),
    .rst    (rst),
    .Q      (s_D1_2)
    );
    DFF_Sync DFF2(
    .D      (s_D1_2),
    .clk    (clk),
    .rst    (rst),
    .Q      (s_D1_3)
    );
    DFF_Sync DFF3(
    .D      (s_D1_3),
    .clk    (clk),
    .rst    (rst),
    .Q      (s_D1_4)
    );
    DFF_Sync DFF4(
    .D      (s_D1_4),
    .clk    (clk),
    .rst    (rst),
    .Q      (Q)
    );

endmodule 

