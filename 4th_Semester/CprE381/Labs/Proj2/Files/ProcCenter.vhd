-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Mips Processor, Center and Control
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains the center portion of the MIPS 
-- processor, beginning at the input of machine code instruction and 
-- ending at the output of the ALU.
--
-- Test bench for center and control together
--
-- NOTES:
-- 2/25/19 by SG::Design created.
-------------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.all;

entity ProcCenter is
   port(clk			: in std_logic;
		Instr		: in std_logic_vector(31 downto 0);		-- Incoming MIPS machine code
		iRst		: in std_logic;							-- Used to reset registers
		iWriteData	: in std_logic_vector(31 downto 0);		-- Incoming from data mem

		Read2Out	: out std_logic_vector(31 downto 0);
		oJump		: out std_logic;
		oBranch		: out std_logic;
		oMemWrite	: out std_logic;
		oMemRead	: out std_logic;
		oMemtoReg	: out std_logic;
		ALUOut		: out std_logic_vector(31 downto 0));
		
end ProcCenter;

architecture structure of ProcCenter is
	
	component Control
	   port(Opcode		: in std_logic_vector;		-- Bits [31-26] of machine instruction
			Funct		: in std_logic_vector;		-- Bits [5-0] of machine instruction
	   
			--ALUOp		: out std_logic_vector;		-- Integrated into control, outputs ALUCtrl instead
			Jump		: out std_logic;
			Branch		: out std_logic;
			MemWrite	: out std_logic;
			MemRead		: out std_logic;
			RegWrite	: out std_logic;
			MemtoReg	: out std_logic;
			ALUSrc		: out std_logic;
			RegDst		: out std_logic;
			ALUCtrl		: out std_logic_vector);	-- Control signal for ALU
			
	end component;
	
	component Center
	   port(clk			: in std_logic;
			iRst		: in std_logic;				-- Used to reset regs
			instr		: in std_logic_vector;		-- Incoming MIPS machine code
			WriteData	: in std_logic_vector;		-- Incoming data to write to reg file
	   
			----- Control Signals -----
			RegDst		: in std_logic;
			RegWr		: in std_logic;
			ALUSrc		: in std_logic;
			ALUCtrl		: in std_logic_vector;
			
			----- Output Signals -----
			--Read1Out	: out std_logic_vector;
			Read2Out	: out std_logic_vector;
			ALUOut		: out std_logic_vector;
			ALUZero		: out std_logic;
			SExtOut		: out std_logic_vector);
	end component;
	
	
	signal ALUCtrl		: std_logic_vector(3 downto 0);
	signal s_RegWrite	: std_logic;
	signal s_ALUSrc		: std_logic;
	signal s_RegDst		: std_logic;
	
begin
	
	ctrl: Control
	   port map(Opcode		=> Instr(31 downto 26),
				Funct		=> Instr(5 downto 0),
				
				Jump		=> oJump,
				Branch		=> oBranch,
				MemWrite	=> oMemWrite,
				MemRead		=> oMemRead,
				RegWrite	=> s_RegWrite,
				MemtoReg	=> oMemtoReg,
				ALUSrc		=> s_ALUSrc,
				RegDst		=> s_RegDst,
				ALUCtrl		=> ALUCtrl);
				
	cent: Center
	   port map(clk			=> clk,
				instr		=> Instr,			-- All 32 bits
				iRst		=> iRst,
				WriteData	=> iWriteData,
				
				----- Control Signals -----
				RegDst		=> s_RegDst,
				RegWr		=> s_RegWrite,
				ALUSrc		=> s_ALUSrc,
				--ALUOp		=> ALUCtrl,
				ALUCtrl		=> ALUCtrl,
				
				----- Output Signals -----
				--Read1Out	=> Read1Out,
				Read2Out	=> Read2Out,
				ALUOut		=> ALUOut,
				ALUZero		=> ALUZero,
				SExtOut		=> SExtOut);
	
end structure;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	