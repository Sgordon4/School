


receiver : secondary.o utilities.o ccitt16.o
	gcc -o receiver receiver.o secondary.o utilities.o ccitt16.o


receiver.o : receiver.c
	gcc -c receiver.c

secondary.o : secondary.c
	gcc -c secondary.c
    
utilities.o : utilities.c
	gcc -c utilities.c



clean:
	rm receiver receiver.o secondary.o utilities.o
	
