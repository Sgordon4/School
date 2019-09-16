library IEEE;
use IEEE.std_logic_1164.all;

entity Control is
	
   port(Instr		: in std_logic_vector(31 downto 26);	-- Bits [31-26] of machine instruction
		ALUOp		: out std_logic_vector(1 downto 0);
		Branch		: out std_logic;
		MemWrite	: out std_logic;
		MemRead		: out std_logic;
		RegWrite	: out std_logic;
		MemtoReg	: out std_logic;
		ALUSrc		: out std_logic;
		RegDst		: out std_logic);
	
end Control;

architecture behavior of Control is 

signal RType, lw, sw, beq	: std_logic;

begin 

	RType	<= ((not Instr(31)) and (not Instr(30)) and (not Instr(29)) and 
				(not Instr(28)) and (not Instr(27)) and (not Instr(26)));
				
	lw		<= (    (Instr(31)) and (not Instr(30)) and (not Instr(29)) and 
				(not Instr(28)) and      Instr(27)  and      Instr(26));
				
	sw		<= (    (Instr(31)) and (not Instr(30)) and     (Instr(29)) and 
				(not Instr(28)) and      Instr(27)  and      Instr(26));
				
	beq		<= ((not Instr(31)) and (not Instr(30)) and (not Instr(29)) and 
				    (Instr(28)) and (not Instr(27)) and (not Instr(26)));
					
					
					
					
	RegDst		<= RType;
	ALUSrc		<= lw or sw;
	MemtoReg	<= lw;
	RegWrite	<= RType or lw;
	MemRead		<= lw;
	MemWrite	<= sw;
	Branch		<= beq;
	ALUOp(1)	<= RType;
	ALUOp(0)	<= beq;
	
end behavior;