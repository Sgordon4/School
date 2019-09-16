-------------------------------------------------------------------------
-- Sean Gordon
-- CprE 281
-- Section C/10:00 a.m. Friday
-------------------------------------------------------------------------

-- 32-Bit Barrel Shifter
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation for a behavioral
-- 32-Bit Barrel Shifter in alignment with part 1 of Project 1 for CprE 381
--
-- Who decided this needed to be structural. That's ridiculous. What could
-- that possibly teach anyone but to dislike these labs even more.
--
-- NOTES:
-- 2/21/19 by SG::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity BarrelShifter32Bit is 
   port(i_A  	: in std_logic_vector(31 downto 0);
		i_lr	: in std_logic;		-- Left = 0, Right = 1
		i_log	: in std_logic;		-- Logical = 0, Arithmetic = 1
		i_Samt	: in std_logic_vector(4 downto 0);
		o_F  	: out std_logic_vector(31 downto 0));
		
end BarrelShifter32Bit;


architecture structure of BarrelShifter32Bit is 

	component MuxCascade_32
	   port(i_S  : in std_logic;
			i_A  : in std_logic_vector;
			i_B  : in std_logic_vector;
			o_F  : out std_logic_vector);
	end component;
	
	signal i_L1	: std_logic_vector(31 downto 0);
	signal o_L1 : std_logic_vector(31 downto 0);
	signal i_L2	: std_logic_vector(31 downto 0);
	signal o_L2 : std_logic_vector(31 downto 0);
	signal i_L4	: std_logic_vector(31 downto 0);
	signal o_L4 : std_logic_vector(31 downto 0);
	signal i_L8	: std_logic_vector(31 downto 0);
	signal o_L8 : std_logic_vector(31 downto 0);
	signal i_L16: std_logic_vector(31 downto 0);
	signal o_L16: std_logic_vector(31 downto 0);
	
	signal i_A1	: std_logic_vector(31 downto 0);
	signal o_A1 : std_logic_vector(31 downto 0);
	signal i_A2	: std_logic_vector(31 downto 0);
	signal o_A2 : std_logic_vector(31 downto 0);
	signal i_A4	: std_logic_vector(31 downto 0);
	signal o_A4 : std_logic_vector(31 downto 0);
	signal i_A8	: std_logic_vector(31 downto 0);
	signal o_A8 : std_logic_vector(31 downto 0);
	signal i_A16: std_logic_vector(31 downto 0);
	signal o_A16: std_logic_vector(31 downto 0);
	
	signal i_R1	: std_logic_vector(31 downto 0);
	signal o_R1 : std_logic_vector(31 downto 0);
	signal i_R2	: std_logic_vector(31 downto 0);
	signal o_R2 : std_logic_vector(31 downto 0);
	signal i_R4	: std_logic_vector(31 downto 0);
	signal o_R4 : std_logic_vector(31 downto 0);
	signal i_R8	: std_logic_vector(31 downto 0);
	signal o_R8 : std_logic_vector(31 downto 0);
	signal i_R16: std_logic_vector(31 downto 0);
	signal o_R16: std_logic_vector(31 downto 0);
	
	
	begin
	
	-- Left shifting
	L1Wire:	i_L1 <=  i_A(30 downto 0) & '0';
	L1Mux:	MuxCascade_32 port map(i_S => i_Samt(0), i_A => i_A,  i_B => i_L1, o_F => o_L1);
	
	L2Wire:	i_L2 <= o_L1(29 downto 0) & "00";
	L2Mux:	MuxCascade_32 port map(i_S => i_Samt(1), i_A => o_L1, i_B => i_L2, o_F => o_L2);
	
	L4Wire:	i_L4 <= o_L2(27 downto 0) & "0000";
	L4Mux:	MuxCascade_32 port map(i_S => i_Samt(2), i_A => o_L2, i_B => i_L4, o_F => o_L4);
	
	L8Wire:	i_L8 <= o_L4(23 downto 0) & "00000000";
	L8Mux:	MuxCascade_32 port map(i_S => i_Samt(3), i_A => o_L4, i_B => i_L8, o_F => o_L8);
	
	L16Wire:i_L16<= o_L8(15 downto 0) & "0000000000000000";
	L16Mux:	MuxCascade_32 port map(i_S => i_Samt(4), i_A => o_L8, i_B => i_L16,o_F => o_L16);
	
	
	
	-- Arithmetic shifting
	A1Wire:	i_A1 <= i_A(31) & i_A(31 downto 1);
	A1Mux:	MuxCascade_32 port map(i_S => i_Samt(0), i_A => i_A,  i_B => i_A1, o_F => o_A1);
	
	A2Wire:	i_A2 <= i_A(31)&i_A(31) & o_A1(31 downto 2);
	A2Mux:	MuxCascade_32 port map(i_S => i_Samt(1), i_A => o_A1, i_B => i_A2, o_F => o_A2);
	
	A4Wire:	i_A4 <= i_A(31)&i_A(31)&i_A(31)&i_A(31) & o_A2(31 downto 4);
	A4Mux:	MuxCascade_32 port map(i_S => i_Samt(2), i_A => o_A2, i_B => i_A4, o_F => o_A4);
	
	A8Wire:	i_A8 <= i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31) & o_A4(31 downto 8);
	A8Mux:	MuxCascade_32 port map(i_S => i_Samt(3), i_A => o_A4, i_B => i_A8, o_F => o_A8);
	
	A16Wire:i_A16<= i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31)&i_A(31) & o_A8(31 downto 16);
	A16Mux:	MuxCascade_32 port map(i_S => i_Samt(4), i_A => o_A8, i_B => i_A16,o_F => o_A16);
	
	
	
	-- Right shifting
	R1Wire:	i_R1 <= '0' & i_A(31 downto 1);
	R1Mux:	MuxCascade_32 port map(i_S => i_Samt(0), i_A => i_A,  i_B => i_R1, o_F => o_R1);
	
	R2Wire:	i_R2 <= "00" & o_R1(31 downto 2);
	R2Mux:	MuxCascade_32 port map(i_S => i_Samt(1), i_A => o_R1, i_B => i_R2, o_F => o_R2);
	
	R4Wire:	i_R4 <= "0000" & o_R2(31 downto 4);
	R4Mux:	MuxCascade_32 port map(i_S => i_Samt(2), i_A => o_R2, i_B => i_R4, o_F => o_R4);
	
	R8Wire:	i_R8 <= "00000000" & o_R4(31 downto 8);
	R8Mux:	MuxCascade_32 port map(i_S => i_Samt(3), i_A => o_R4, i_B => i_R8, o_F => o_R8);
	
	R16Wire:i_R16<= "0000000000000000" & o_R8(31 downto 16);
	R16Mux:	MuxCascade_32 port map(i_S => i_Samt(4), i_A => o_R8, i_B => i_R16,o_F => o_R16);
	
	
	-- I don't want to spend any more goddamn time on overcomplicated
	-- garbage so I'm just writing a process for output selection
	process(i_lr, i_log, o_L16, o_A16, o_R16)
	begin
		if(i_lr = '0') then			-- We want to shift left logical
			o_F <= o_L16;
		else						-- We choose between logical and arithmetic right shifts
			if(i_log = '0') then	-- Right logical
				o_F <= o_R16;
			else
				o_F <= o_A16;
			end if;
		end if;
	end process;
		
end architecture structure;
	-- https://www.csee.umbc.edu/portal/help/VHDL/samples/bshift.vhdl
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	