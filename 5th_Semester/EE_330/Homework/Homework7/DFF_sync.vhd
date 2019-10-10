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

	process(i_Clk)
	begin 
	
		if(rising_edge(i_Clk)) then
			if(i_Rst='1') then 
				o_Q <= '0';
			else
				o_Q <= i_D;
			end if;
		end if;  
		
	end process; 
	
end behavior;