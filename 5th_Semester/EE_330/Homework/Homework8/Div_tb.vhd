library IEEE;
use IEEE.std_logic_1164.all;

-- 1Mhz => 1 us
 
entity Div_tb is
end Div_tb;
 
architecture behavior of Div_tb is
	signal s_clk 		: std_logic := '1';
	
	signal s_Sel	: std_logic;
	signal s_Rst	: std_logic;
	signal s_F		: std_logic;
begin

DUT : entity work.ClockDivider
  port map (i_Clk   => s_clk,
			i_Rst => s_Rst,
			i_Sel => s_Sel,
			
			o_F	  => s_F);
			
	s_clk <= not s_clk after 500 ns;
 
process is
begin

	s_Rst   <= '1';
	s_Sel	<= '0';
    wait for 4 us;
	-- Reset everything
	-- Wait for four clock pulses
	
	s_Rst   <= '0';
	s_Sel	<= '0';
    wait for 4 us;
	-- Wait for four clock pulses
	
    s_Rst   <= '0';
	s_Sel	<= '1';
    wait for 4 us;
	-- Wait for four clock pulses
	
	s_Rst   <= '1';
	s_Sel	<= '0';
    wait for 4 us;
	-- Wait for four clock pulses
	
    s_Rst   <= '1';
	s_Sel	<= '1';
    wait for 4 us;
	-- Wait for four clock pulses

end process;
end behavior;