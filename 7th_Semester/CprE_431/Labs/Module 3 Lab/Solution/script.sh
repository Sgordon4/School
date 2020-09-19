#!/bin/bash
filename="passwords.txt"
realHash="\$6\$xgLS35S6\$2UjEq.dUhICPw9zgDVJXcQYQp/9ilLPQt/8Zgu0uwngI5mVvB1eKQG9SnVLjmOOfkB4Jjb5VSAXGXjY4Cf5k90"
salt="xgLS35S6"


while read -r line; do
	val="$(openssl passwd -6 -salt $salt \'$line\')"
	if (test $val = $realHash); then
		echo "YUUUHHHHHHHH: $line"
		break; fi

	#echo $val


done < $filename


#num=5; if ( test $realHash = $realHash); then echo "yes"; else echo "no"; fi
