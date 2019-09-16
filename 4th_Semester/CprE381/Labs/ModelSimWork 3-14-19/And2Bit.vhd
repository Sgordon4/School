-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Two-Bit AND Gate
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a Two-Bit
-- AND Gate
--
--
-- NOTES:
-- 2/25/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity And2Bit is
   port(i_A			: in std_logic;		-- 1 bit input
		i_B			: in std_logic;		-- 1 bit input
		o_F         : out std_logic);   -- 1 bit output

end And2Bit;

architecture dataflow of And2Bit is
begin
	o_F <= i_A and i_B;					-- Difficult stuff, really
end dataflow;