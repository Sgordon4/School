library IEEE;
use IEEE.std_logic_1164.all;

entity Control is
	
   port(Opcode		: in std_logic_vector(31 downto 26);	-- Bits [31-26] of machine instruction
		Funct		: in std_logic_vector(5 downto 0);		-- Bits [5-0] of machine instruction
		
		--ALUOp		: out std_logic_vector(1 downto 0);		-- ALUCtrl is made in-house here, no need for ALUOp
		Jump		: out std_logic;
		Branch		: out std_logic;
		MemWrite	: out std_logic;
		MemRead		: out std_logic;
		RegWrite	: out std_logic;
		MemtoReg	: out std_logic;
		ALUSrc		: out std_logic;
		RegDst		: out std_logic;
		ALUCtrl		: out std_logic_vector(3 downto 0));	-- Control signal for ALU
	
end Control;

architecture behavior of Control is 
begin 

	---------- Just implementing a LUT ----------

	RegDst 		<= '1' when Opcode = "000000" else
				   '-' when Opcode = "101011" else '0';
	ALUSrc 		<= '1' when Opcode /= "000000" else '0';
	MemtoReg	<= '1' when (Opcode = "100011") or (Opcode = "001111") else
				   '-' when Opcode = "101011" else '0';
	RegWrite	<= '1' when Opcode /= "101011" else '0';
	MemRead		<= '1' when (Opcode = "100011") or (Opcode = "001111") else '0';
	MemWrite	<= '1' when Opcode = "101011" else '0';
	Branch 		<= '0';
	Jump		<= '0';
	
	
	---------------- ALUCtrl LUT ----------------
	
				  -- Add 
	ALUCtrl		<= 	"0110" when (Opcode = "000000" and Funct = "100000") or		-- Add
								(Opcode = "001000") or 							-- Addi
								(Opcode = "100011") or							-- Lw
								(Opcode = "001111") or 							-- Lui
								(Opcode = "101011") else						-- Sw
				  -- Addu
					"0010" when (Opcode = "000000" and Funct = "100001") or		-- Addu
								(Opcode = "001001") else						-- Addiu
				  -- Subu
					"1010" when	(Opcode = "000000" and Funct = "100011") else	-- Subu
				----------------------------------------------------------------
				  -- And
					"0000" when (Opcode = "000000" and Funct = "100100") or		-- And
								(Opcode = "001100") else						-- Andi
				  -- Or
					"1000" when (Opcode = "000000" and Funct = "100101") or		-- Or
								(Opcode = "001101") else						-- Ori
				  -- Nor
					"1100" when	(Opcode = "000000" and Funct = "100111") else	-- Nor
				  -- Xor
					"0100" when	(Opcode = "000000" and Funct = "100110") else	-- Xor
				----------------------------------------------------------------
				  -- Sll
					"0001" when	(Opcode = "000000" and Funct = "000000") else	-- Sll
				  -- Srl
					"1001" when	(Opcode = "000000" and Funct = "000010") else	-- Srl
				  -- Sra
					"1101" when	(Opcode = "000000" and Funct = "000011") else	-- Sra
									------------------------					
				  -- Slt
					"--11" when	(Opcode = "000000" and Funct = "101010") or		-- Slt
								(Opcode = "000000" and Funct = "101011") or		-- Sltu
								(Opcode = "001010") or							-- Slti
								(Opcode = "001011");							-- Sltiu
	
end behavior;