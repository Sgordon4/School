import subprocess
import datetime as dt
from . import  generate_project as gp

r'''
:temporary script for testing
C:\altera\intelFPGA\18.0\quartus\bin64\quartus_map --read_settings_files=on --write_settings_files=off qs2 -c qs2

C:\altera\intelFPGA\18.0\quartus\bin64\quartus_fit --read_settings_files=off --write_settings_files=off qs2 -c qs2
C:\altera\intelFPGA\18.0\quartus\bin64\quartus_asm --read_settings_files=off --write_settings_files=off qs2 -c qs2
C:\altera\intelFPGA\18.0\quartus\bin64\quartus_sta --sdc="qs2.sdc" qs2 --do_report_timing
'''

quartus_bin_dir = r'C:\altera\intelFPGA\18.0\quartus\bin64'

def build_all():
    pname = gp.project_name

    starttime = dt.datetime.now()

    print(f'\nStarting compilation at {str(starttime)}\n')

    # starting mapping
    exit_code = subprocess.call(
        [f'{quartus_bin_dir}\\quartus_map','--read_settings_files=on','--write_settings_files=off',pname,'-c',pname],
        cwd='QuartusWork/'
    )

    if exit_code != 0:
        print('Error during compilation or mapping')
        return False

    # starting fitting
    exit_code = subprocess.call(
        [f'{quartus_bin_dir}\\quartus_fit','--read_settings_files=on','--write_settings_files=off',pname,'-c',pname],
        cwd='QuartusWork/'
    )

    if exit_code != 0:
        print('Error during fitting')
        return False

    # starting assembly
    exit_code = subprocess.call(
        [f'{quartus_bin_dir}\\quartus_asm','--read_settings_files=on','--write_settings_files=off',pname,'-c',pname],
        cwd='QuartusWork/'
    )

    if exit_code != 0:
        print('Error during assembly')
        return False

    # generate timing
    with open('temp/timing_dump.txt','w') as timing_log:
        exit_code = subprocess.call(
            [f'{quartus_bin_dir}\\quartus_sta',f'--sdc={pname}.sdc',pname,'--do_report_timing'],
            cwd='QuartusWork/',
            stdout = timing_log
        )

        if exit_code != 0:
            print('Error during assembly')
            return False

    endtime = dt.datetime.now()
    print('\nTiming generation complete!')
    print(f'completed in {str(endtime-starttime)}')

    return True

