library IEEE;
use IEEE.std_logic_1164.all;

-- 1Mhz => 1 us
 
entity DFF_sync_tb is
end DFF_sync_tb;
 
architecture behavior of DFF_sync_tb is
	signal s_clk 		: std_logic := '0';
	
	signal s_D 		: std_logic;
	signal s_Rst	: std_logic;
	signal s_Q		: std_logic;
begin
   
DUT : entity work.DFF_sync
  port map (i_D   => s_D,
			i_Clk => s_clk,
			i_Rst => s_Rst,
			
			o_Q	  => s_Q);
			
	s_clk <= not s_clk after 500 ns;
 
process is
begin
	
	s_D   <= '0';
	s_Rst <= '0';
    wait for 500 ns;
	
    s_D   <= '0';
	s_Rst <= '0';
    wait for 500 ns;
	
    s_D   <= '1';
	s_Rst <= '0';
    wait for 500 ns;
	
	s_D   <= '1';
	s_Rst <= '0';
    wait for 500 ns;
	
	
	s_D   <= '0';
	s_Rst <= '1';
    wait for 500 ns;
	
    s_D   <= '0';
	s_Rst <= '1';
    wait for 500 ns;
	
    s_D   <= '1';
	s_Rst <= '1';
    wait for 500 ns;
	
	s_D   <= '1';
	s_Rst <= '1';
    wait for 500 ns;

end process;
end behavior;