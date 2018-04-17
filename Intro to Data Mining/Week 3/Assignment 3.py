
# coding: utf-8

# ---
# 
# _You are currently looking at **version 1.1** of this notebook. To download notebooks and datafiles, as well as get help on Jupyter notebooks in the Coursera platform, visit the [Jupyter Notebook FAQ](https://www.coursera.org/learn/python-text-mining/resources/d9pwm) course resource._
# 
# ---

# # Assignment 3
# 
# In this assignment you will explore text message data and create models to predict if a message is spam or not. 

# In[87]:

import pandas as pd
import numpy as np

spam_data = pd.read_csv('spam.csv')

spam_data['target'] = np.where(spam_data['target']=='spam',1,0)
spam_data.head(10)


# In[88]:

from sklearn.model_selection import train_test_split


X_train, X_test, y_train, y_test = train_test_split(spam_data['text'], 
                                                    spam_data['target'], 
                                                    random_state=0)


# ### Question 1
# What percentage of the documents in `spam_data` are spam?
# 
# *This function should return a float, the percent value (i.e. $ratio * 100$).*

# In[5]:

def answer_one():
    
    spam = [w for w in spam_data['target'] if w == 1];
    
    return (len(spam)/len(spam_data['target']))*100;


# In[6]:

answer_one()


# ### Question 2
# 
# Fit the training data `X_train` using a Count Vectorizer with default parameters.
# 
# What is the longest token in the vocabulary?
# 
# *This function should return a string.*

# In[7]:

from sklearn.feature_extraction.text import CountVectorizer

def answer_two():

    # Fit the CountVectorizer to the training data
    vect = CountVectorizer().fit(X_train);
    
    
    return sorted(vect.get_feature_names(), key = lambda x : len(x))[-1];


# In[8]:

answer_two()


# ### Question 3
# 
# Fit and transform the training data `X_train` using a Count Vectorizer with default parameters.
# 
# Next, fit a fit a multinomial Naive Bayes classifier model with smoothing `alpha=0.1`. Find the area under the curve (AUC) score using the transformed test data.
# 
# *This function should return the AUC score as a float.*

# In[9]:

from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import roc_auc_score

def answer_three():
    # Fit the CountVectorizer to the training data
    vect = CountVectorizer().fit(X_train)
    # transform the documents in the training data to a document-term matrix
    X_train_vectorized = vect.transform(X_train)
    
    # Train the model
    model = MultinomialNB(alpha=0.1);
    model.fit(X_train_vectorized,y_train);
    # Predict the transformed test documents AUC = Area Under Curve Score
    predictions = model.predict(vect.transform(X_test))
    
    return roc_auc_score(y_test,predictions)


# In[10]:

answer_three()


# ### Question 4
# 
# Fit and transform the training data `X_train` using a Tfidf Vectorizer with default parameters.
# 
# What 20 features have the smallest tf-idf and what 20 have the largest tf-idf?
# 
# Put these features in a two series where each series is sorted by tf-idf value and then alphabetically by feature name. The index of the series should be the feature name, and the data should be the tf-idf.
# 
# The series of 20 features with smallest tf-idfs should be sorted smallest tfidf first, the list of 20 features with largest tf-idfs should be sorted largest first. 
# 
# *This function should return a tuple of two series
# `(smallest tf-idfs series, largest tf-idfs series)`.*

# In[11]:

from sklearn.feature_extraction.text import TfidfVectorizer

# I would need tf-idf values and the feature names.

def answer_four():
    vect = TfidfVectorizer().fit(X_train); # a Tfidf object. 
    X_train_vectorised = vect.transform(X_train); # a vectorised csr matrix
    feature_names = np.array(vect.get_feature_names())
    
    # A glance at how a csr matrix works.
    #X_train_vectorised is a csr sparse matrix and is accessed differently X_train_vectorised[0,7305]
    # X_trained_vectorised presents the data in this manner: (document number, feature_index)  feature_count
    # Extracting out of the csr matrix:
    # feature_index = X_train_vectorised[0,:].nonzero()[1] 
    # Here, we are extracting all data that's (0 , feature_index) feature_count
    # with non-zero feature-count, and extracting the feature_index.
        
        
    sorted_tfidf_index = X_train_vectorised.max(0) # Pay attention. This sums up the feature_count according to feature_index and sorts it from feature_index = 0 - 7353.
                                                   # Basically it becomes (feature_index) total_feature_count NEAT!
    sorted_tfidf_index = sorted_tfidf_index.toarray()[0]; # grab the array of tfidf values. [0] because toarray creates a list within the array, and [0] extracts that list.
    sorted_tfidf_index = sorted_tfidf_index.argsort(); # Returns a sorted list, but with their indices in the original array.
    
    # sorted_tfidf_index = X_train_vectorised.max(0).toarray()[0].argsort() <-- Doing it fast.
    
    smallest_tfidf_names = feature_names[sorted_tfidf_index[:20]];
    largest_tfidf_names = feature_names[sorted_tfidf_index[-20:]];
    
    smallest_tfidf_values = X_train_vectorised.max(0).toarray()[0][sorted_tfidf_index[:20]]
    largest_tfidf_values = X_train_vectorised.max(0).toarray()[0][sorted_tfidf_index[-20:]]
    
    smallest_tfidf_series = pd.Series(smallest_tfidf_values,index=smallest_tfidf_names)
    largest_tfidf_series = pd.Series(largest_tfidf_values,index=largest_tfidf_names)
    
    df_small = smallest_tfidf_series.reset_index(drop=False).sort_values([0,"index"],ascending = [True,True]);
    df_large = largest_tfidf_series.reset_index(drop=False).sort_values([0,"index"],ascending = [False,True]);
    
    
    
    return (pd.Series(list(df_small[0]),index=list(df_small["index"])) , pd.Series(list(df_large[0]),index=list(df_large["index"])) )


