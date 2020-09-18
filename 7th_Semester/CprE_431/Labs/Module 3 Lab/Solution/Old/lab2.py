import os
import io
import subprocess


salt = "$6$xgLS35S6"
realHash = "$6$xgLS35S6$2UjEq.dUhICPw9zgDVJXcQYQp/9ilLPQt/8Zgu0uwngI5mVvB1eKQG9SnVLjmOOfkB4Jjb5VSAXGXjY4Cf5k90"

f = io.open("100k-most-used-passwords-NIST.txt", mode="r", encoding="utf8", errors="surrogateescape")
cmd = "openssl passwd -6 -salt xgLS35S6 2>/dev/null"

for line in f:
	line = line.strip()
	#print(repr(line))

	#c = crypt.crypt(line, salt)
	#c = os.system(cmd+line)
	c = subprocess.call(cmd+line, shell=True)
	#c = subprocess.call(['openssl', '-6', '-salt', 'xgLS35S6'])
	if(c == realHash):
		print(line)
		break

print("End")
