

all: receiver sender


receiver : receiver.o secondary.o utilities.o ccitt16.o
	gcc -fPIC -o receiver receiver.o secondary.o utilities.o ccitt16.o

secondary.o : secondary.c
	gcc -c secondary.c
    
utilities.o : utilities.c
	gcc -c utilities.c



sender : sender.o primary.o utilities.o ccitt16.o
	gcc -o sender sender.o primary.o utilities.o ccitt16.o
	
primary.o : primary.c
	gcc -c primary.c



clean:
	rm receiver secondary.o utilities.o sender primary.o
	
