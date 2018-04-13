#!/usr/bin/python

""" 
    This is the code to accompany the Lesson 2 (SVM) mini-project.

    Use a SVM to identify emails from the Enron corpus by their authors:    
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
from sklearn import svm;
classifier = svm.SVC(C = 10000.0, kernel = "rbf"); #kernel = linear

# Improve speed by reducing training dataset
#features_train = features_train[:len(features_train)/100];
#labels_train = labels_train[:len(labels_train)/100];

t0 = time();
classifier.fit(features_train,labels_train);
print "Time taken for training: ",round(time()-t0,5),"s";

t1 = time();
predictions = classifier.predict(features_test);
print "Time taken for predicting: ",round(time()-t1,5),"s";

from sklearn.metrics import accuracy_score;
accuracy = accuracy_score(predictions,labels_test,True);
print accuracy;

chrisCount = 0;
for i in range(len(predictions)):
    if(predictions[i] == 1):
        chrisCount += 1;

print chrisCount , len(predictions) - chrisCount;
#Print out certain predictions
#print predictions[10], predictions[26], predictions[50];

#########################################################


