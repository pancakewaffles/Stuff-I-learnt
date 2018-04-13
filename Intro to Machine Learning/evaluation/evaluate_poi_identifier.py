#!/usr/bin/python


"""
    Starter code for the evaluation mini-project.
    Start by copying your trained/tested POI identifier from
    that which you built in the validation mini-project.

    This is the second step toward building your POI identifier!

    Start by loading/formatting the data...
"""

import pickle
import sys
sys.path.append("../tools/")
from feature_format import featureFormat, targetFeatureSplit

data_dict = pickle.load(open("../final_project/final_project_dataset.pkl", "r") )

### add more features to features_list!
features_list = ["poi", "salary"]

data = featureFormat(data_dict, features_list)
labels, features = targetFeatureSplit(data)

def countPOIinPrediction(x):
    count = 0;
    for person in x:
        if person == 1:
            count += 1;
    return count;

def countTruePositives(x,y):
    count = 0;
    for i in range(0,len(x)):
        if x[i] == 1. and y[i] == 1.:
            count += 1;
    return count;

### your code goes here 

from sklearn.model_selection import train_test_split;
# Remember, always features before labels, and train before a test
features_train , features_test, labels_train, labels_test = train_test_split(features,labels,test_size = 0.3, random_state = 42);

from sklearn.tree import DecisionTreeClassifier;
clf = DecisionTreeClassifier();
clf.fit(features_train,labels_train);

print clf.score(features_test,labels_test);

x = clf.predict(features_test);
print "I have predicted %d POIs in the test set." % (countPOIinPrediction(x));
print "There are a total number of %d persons in the test set." % (len(labels_test));
print "There are actually %d POIs in the test set." % (countPOIinPrediction(labels_test));

print "This is what I have predicted.\n";
print x ,"\n";

print "This is what the actual results are.\n";
print labels_test,"\n";

print "There are %d true positives." % countTruePositives(x,labels_test);

# LOL 0 true positives hahaha what a stupid machine.
# Let's try to improve it using Precision and Recall.

# Remember, Precision is: Out of your predictions, how many match the actual result?
#           Recall is:    Out of all the actual results, how many did you predict correctly?

from sklearn.metrics import precision_score, recall_score;
print "Precision is: ", precision_score(labels_test,x);
print "Recall is: ",recall_score(labels_test,x);

