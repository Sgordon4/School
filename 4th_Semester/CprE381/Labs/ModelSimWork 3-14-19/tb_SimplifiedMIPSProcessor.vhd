-------------------------------------------------------------------------
-- Henry Duwe
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- tb_SimplifiedMIPS_Processor.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a testbench for the 381 MIPS Processor
-- that prints an ordered trace of all writes to architectural registers
-- or the data memory.

-- 02/26/2018 by H3::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;  -- For logic types I/O
library std;
use std.env.all;                -- For hierarchical/external signals
use std.textio.all;             -- For basic I/O

--Usually name your testbench similar to below for clarify tb_<name>
entity tb_SimplifiedMIPSProcessor is
--Generic for half of the clock cycle period
  generic(gCLK_HPER   : time := 10 ns;
          N           : integer := 32);  
end tb_SimplifiedMIPSProcessor;

architecture mixed of tb_SimplifiedMIPSProcessor is

--Define the total clock period time
constant cCLK_PER  : time := gCLK_HPER * 2;

--We will be making an instance of the file that we are testing
--TODO: Provide the appropriate port declarations for your processor
component MIPS_processor is
  generic (N : integer);
  port(iCLK            : in std_logic;
       iRST            : in std_logic;
       iInstLd         : in std_logic;
       iInstAddr       : in std_logic_vector(N-1 downto 0);
       iInstExt        : in std_logic_vector(N-1 downto 0);
       oALUOut         : out std_logic_vector(N-1 downto 0));
end component;

-- Create signals for all of the inputs and outputs of the file that you are testing
-- := '0' or := (others => '0') just make all the signals start at an initial value of zero
signal CLK, reset : std_logic := '0';

signal alu_out : std_logic_vector(N-1 downto 0);

begin



