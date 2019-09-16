-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Two-to-One Multiplexer with N-Bit Compatibility
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a two-to-one
-- multiplexer for N-bits in alignment with part 2 of lab 2 for CprE 381
--
--
-- NOTES:
-- 1/26/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity MuxCascade_32 is 
	generic(N : integer := 32);
	port(i_A  : in std_logic_vector(N-1 downto 0);
		 i_B  : in std_logic_vector(N-1 downto 0);
		 i_S  : in std_logic;
		 o_F  : out std_logic_vector(N-1 downto 0));
		
end MuxCascade_32;


architecture dataflow of MuxCascade_32 is 

begin
	process(i_S, i_A, i_B)
	begin

		if(i_S = '0') then
			o_F <= i_A;
		else
			o_F <= i_B;
		end if;
		
	end process;
end dataflow;




















