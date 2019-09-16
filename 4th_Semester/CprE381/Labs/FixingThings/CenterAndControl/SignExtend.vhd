-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Sign Extender
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a sign extender for lab 4 part 2 for 
-- CprE 381
--
-- NOTES:
-- 2/12/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;

entity SignExtend is
   generic (
		IN_WIDTH : natural := 16;
		OUT_WIDTH : natural := 32
	);
   port(A	: in std_logic_vector(IN_WIDTH-1 downto 0);
		S	: in std_logic;
		F	: out std_logic_vector(OUT_WIDTH-1 downto 0)
	);
end SignExtend;

architecture behavior of SignExtend is
begin
	process(A, S)
	begin 
	
		if(S = '1') then 	--Sign extend
			F <= std_logic_vector(resize(signed(A), F'length));
		else 				--No sign extend
			F <= std_logic_vector(resize(unsigned(A), F'length));
		end if;

	end process;
end architecture;