-- TODO: Make an instance of the component to test and wire all signals to the corresponding
-- input or output.
  MySimplifiedMIPSProcess: MIPS_processor
  generic map(N      => N)
  port map(
            iCLK      => CLK,
            iRST      => reset,
            iInstLd   => '0',
            iInstAddr => "--------------------------------",
            iInstExt  => "--------------------------------",
            oALUOut   => alu_out);
  --You can also do the above port map in one line using the below format: http://www.ics.uci.edu/~jmoorkan/vhdlref/compinst.html
  --port map(CLK, reset, rs_sel, rt_sel, reg_we, w_addr, reg_dest, immediate, sel_imm, ALU_OP, shamt, mem_we, rs_data, rt_data, ALU_out, dmem_out);
  
  --This first process is to automate the clock for the test bench
  P_CLK: process
  begin
    CLK <= '1';
    wait for gCLK_HPER;
    CLK <= '0';
    wait for gCLK_HPER;
  end process;

    -- This process resets the processor.
  P_RST: process
  begin
  	reset <= '0';
    wait for gCLK_HPER;
	reset <= '1';
    wait for gCLK_HPER;
	reset <= '0';
	wait;
  end process;  
  
  -- Dumps modifications to the state of the processor to trace file
  P_DUMP_STATE: process (CLK) 
    file my_dump : TEXT open WRITE_MODE is "dump.out";
    variable my_line : LINE;
    variable cycle_cnt : integer := 0;

    -- Setup hierarchical/external names
    -- Reference for external names: https://www.doulos.com/knowhow/vhdl_designers_guide/vhdl_2008/vhdl_200x_ease/#hierarchicalnames
    alias memWr is <<signal MySimplifiedMIPSProcess.s_DMemWr : std_logic>>;
    alias memAddr is <<signal MySimplifiedMIPSProcess.s_DMemAddr : std_logic_vector(N-1 downto 0)>>;
    alias memData is <<signal MySimplifiedMIPSProcess.s_DMemData : std_logic_vector(N-1 downto 0)>>;

    alias regWr is <<signal MySimplifiedMIPSProcess.s_RegWr : std_logic>>;
    alias regWrAddr is <<signal MySimplifiedMIPSProcess.s_RegWrAddr : std_logic_vector(4 downto 0)>>;
    alias regWrData is <<signal MySimplifiedMIPSProcess.s_RegWrData : std_logic_vector(N-1 downto 0)>>;

    alias halt is <<signal MySimplifiedMIPSProcess.s_Halt : std_logic>>;
	
	alias instrAddr is <<signal MySimplifiedMIPSProcess.pcSys.s_PCOut : std_logic_vector(31 downto 0)>>;
	--alias instrAddr is <<signal MySimplifiedMIPSProcess.s_NextInstAddr : std_logic_vector(31 downto 0)>>;
	alias instr is <<signal MySimplifiedMIPSProcess.s_Inst : std_logic_vector(31 downto 0)>>;
	
	
	alias ra1 is <<signal MySimplifiedMIPSProcess.temp1 : std_logic_vector(4 downto 0)>>;
	alias ra2 is <<signal MySimplifiedMIPSProcess.temp2 : std_logic_vector(4 downto 0)>>;
	alias wa is <<signal MySimplifiedMIPSProcess.s_RegWrAddr : std_logic_vector(4 downto 0)>>;
	alias rd1 is <<signal MySimplifiedMIPSProcess.s_ReadData1 : std_logic_vector(31 downto 0)>>;
	alias rd2 is <<signal MySimplifiedMIPSProcess.s_ReadData2 : std_logic_vector(31 downto 0)>>;


  begin

      if (rising_edge(CLK)) then

		if (regWr) then
		  -- write(my_line, LF);
		  -- write(my_line, string'("Instruction address: "));
          -- hwrite(my_line, instrAddr);
		  write(my_line, LF);
		  write(my_line, string'("Instruction: "));
          hwrite(my_line, instr);
		  write(my_line, LF);
		  
		  write(my_line, string'("RA1: "));
          hwrite(my_line, ra1);
		  write(my_line, LF);
		  write(my_line, string'("RA2: "));
          hwrite(my_line, ra2);
		  write(my_line, LF);
		  write(my_line, string'("WA: "));
          hwrite(my_line, wa);
		  write(my_line, LF);
		  write(my_line, string'("RD1: "));
          hwrite(my_line, rd1);
		  write(my_line, LF);
		  write(my_line, string'("RD2: "));
          hwrite(my_line, rd2);
		  write(my_line, LF);
		  write(my_line, string'("RegWr: "));
          write(my_line, regWr);
		  write(my_line, LF);
		
          write(my_line, string'("In clock cycle: "));
          write(my_line, cycle_cnt);
          writeline(my_dump, my_line);
          write(my_line, string'("Register Write to Reg: 0x"));
          hwrite(my_line, regWrAddr);
          write(my_line, string'(" Val: 0x"));
          hwrite(my_line, regWrData);
		  writeline(my_dump, my_line);
        end if;
        if (memWr) then
          write(my_line, string'("In clock cycle: "));
          write(my_line, cycle_cnt);
          writeline(my_dump, my_line);
          write(my_line, string'("Memory Write to Addr: 0x"));
          hwrite(my_line, memAddr);
          write(my_line, string'(" Val: 0x"));
          hwrite(my_line, memData);
          writeline(my_dump, my_line);
		end if;
        cycle_cnt := cycle_cnt + 1;

    if (halt = '1') then
			write(my_line, string'("Execution is stopping!"));
			writeline(my_dump, my_line);
			stop(2);
		end if;
      end if;
  end process;

end mixed;




-- if (regWr) then
		
          -- write(my_line, string'("In clock cycle: "));
          -- write(my_line, cycle_cnt);
		  
		  -- write(my_line, LF);
		  -- write(my_line, string'(" Instruction address: "));
		  -- write(my_line, instrAddr);
		  
		  -- write(my_line, LF);
		  -- write(my_line, string'(" Instruction: "));
		  -- write(my_line, instrAddr);
		  
		  
          -- writeline(my_dump, my_line);
          -- write(my_line, string'("Register Write to Reg: 0x"));
          -- hwrite(my_line, regWrAddr);
          -- write(my_line, string'(" Val: 0x"));
          -- hwrite(my_line, regWrData);
          -- writeline(my_dump, my_line);
        -- end if;

