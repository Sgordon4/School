library IEEE;
use IEEE.std_logic_1164.all;

entity FullAdder is
   port(i_A		: in std_logic;
		i_B		: in std_logic;
		Cin		: in std_logic;
		Sum		: out std_logic;
		Cout	: out std_logic);
		
end FullAdder;

architecture structure of FullAdder is
begin

	Sum <= ((i_A xor i_B) xor Cin);
	Cout <= ((i_A and i_B) or (i_A and Cin) or (i_B and Cin));

end structure;