#!/usr/bin/python

import matplotlib.pyplot as plt
from prep_terrain_data import makeTerrainData
from class_vis import prettyPicture

features_train, labels_train, features_test, labels_test = makeTerrainData()


### the training data (features_train, labels_train) have both "fast" and "slow"
### points mixed together--separate them so we can give them different colors
### in the scatterplot and identify them visually
grade_fast = [features_train[ii][0] for ii in range(0, len(features_train)) if labels_train[ii]==0]
bumpy_fast = [features_train[ii][1] for ii in range(0, len(features_train)) if labels_train[ii]==0]
grade_slow = [features_train[ii][0] for ii in range(0, len(features_train)) if labels_train[ii]==1]
bumpy_slow = [features_train[ii][1] for ii in range(0, len(features_train)) if labels_train[ii]==1]


#### initial visualization
"""
plt.xlim(0.0, 1.0)
plt.ylim(0.0, 1.0)
plt.scatter(bumpy_fast, grade_fast, color = "b", label="fast")
plt.scatter(grade_slow, bumpy_slow, color = "r", label="slow")
plt.legend()
plt.xlabel("bumpiness")
plt.ylabel("grade")
plt.show()
"""
################################################################################


### your code here!  name your classifier object clf if you want the 
### visualization code (prettyPicture) to show you the decision boundary

def accuracyTest(predictions,actual):
    from sklearn.metrics import accuracy_score;
    acc = accuracy_score(predictions,actual,True);
    return acc;

### Let's do all three algorithms

#Algorithm 1 : k-Nearest Neighbour (affectionately known as kNN)
def kNearestNeighbour():
    print "Running kNearestNeighbour Algorithm";
    from sklearn.neighbors import KNeighborsClassifier;
    clf = KNeighborsClassifier(algorithm = "auto",n_neighbors = 10, weights = "distance");
    clf.fit(features_train,labels_train);
    predictions = clf.predict(features_test);
    return predictions,clf;

#Algorithm 2: adaBoost
def adaBoost():
    print "Running adaBoost Algorithm";
    from sklearn.ensemble import AdaBoostClassifier;
    clf = AdaBoostClassifier(n_estimators = 20,learning_rate = 1);
    clf.fit(features_train,labels_train);
    predictions = clf.predict(features_test);
    return predictions,clf;

#Algorithm 3: randomForest
def randomForest():
    print "Running randomForest Algorithm";
    from sklearn.ensemble import RandomForestClassifier;
    clf = RandomForestClassifier(n_estimators = 50,criterion = "entropy", min_samples_split = 10);
    clf.fit(features_train,labels_train);
    predictions = clf.predict(features_test);
    return predictions,clf;

response = int(raw_input("Which algorithm do you want to run?"));

if(response == 1):
    predictions,clf = kNearestNeighbour();
elif(response == 2):
    predictions,clf = adaBoost();
elif(response == 3):
    predictions,clf = randomForest();
else:
    print "Response is invalid.";
    



print "Accuracy: ", accuracyTest(predictions,labels_test)
try:
    prettyPicture(clf, features_test, labels_test)
except NameError:
    pass
