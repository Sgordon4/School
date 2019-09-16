@echo off
cls
@pushd %~dp0

:: Path to Python 3.6 on both lab compulters and VDI
set lab_path="C:\Program Files (x86)\Python36-32\python.exe"
set vdi_path="C:\Python\python.exe"

::Check which python location exists and then set python_path to that location
if exist %lab_path% (
    set python_path=%lab_path%
) else if exist %vdi_path% (
    set python_path=%vdi_path%
) else (
    echo Could not find python interperter on computer
    exit 1
)


:: Command line options:
::  --asm-file: Relative path to assembly file to simulate using unix style paths. Example: MARsWork/Examples/fibonacci.asm
::  --nocompile: Disables compilation to save time when the processor has not changed
::  --max-mismatches: Number of incorrect instructions to print before the program claims failure. Default: 3
::  --sim-timeout: Changes the ammount of time before simulation is forcefully stopped. Default: 30
%python_path% test_framework.py

::Pause so students can see final output if they are not using the command line
:: use > nul to hide the pause output so we can print our own message
echo Press any key to close . . .
pause > nul

@popd