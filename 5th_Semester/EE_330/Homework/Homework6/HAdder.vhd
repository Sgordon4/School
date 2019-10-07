-------------------------------------------------------------------------
-- Sean Gordon
-- EE 330
-- Homework 6
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
use IEEE.numeric_std.all;


entity HAdder is
  port (i_A		: in std_logic;
		i_B		: in std_logic;
	
		o_S		: out std_logic;
		o_C 	: out std_logic);
	
end HAdder;
 
architecture structure of HAdder is
begin
	o_S <= i_A xor i_B;
	o_C <= i_A and i_B;
end structure;