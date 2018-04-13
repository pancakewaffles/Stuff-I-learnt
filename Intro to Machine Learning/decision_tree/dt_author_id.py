#!/usr/bin/python

""" 
    This is the code to accompany the Lesson 3 (decision tree) mini-project.

    Use a Decision Tree to identify emails from the Enron corpus by author:    
    Sara has label 0
    Chris has label 1
"""
    
import sys
from time import time
sys.path.append("../tools/")
from email_preprocess import preprocess


### features_train and features_test are the features for the training
### and testing datasets, respectively
### labels_train and labels_test are the corresponding item labels
features_train, features_test, labels_train, labels_test = preprocess()




#########################################################
### your code goes here ###
from sklearn.tree import DecisionTreeClassifier;
classifier = DecisionTreeClassifier(min_samples_split = 40); #criterion = "entropy"

t0 = time();
classifier.fit(features_train,labels_train);
print "Time spent on training: ",round(time()-t0,5),"s";

t1 = time();
predictions = classifier.predict(features_test);
print "Time spent on predicting: ",round(time() - t1,5),"s";

from sklearn.metrics import accuracy_score as acc;
accuracy = acc(predictions,labels_test,True);
print accuracy;

print len(features_train[0]);


#########################################################


