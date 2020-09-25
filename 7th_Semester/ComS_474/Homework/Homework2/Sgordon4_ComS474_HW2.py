import numpy as np 
import numpy.random
import matplotlib.pyplot as plt
import numpy.linalg

N = 10
X1 = numpy.vstack((numpy.random.normal(1, .1, N), 
				numpy.random.normal(2, 1, N)))
X2 = numpy.vstack((numpy.random.normal(1.1, 0.1, N), 
				numpy.random.normal(3, 1, N)))

X = numpy.hstack((X1, X2)) 
X = numpy.vstack((X, numpy.ones(2*N))) # augment

y = numpy.hstack((numpy.ones(N)*-1, 1*numpy.ones(N)))





def plot_mse(X, y, filename):
	W = np.linalg.inv(X.dot(X.T)).dot(X).dot(y)
	
	a,b,c=W[0], W[1], W[2]
	h = [0, -1*c/a]
	vd = [-1*c/b, 0]
	
	
	fig = plt.figure()
	for i in range(0, len(X[0])):
		if y[i] == -1:
			plt.plot(X[0][i], X[1][i], '.r')
		else:
			plt.plot(X[0][i], X[1][i], '.b')
	plt.plot(h, vd)
	fig.savefig(filename)
	plt.show()
	
	
	return W



def split_by_class(X, y):
	X1 = np.empty((0,3))
	X2 = np.empty((0,3))
	
	XT = X.T
	for i in range(len(XT)):
		if y[i] == -1:
			X1 = np.vstack((X1, XT[i]))
		else:
			X2 = np.vstack((X2, XT[i]))
	
	return X1, X2


def plot_fisher(X, y, filename):
	a, b = split_by_class(X, y)
	
	a = np.delete(a, 2, 1)	#Trim off bias column
	b = np.delete(b, 2, 1)	
	print(a)
	
	#a = np.random.multivariate_normal((1.5, 3), [[0.5, 0], [0, .05]], 10)
	#b = np.random.multivariate_normal((4, 1.5), [[0.5, 0], [0, .05]], 10)
	
	Sw = np.cov(a.T) + np.cov(b.T)
	inv_S = np.linalg.inv(Sw)
	mu_a, mu_b = a.mean(axis=0).reshape(-1,1), b.mean(axis=0).reshape(-1,1)
	print(mu_a)
	res = inv_S.dot(mu_a-mu_b)
	print(res.T)
	res = res*-1


	fig = plt.figure()
	plt.plot(a[:,0], a[:,1], 'b.', b[:,0], b[:,1], 'r.')
	plt.plot([res[0], -res[0]], [res[1], -res[1]]) # this is the solution
	plt.plot(mu_a[0], mu_a[1], 'cx')
	plt.plot(mu_b[0], mu_b[1], 'yx')
	plt.gca().axis('square')
	fig.savefig(filename)
	plt.show()
	
	return res


plot_mse(X, y, "mse.png")
plot_fisher(X, y, "fisher.png")
