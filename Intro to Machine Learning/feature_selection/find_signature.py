#!/usr/bin/python

import pickle
import numpy
numpy.random.seed(42)


### The words (features) and authors (labels), already largely processed.
### These files should have been created from the previous (Lesson 10)
### mini-project.
words_file = "../text_learning/your_word_data.pkl" 
authors_file = "../text_learning/your_email_authors.pkl"
word_data = pickle.load( open(words_file, "r"))
authors = pickle.load( open(authors_file, "r") )



### test_size is the percentage of events assigned to the test set (the
### remainder go into training)
### feature matrices changed to dense representations for compatibility with
### classifier functions in versions 0.15.2 and earlier
from sklearn import cross_validation
features_train, features_test, labels_train, labels_test = cross_validation.train_test_split(word_data, authors, test_size=0.1, random_state=42)

print "Created training data and test data.";

from sklearn.feature_extraction.text import TfidfVectorizer
vectorizer = TfidfVectorizer(sublinear_tf=True, max_df=0.5,
                             stop_words='english')

features_train = vectorizer.fit_transform(features_train)  # fitted to the vectorizer. Vectorizer now has an internal word list (features list) which it uses to strip features_train to basics for easier processing.
features_test  = vectorizer.transform(features_test).toarray() # Vectorizer stripped features_test to basics for easier processing.

print "tfidf-vectorized the features (words) for better training."

### a classic way to overfit is to use a small number
### of data points and a large number of features;
### train on only 150 events to put ourselves in this regime
features_train = features_train[:150].toarray()
labels_train   = labels_train[:150]




### your code goes here
from sklearn import tree;
clf = tree.DecisionTreeClassifier();
print "Training on 150 data...";
clf.fit(features_train,labels_train);
print "Successfully trained on 150 data.";

print "Predicting...";
predictions = clf.predict(features_test);
print "Successfully predicted on test data.";

print "Accuracy: ", clf.score(features_test,labels_test);

# So after eliminating sshacklensf and cgermannsf, we can run featuresImportance again to find another high-importance word ("houectect"). (Sigh). But we can leave it at that. It doesn't seem indicative enough.

"""
# Issue 1: sshacklensf
print "Here's the issue. We trained on a very small dataset so we should have a very overfitted classifier. Yet the accuracy on the test data is surprisingly high (~0.947). Something's wrong!";

print "###########################################";
### Time to find out what's wrong!

featuresImportance = clf.feature_importances_; # returns an array of importance coefficients that tells us how much each feature is important.

print [i for i,x in enumerate(featuresImportance) if x > 0.2];
print [x for i,x in enumerate(featuresImportance) if x > 0.2];

# Okay, so the feature at index 33614 has an importance of 0.76471 which is ridiculously high. Let's find out what word it is.

print "And the word is : ",vectorizer.get_feature_names()[33614]; # sshacklensf
"""

"""
# Issue 2: Another outlier, cgermannsf
print "Hmmm... After removing sshacklensf the accuracy is still very high (~0.951). Maybe there's another outlier?"
print "###########################################";
### Time to find out what's wrong!

featuresImportance = clf.feature_importances_;
print [i for i,x in enumerate(featuresImportance) if x > 0.2];
print [x for i,x in enumerate(featuresImportance) if x > 0.2];

# Okay, so the feature at index 14343 has an importance of 0.66667 which is ridiculously high. Let's find out what word it is.
print "And the word is : ",vectorizer.get_feature_names()[14343]; # cgermannsf
"""



