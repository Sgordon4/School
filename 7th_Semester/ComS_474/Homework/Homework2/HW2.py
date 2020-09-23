import numpy as np 
import matplotlib.pyplot as plt

def plot_mse(X, y, filename):
    w = np.array([0,0,0]) # just a placeholder
	
	#Add a bias column to each x
	np.c_[ X, np.ones(len(X)) ]
	
	
	int numIter = 100
	for i in range (0, numIter):
		

    # your code here


    return w

def plot_fisher(X, y, filename): 
    w = np.array([0,0,0]) # just a placeholder
    # your code here 

    return w

X = np.array([[1, 2], [-3, 1], [3, 1]])
y = np.array([[1], [-1], [1]]])
filename = "testing.png"