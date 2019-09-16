# dump_compare.py compares the output of the Test Framework provided Testbench and MARS. 
# Usase is as follows (works in both python 2 and 3, but tested with python 2.7):
# 	python dump_compare.py <testbench_file> <mars_file> <max_mismatches>
# Example Usage:
# 	python dump_compare.py dump.out student_MARSdump.out 10
#
# NOTE: max_mismatches need not be provided, it will default to 2.

import sys
import re

mars_firstline_regex = r'[0-9]*\[inst #(?P<num>[0-9]+)\] (?P<instr>[0-9$a-z ,\-\(\)]+)' # statrts with [0-9]* to ignore known mars issue
student_firstline_regex = r'In clock cycle: (?P<cycle>[0-9]+)'
register_write_regex = r'Register Write to Reg: (?P<reg>[0-9A-Fa-fxX]+) Val: (?P<val>[0-9A-Fa-fxX]+)'
memory_write_regex = r'Memory Write to Addr: (?P<addr>[0-9A-Fa-fxX]+) Val: (?P<val>[0-9A-Fa-fxX]+)'

def main():
	max_mismatches = 2
	
	if not 3 <= len(sys.argv) <= 4:
		print('Improper usage, expecting python dump_compare.py <testbench_file> <mars_file> <max_mismatches>')
		print('Note: max_mismatches will default to 2 if not set')
		return 1

	student_file_path = sys.argv[1]
	mars_file_path = sys.argv[2]
	if len(sys.argv) == 4:
		try:
			max_mismatches = int(sys.argv[3])
		except (ValueError, TypeError):
			print('Invalid Argument in position 4, only numbers are accepted')
			return 1
		
	print('Maximum Number of Mismatches Accepted: ' + str(max_mismatches))
	print('')

	compare(student_file_path,mars_file_path,max_mismatches=max_mismatches)


