//------------------------------------
// D flip flop with enable designed to 
// only change value on clock posedge
//
// Sean Gordon
// SGordon4
//------------------------------------
module DFF_Sync (
    D,
    clk,
    rst,
    en,
    Q
);

    input D, clk, rst, en;
    output reg Q;

    //always @ ((posedge clk) or en)
	always @ (posedge clk)
    if(en) begin 
        if(rst)
            Q <= 1'b0;
        else 
            Q <= D;
    end
endmodule
