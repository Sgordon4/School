-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Full ALU
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a full ALU,
-- compatible with MIPS instructions
--
--
-- NOTES:
-- 2/25/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity ALU is
   port(i_A		: in std_logic_vector(31 downto 0);
		i_B		: in std_logic_vector(31 downto 0);
		i_Samt	: in std_logic_vector(10 downto 6);		-- Shifter shift amount, takes Instr[10-6]
		ALUCtrl	: in std_logic_vector(3 downto 0);		-- Selects ALU operation
		
		ovf		: out std_logic;						-- Overflow
		zero	: out std_logic;						-- Is output 0?
		Cout	: out std_logic;
		o_F		: out std_logic_vector(31 downto 0));

end ALU;

architecture structure of ALU is 

	component LogicUnit
	   port(i_A			: in std_logic_vector;
			i_B			: in std_logic_vector;
			i_S			: in std_logic_vector;
			o_F			: out std_logic_vector);
	end component;

	component AddSubSU
	   port(A			: in std_logic_vector;
			B			: in std_logic_vector;
			i_S			: in std_logic_vector;
			o_F			: out std_logic_vector;
			ovf			: out std_logic;
			Cout		: out std_logic);
	end component;
	
	component BarrelShifter32Bit
	   port(i_A  	: in std_logic_vector;
			i_lr	: in std_logic;		-- Left = 0, Right = 1
			i_log	: in std_logic;		-- Logical = 0, Arithmetic = 1
			i_Samt	: in std_logic_vector;
			o_F  	: out std_logic_vector);
	end component;
	
	component SLT
	   port(i_A		: in std_logic_vector;
			i_B		: in std_logic_vector;
			o_F		: out std_logic_vector);
	end component;
	
	component Mux2to1
	   port(i_A  : in std_logic_vector;
			i_B  : in std_logic_vector;
			i_S  : in std_logic;
			o_F  : out std_logic_vector);
	end component;
	
	
	signal s_AddSub		: std_logic_vector(31 downto 0);
	signal s_Logic		: std_logic_vector(31 downto 0);
	signal s_Shift		: std_logic_vector(31 downto 0);
	signal s_SLT		: std_logic_vector(31 downto 0);
	
	signal s_mux1		: std_logic_vector(31 downto 0);
	signal s_mux2		: std_logic_vector(31 downto 0);
	
	signal s_out		: std_logic_vector(31 downto 0);
	
begin
				
	logic: LogicUnit
	   port map(i_A		=> i_A,
				i_B		=> i_B,
				i_S		=> ALUCtrl(3 downto 2),
				o_F		=> s_Logic);
	
	addnsub: AddSubSU
	   port map(A		=> i_A,
				B		=> i_B,
				i_S		=> ALUCtrl(3 downto 2),
				o_F		=> s_AddSub,
				ovf		=> ovf,
				Cout	=> Cout);
	
	shifter: BarrelShifter32Bit
	   port map(i_A		=> i_B,				-- May need to be i_B, not sure what the specs are
				i_lr	=> ALUCtrl(3),
				i_log	=> ALUCtrl(2),
				i_Samt	=> i_Samt,
				o_F		=> s_Shift);
				
	setLT: SLT
	   port map(i_A		=> i_A,
				i_B		=> i_B,
				o_F		=> s_SLT);
				
				
				
				
	
	-- Choose between Logic and Add/Sub
	mux1: Mux2to1
	   port map(i_A 	=> s_Logic,
				i_B		=> s_AddSub,
				i_S		=> ALUCtrl(1),
				o_F		=> s_mux1);
			
	-- Choose between Shifter and slt
	mux2: Mux2to1
	   port map(i_A 	=> s_Shift,
				i_B		=> s_SLT,
				i_S		=> ALUCtrl(1),
				o_F		=> s_mux2);
			
	-- Choose between mux1 and mux2
	mux3: Mux2to1
	   port map(i_A 	=> s_mux1,
				i_B		=> s_mux2,
				i_S		=> ALUCtrl(0),
				o_F		=> s_out);
				
	Zero 	<= '1' when (s_out = "00000000000000000000000000000000") else '0';
	-- Zero <= '1' when (signed(o_F) = 0) else '0';		-- Could use this instead idk
	
	o_F 	<=  s_out;
	
end structure;
				
				
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		