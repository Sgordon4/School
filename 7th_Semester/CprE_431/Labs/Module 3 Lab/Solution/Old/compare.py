import hashlib


#Given in external file
salt = "xgLS35S6"
realHash = "2UjEq.dUhICPw9zgDVJXcQYQp/9ilLPQt/8Zgu0uwngI5mVvB1eKQG9SnVLjmOOfkB4Jjb5VSAXGXjY4Cf5k90"

f = open("100k-most-used-passwords-NIST.txt", encoding="utf8", errors="surrogateescape")


#realHash = hashlib.sha512((salt+"fuckyou1").encode('utf-8')).hexdigest()
#realHash = "09b24a18ffb7c55c132a5dd17b1c3c7c7d38417d8cf801fa124149d4bf740a48e9f0c18e873fec85ff5558af32523dace5724e52248ffac837f7d8e79ce80558"
#print(realHash)


count = 1
for x in f:
	x = x.rstrip()								#Strip trailing newline
	#print(repr(x))
	hash = hashlib.sha512((salt+x).encode('utf-8')).hexdigest()	#Generate hash
	
	#print(count, x, hash)
	'''
	print(count)
	print(repr(x))
	print(hash)
	print(realHash)
	print()
	count += 1
	#if(count == 70):break
	'''
	
	
	
	
	if(hash == realHash):
		print(x)
		break


print("End")
