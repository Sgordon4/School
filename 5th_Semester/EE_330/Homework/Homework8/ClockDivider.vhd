library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;

entity ClockDivider is 
	port(i_Clk	: in std_logic;
		 i_Rst	: in std_logic;
		 i_Sel	: in std_logic;
		 
		 o_F	: out std_logic);
		
end ClockDivider;


architecture structure of ClockDivider is 

	component DFF_sync
	   port(i_D			: in std_logic;
			i_Clk       : in std_logic;
			i_Rst		: in std_logic;
			o_Q			: out std_logic);
	end component;
	
	component Mux2to1Modded
	   port(i_A         : in std_logic;
			i_B			: in std_logic;
			i_S			: in std_logic;
			o_F         : out std_logic);
	end component;
	
	signal s_D1, s_D2		: std_logic;
	signal s_notD1, s_notD2	: std_logic; 
	
	
begin

	DFF1: DFF_sync
	port map(	i_D		=> s_notD1,
				i_Clk	=> i_Clk,
				i_Rst	=> i_Rst,
				o_Q		=> s_D1);
	
	s_notD1 <= not s_D1;
				
	DFF2: DFF_sync
	port map(	i_D		=> s_notD2,
				i_Clk	=> s_notD1,
				i_Rst	=> i_Rst,
				o_Q		=> s_D2);
				
	s_notD2 <= not s_D2;
			
	mux: Mux2to1Modded
	port map(	i_A		=> s_D1,
				i_B		=> s_D2,
				i_S		=> i_Sel,
				o_F		=> o_F);
	
end structure;
