-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- Mips Register File
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a Mips
-- Register file in alignment with part 1 of lab 3 for CprE 381
--
--
-- NOTES:
-- 2/6/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

package bus_multiplexer_pkg is
        type bus_array is array(31 downto 0) of std_logic_vector(31 downto 0);
end package;

library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.math_real.all;
use work.bus_multiplexer_pkg.all;

entity RegFile is
	generic(N : integer := 32);
   port(iCLK			: in std_logic;			-- Clock input
		iRst			: in std_logic;			-- Reset regs
		RegWrite		: in std_logic;			-- Enables any data writing
		
		ReadAddr1		: in std_logic_vector(4 downto 0);	--Mux1 select
		ReadAddr2		: in std_logic_vector(4 downto 0);	--Mux2 select
		WriteAddr		: in std_logic_vector(4 downto 0);	--Decoder select
		
		WriteData		: in std_logic_vector(31 downto 0);	--Data to be written
		
		ReadData1		: out std_logic_vector(31 downto 0);-- Mux1 output
		ReadData2		: out std_logic_vector(31 downto 0); -- Mux2 output
		Reg2			: out std_logic_vector(31 downto 0) -- Register 2 value, used with syscall for halting programs
		);

end RegFile;

architecture structure of RegFile is

	component NBitReg
	   port(i_CLK       : in std_logic;     		-- Clock input
			i_RST       : in std_logic;    			-- Reset input
			i_WE        : in std_logic;     		-- Write enable input
			i_D         : in std_logic_vector;     	-- Data value input
			o_Q         : out std_logic_vector);   	-- Data value output
	end component;
	
	component NBitDec
	   port(i_E        	: in std_logic;     		-- Enable input
			i_A         : in std_logic_vector;		-- N-Bit input
			o_F         : out std_logic_vector);   	-- N-Bit output
	end component;
	
	component NBitMux
	   port(i_S         : in std_logic_vector;		-- N-Bit log2(N) bit select
			i_A			: in bus_array;				-- N-Bit Input
			o_F         : out std_logic_vector);   	-- N-Bit output
	end component;
	
	component And2Bit
	   port(i_A			: in std_logic;				-- 1 bit input
			i_B			: in std_logic;				-- 1 bit input
			o_F         : out std_logic);   		-- 1 bit output
	end component;
	
	--signal clk_enable	: std_logic;						-- Result of iCLK and RegWrite
	signal decoder_out	: std_logic_vector(31 downto 0);	-- Output of decoder
	signal reg_enable	: std_logic_vector(31 downto 0);	-- Result of iCLK and decoder_out
	signal s_Reg_Mux	: bus_array;						-- Signal carrying regs signal to mux
	
begin

	decoder: NBitDec
	port map(	i_E		=> '1',						-- Just keep the decoder enabled
				i_A		=> WriteAddr,
				o_F		=> decoder_out);
			
	multiplexor1: NBitMux
	port map(	i_S    	=> ReadAddr1,
				i_A		=> s_Reg_Mux,
				o_F		=> ReadData1);
				
	multiplexor2: NBitMux
	port map(	i_S    	=> ReadAddr2,
				i_A		=> s_Reg_Mux,
				o_F		=> ReadData2);
				
	
	-------- Create Registers --------
	
	-- clk_enable <= iCLK and RegWrite;				--
	
	reg0: NBitReg
	   port map(i_CLK  	=> '0',						-- Value will never change
				i_RST	=> '1',						-- Keep reg0 reset
				i_WE	=> '0',						-- Value will never change
				i_D  	=> "00000000000000000000000000000000",
				o_Q		=> s_Reg_Mux(0));
	
	G1: for i in 1 to N-1 generate
	
		-- Combine iCLK and decoder_out to activate registers
	   ands: And2Bit
	   port map(i_A		=> iCLK,
				i_B		=> decoder_out(i),
				o_F		=> reg_enable(i));
	
	   regs: NBitReg
	   port map(i_CLK  	=> reg_enable(i),
				i_RST	=> iRst,
				i_WE	=> RegWrite,
				i_D  	=> WriteData,
				o_Q		=> s_Reg_Mux(i));
				
	end generate;
	
	Reg2 <= s_Reg_Mux(2);		-- Register 2 value, used with syscall for halting programs
	
end structure;
	
	
	
	
	
	
	
	
	
	
	
	
	
	