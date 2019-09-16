-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Set on Less Than Unit
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for an ALU logic
-- unit, capable of and/or/xor/nor operations
--
--
-- NOTES:
-- 2/28/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.std_logic_unsigned.all;

entity SLT is 
   port(i_A		: in std_logic_vector(31 downto 0);
		i_B		: in std_logic_vector(31 downto 0);
		o_F		: out std_logic_vector(31 downto 0));
end SLT;

architecture behavior of SLT is 
begin
	process(i_A, i_B)
	begin
	
		if(signed(i_A) < signed(i_B)) then
			o_F <= (0 => '1', others => '0');
		else
			o_F <= (0 => '0', others => '0');
		end if;
		
	end process;
end behavior;