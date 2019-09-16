library IEEE;
use IEEE.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.numeric_std.all;


entity AddSubSU is
   generic (N : natural := 32);
   port(A, B	: in std_logic_vector(N-1 downto 0);
		i_S		: in std_logic_vector(1 downto 0);
		o_F		: out std_logic_vector(N-1 downto 0);
		ovf		: out std_logic;
		Cout	: out std_logic);
end AddSubSU;

architecture behavior of AddSubSU is

	component Mux2to1
	   port(i_A  : in std_logic_vector;
			i_B  : in std_logic_vector;
			i_S  : in std_logic;
			o_F  : out std_logic_vector);
	end component;

	signal sAdd	: std_logic_vector(N downto 0);
	signal uAdd	: std_logic_vector(N downto 0);
	signal sSub	: std_logic_vector(N downto 0);
	signal uSub	: std_logic_vector(N downto 0);
	
	signal add	: std_logic_vector(N downto 0);
	signal sub	: std_logic_vector(N downto 0);
	signal comb	: std_logic_vector(N downto 0);
	
	signal s_ovf: std_logic;		-- Overflow calculation signal

begin
	
	sAdd <= std_logic_vector(unsigned('0' & A(N-1 downto 0)) + unsigned('0' & B(N-1 downto 0)));
	uAdd <= std_logic_vector(signed('0' & A(N-1 downto 0)) + signed('0' & B(N-1 downto 0)));
	sSub <= std_logic_vector(unsigned('0' & A(N-1 downto 0)) - unsigned('0' & B(N-1 downto 0)));
	uSub <= std_logic_vector(signed('0' & A(N-1 downto 0)) - signed('0' & B(N-1 downto 0)));
	
	
	-- Accidentally switched i_S 0 and 1 when setting control signals
	
	addMux: Mux2to1							-- Add unsigned
	   port map(i_A		=> uAdd,
				i_B		=> sAdd,
				i_S		=> i_S(0),			-- Add signed
				o_F		=> add);
				
	subMux: Mux2to1							-- Sub unsigned
	   port map(i_A		=> uSub,
				i_B		=> sSub,
				i_S		=> i_S(0),			-- Sub signed
				o_F		=> sub);
				
	combMux: Mux2to1
	   port map(i_A		=> add,
				i_B		=> sub,
				i_S		=> i_S(1),
				o_F		=> comb);
	
				  -- Signed
	s_ovf		<= 	(i_S(0) and add(N-1) and comb(N)) when (i_S(1) = '0') else
					(i_S(0) and sub(N-1) and comb(N));
	--s_ovf		<= (i_S(0) & (not i_S(1)) & add(N-1) & comb(N)) or (i_S(0) & i_S(1) & sub(N-1) & comb(N));
	
	o_F 	<= comb(N-1 downto 0);
	ovf		<= s_ovf;
	Cout 	<= comb(N);
	
	-- process(A, B, i_S)
	-- begin
	
		-- if(i_S = "00") then		-- Add signed
			-- s <= '1' & std_logic_vector(signed('0' & A(N-1 downto 0)) + signed('0' & B(N-1 downto 0)));
		-- elsif(i_S = "01") then	-- Add unsigned
			-- s <= '1' & std_logic_vector(unsigned(A(N-1 downto 0)) + unsigned(B(N-1 downto 0)));
		-- elsif(i_S = "10") then	-- Sub signed
			-- s <= '1' & std_logic_vector(signed(A(N-1 downto 0)) - signed(B(N-1 downto 0)));
		-- else					-- Sub unsigned
			-- s <= '1' & std_logic_vector(unsigned(A(N-1 downto 0)) - unsigned(B(N-1 downto 0)));
		-- end if;
		
		--Calculate overflow here
		-- o_F <= s(N-1 downto 0);
		-- Cout <= s(N);
	
	-- end process;
end behavior;