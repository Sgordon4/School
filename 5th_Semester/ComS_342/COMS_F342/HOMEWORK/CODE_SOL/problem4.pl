%% Prerequisite problem
%%  - each course has a list of prerequisites
%%  - some courses have no prerequisites, we use an empty list for that. e.g: prerequisites(coms104, []).
%%  - we use just prerequisite and do not use the passing grade as a constraint.
%%  - there are some courses that have different combinations of prerequisites. e.g. COMS 208 Prereq: MIS/COM S 207,
%%    credit or enrollment in MATH 151, MATH 160, or MATH 165
%%  - for coms208 we use three clauses prerequisites(coms208, [coms207, math151]). prerequisites(coms208, [coms207,
%%    math160]). prerequisites(coms208, [coms207, math165]).
%%  - this is because a prolog program is a conjunction of Horn clauses and in this case, the three prerequisites
%%    clauses act as an or operator.
%%  - cantake and totake are the rules used to answers questions 3(b) and 3(c). answer for 3(a) are just the
%%    prerequisite rules
%%  - there are some helper functions such as list2set that help to delete duplicates

%% Facts 
% 100's courses
prerequisites(coms101, []).
prerequisites(coms103, []).
prerequisites(coms104, []).
prerequisites(coms105, [coms104]).
prerequisites(coms105A, [coms104]).
prerequisites(coms105B, [coms104]).
prerequisites(coms106, []).
prerequisites(coms107, []).
prerequisites(coms108, [coms107]).
prerequisites(coms113, []).
prerequisites(coms127, [math140]).   

% 200' courses
prerequisites(coms203, []).
prerequisites(coms207, [math150]).
prerequisites(coms207, [math140]).
prerequisites(coms208, [coms207, math151]).
prerequisites(coms208, [coms207, math160]).
prerequisites(coms208, [coms207, math165]).
prerequisites(coms227, [math143]).
prerequisites(coms228, [coms227, math165]).
prerequisites(coms230, [coms227, math165, engl150]).
prerequisites(coms252, [coms107]).
prerequisites(coms252, [coms127]).
prerequisites(coms252, [coms207]).
prerequisites(coms252, [coms227]).
prerequisites(coms290, []).
prerequisites(coms290H, []).

% 300's courses
prerequisites(coms309, [coms228, math165]).
prerequisites(coms311, [coms228, math166, engl150, coms230]).
prerequisites(coms311, [coms228, math166, engl150, cpre310]).
prerequisites(coms319, [coms228]).
prerequisites(coms321, [coms228, math165, coms230, engl250]).
prerequisites(coms321, [coms228, math165, cpre281, engl250]).
prerequisites(coms327, [coms228, math165]).
prerequisites(coms331, [coms228, math166, coms230, engl250]).
prerequisites(coms331, [coms228, math166, cpre310, engl250]).
prerequisites(coms336, [coms327, math207]).
prerequisites(coms336, [coms327, math317]).
prerequisites(coms342, [coms228, math165, coms230]).
prerequisites(coms342, [coms228, math165, cpre310]).
prerequisites(coms350, [math201]).
prerequisites(coms350, [coms230]).
prerequisites(coms352, [coms321, coms327, engl250]).
prerequisites(coms362, [coms228, math165, engl250]).
prerequisites(coms363, [coms228, math165, engl250]).
prerequisites(coms398, []).

