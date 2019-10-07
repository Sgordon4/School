library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
 
entity HAdder_tb is
end HAdder_tb;
 
architecture behavior of HAdder_tb is
  signal s_A  : std_logic;
  signal s_B  : std_logic;
  signal s_S  : std_logic;
  signal s_C  : std_logic;
begin
   
DUT : entity work.HAdder
    port map (
		i_A => s_A,
		i_B => s_B,
		o_S => s_S,
		o_C => s_C);
 
process is
begin
	s_A <= '0';
    s_B <= '0';
    wait for 10 ns;
    s_A <= '0';
    s_B <= '1';
    wait for 10 ns;
    s_A <= '1';
    s_B <= '0';
    wait for 10 ns;
    s_A <= '1';
    s_B <= '1';
    wait for 10 ns;
end process;
end behavior;