Sean Gordon
Sgordon4

ComS 472 Project 2
A Theorem Prover for Propositional Logic



*********
To run the program, the path for the input file must be specified in Main. 
A preexisting sample path from my own computer can be used for reference.
*********


This project consists of 1 main file, 4 functional files, and 4 datatypes:

[Main], [Resolver, ExpressionToPostfix, PostfixToTree, TreeToCNF], and 
[ConjunctiveNormalForm, Clause, Literal, Node]



Main parses the PL sentences from the input file, then calls stringToCNF.
stringToCNF in turn calls ExpressionToPostfix, PostfixToTree, and TreeToCNF to convert the input to CNF.
Once all PLs are in CNF, Main calls Resolver to prove clauses using the KB.



More information can be gleaned from the Node and ConjunctiveNormalForm data types using:

someNode.printPretty();
System.out.println(someCNF.toString());
System.out.println(someCNF.printStructure());

Some examples of this are commented out in Main.stringToCNF()



All of the output is built using a stringBuilder named 'output' and printed at the end of Main.



More in depth information can be found in the project's JavaDoc