% 400's courses
prerequisites(coms402, []).
prerequisites(coms402A, [coms437]).
prerequisites(coms402B, [coms402A]).
prerequisites(coms402C, []).
prerequisites(coms409, [coms309]).
prerequisites(coms410, [coms228, coms309]).
prerequisites(coms412, [coms230,coms311,stat330]).
prerequisites(coms412, [cpre310,coms311,stat330]).
prerequisites(coms414, [coms227]).
prerequisites(coms414, [coms207]).
prerequisites(coms414, [geron377]).
prerequisites(coms414, [artgr271]).
prerequisites(coms415, []).
prerequisites(coms417, [coms309, coms230, engl250, spcm212]).
prerequisites(coms417, [coms309, cpre310, engl250, spcm212]).
prerequisites(coms418, [coms311]).
prerequisites(coms421, [math301]).
prerequisites(coms421, [math207]).
prerequisites(coms421, [math317]).
prerequisites(coms421, [coms230]).
prerequisites(coms424, [math265, math207]).
prerequisites(coms424, [math265, math317]).
prerequisites(coms425, [coms311, coms230, engl250, spcm212]).
prerequisites(coms426, [cpre308, cpre315]).
prerequisites(coms426, [coms321, cpre315]).
prerequisites(coms426, [coms321, coms311]).
prerequisites(coms430, [coms311, coms362, engl250, spcm212]).
prerequisites(coms430, [coms311, coms363, engl250, spcm212]).
prerequisites(coms433, [coms331]).
prerequisites(coms435, [coms228, coms230, coms311]).
prerequisites(coms435, [coms228, cpre310, coms311]).
prerequisites(coms437, [coms336]).
prerequisites(coms440, [coms331, coms342, engl250, spcm212]).
prerequisites(coms441, [coms342]).
prerequisites(coms441, [coms340]).
prerequisites(coms444, [math165]).
prerequisites(coms444, [stat401]).
prerequisites(coms454, [coms311, coms352]).
prerequisites(coms455, [coms311, coms230, stat330, engl150, spcm212]).
prerequisites(coms461, [coms311, engl250, scpm212]).
prerequisites(coms472, [coms311, coms230, stat330, engl250, scpm212, coms342]).
prerequisites(coms472, [coms311, cpre310, stat330, engl250, scpm212, coms342]).
prerequisites(coms474, [coms311, coms230, stat330, math165, engl250, scpm212, coms342]).
prerequisites(coms477, [coms288, coms230, math166, math207]).
prerequisites(coms477, [coms288, cpre310, math166, math317]).
prerequisites(coms481, [coms265, math266]).
prerequisites(coms481, [coms265, math267]).
prerequisites(coms486, [coms352]).
prerequisites(coms487, [coms352]).
prerequisites(coms487, [cpre489]).
prerequisites(coms490, []).
prerequisites(coms490H, []).

% math courses
prerequisites(math140, []).
prerequisites(math150, []).
prerequisites(math143, []).
prerequisites(math151, []).
prerequisites(math160, []).
prerequisites(math165, []).
prerequisites(math166, []).
prerequisites(math301, []).
prerequisites(math201, []).
prerequisites(math207, []).
prerequisites(math317, []).
prerequisites(math265, []).
prerequisites(math266, []).
prerequisites(math267, []).

% english courses
prerequisites(engl150, []).
prerequisites(engl250, []).

% cpre courses
prerequisites(cpre281, []).
prerequisites(cpre310, []).
prerequisites(cpre308, []).
prerequisites(cpre315, []).
prerequisites(cpre489, []).

% stat courses
prerequisites(stat330, []).
prerequisites(stat401, []).

% misc
prerequisites(geron377, []).
prerequisites(artgr271, []).
prerequisites(spcm212, []).

%% Rules

flatten([[X|S]|T], F) :- flatten([X|[S|T]], F).
flatten([[]|S], F) :- flatten(S, F).
flatten([X|S], [X|T]) :- \+(X = []), \+(X = [_|_]), flatten(S, T).
flatten([], []).

list2set([] , []).
list2set([E|Es] , Set ) :-
   member(E, Es) ,
   list2set(Es , Set).
list2set([E|Es] , [E|Set] ) :-
   maplist(dif(E), Es),
   list2set(Es , Set).

subset([], []).
subset([E|Tail], [E|NTail]):-
  subset(Tail, NTail).
subset([_|Tail], NTail):-
  subset(Tail, NTail).

list_not_empty([], false).
list_not_empty([_|_], true).


%% Append two lists
append([], L2, L2) :- 
append([H | Tail], L2, [H | RTail]) :-
    append(Tail, L2, RTail).  

% returns all the prerequisites of a list of courses recursively
prerequisites_list([], []).
prerequisites_list([C | T], P) :-
    allprerequisites(C, P1),
    prerequisites_list(T, P2),
    append(P1, P2, P3),
    list2set(P3, P).

% returns all the prerequisites of course C recursively
allprerequisites(C, P) :-
    prerequisites(C, P1),
%    list_not_empty(P1, true),
    prerequisites_list(P1, P2),
    append(P1, P2, P3),
    list2set(P3, P).

% returns a course C2 that can be taken with the prerequisites of another course C
cantake_one(C, C2) :-
    allprerequisites(C, P1),
    append([C], P1, P2),
    prerequisites(C2, P3),
    list_not_empty(P3, true),
    subset(P2, P3).

% returns all possible set of courses that can be taken if course C has been
% taken
cantake(C, L) :-
    findall(C2, cantake_one(C, C2), L1),
%    delete(C, L1, L2),
    list2set(L1, L).

% returns the set of courses that need to be taken in order to take course C
totake(C, L) :-
    prerequisites(C, L1), % 
    prerequisites_list(L1, L2),
    append(L1, L2, L3),
    list2set(L3, L).