# In[12]:

answer_four()


# ### Question 5
# 
# Fit and transform the training data `X_train` using a Tfidf Vectorizer ignoring terms that have a document frequency strictly lower than **3**.
# 
# Then fit a multinomial Naive Bayes classifier model with smoothing `alpha=0.1` and compute the area under the curve (AUC) score using the transformed test data.
# 
# *This function should return the AUC score as a float.*

# In[13]:

from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import roc_auc_score

def answer_five():
    vect = TfidfVectorizer(min_df = 3).fit(X_train); 
    X_train_vectorised = vect.transform(X_train);
    
    model = MultinomialNB(alpha=0.1).fit(X_train_vectorised,y_train);
    predictions = model.predict(vect.transform(X_test))
    
    
    return roc_auc_score(y_test,predictions)


# In[14]:

answer_five()


# ### Question 6
# 
# What is the average length of documents (number of characters) for not spam and spam documents?
# 
# *This function should return a tuple (average length not spam, average length spam).*

# In[15]:

def answer_six():
    spam_data['length'] = [len(w) for w in spam_data["text"] ]
    spam_sentences_lengtharray = [];
    notspam_sentences_lengtharray = [];
    for i in range(len(spam_data['text'])):
        if spam_data['target'][i] == 0:
            notspam_sentences_lengtharray.append(spam_data['length'][i]);
        else:
            spam_sentences_lengtharray.append(spam_data['length'][i]);
    
            
   
    return (np.mean(notspam_sentences_lengtharray), np.mean(spam_sentences_lengtharray))


# In[16]:

answer_six()


# <br>
# <br>
# The following function has been provided to help you combine new features into the training data:

# In[17]:

def add_feature(X, feature_to_add):
    """
    Returns sparse feature matrix with added feature.
    feature_to_add can also be a list of features.
    """
    from scipy.sparse import csr_matrix, hstack
    return hstack([X, csr_matrix(feature_to_add).T], 'csr')


# ### Question 7
# 
# Fit and transform the training data X_train using a Tfidf Vectorizer ignoring terms that have a document frequency strictly lower than **5**.
# 
# Using this document-term matrix and an additional feature, **the length of document (number of characters)**, fit a Support Vector Classification model with regularization `C=10000`. Then compute the area under the curve (AUC) score using the transformed test data.
# 
# *This function should return the AUC score as a float.*

# In[18]:

from sklearn.svm import SVC
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics import roc_auc_score
    
def answer_seven():
    vect = TfidfVectorizer(min_df = 5).fit(X_train); 
    X_train_vectorised = vect.transform(X_train); # csr matrix
    X_train_vectorised = add_feature(X_train_vectorised,X_train.str.len()) # you can only use add_feature with a csr matrix
    X_test_vectorised = add_feature(vect.transform(X_test),X_test.str.len());
    
    clf = SVC(C=10000).fit(X_train_vectorised,y_train);
    
    #print(X_train.str.len())
    predictions = clf.predict(X_test_vectorised);
    
    return roc_auc_score(y_test,predictions);


# In[19]:

answer_seven()


# ### Question 8
# 
# What is the average number of digits per document for not spam and spam documents?
# 
# *This function should return a tuple (average # digits not spam, average # digits spam).*

# In[20]:

def answer_eight():
    spam_data['noofdigits'] = spam_data["text"].str.count(r'\d');
    # spam_Data['noofdigits'] = spam_data["text"].map( lambda x : len([s for s in x if s.isdigits()]))
    not_spam_digits = spam_data['noofdigits'][spam_data['target'] == 0]
    spam_digits = spam_data['noofdigits'][spam_data['target'] == 1]
    return ( np.mean(not_spam_digits),  np.mean(spam_digits) )


# In[21]:

answer_eight()


# ### Question 9
# 
# Fit and transform the training data `X_train` using a Tfidf Vectorizer ignoring terms that have a document frequency strictly lower than **5** and using **word n-grams from n=1 to n=3** (unigrams, bigrams, and trigrams).
# 
# Using this document-term matrix and the following additional features:
# * the length of document (number of characters)
# * **number of digits per document**
# 
# fit a Logistic Regression model with regularization `C=100`. Then compute the area under the curve (AUC) score using the transformed test data.
# 
# *This function should return the AUC score as a float.*

