//------------------------------------
// 4-Bit up counter designed to reset
// upon reaching 10.
//
// Author:
// Bryan Friestad (bryanf)
//
// Edited:
// Sean Gordon (SGordon4)
//------------------------------------

module countToTen(clk, rst, cnt_of_10);
	input wire clk;
	input wire rst;
	output reg cnt_of_10;

	reg [3:0] count;
	  
	  
	always @ (posedge clk)
		if(rst)
			count <= 1;		//This line edited

		else begin
			count <= count + 1;
			
			if(count == 9) begin 
				count <= 0;
				cnt_of_10 <= 1;
			end
			else 
				cnt_of_10 <= 0;
		end
endmodule
