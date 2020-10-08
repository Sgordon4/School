# The function implementating SMO is taken from 
# https://github.com/LasseRegin/SVM-w-SMO/blob/master/SVM.py

import numpy
import sklearn, sklearn.datasets, sklearn.utils, sklearn.model_selection, sklearn.svm
	
def study_C_fix_split(C_range): 
	"""
	Examples 
	-----------
	>>> study_C_fix_split([1,10,100,1000])
	10
	>>> study_C_fix_split([10**p for p in range(-5,5)])
	10
	"""
	best_C = 0 # placeholder

	# load the data
	data = sklearn.datasets.load_breast_cancer()
	X, y = data["data"], data["target"]

	# prepare the training and testing data
	X_train, X_test, y_train, y_test = sklearn.model_selection.train_test_split(X, y, test_size=0.2, random_state=1)

	# Your code here
	best_score = -float("inf")
	
	for c in C_range:
		classifier = sklearn.svm.SVC(C=c, kernel='linear', random_state=1)
		classifier.fit(X_train, y_train)
		score = classifier.score(X_test, y_test)
		
		if(score > best_score):
			best_score = score
			best_C = c

	return best_C

def study_C_gridCV(C_range):
	"""

	C_range: 1-D list of floats or integers 

	Examples
	--------------

	>>> study_C_gridCV([1,2,3,4,5])
	2
	>>> study_C_gridCV(numpy.array([0.1, 1, 10]))
	10.0
	"""
	best_C = 0  #placeholder

	# load the data
	data = sklearn.datasets.load_breast_cancer()
	X, y = data["data"], data["target"]

	# shuffle the data
	X, y = sklearn.utils.shuffle(X, y, random_state=1)
		

	model = sklearn.svm.SVC(
			kernel='linear',
			random_state=1)

	paras = {'C':C_range}

	# your code here
	
	classifier = sklearn.model_selection.GridSearchCV(model, paras)
	classifier.fit(X, y)
	best_C = classifier.best_params_['C']
	

	return best_C

def study_C_and_sigma_gridCV(C_range, sigma_range):
	"""

	Examples 
	------------
	>>> study_C_and_sigma_gridCV([0.1, 0.5, 1, 3, 9, 100], numpy.array([0.1, 1, 10]))
	(0.1, 0.1)
	>>> study_C_and_sigma_gridCV([10**p for p in range(-5, 5, 1)], [10**p for p in range(-5, 5, 1)])
	(1000, 1e-05)
	"""
	best_C, best_sigma = 0, 0 # placeholders

	# load the data
	data = sklearn.datasets.load_breast_cancer()
	X, y = data["data"], data["target"]

	# shuffle the data
	X, y = sklearn.utils.shuffle(X, y, random_state=1)

	# your code here
	model = sklearn.svm.SVC(
			kernel='rbf',
			random_state=1)
			
	paras = {'C':C_range, 'gamma':sigma_range}
	
	classifier = sklearn.model_selection.GridSearchCV(model, paras)
	classifier.fit(X, y)
	best_C = classifier.best_params_['C']
	best_sigma = classifier.best_params_['gamma']

	return best_C, best_sigma


if __name__ == "__main__":
	import doctest
	# doctest.testmod()
	doctest.run_docstring_examples(study_C_and_sigma_gridCV, globals())

print("\nTesting1")
print(study_C_fix_split([1,10,100,1000]))
print(study_C_fix_split([10**p for p in range(-5,5)]))

print("\nTesting2")
print(study_C_gridCV([1,2,3,4,5]))
print(study_C_gridCV(numpy.array([0.1, 1, 10])))

print("\nTesting3")
print(study_C_and_sigma_gridCV([0.1, 0.5, 1, 3, 9, 100], numpy.array([0.1, 1, 10])))
print(study_C_and_sigma_gridCV([10**p for p in range(-5, 5, 1)], [10**p for p in range(-5, 5, 1)]))