-------------------------------------------------------------------------
-- Henry Duwe
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- MIPS_Processor.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a skeleton of a MIPS_Processor  
-- implementation.

-- 01/29/2019 by H3::Design created.
-------------------------------------------------------------------------


library IEEE;
use IEEE.std_logic_1164.all;

entity MIPS_Processor is
  generic(N : integer := 32);
  port(iCLK            : in std_logic;
       iRST            : in std_logic;
	   
       iInstLd         : in std_logic;
       iInstAddr       : in std_logic_vector(N-1 downto 0);
       iInstExt        : in std_logic_vector(N-1 downto 0);
	   
       oALUOut         : out std_logic_vector(N-1 downto 0)); -- TODO: Hook this up to the output of the ALU. It is important for synthesis that you have this output that can effectively be impacted by all other components so they are not optimized away.

end  MIPS_Processor;


architecture structure of MIPS_Processor is

  -- Required data memory signals
  signal s_DMemWr       : std_logic; -- TODO: use this signal as the final active high data memory write enable signal
  signal s_DMemAddr     : std_logic_vector(N-1 downto 0); -- TODO: use this signal as the final data memory address input
  signal s_DMemData     : std_logic_vector(N-1 downto 0); -- TODO: use this signal as the final data memory data input
  signal s_DMemOut      : std_logic_vector(N-1 downto 0); -- TODO: use this signal as the data memory output
 
  -- Required register file signals 
  signal s_RegWr        : std_logic; -- TODO: use this signal as the final active high write enable input to the register file
  signal s_RegWrAddr    : std_logic_vector(4 downto 0); -- TODO: use this signal as the final destination register address input
  signal s_RegWrData    : std_logic_vector(N-1 downto 0); -- TODO: use this signal as the final data memory data input

  -- Required instruction memory signals
  signal s_IMemAddr     : std_logic_vector(N-1 downto 0); -- Do not assign this signal, assign to s_NextInstAddr instead
  signal s_NextInstAddr : std_logic_vector(N-1 downto 0); -- TODO: use this signal as your intended final instruction memory address input.
  signal s_Inst         : std_logic_vector(N-1 downto 0); -- TODO: use this signal as the instruction signal 

  -- Required halt signal -- for simulation
  signal v0             : std_logic_vector(N-1 downto 0); -- TODO: should be assigned to the output of register 2, used to implement the halt SYSCALL
  signal s_Halt         : std_logic;  -- TODO: this signal indicates to the simulation that intended program execution has completed. This case happens when the syscall instruction is observed and the V0 register is at 0x0000000A. This signal is active high and should only be asserted after the last register and memory writes before the syscall are guaranteed to be completed.

  component mem is
    generic(ADDR_WIDTH : integer;
            DATA_WIDTH : integer);
    port(
          clk          : in std_logic;
          addr         : in std_logic_vector((ADDR_WIDTH-1) downto 0);
          data         : in std_logic_vector((DATA_WIDTH-1) downto 0);
          we           : in std_logic := '1';
          q            : out std_logic_vector((DATA_WIDTH -1) downto 0));
    end component;

  -- TODO: You may add any additional signals or components your implementation 
  --       requires below this comment
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	----------- PC unit of Processor ------------
	
	
	component PC
	   port(Clk				: in std_logic;
			Rst				: in std_logic;
			Instr			: in std_logic_vector;	-- Bits [25-0] of machine instruction
			SExt			: in std_logic_vector;	-- Output from the 16->32 sign extender underneath the regfile in diagram
			
			Jump			: in std_logic;			-- Control signal
			Branch			: in std_logic;			-- Control signal
			Zero			: in std_logic;			-- Output from ALU
		   
			PCOut			: out std_logic_vector);-- Output next instruction memory address
	end component;
	
	
	---------------------------------------------
	
	
	--------- Control unit of Processor ---------
	
	
	component Control
	   port(Opcode		: in std_logic_vector;		-- Bits [31-26] of machine instruction
			Funct		: in std_logic_vector;		-- Bits [5-0] of machine instruction
			
			--ALUOp		: out std_logic_vector;		-- ALUCtrl is made in-house here, no need for ALUOp
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
	
	
	signal s_Opcode		: std_logic_vector(5 downto 0);	-- Bits [31-26] of machine instruction
	signal s_Funct		: std_logic_vector(5 downto 0);	-- Bits [5-0] of machine instruction
	
	signal s_Jump		: std_logic;		-- Currently unused
	signal s_Branch		: std_logic;		-- Currently unused
	signal s_MemWrite	: std_logic;
	signal s_MemRead	: std_logic;
	signal s_RegWrite	: std_logic;
	signal s_MemtoReg	: std_logic;
	signal s_ALUSrc		: std_logic;
	signal s_RegDst		: std_logic;
	signal s_ALUCtrl	: std_logic_vector(3 downto 0);
	
	
	---------------------------------------------
	
	
	
	component Mux2to1
	   port(i_A  : in std_logic_vector;
			i_B  : in std_logic_vector;
			i_S  : in std_logic;
			o_F  : out std_logic_vector);
	end component;
	
	
	
	---------- Center of the Processor ----------
	
	
	component RegFile
	   port(iCLK		: in std_logic;
			iRst		: in std_logic;			-- Reset all regs
			RegWrite	: in std_logic;			-- Reg File enable
			
			ReadAddr1	: in std_logic_vector;	-- Mux1 select
			ReadAddr2	: in std_logic_vector;	-- Mux2 select
			WriteAddr	: in std_logic_vector;
			
			WriteData	: in std_logic_vector;	-- Data to be written
		
			ReadData1	: out std_logic_vector;	-- Mux1 output
			ReadData2	: out std_logic_vector;	-- Mux2 output
			Reg2		: out std_logic_vector);-- Register 2 value, used for halting programs
	end component;
	
	
	component SignExtend
	   port(A			: in std_logic_vector;
			S			: in std_logic;
			F			: out std_logic_vector);
	end component;
	
	
	component ALU
	   port(i_A			: in std_logic_vector;
			i_B			: in std_logic_vector;
			i_Samt		: in std_logic_vector;
			ALUCtrl		: in std_logic_vector;
			
			ovf			: out std_logic;
			zero		: out std_logic;
			Cout		: out std_logic;
			o_F			: out std_logic_vector);
	end component;
	
	
	--signal s_mux_writeAddr	: std_logic_vector(4 downto 0);		-- Mux before WriteAddr (Uses RegDst) (Replaced by given s_RegWrAddr)
	signal s_signExtended	: std_logic_vector(31 downto 0);	-- Result of sign extend
	signal s_ReadData1		: std_logic_vector(31 downto 0);	-- Output of RegFile ReadData1
	signal s_ReadData2		: std_logic_vector(31 downto 0);	-- Output of RegFile ReadData2
	signal s_ALU_B			: std_logic_vector(31 downto 0);	-- Mux before ALU (input B, uses ALUSrc)
	
	signal s_ALU_Ovf		: std_logic;						-- Result of ALU overflow, just putting it in limbo
	signal s_ALU_Cout		: std_logic;						-- Result of ALU Cout, just putting it in limbo
	signal s_ALU_Zero		: std_logic;						-- Is ALU result 0?
	signal s_ALU_Out		: std_logic_vector(31 downto 0);	-- Result of ALU
	

	---------------------------------------------
	
	
	signal s_ALUOut		: std_logic_vector(N-1 downto 0);
	signal s_WriteData	: std_logic_vector(N-1 downto 0);
	
	signal s_Read2Out	: std_logic_vector(N-1 downto 0);
	
	
	
	
	signal temp1 		: std_logic_vector(4 downto 0);
	signal temp2 		: std_logic_vector(4 downto 0);
	
	
	
	
	--For testing purposes
	signal intoTheVoid			: std_logic_vector(N-1 downto 0);
	
	
	
	
	
	
	
	
	
	
	
	
  
begin


	--For testing purposes
	s_Inst	<= iInstExt;



  -- TODO: This is required to be your final input to your instruction memory. This provides a feasible method to externally load the memory module which means that the synthesis tool must assume it knows nothing about the values stored in the instruction memory. If this is not included, much, if not all of the design is optimized out because the synthesis tool will believe the memory to be all zeros.
  with iInstLd select
    s_IMemAddr <= s_NextInstAddr when '0', 	--"00000000000000000000000000000010" when '0', --
      iInstAddr when others;


  IMem: mem
    generic map(ADDR_WIDTH => 10,
                DATA_WIDTH => N)
    port map(clk  => iCLK,
             addr => s_IMemAddr(11 downto 2),
             data => iInstExt,
             we   => iInstLd,
             q    => intoTheVoid);				-- Output
  
  DMem: mem
    generic map(ADDR_WIDTH => 10,
                DATA_WIDTH => N)
    port map(clk  => iCLK,
             addr => s_DMemAddr(11 downto 2),
             data => s_DMemData,
             we   => s_DMemWr,
             q    => s_DMemOut);			-- Output

			 
	s_Halt <='1' when (s_Inst(31 downto 26) = "000000") and (s_Inst(5 downto 0) = "001100") and (v0 = "00000000000000000000000000001010") else '0';
  
  -- TODO: For vo, small hack recommended by ta is to make another "R3" output from Reg File that permanently points to reg2 (0,1,'2') and propogate it up

  -- TODO: Implement the rest of your processor below this comment!
	
	
	
	
	
	
	
	
	
	
	----------- PC unit of Processor ------------
	
	
	pcSys: PC
	   port map(Clk			=> iCLK,
				Rst			=> iRST,
				Instr		=> s_Inst(25 downto 0),
				SExt		=> s_signExtended,
				
				Jump		=> s_Jump,
				Branch		=> s_Branch,
				Zero		=> s_ALU_Zero,
				
				PCOut		=> s_NextInstAddr);
	
	
	---------------------------------------------
	
	
	s_Opcode	<= s_Inst(31 downto 26);
	s_Funct		<= s_Inst(5 downto 0);
	
	
	--------- Control unit of Processor ---------
	
	
	ctrl: Control
	   port map(Opcode		=> s_Opcode,
				Funct		=> s_Funct,
				
				Jump		=> s_Jump,
				Branch		=> s_Branch,
				MemWrite	=> s_MemWrite,
				MemRead		=> s_MemRead,
				RegWrite	=> s_RegWrite,
				MemtoReg	=> s_MemtoReg,
				ALUSrc		=> s_ALUSrc,
				RegDst		=> s_RegDst,
				ALUCtrl		=> s_ALUCtrl);
	
	
	s_RegWr 	<= s_RegWrite;						-- s_RegWrite is from Control
	
	
	---------------------------------------------
	
	
	s_DMemWr	<= s_MemWrite;
	s_DMemAddr	<= s_ALU_Out;
	s_DMemData	<= s_ReadData2;
	
	
	s_RegWrData	<= s_WriteData;
	
	
	---------- Center of the Processor ----------
  
  
	mux1: Mux2to1
	   port map(i_A		=> s_Inst(20 downto 16),
				i_B		=> s_Inst(15 downto 11),
				i_S		=> s_RegDst,
				o_F		=> s_RegWrAddr);			--s_mux_writeAddr);
				
				
	temp1 <= s_Inst(25 downto 21);
	temp2 <= s_Inst(20 downto 16);
	
	regs: RegFile
	   port map(iCLK		=> iCLK,
				iRst		=> iRST,
				RegWrite	=> s_RegWr,				-- RegWr,
				
				ReadAddr1	=> temp1,
				ReadAddr2	=> temp2,
				WriteAddr	=> s_RegWrAddr,			--s_mux_writeAddr,
				
				WriteData	=> s_RegWrData,			-- WriteData,
				
				ReadData1	=> s_ReadData1,
				ReadData2	=> s_ReadData2,
				Reg2		=> v0);
				
	SEXT: SignExtend
	   port map(A		=> s_Inst(15 downto 0),
				S		=> '1',		-- I actually don't know where this signal should come from, maybe aluop(2)
				F		=> s_signExtended);
	
	mux2: Mux2to1
	   port map(i_A		=> s_ReadData2,
				i_B		=> s_signExtended,
				i_S		=> s_ALUSrc,
				o_F		=> s_ALU_B);
	
	-- ALUControl no longer exists, implemented into main Control unit --
	
	aluBody: ALU
	   port map(i_A		=> s_ReadData1,
				i_B		=> s_ALU_B,
				i_Samt	=> s_Inst(10 downto 6),
				ALUCtrl => s_ALUCtrl,				-- Input directly from control unit
				
				ovf		=> s_ALU_Ovf,
				zero	=> s_ALU_Zero,
				Cout	=> s_ALU_Cout,
				o_F		=> s_ALU_Out);
  
  
	---------------------------------------------
	
	
	oALUOut <= s_ALU_Out;
	
	
	
	dmemMux: Mux2to1
	   port map(i_A		=> s_ALU_Out,
				i_B		=> s_DMemOut,
				i_S		=> s_MemToReg,
				o_F		=> s_WriteData);
  

end structure;
