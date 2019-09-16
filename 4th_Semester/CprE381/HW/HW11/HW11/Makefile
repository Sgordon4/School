
SRCS := $(wildcard test*.c)
BINS = ${SRCS:.c=}
CFLAGS =  
CPPFLAGS = 

all: $(BINS)

test% : test%.c
	$(CC) $(CFLAGS) $(CPPFLAGS) $< -o $@

clean:
	rm -f $(BINS)