def compare(student_file_path, mars_file_path, max_mismatches=2):
	mars_syscall = False
	cur_mismatches = 0

	student_file = open(student_file_path, 'r')
	mars_file = open(mars_file_path, 'r')
	
	while cur_mismatches < max_mismatches:
		#get mars file first line
		mars_firstline = mars_file.readline().strip()

		#search mars first line
		if mars_firstline:
			mars_firstline_search = re.search(mars_firstline_regex, mars_firstline)

		#skip all j, bne, syscall and beq instructions
		#print(mars_firstline)
		while mars_firstline and ('syscall' in mars_firstline_search.group('instr') or 'jr ' in mars_firstline_search.group('instr') or 'j ' in mars_firstline_search.group('instr') or 'beq ' in mars_firstline_search.group('instr') or 'bne ' in mars_firstline_search.group('instr')):
			if mars_firstline and ('syscall' in mars_firstline_search.group('instr')):
				mars_syscall = True
			mars_firstline = mars_file.readline().strip()

			# a syscall may have printed characters, so we will ignore any non dump lines
			if not re.search(mars_firstline_regex,mars_firstline):
				continue

			if mars_firstline:
				mars_firstline_search = re.search(mars_firstline_regex, mars_firstline)
			#print(mars_firstline)
		#get mars file second line
		if mars_firstline:
			mars_secondline = mars_file.readline().strip()
		
		#check whether the memory or the register file was written
		if mars_firstline and 'Register' in mars_secondline:
			mars_secondline_search = re.search(register_write_regex, mars_secondline)
			mars_reg_write = True
		elif mars_firstline:
			#temp_list = list(mars_secondline)
			#temp_list[28] = '0'
			#mars_secondline = "".join(temp_list);
			mars_secondline_search = re.search(memory_write_regex, mars_secondline)
			mars_reg_write = False
		
		#get student file first line
		student_firstline = student_file.readline().strip()
		if student_firstline == 'Execution is stopping!':
			student_firstline = ''
		#search student first line
		if student_firstline:
			student_firstline_search = re.search(student_firstline_regex, student_firstline)
		#get student file second line
		if student_firstline:
			student_secondline = student_file.readline().strip()
		
		#check whether the memory or the register file was written
		if student_firstline and 'Register' in student_secondline:
			student_secondline_search = re.search(register_write_regex, student_secondline)
			student_reg_write = True
		elif student_firstline:
			student_secondline_search = re.search(memory_write_regex, student_secondline)
			student_reg_write = False
		
		#Student continues to execute instructions when mars has completed
		if not mars_firstline and student_firstline:
			if not (student_secondline and student_secondline == 'Register Write to Reg: 0x00 Val: 0x00000000' and mars_syscall):
				
				cur_mismatches = cur_mismatches + 1
				if cur_mismatches == 1:
					print('Oh no...')
					print('')
				print('Cycle: ' + student_firstline_search.group('cycle'))
				print('MARS Stopped Execution, Student Improperly Continues')
				print('MARS instruction number: NA\tInstruction: NA')
				print('MARS: Execution Ended')
				print('Student: ' + student_secondline)
				print('')
		#Student ends execution early
		elif mars_firstline and not student_firstline:
			cur_mismatches = cur_mismatches + 1
			if cur_mismatches == 1:
				print('Oh no...')
				print('')
			print('Cycle: NA')
			print('MARS Continues Execution, Student Ends Early')
			print('MARS instruction number: ' + mars_firstline_search.group('num') + '\tInstruction: ' + mars_firstline_search.group('instr'))
			print('MARS: ' + mars_secondline)
			print('Student: Execution Ended')
			print('')
		#Both student and mars stop executing 
		elif not mars_firstline and not student_firstline:
			break;
		#Student and mars wrote to either register file or memory:
		else:
			if student_reg_write == mars_reg_write:
				if mars_reg_write:
					#print(mars_secondline_search.group('val') + "\t" + student_secondline_search.group('val'))
					#print(mars_secondline_search.group('reg') + "\t" + student_secondline_search.group('reg'))
					#print("%r" % (mars_secondline_search.group('val') == student_secondline_search.group('val')))
					#print("%r" % (mars_secondline_search.group('reg') == student_secondline_search.group('reg')))
					if not (mars_secondline_search.group('val') == student_secondline_search.group('val') and mars_secondline_search.group('reg') == student_secondline_search.group('reg')):
						cur_mismatches = cur_mismatches + 1
						if cur_mismatches == 1:
							print('Oh no...')
							print('')
						print('Cycle: ' + student_firstline_search.group('cycle'))
						print('Incorrect Write to Register File')
						print('MARS instruction number: ' + mars_firstline_search.group('num') + '\tInstruction: ' + mars_firstline_search.group('instr'))
						print('MARS: ' + mars_secondline)
						print('Student: ' + student_secondline)
						print('')
				else:
					if not (mars_secondline_search.group('val') == student_secondline_search.group('val') and mars_secondline_search.group('addr') == student_secondline_search.group('addr')):
						cur_mismatches = cur_mismatches + 1
						if cur_mismatches == 1:
							print('Oh no...')
							print('')
						print('Cycle: ' + student_firstline_search.group('cycle'))
						print('Incorrect Write to Memory')
						print('MARS instruction number: ' + mars_firstline_search.group('num') + '\tInstruction: ' + mars_firstline_search.group('instr'))
						print('MARS: ' + mars_secondline)
						print('Student: ' + student_secondline)
						print('')
			else:
				cur_mismatches = cur_mismatches + 1
				if cur_mismatches == 1:
					print('Oh no...')
					print('')
				print('Cycle: ' + student_firstline_search.group('cycle'))
				print('Writing to Different Components')
				print('MARS instruction number: ' + mars_firstline_search.group('num') + '\tInstruction: ' + mars_firstline_search.group('instr'))
				print('MARS: ' + mars_secondline)
				print('Student: ' + student_secondline)
				print('')
	
	mars_file.close()
	student_file.close()

	#Print help information
	print('')
	if not (cur_mismatches == 0): 
		print("Helpful resources for Debugging:")
		print(student_file_path + ": output from the VHDL testbench during program execution on your processor")
		print(mars_file_path + ": output from MARS containing expected output")
		print("vsim.wlf: waveform file generated by processor simulation, you can display this simulation in ModelSim without resimulating your processor by hand")
		print('')

	#Print final message
	if cur_mismatches == 0:
		print('Victory!! Your processes matches MARS expected output with no mismatches!!')
	elif cur_mismatches < max_mismatches:
		print(f'Almost! your processor completed the program with  {cur_mismatches}/{max_mismatches} allowed mismatches')
	else:
		print(f'You have reached the maximum mismatches ({cur_mismatches})')
	print('')
	
	
	return 0


if __name__ == '__main__':
    main()
	
#mars output regexs
#r'\[inst #(?P<num>[0-9]+)\] (?P<instr>[0-9$a-z ,\-\(\)]+)'
#r'Register Write to Reg: (?P<reg>[0-9A-Fa-fx]+) Val: (?P<val>[0-9xA-Fa-f]+)'
#r'Memory Write to Addr: (?P<addr>[0-9A-Fa-fx]+) Val: (?P<val>[0-9xA-Fa-f]+)'

#student testbench output regexs
#r'In clock cycle: (?P<cycle>[0-9]+)'
#r'Register Write to Reg: (?P<reg>[0-9A-Fa-fx]+) Val: (?P<val>[0-9xA-Fa-f]+)'
#r'Memory Write to Addr: (?P<addr>[0-9A-Fa-fx]+) Val: (?P<val>[0-9xA-Fa-f]+)'