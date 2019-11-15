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
    output Q;
    reg Q;

    always @ ((posedge clk) or en)
    if(en) begin 
        if(rst) begin
            Q <= 0;
        end
        else begin
            Q <= D;
        end
    end
endmodule
