-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Mips Processor, Center and Control
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains the center portion of the MIPS 
-- processor, beginning at the input of machine code instruction and 
-- WriteData, and ending at the output of the ALU, ReadData2, and the
-- Sign Extension for the PC register.
--
-- Test bench for center and control together
--
-- NOTES:
-- 2/25/19 by SG::Design created.
-------------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.all;

entity CentControl is
   port(clk			: in std_logic;
		Instr		: in std_logic_vector(31 downto 0);		-- Incoming MIPS machine code
		WriteData	: in std_logic_vector(31 downto 0);		-- Incoming data to write to reg file
		
		initializing: in std_logic;							-- Used to initialize regs, set to 0 after
		WriteAddr	: in std_logic_vector(4 downto 0);
		
		----- Outgoing Control Signals -----
		-- Any commented ports will stay as internal signals, not outgoing
		-- Left for ease of signal tracking
		
		--ALUOp		: out std_logic_vector(1 downto 0);
		Branch		: out std_logic;
		MemWrite	: out std_logic;
		MemRead		: out std_logic;
		--RegWrite	: out std_logic;
		MemtoReg	: out std_logic;
		--ALUSrc		: out std_logic;
		--RegDst		: out std_logic);
		
		----- ALU Outgoing Signals -----
		Read1Out	: out std_logic_vector(31 downto 0);
		Read2Out	: out std_logic_vector(31 downto 0);
		ALUOut		: out std_logic_vector(31 downto 0);
		ALUZero		: out std_logic;
		SExtOut		: out std_logic_vector(31 downto 0));
		
end CentControl;

architecture structure of CentControl is
	
	component Control
	   port(Instr	: in std_logic_vector;	-- Bits [31-26] of machine instruction
	   
			ALUOp		: out std_logic_vector;
			Branch		: out std_logic;
			MemWrite	: out std_logic;
			MemRead		: out std_logic;
			RegWrite	: out std_logic;
			MemtoReg	: out std_logic;
			ALUSrc		: out std_logic;
			RegDst		: out std_logic);
	end component;
	
	component Center
	   port(clk			: in std_logic;
			instr		: in std_logic_vector;		-- Incoming MIPS machine code
			WriteData	: in std_logic_vector;		-- Incoming data to write to reg file
	   
			initializing: in std_logic;				-- Used to initialize regs, set to 0 after
			WriteAddr	: in std_logic_vector;
	   
			----- Control Signals -----
			RegDst		: in std_logic;
			RegWr		: in std_logic;
			ALUSrc		: in std_logic;
			ALUOp		: in std_logic_vector;
			
			----- Output Signals -----
			Read1Out	: out std_logic_vector;
			Read2Out	: out std_logic_vector;
			ALUOut		: out std_logic_vector;
			ALUZero		: out std_logic;
			SExtOut		: out std_logic_vector);
	end component;
	
	
	signal s_ALUOp		: std_logic_vector(1 downto 0);
	signal s_RegWrite	: std_logic;
	signal s_ALUSrc		: std_logic;
	signal s_RegDst		: std_logic;
	
	signal s_ALUOut		: std_logic_vector(31 downto 0);	-- Used to drive writeData because Data memory isn't made yet
	signal s_WriteData	: std_logic_vector(31 downto 0);	-- Used to drive writeData because Data memory isn't made yet
	--alias buried_signal : std_logic_vector is << signal .centcontrol.cent.s_ReadData1 : std_logic_vector >>;
	
begin
	
	ctrl: Control
	   port map(Instr		=> Instr(31 downto 26),
				
				ALUOp		=> s_ALUOp,
				Branch		=> Branch,
				MemWrite	=> MemWrite,
				MemRead		=> MemRead,
				RegWrite	=> s_RegWrite,
				MemtoReg	=> MemtoReg,
				ALUSrc		=> s_ALUSrc,
				RegDst		=> s_RegDst);
				
	cent: Center
	   port map(clk			=> clk,
				instr		=> Instr,			-- All 32 bits
				WriteData	=> s_WriteData,--WriteData,
				
				initializing=> initializing,	-- Used to initialize regs, set to 0 after
				WriteAddr	=> WriteAddr,
		   
				----- Control Signals -----
				RegDst		=> s_RegDst,
				RegWr		=> s_RegWrite,
				ALUSrc		=> s_ALUSrc,
				ALUOp		=> s_ALUOp,
				
				----- Output Signals -----
				Read1Out	=> Read1Out,
				Read2Out	=> Read2Out,
				ALUOut		=> s_ALUOut,
				ALUZero		=> ALUZero,
				SExtOut		=> SExtOut);
	
	
	-- Done because Data memory isn't implemented yet, will change when full processor is built
	ALUOut <= s_ALUOut;
	s_WriteData <= s_ALUOut;
	
	
end structure;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	