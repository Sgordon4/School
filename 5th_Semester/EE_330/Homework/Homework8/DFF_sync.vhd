library IEEE;
use IEEE.std_logic_1164.all;

entity DFF_sync is 
   port(i_D		: in std_logic;
		i_Clk	: in std_logic;
		i_Rst	: in std_logic;
		
		o_Q		: out std_logic);
end DFF_sync;

architecture behavior of DFF_sync is  
begin

	process(i_Clk, i_Rst)
	begin 

		-- We are now asynchronously resettable
		if(i_Rst = '1') then
			o_Q <= '0';
		elsif(rising_edge(i_Clk)) then
			o_Q <= i_D;
		end if;  
		
	end process; 
	
end behavior;