all: bytedump bsdump-template fat12ls-template

bytedump: bytedump.c
	gcc -o bytedump bytedump.c

bsdump-template: bsdump-template.c
	gcc -o bsdump-template bsdump-template.c

fat12ls-template: fat12ls-template.c
	gcc -o fat12ls-template fat12ls-template.c

clean:
	-rm -f *.o
	-rm -f bytedump
	-rm -f bsdump-template
	-rm -f fat12ls-template
