-------------------------------------------------------------------------
-- Sean Gordon
-- EE 330
-------------------------------------------------------------------------

-- Test Bench For Nor Gate Logic
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a test bench for logic built using
-- only Nor gates in accordance with EE330 HW2
--
--
-- NOTES:
-- 9/9/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity HW2Nor_TB is

end HW2Nor_TB;

architecture behavior of HW2Nor_TB is

    component NorAndGate
      port(i_A  : in std_logic;
           i_B  : in std_logic;
           o_F  : out std_logic);
    end component;

    component NorOrGate
      port(i_A  : in std_logic;
           i_B  : in std_logic;
           o_F  : out std_logic);
    end component;

    component NorNotGate
      port(i_A  : in std_logic;
           o_F  : out std_logic);
    end component;


    -- Signals reprresenting input/output signals
    signal s_iA, s_iB, s_iC, s_oF;


    -- Signals interconnecting various gates for first half of function
    signal s_notA, s_and11, s_and12;

    -- Signals interconnecting various gates for second half of function
    signal s_notC, s_and21, s_and22;

    -- Signal connecting both halves of function
    signal s_or;




    begin

        -----------------------------------
        --           First Half          --
        -----------------------------------
        notA: NorNotGate
           port map(i_A => s_iA,
                    o_F => s_notA);


        And11: NorAndGate
           port map(i_A => s_notA,
                    i_B => s_iB,
                    o_F => s_and11);

        And12: NorAndGate
           port map(i_A => s_and11,
                    i_B => s_iC,
                    o_F => s_and22);

        -----------------------------------
        --          Second Half          --
        -----------------------------------

        notC: NorNotGate
           port map(i_A => s_iC,
                    o_F => s_notC);


        And21: NorAndGate
           port map(i_A => s_iA,
                    i_B => s_iB,
                    o_F => s_and21);

        And22: NorAndGate
           port map(i_A => s_and21,
                    i_B => s_notC,
                    o_F => s_and22);

        -----------------------------------
        --            Center OR          --
        -----------------------------------


        Or1: NorOrGate
           port map(i_A => s_and12,
                    i_B => s_and22,
                    o_F => s_oF);

        -----------------------------------


        process
        begin

          s_iA <= '0';
          s_iB <= '0';
          s_iC <= '0';
          wait for 100 ns;

          s_iA <= '0';
          s_iB <= '0';
          s_iC <= '1';
          wait for 100 ns;

          s_iA <= '0';
          s_iB <= '1';
          s_iC <= '0';
          wait for 100 ns;

          s_iA <= '0';
          s_iB <= '1';
          s_iC <= '1';
          wait for 100 ns;

          s_iA <= '1';
          s_iB <= '0';
          s_iC <= '0';
          wait for 100 ns;

          s_iA <= '1';
          s_iB <= '0';
          s_iC <= '1';
          wait for 100 ns;

          s_iA <= '1';
          s_iB <= '1';
          s_iC <= '0';
          wait for 100 ns;

          s_iA <= '1';
          s_iB <= '1';
          s_iC <= '1';
          wait for 100 ns;



        end process;

end behavior;
