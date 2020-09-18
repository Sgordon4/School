#!/bin/bash
filename="100k-most-used-passwords-NIST.txt"
n=1
while read line; do
	echo "Line no. $n : $line"
	n=$((n+1))
done < $filename
