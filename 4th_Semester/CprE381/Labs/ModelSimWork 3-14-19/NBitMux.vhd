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

package bus_multiplexer_pkg is
        type bus_array is array(31 downto 0) of std_logic_vector(31 downto 0);
end package;

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.math_real.all;
use work.bus_multiplexer_pkg.all;



entity NBitMux is
	generic(N : integer := 32;		-- Mux bundle size		--Bundle width has no bearing on mux operation
			W : integer := 32);		-- Mux width
	
									-- Calculates the log2 of W to find adequate # of select bits
  port(i_S          : in std_logic_vector( positive(ceil(log2(real(W)))) -1 downto 0);  -- N-Bit log2(N) bit select
	   i_A			: in bus_array;														-- N-Bit array input
       o_F          : out std_logic_vector(N-1 downto 0));   							-- N-Bit output

end NBitMux;

architecture dataflow of NBitMux is

--type arr is array (N-1 downto 0) of std_logic_vector(W-1 downto 0);
--signal i_A : arr := (others => (others => '0'));


begin
	
	o_F <= i_A(to_integer(unsigned(i_S)));

end dataflow;

