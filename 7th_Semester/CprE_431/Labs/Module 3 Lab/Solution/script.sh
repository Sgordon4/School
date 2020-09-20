#!/bin/bash

filename="100k-most-used-passwords-NIST.txt"
dos2unix $filename		#Remove carriage returns, original hashed without them

realHash="\$6\$QpU0v3n/\$Z5BKWAKu6SsZMI4KStZmlR/IZuhE9Ts.cezqBca3iApKmbT/GSBC1GUHf0I0mmytOdmqzclHkT47idGnpmHoe0"
salt="QpU0v3n/"

count=1
while read -r line; do
	val="$(openssl passwd -6 -salt $salt $line)"
	echo "$count : $val" | cat -v
	
	if (test $val = $realHash); then
		echo "Correct Password: $line"
		break; fi
	
	count=$((count+1))

done < $filename