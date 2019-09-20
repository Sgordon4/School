-------------------------------------------------------------------------
-- Sean Gordon
-- EE 330
-------------------------------------------------------------------------

-- One-Bit Not Gate Using Two-Bit Nor Gate
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a One-Bit
-- Not gate using an implementation of a two bit Nor gate.
--
--
-- NOTES:
-- 9/9/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity NorNotGate is
   port(i_A     : in std_logic;
        o_F     : out std_logic);
end NorNotGate;

architecture structure of NorNotGate is

    component Nor2Bit
      port(i_A  : in std_logic;
           i_B  : in std_logic;
           o_F  : out std_logic);
    end component;

    begin

        norA: Nor2Bit
           port map(i_A => i_A,
                    i_B => i_A,
                    o_F => o_F);

end structure;
