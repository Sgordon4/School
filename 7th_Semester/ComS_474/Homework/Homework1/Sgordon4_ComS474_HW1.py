'''
1) The number of permutations of $n$ variables = n!
The number of permutations of m operations in 1 combination of n variables = (m)^{n-1}
Thus, the total number of permutations of 3 operations and 4 variables = (3)^{4-1}*4! = 648
'''

import matplotlib.pyplot as plt
def learning_curve(X, Y, filename):
	filename = filename+".png"
	iterNum = []
	scores = []
	
	for i in range(50, 2050, 50):
		iterNum.append(i)
		scores.append(test_NN(X, Y, i))
	
	
	plt.plot(iterNum, scores)
	plt.xlabel('Max Iterations')
	plt.ylabel('Score')
	plt.savefig(filename)
		
	return iterNum, scores

#Example function call
#learning_curve(Ts, Hs, "NN_plot")