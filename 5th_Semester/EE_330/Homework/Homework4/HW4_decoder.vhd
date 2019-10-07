library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
use IEEE.numeric_std.all;
use IEEE.math_real.all;

entity HW4_decoder is
   port(i_t		: in std_logic_vector(14 downto 0);
		o_b     : out std_logic_vector(3 downto 0));
end HW4_decoder;

architecture behavior of HW4_decoder is

    begin
					-- Every second set of inputs
		o_b(0)	<=	'1' when ((to_integer(unsigned(i_t)) mod 3) = 1)
						else '0';
						
					-- Every fourth set of inputs
		o_b(1)	<=	'1' when (to_integer(unsigned(i_t) mod 15) >= 3)
						else '0';
						
					-- Every fourth set of inputs
		o_b(2)	<=	'1' when (to_integer(unsigned(i_t) mod 255) >= 15)
						else '0';
						
					-- Every fourth set of inputs
		o_b(3)	<=	'1' when (to_integer(unsigned(i_t)) >= 255)
						else '0';

end behavior;