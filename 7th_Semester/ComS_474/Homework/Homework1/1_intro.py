import random
import sklearn.neural_network
import numpy.random
import ipywidgets

def generate_data(N):
    g = 8.9
    
    # If using Python's own random number generator
	#Ts = [random.random() for _ in range(N)]
	#Hs = [g*(t**2)/2 for t in Ts]

    # If using Numpy random number generator
    Ts = numpy.random.rand(N)
    Hs = g*(Ts**2)/2
    
    return Ts, Hs

Ts, Hs = generate_data(500)

#print(Hs)


def test_NN(Ts, Hs, max_iter=200):
    NN = sklearn.neural_network.MLPRegressor(
        hidden_layer_sizes=(4,4), 
        activation='tanh', 
        max_iter=max_iter
        )
    Ts = Ts.reshape(-1, 1) # learned from error
    NN.fit(Ts, Hs)
	#predictions = NN.predict(Ts)
    score = NN.score(Ts, Hs)
    return score
    
    
# test_NN(Ts, Hs, max_iter)





ipywidgets.interact(
    test_NN, 
    Ts=ipywidgets.fixed(Ts), 
    Hs=ipywidgets.fixed(Hs), 
    max_iter = ipywidgets.IntSlider(
        min=50,
        max=5000,
        step=50,
        description='Max # of iterations', 
        layout=ipywidgets.Layout(width='100%')
        )
#     max_iter=ipywidgets.SelectionSlider(
#                description='max_iters',
#                options = list(numpy.logspace(2, 4, 10).astype(int))
#             )
    )

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
learning_curve(Ts, Hs, "NN_plot")