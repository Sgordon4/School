library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
use IEEE.numeric_std.all;


entity FAdder is
  port (i_A		: in std_logic;
		i_B		: in std_logic;
		i_C		: in std_logic;
	
		o_S		: out std_logic;
		o_C 	: out std_logic);
	
end FAdder;
 
architecture structure of FAdder is
	signal s_A, s_B			: std_logic;
	signal s_S, s_C1, s_C2	: std_logic;
	
	component HAdder
	  port (i_A		: in std_logic;
			i_B		: in std_logic;
	
			o_S		: out std_logic;
			o_C 	: out std_logic);
	end component;
	
begin

	HAdd1: HAdder
	   port map(i_A		=> i_A,
				i_B		=> i_B,
				o_S		=> s_S,
				o_C		=> s_C1);
	
	HAdd2: HAdder
	   port map(i_A		=> s_S,
				i_B		=> i_C,
				o_S		=> o_S,
				o_C		=> s_C2);
	
	o_C <= s_C1 or s_C1;
end structure;
