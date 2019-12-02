sentence(S) :- s(S, []).

s --> f.
s --> t, n, t.

f --> [if], b, [then], s.
f --> [if], b, [then], s, [else], s.

b --> t, e, t.

t --> [x].
t --> [y].
t --> [z].
t --> [1].
t --> [0].

e --> [>].
e --> [<].

n --> [+].
n --> [-].
n --> [=].