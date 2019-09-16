-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Two-to-One Multiplexer with N-Bit Compatibility 
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a two-to-one
-- multiplexer for N-bits in alignment with part 2 of lab 2 for CprE 381
-- Styled as dataflow
--
-- NOTES:
-- 1/26/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;

entity Mux2to1 is 
	port(i_A  : in std_logic_vector;
		 i_B  : in std_logic_vector;
		 i_S  : in std_logic;
		 o_F  : out std_logic_vector);
		
end Mux2to1;


architecture dataflow of Mux2to1 is 
begin

	o_F <= i_A when (i_S = '0') else i_B;
	--o_F <= (i_A and (not i_S)) or (i_B and i_S);

end dataflow;

























