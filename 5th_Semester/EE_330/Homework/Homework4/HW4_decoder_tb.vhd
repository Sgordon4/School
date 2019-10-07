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

entity decoder_tb is

end decoder_tb;

architecture behavior of decoder_tb is

    component HW4_decoder
       port(i_t		: in std_logic_vector(14 downto 0);
			o_b     : out std_logic_vector(3 downto 0));
    end component;
	
	
	signal s_it		: std_logic_vector(14 downto 0);
	signal s_ob		: std_logic_vector(3 downto 0);


    begin

        DUT: HW4_decoder
		   port map(i_t => s_it,
					o_b => s_ob);


        process
        begin

          s_it <= "000000000000000";
          wait for 100 ns;
		  
		  s_it <= "000000000000001";
          wait for 100 ns;
		  
		  s_it <= "000000000000011";
          wait for 100 ns;
		  
		  s_it <= "000000000000111";
          wait for 100 ns;
		  
		  
		  s_it <= "000000000001111";
          wait for 100 ns;
		  
		  s_it <= "000000000011111";
          wait for 100 ns;
		  
		  s_it <= "000000000111111";
          wait for 100 ns;
		  
		  s_it <= "000000001111111";
          wait for 100 ns;
		  
		  
		  s_it <= "000000011111111";
          wait for 100 ns;
		  
		  s_it <= "000000111111111";
          wait for 100 ns;
		  
		  s_it <= "000001111111111";
          wait for 100 ns;
		  
		  s_it <= "000011111111111";
          wait for 100 ns;
		  
		  
		  s_it <= "000111111111111";
          wait for 100 ns;
		  
		  s_it <= "001111111111111";
          wait for 100 ns;
		  
		  s_it <= "011111111111111";
          wait for 100 ns;
		  
		  s_it <= "111111111111111";
          wait for 100 ns;


        end process;

end behavior;
