//------------------------------------
// D flip flop designed to only change
// value on clock posedge
//
// Sean Gordon
// SGordon4
//------------------------------------
module DFF_Sync (
    D,
    clk,
    rst,
    Q
);

    input D, clk, rst;
    output Q;
    reg Q;

    always @ (posedge clk)
    if(~reset) begin
        Q <= 0;
    end
    else begin
        Q <= D;
    end
endmodule