# In[26]:

from sklearn.linear_model import LogisticRegression
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics import roc_auc_score

def answer_nine():
    vect = TfidfVectorizer(min_df = 5, ngram_range = (1,3)).fit(X_train); 
    X_train_vectorised = vect.transform(X_train); 
    X_train_vectorised = add_feature(X_train_vectorised,X_train.str.len()); # Adding features
    X_train_vectorised = add_feature(X_train_vectorised,X_train.str.count(r'\d'));
    

    X_test_vectorised = vect.transform(X_test);
    X_test_vectorised = add_feature(X_test_vectorised,X_test.str.len());
    X_test_vectorised = add_feature(X_test_vectorised,X_test.str.count(r'\d'));

    
    clf = LogisticRegression(C=100).fit(X_train_vectorised,y_train)
    
    
    predictions = clf.predict(X_test_vectorised)
    
    return roc_auc_score(y_test,predictions)


# In[27]:

answer_nine()


# ### Question 10
# 
# What is the average number of non-word characters (anything other than a letter, digit or underscore) per document for not spam and spam documents?
# 
# *Hint: Use `\w` and `\W` character classes*
# 
# *This function should return a tuple (average # non-word characters not spam, average # non-word characters spam).*

# In[30]:

def answer_ten():
    # r'\w' -> Word character and r'\W' -> Non word character
    spam_data['nonwordchars'] = spam_data["text"].str.count(r'\W');
    
    not_spam_nonwordchars = spam_data['nonwordchars'][spam_data['target'] == 0]
    spam_nonwordchars = spam_data['nonwordchars'][spam_data['target'] == 1]
    
    return ( np.mean(not_spam_nonwordchars),  np.mean(spam_nonwordchars) )


# In[31]:

answer_ten()


# ### Question 11
# 
# Fit and transform the training data X_train using a Count Vectorizer ignoring terms that have a document frequency strictly lower than **5** and using **character n-grams from n=2 to n=5.**
# 
# To tell Count Vectorizer to use character n-grams pass in `analyzer='char_wb'` which creates character n-grams only from text inside word boundaries. This should make the model more robust to spelling mistakes.
# 
# Using this document-term matrix and the following additional features:
# * the length of document (number of characters)
# * number of digits per document
# * **number of non-word characters (anything other than a letter, digit or underscore.)**
# 
# fit a Logistic Regression model with regularization C=100. Then compute the area under the curve (AUC) score using the transformed test data.
# 
# Also **find the 10 smallest and 10 largest coefficients from the model** and return them along with the AUC score in a tuple.
# 
# The list of 10 smallest coefficients should be sorted smallest first, the list of 10 largest coefficients should be sorted largest first.
# 
# The three features that were added to the document term matrix should have the following names should they appear in the list of coefficients:
# ['length_of_doc', 'digit_count', 'non_word_char_count']
# 
# *This function should return a tuple `(AUC score as a float, smallest coefs list, largest coefs list)`.*

# In[94]:

from sklearn.linear_model import LogisticRegression
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics import roc_auc_score

def answer_eleven():
    
    
    
    vect = CountVectorizer(min_df = 5, ngram_range = (2,5), analyzer = 'char_wb').fit(X_train); # analyzer='char_wb' tells CountVectorizer to use character n-grams
                                                                                                # makes the model less prone to spelling mistakes
    X_train_vectorised = vect.transform(X_train); 
    X_train_vectorised = add_feature(X_train_vectorised,X_train.str.len()); # Adding features
    X_train_vectorised = add_feature(X_train_vectorised,X_train.str.count(r'\d'));
    X_train_vectorised = add_feature(X_train_vectorised,X_train.str.count(r'\W'));


    X_test_vectorised = vect.transform(X_test);
    X_test_vectorised = add_feature(X_test_vectorised,X_test.str.len());
    X_test_vectorised = add_feature(X_test_vectorised,X_test.str.count(r'\d'));
    X_test_vectorised = add_feature(X_test_vectorised,X_test.str.count(r'\W'));
    
    clf = LogisticRegression(C=100).fit(X_train_vectorised,y_train)
    
    predictions = clf.predict(X_test_vectorised)
    
    sorted_coef_index = clf.coef_[0].argsort()
    
    feature_names = np.array(vect.get_feature_names())
    feature_names = np.concatenate((feature_names, np.array(['length_of_doc', 'digit_count', 'non_word_char_count']))); # numpy arrays 
    
    # Well the way this works is when you added the features in you added the values in but not their names? so you still have to 
    # add their names to the feature_names list
    
    return ( roc_auc_score(y_test,predictions) , list(feature_names[sorted_coef_index[:10]] ) , list(feature_names[sorted_coef_index[:-11:-1]] )  ) # largest first hence the weird [:-11:-1]
    

    


# In[95]:

answer_eleven()


# In[ ]:



