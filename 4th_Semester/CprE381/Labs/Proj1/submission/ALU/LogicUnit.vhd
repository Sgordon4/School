-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Logic Unit
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for an ALU logic
-- unit, capable of and/or/xor/nor operations
--
--
-- NOTES:
-- 2/25/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;

entity LogicUnit is 
   port(i_A		: in std_logic_vector(31 downto 0);
		i_B		: in std_logic_vector(31 downto 0);
		i_S		: in std_logic_vector(1 downto 0);
		o_F		: out std_logic_vector(31 downto 0));
end LogicUnit;

architecture behavior of LogicUnit is
begin

	o_F <= 	(i_A and i_B) when (i_S = "00") else 
			(i_A xor i_B) when (i_S = "01") else
			(i_A or i_B)  when (i_S = "10") else
			(i_A nor i_B);
	
end behavior;














