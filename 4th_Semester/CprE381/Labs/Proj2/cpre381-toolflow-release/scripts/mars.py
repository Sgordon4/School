import subprocess

def generate_hex(asm_file_path):
	'''
	uses mars to generate:
		- temp/imem.hex
		- temp/dmem.hex

	accepts path to assembly file as input and does no error checking because the simulation
	should have been run first guarenteeing the program will assemble.
	'''

	# Use check_output instead of call to supress output
	subprocess.check_output(
		['java','-jar','MARsWork/Mars_CPRE381_v1.jar','a','dump','.text','HexText','temp/imem.hex',asm_file_path],
		)

	# Use check_output instead of call to supress output
	subprocess.check_output(
		['java','-jar','MARsWork/Mars_CPRE381_v1.jar','a','dump','.data','HexText','temp/dmem.hex',asm_file_path],
		)

	# I am not concerned with error codes since we have a guarentee the simulation runs from run_mars_sim()

	
def run_sim(asm_file=None):
	'''
	Simulates Assembly file in MARS. Guarentees that a valid assembly file is given or else it
	will call continue to prompt user.

	Returns the path to the correct assembly file.
	'''

	if asm_file:
		path = asm_file
	else:
		print('Please provide the assembly file to run.')
		print('Use unix style paths like: MARsWork/Examples/fibonacci.asm')
		path = input('>').strip()

	# open in write+create mode
	mars_dump_file = open('temp/mars_dump.out','w+')
	subprocess.call(
		['java','-jar','MARsWork/Mars_CPRE381_v1.jar','nc',path],
		stdout=mars_dump_file
		)
	mars_dump_file.close()

	# Mars does not seem provide non-zero error codes, so we need to look at the dump to check for errors

	# Re-open the file in read mode, so we can check if an error has occoured.
	# This should be faster than buffering the whole file in the program since
	# We only need to check one line
	mars_dump_file = open('temp/mars_dump.out','r+')
	first_line = mars_dump_file.readline()

	# if we couldn't sim and they provided a file via arguments
	#	prompt for a different file
	# elif we couldn't sim and they wer prompted
	# 	prompt again
	# else
	# 	return the correct path
	if asm_file and first_line.find('[inst #1]') != 0:
		print("Could not simulate assembly file with error:")
		print(first_line)
		print('please enter file manually')
		print('\n')
		return run_sim(asm_file=None)
	elif first_line.find('[inst #1]') != 0:
		print("Could not simulate assembly file with error:")
		print(first_line)
		print('\n')
		return run_sim(asm_file=None)
	else:
		print('Successfully simulated program in MARS\n')
		return path