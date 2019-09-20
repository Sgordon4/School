-------------------------------------------------------------------------
-- Sean Gordon
-- EE 330
-------------------------------------------------------------------------

-- Two-Bit NOR Gate
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a Two-Bit
-- NOR Gate
--
--
-- NOTES:
-- 8/30/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity Nor2Bit is
   port(i_A			: in std_logic;		-- 1 bit input
		i_B			: in std_logic;		-- 1 bit input
		o_F         : out std_logic);   -- 1 bit output

end Nor2Bit;

architecture dataflow of Nor2Bit is
begin
	o_F <= i_A nor i_B;					-- Difficult stuff, really
end dataflow;