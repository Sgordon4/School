from scripts.quartus import build, parse_timings, generate_project as gp
import os
import shutil
import subprocess

def main():
    vhd_list = gp.find_vhd_files(dir='ModelSimWork/src')

    # create temp directory if it doesn't exist
    os.makedirs('temp',exist_ok=True)

    # create quartus directory if it doesn't exist, if another process is using
    # The directory we need to exit
    try: 
        shutil.rmtree('QuartusWork',ignore_errors=True)
        os.makedirs('QuartusWork')
    except: 
        print("Could not delete QuartusWork")
        exit(1)

    if vhd_list == []:
        print('no vhd files were found')
        exit(1)

    gp.write_qsf(vhd_list,dir='QuartusWork')
    gp.write_qpf(dir='QuartusWork')
    gp.write_sdc(dir='QuartusWork')

    build_success = build.build_all()
    if not build_success:
        exit(1)

    parse_success = parse_timings.parse_timings()
    if not parse_success:
        exit(1)

    # Use Popen to start notepad in a non-blocking manner
    subprocess.Popen(['Notepad','temp/timing.txt'])


if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        pass
        