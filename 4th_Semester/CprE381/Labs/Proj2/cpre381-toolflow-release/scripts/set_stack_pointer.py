# set_stack_pointer.py adds a line to the beginning of an assembly file to allow the stacks to 
# align for both MARS and your processor.
#
# NOTE: YOUR FILE MUST HAVE A main: label. Avoid having your program start on the same line as the 
# main label this can cause issues depending on the program, just have the main label on a line of 
# its own.
# Ex.
# .data
# 	 <variables>
# .text
# main:
#    <program>

import sys

def main():
	print('Assembly file edit started...')
	new_line = '\taddi $sp, $0, 0x3000'
	if len(sys.argv) is 3:
		student_file_path = sys.argv[1]
		output_file_path = sys.argv[2]
	else:
		print('Improper usage, expecting python set_stack_pointer.py <student_file> <output_file>')
		return 1;
	
	print('Reading student file')
	student_file = open(student_file_path, 'r')
	student_list = student_file.readlines()
	student_file.close()
	
	for i in range(0, len(student_list)-1):
		if "main:" in student_list[i]:
			print('Found \'main:\', inserting new line')
			student_list.insert(i+1, new_line)
			break;
	
	print('Writing output file')
	output_file = open(output_file_path, 'w');
	for line in student_list:
		output_file.write(line.strip())
		output_file.write('\n')
	output_file.close()
	
	print('Assembly file edit complete')
	return 0
	
if __name__ == '__main__':
    main()