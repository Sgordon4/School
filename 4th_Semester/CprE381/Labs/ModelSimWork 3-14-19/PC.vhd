library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity PC is
   generic(N : integer := 32);
   port(Clk				: in std_logic;
		Rst				: in std_logic;
		Instr			: in std_logic_vector(25 downto 0);
		SExt			: in std_logic_vector(31 downto 0);
		
		Jump			: in std_logic;
		Branch			: in std_logic;
		Zero			: in std_logic;
       
		PCOut			: out std_logic_vector(31 downto 0));
end  PC;

architecture dataflow of PC is 

	component NBitReg
	   port(i_CLK       : in std_logic;     		-- Clock input
			i_RST       : in std_logic;    			-- Reset input
			i_WE        : in std_logic;     		-- Write enable input
			i_D         : in std_logic_vector;     	-- Data value input
			o_Q         : out std_logic_vector);   	-- Data value output
	end component;
	
	
	signal s_PCOut		: std_logic_vector(31 downto 0);
	
	signal s_add1Out	: std_logic_vector(31 downto 0);
	signal s_SL2_1		: std_logic_vector(27 downto 0);
	signal s_mux2In1	: std_logic_vector(31 downto 0);
	
	signal s_SL2_2		: std_logic_vector(31 downto 0);
	signal s_add2Out	: std_logic_vector(31 downto 0);
	
	signal s_andOut		: std_logic;
	
	signal s_mux1Out	: std_logic_vector(31 downto 0);
	signal s_mux2Out	: std_logic_vector(31 downto 0);
	
begin
	
	pcReg: NBitReg
	   port map(i_CLK  	=> Clk,
				i_RST	=> Rst,
				i_WE	=> '1',
				i_D  	=> s_mux2Out,
				o_Q		=> s_PCOut);
	
	
	s_add1Out 	<= std_logic_vector(unsigned(s_PCOut) + 4);
	s_SL2_1		<= Instr(25 downto 0) & "00";
	s_mux2In1	<= s_add1Out(31 downto 28) & s_SL2_1;
	
	s_SL2_2		<= SExt(29 downto 0) & "00";
	s_add2Out	<= std_logic_vector(unsigned(s_add1Out) + unsigned(s_SL2_2));
	
	s_andOut	<= Branch and Zero;
	
	s_mux1Out	<=  s_add1Out when s_andOut = '0' else
					s_add2Out when s_andOut = '1';
					
	s_mux2Out	<=  s_mux1Out when Jump	= '0' else 
					s_mux2In1 when Jump = '1';
					
	PCOut <= s_PCOut;
	
end dataflow;