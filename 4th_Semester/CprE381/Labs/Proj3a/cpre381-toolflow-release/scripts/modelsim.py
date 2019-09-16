import subprocess
import os
import re
import shutil

# used to see if output may have timed out but outputted correctly (meaning no halt signal)
expected_firstline = re.compile(r'In clock cycle: (?P<cycle>[0-9]+)')

def compile():
	'''
	Compiles everything in ModelSimWork/src/ into work
	assumes directory is present. Open's notepad with any compile errors

	Returns True if compilation succeeded
	'''

	compile_log = open('temp/vcom_compile.log','w+')
	print('starting compilation...')

	# Remove the work before compilation to ensure a blank slate and prevent potential issues.
	# If work is not removed, and the last run was terminated unexpectedly, compilation errors
	# will occour
	if os.path.isdir('ModelsimWork/work'):
		shutil.rmtree('ModelsimWork/work') # delete a non empty directory

	# Create work folder for compiled work
	try:
		subprocess.check_output( # use check_output to suppress any output
			['C:\\modeltech64_10.5b\\win64\\vlib.exe','ModelSimWork/work']) 
	except:
		print("could not successfully create work folder")
		return False

	try:
		exit_code = subprocess.call(
			['C:\\modeltech64_10.5b\\win64\\vcom.exe','-2008','-work','ModelSimWork/work','ModelSimWork/src/*.vhd'],
			stdout=compile_log,
			timeout=60
		)
	except subprocess.TimeoutExpired:
		print('Compilation timed out (if you think this was a mistake you can increase the time to more than 60 seconds in the script).\n')
		subprocess.Popen(['Notepad','temp/vcom_compile.log'])
		return False
	except:
		return False

	compile_log.close()

	if(exit_code != 0):
		print('could not compile successfully, got exit code {}'.format(exit_code))
		
		# Use Popen to start notepad in a non-blocking manner with the compile error
		subprocess.Popen(['Notepad','temp/vcom_compile.log'])
		return False

	print('Successfully compiled vhdl\n')
	return True

def sim(timeout=30):
	'''
	Simulates testbench using vCom. All work should be compiled before this method is called

	Returns True if the simulation was successful, otherwise False
	'''

	sim_log = open('temp/vsim.log','w')
	print('Starting VHDL Simulation...')
	
	try:
		# C:\modeltech64_10.5b\win64\vsim.exe -c -novopt tb_SimplifiedMIPSProcessor -do modelsim_fremawork.do
		exit_code = subprocess.call(
			['C:\\modeltech64_10.5b\\win64\\vsim.exe','-c','-novopt','tb_SimplifiedMIPSProcessor','-do','modelsim_framework.do'],
			stdout=sim_log,
			stderr=sim_log,
			cwd='ModelSimWork/', #Run this process in a different directory for work folder
			timeout=timeout # If the do file doesn't reach the 'quit' we need to manually kill the process
		)

		# check if process exited with error
		if(exit_code != 0):
			print('could not simulate successfully, got exit code {}'.format(exit_code))

			# Use Popen to start notepad in a non-blocking manner with the sim error
			subprocess.Popen(['Notepad','temp/vsim.log'])
			return False

	except subprocess.TimeoutExpired:

		if timeout_is_nohalt():
			print('** Warning: Simulation timed out, but produced some valid output, most likely the halt signal is incorrect or the application contains an infinite loop **')
		else:
			# close the simulation with a failure since the simulation didn't produce a valid output
			print('Simulation timed out (if you think this was a mistake you can increase the time to more than 30 seconds explicitly via --sim-timeout)\n')
			subprocess.Popen(['Notepad','temp/vsim.log'])
			# move file into the temp directory for student debugging
			wlf_loc = 'temp/vsim.wlf'
			if os.path.exists(wlf_loc):
				os.remove(wlf_loc)
			shutil.copyfile('ModelSimWork/vsim.wlf',wlf_loc)
			return False
	finally:
		sim_log.close()

	# move file into the temp directory for compare
	dump_loc = 'temp/modelsim_dump.out'
	if os.path.exists(dump_loc):
		os.remove(dump_loc)
	shutil.copyfile('ModelSimWork/dump.out',dump_loc)

	# move file into the temp directory for student debugging
	wlf_loc = 'temp/vsim.wlf'
	if os.path.exists(wlf_loc):
		os.remove(wlf_loc)
	shutil.copyfile('ModelSimWork/vsim.wlf',wlf_loc)

	print('Successfully simulated program!\n')
	return True


def timeout_is_nohalt():
	'''
	Opens dump file to check if file is formatted correctly despite process timing out.
	This would indicate that no halt signal was implemented, but simulation was correct otherwise.

	Retuns False if halt didn't cause the time out
		, True if it may have times out because of halt
	'''
	if not os.path.isfile('ModelSimWork/dump.out'):
		return False

	with open('ModelSimWork/dump.out') as f:
		firstline = f.readline()
	
	return expected_firstline.match(firstline)

def is_installed():
	'''
	Returns True if modelsim is installed on the computer in the expected location
	'''

	# This only checks the existance of the win64 directory with the executables we want

	if os.path.isdir('C:/modeltech64_10.5b/win64'):
		return True
	else:
		return False
