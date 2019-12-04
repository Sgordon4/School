sentence(S) :- s(S, []).

s(L1, L2) :- f(L1, L2).
s(L1, L4) :- t(L1, L2), n(L2, L3), t(L3, L4).

/*
f(L1, L5) :- if(L1, L2), b(L2, L3), 
    			then(L3, L4), f_to_s(L4, L5).
f(L1, L7) :- if(L1, L2), b(L2, L3), then(L3, L4), 
    			s(L4, L5), else(L5, L6), f_to_s(L6, L7).

f_to_s([X|_], L2) :- s(X, L2).
*/
f(L1, L5) :- if(L1, L2), b(L2, L3), 
    			then(L3, L4), s(L4, L5).
f(L1, L7) :- if(L1, L2), b(L2, L3), then(L3, L4), 
    			s(L4, L5), else(L5, L6), s(L6, L7).

b(L1, L4) :- t(L1, L2), e(L2, L3), t(L3, L4).

if([if|Tail], Tail).
then([then|Tail], Tail).
else([else|Tail], Tail).

t([x|Tail], Tail).
t([y|Tail], Tail).
t([z|Tail], Tail).
t([1|Tail], Tail).
t([0|Tail], Tail).

e([<|Tail], Tail).
e([>|Tail], Tail).

n([+|Tail], Tail).
n([-|Tail], Tail).
n([=|Tail], Tail).

/*
sentence([if, x, >, 0, then, x, =, 1).
sentence([if, x, >, 0, then, x, =, 1, else, x, =, 0]).
sentence([if, x, >, 0, then, x, =, 1, else, if, x, <, 0, then, x, =, 0]).
*/



