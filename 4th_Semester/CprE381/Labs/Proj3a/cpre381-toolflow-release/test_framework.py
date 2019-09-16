import argparse
import re
import os
from scripts import dump_compare, mars, modelsim

def main():
	'''
	Main method for the test framework
	'''
	# Algorithm:
	# 1) parse arguments
	# 2) check required files exist
	# 3) run MARS sim
	# 4) compile student vhdl
	# 5) generate hex with MARS
	# 6) sim student vhdl
	# 7) compare output

	# 1) parse arguments
	options = parse_args()

	# 2) check required files exist
	missing_file = check_project_files_exist()
	if missing_file:
		print(f'\nCould not find {missing_file}')
		print('program is exiting\n')
		exit()

	if not modelsim.is_installed():
		print('\nModelsim does not seem to be installed in the expected location: C:/modeltech64_10.5b/win64')
		print('program is exiting\n')
		exit()

	if not check_vhdl_present():
		print('\nOops! It doesn\'t look like you\'ve copied your processor into ModelSimWork/src/\n')
		return

	# create temp directory if it doesn't exist
	if not os.path.isdir('temp'):
		os.makedirs('temp')

	# 3) run MARS sim
	print('')
	options['asm-path'] = mars.run_sim(asm_file=options['asm-path'])

	# 4) compile student vhdl
	if options['compile']:
		compile_success = modelsim.compile()
		if not compile_success:
			return
	else:
		print('Skipping compilation\n')

	# 5) generate hex with MARS
	mars.generate_hex(options['asm-path'] )
	
	# 6) sim student vhdl
	sim_success = modelsim.sim(timeout=options['sim-timeout'])
	if not sim_success:
		return

	# 7) compare output
	compare_dumps(options)


expected_vhd = {'tb_SimplifiedMIPSProcessor.vhd','mem.vhd','MIPS_Processor.vhd'}

def check_vhdl_present():
	'''
	Checks if there are any VHDL files present in the src folder other than the provided
	top-level design.

	Returns True if other files exist
	'''
	src_dir = os.listdir('ModelSimWork/src/')
	vhd_regex = re.compile('.*\.vhd')
	for f in src_dir:
		if vhd_regex.fullmatch(f) and not f in expected_vhd:
			return True

	return False


def parse_args():
	'''
	Parse commnd line arguments into a dictionary, and return that dictionary.

	The returned dictionary has the following keys and types:
	- 'asm-path': str
	- 'max-mismatches': int > 0 
	- 'compile': bool
	'''
	parser = argparse.ArgumentParser()
	parser.add_argument('--asm-file', help='Relative path to assembly file to simulate using unix style paths.')
	parser.add_argument('--max-mismatches', type=check_max_mismatches ,default=3, help='Number of incorrect instructions to print before the program claims failure, default=3')
	parser.add_argument('--nocompile', action='store_true', help='flag used to disable compilation in order to save time')
	parser.add_argument('--sim-timeout',type=check_sim_timeout, default=30, help='change the ammount of time before simulation is forcefully stopped')
	args = parser.parse_args()

	options = {
		'asm-path': args.asm_file,
		'max-mismatches': args.max_mismatches,
		'compile': not args.nocompile,
		'sim-timeout': args.sim_timeout
	}

	return options

def check_sim_timeout(v):
	ivalue = int(v)
	if ivalue <= 0:
		raise argparse.ArgumentTypeError('--sim-timeout should be a positive integer')
	return ivalue

def check_max_mismatches(v):
	ivalue = int(v)
	if ivalue <= 0:
		raise argparse.ArgumentTypeError('--max-mismatches should be a positive integer')
	return ivalue

def check_project_files_exist():
	'''
	Returns None if all required files exist, otherwise returns path to missing file
	'''
	expected_files = [
		'ModelSimWork/src/tb_SimplifiedMIPSProcessor.vhd',
		'MARsWork/Mars_CPRE381_v1.jar'
	]
	for path in expected_files:
		if not os.path.isfile(path):
			return path

	return None


def compare_dumps(options):
	'''
	Compares dumps ans prints the results to the console
	'''

	student_dump = 'temp/modelsim_dump.out'
	mars_dump = 'temp/mars_dump.out'

	# use user mismatches if the option was specified
	mismatches = options['max-mismatches']
	if not mismatches:
		mismatches = 3

	dump_compare.compare(student_dump,mars_dump,max_mismatches=mismatches)


if __name__ == '__main__':
	try:
		main()
	except KeyboardInterrupt: #exit gracefully since this is common
		exit(1)
	except Exception as e:
		print('Program exited with unexpected exception:\n')
		print(e)
