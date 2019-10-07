library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
 
entity FAdder_tb is
end FAdder_tb;
 
architecture behavior of FAdder_tb is
  signal s_A  : std_logic;
  signal s_B  : std_logic;
  signal s_Ci : std_logic;
  
  signal s_S  : std_logic;
  signal s_Co : std_logic;
begin
   
DUT : entity work.FAdder
    port map (
		i_A => s_A,
		i_B => s_B,
		i_C => s_Ci,
		
		o_S => s_S,
		o_C => s_Co);
 
process is
begin
	s_A <= '0';
    s_B <= '0';
	s_Ci <= '0';
    wait for 10 ns;
    s_A <= '0';
    s_B <= '0';
	s_Ci <= '1';
    wait for 10 ns;
	s_A <= '0';
    s_B <= '1';
	s_Ci <= '0';
    wait for 10 ns;
	s_A <= '0';
    s_B <= '1';
	s_Ci <= '1';
    wait for 10 ns;
	
	s_A <= '1';
    s_B <= '0';
	s_Ci <= '0';
    wait for 10 ns;
    s_A <= '1';
    s_B <= '0';
	s_Ci <= '1';
    wait for 10 ns;
	s_A <= '1';
    s_B <= '1';
	s_Ci <= '0';
    wait for 10 ns;
	s_A <= '1';
    s_B <= '1';
	s_Ci <= '1';
    wait for 10 ns;
end process;
end behavior;