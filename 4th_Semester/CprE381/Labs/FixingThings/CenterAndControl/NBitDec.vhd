-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- N-Bit Decoder File
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for an N-Bit
-- Decoder in alignment with part 1 of lab 3 for CprE 381
--
--
-- NOTES:
-- 2/6/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.math_real.all;

entity NBitDec is
	generic(N : integer := 32);
  port(i_E        	: in std_logic;     -- Enable input
										-- Calculates the log2 of N to find adequate # of select bits
       i_A          : in std_logic_vector( positive(ceil(log2(real(N)))) -1 downto 0);     -- N-Bit input
       o_F          : out std_logic_vector(N-1 downto 0));   -- N-Bit output

end NBitDec;

architecture dataflow of NBitDec is
begin
	process(i_E, i_A)
	begin
	
		if(i_E = '1') then 
			-- Set all of the outputs to 0 by default...
			o_F <= (others => '0');
			
			-- Set the specified index of o_F to 1
			o_F(to_integer(unsigned(i_A))) <= '1';
		end if;
		
	end process;
end dataflow;