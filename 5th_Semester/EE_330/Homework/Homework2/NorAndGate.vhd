-------------------------------------------------------------------------
-- Sean Gordon
-- EE 330
-------------------------------------------------------------------------

-- Two-Bit And Gate Using Two-Bit Nor Gate
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a Two-Bit
-- And gate using an implementation of a two bit Nor gate.
--
--
-- NOTES:
-- 9/9/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity NorAndGate is
   port(i_A     : in std_logic;
        i_B     : in std_logic;
        o_F     : out std_logic);
end NorAndGate;

architecture structure of NorAndGate is

    component Nor2Bit
      port(i_A  : in std_logic;
           i_B  : in std_logic;
           o_F  : out std_logic);
    end component;

    -- Signals to interconnect required nor gates
    signal s_AC, s_BC   : std_logic;

    begin

        norA: Nor2Bit
           port map(i_A => i_A,
                    i_B => i_A,
                    o_F => s_AC);

        norB: Nor2Bit
           port map(i_A => i_B,
                    i_B => i_B,
                    o_F => s_BC);


        norC: Nor2Bit
           port map(i_A => s_AC,
                    i_B => s_BC,
                    o_F => o_F);

end structure;
