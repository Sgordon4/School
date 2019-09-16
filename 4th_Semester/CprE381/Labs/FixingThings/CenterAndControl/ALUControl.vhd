library ieee;
use ieee.std_logic_1164.all;

entity ALUControl is
   port(Instr		: in std_logic_vector(5 downto 0);
		ALUOp		: in std_logic_vector(1 downto 0);
		ALUControl	: out std_logic_vector(3 downto 0));

end ALUControl;

architecture behavior of ALUControl is

	alias A : std_logic is Instr(5);
	alias B : std_logic is Instr(4);
	alias C : std_logic is Instr(3);
	alias D : std_logic is Instr(2);
	alias E : std_logic is Instr(1);
	alias F : std_logic is Instr(0);
	
	alias G : std_logic is ALUOp(1);
	alias H : std_logic is ALUOp(0);
	
	signal GnH	: std_logic;
	signal AnBnC: std_logic;
	

begin

	GnH 	<= G and (not H);
	AnBnC	<= A and (not B) and (not C);
	
	ALUControl(3) <= 	(AnBnC and D and F and GnH) or H or
				((not B) and (not C) and (not D) and E and GnH);
				
	ALUControl(2) <= 	(AnBnC and D and E and GnH) or (not G) or
				(AnBnC and (not D) and (not F) and GnH) or
				((not A) and (not B) and (not C) and (not D) and E and F and GnH);
				
	ALUControl(1) <= 	(not G) or (A and (not B) and (not D));
	
	ALUControl(0) <=	((not A) and G) or 
				(A and (not B) and C and (not D) and E and (not F) and G);
	
	
	--  Signal layout in lecture slides
	-- notg	<= not ALUOp(1);
	-- and1	<= ALUOp(1) and Instr(1) and (not Instr(2));
	-- and2	<= ALUOp(1) and (not Instr(2));
	-- and3	<= ALUOp(1) and Instr(1);
	-- and4	<= ALUOp(1) and Instr(0);
	-- and5	<= ALUOp(1) and Instr(3);
	

	-- ALUControl(3) <= and1 or ALUOp(0);
	-- ALUControl(2) <= notg or and2;
	-- ALUControl(1) <= notg or and2 or and3;
	-- ALUControl(0) <= and4 or and5;
	
end behavior;
	
	
	
	
	