
# coding: utf-8

# ---
# 
# _You are currently looking at **version 1.0** of this notebook. To download notebooks and datafiles, as well as get help on Jupyter notebooks in the Coursera platform, visit the [Jupyter Notebook FAQ](https://www.coursera.org/learn/python-text-mining/resources/d9pwm) course resource._
# 
# ---

# # Assignment 2 - Introduction to NLTK
# 
# In part 1 of this assignment you will use nltk to explore the Herman Melville novel Moby Dick. Then in part 2 you will create a spelling recommender function that uses nltk to find words similar to the misspelling. 

# ## Part 1 - Analyzing Moby Dick

# In[17]:

import nltk
import pandas as pd
import numpy as np
from nltk.book import *

# If you would like to work with the raw text you can use 'moby_raw'
with open('moby.txt', 'r') as f:
    moby_raw = f.read()
    
# If you would like to work with the novel in nltk.Text format you can use 'text1'
moby_tokens = nltk.word_tokenize(moby_raw)
text1 = nltk.Text(moby_tokens)


# ### Example 1
# 
# How many tokens (words and punctuation symbols) are in text1?
# 
# *This function should return an integer.*

# In[5]:

def example_one():
    
    return len(nltk.word_tokenize(moby_raw)) # or alternatively len(text1)

example_one()


# ### Example 2
# 
# How many unique tokens (unique words and punctuation) does text1 have?
# 
# *This function should return an integer.*

# In[4]:

def example_two():
    
    return len(set(nltk.word_tokenize(moby_raw))) # or alternatively len(set(text1))

example_two()


# ### Example 3
# 
# After lemmatizing the verbs, how many unique tokens does text1 have?
# 
# *This function should return an integer.*

# In[6]:

from nltk.stem import WordNetLemmatizer

def example_three():

    lemmatizer = WordNetLemmatizer()
    lemmatized = [lemmatizer.lemmatize(w,'v') for w in text1]

    return len(set(lemmatized))

example_three()


# ### Question 1
# 
# What is the lexical diversity of the given text input? (i.e. ratio of unique tokens to the total number of tokens)
# 
# *This function should return a float.*

# In[7]:

def answer_one():
    
    
    return example_two()/example_one();

answer_one()


# ### Question 2
# 
# What percentage of tokens is 'whale'or 'Whale'?
# 
# *This function should return a float.*

# In[85]:

def answer_two():
    
    
    
    
    return float(len([w for w in moby_tokens if w == "whale" or w == "Whale"])/len(moby_tokens))*100;
answer_two()


# ### Question 3
# 
# What are the 20 most frequently occurring (unique) tokens in the text? What is their frequency?
# 
# *This function should return a list of 20 tuples where each tuple is of the form `(token, frequency)`. The list should be sorted in descending order of frequency.*

# In[44]:

def answer_three():
    
    dist = FreqDist(text1);
    d = sorted(dist,key=dist.get,reverse=True);
    li = [];
    for i in range(20):
        li.append(  (d[i],dist[d[i]]) ) ;
    return li
          
answer_three()


# ### Question 4
# 
# What tokens have a length of greater than 5 and frequency of more than 150?
# 
# *This function should return a sorted list of the tokens that match the above constraints. To sort your list, use `sorted()`*

# In[48]:

def answer_four():
    dist = FreqDist(text1);
    li = [w for w in set(text1) if (len(w)>5) and (dist[w]>150)]
    return sorted(li)

answer_four()


# ### Question 5
# 
# Find the longest word in text1 and that word's length.
# 
# *This function should return a tuple `(longest_word, length)`.*

# In[49]:

def answer_five():
    
    length = 0;
    longest_word = "";
    for w in text1:
        if len(w) > length:
            longest_word = w;
            length = len(w);
                
    return (longest_word,length)

answer_five()


# ### Question 6
# 
# What unique words have a frequency of more than 2000? What is their frequency?
# 
# "Hint:  you may want to use `isalpha()` to check if the token is a word and not punctuation."
# 
# *This function should return a list of tuples of the form `(frequency, word)` sorted in descending order of frequency.*

# In[88]:

def answer_six():
   
    dist = FreqDist(text1);
    li = [w for w in sorted(dist,key=dist.get,reverse=True) if dist[w] > 2000 and w.isalpha()];
    tu = [];
    for w in li:
        tu.append( (dist[w],w) );
        
    return tu;
        
        

answer_six()


# ### Question 7
# 
# What is the average number of tokens per sentence?
# 
# *This function should return a float.*

# In[59]:

def answer_seven():
    
    sentences = nltk.sent_tokenize(moby_raw);
    nu = [];
    for sentence in sentences:
        nu.append(len(nltk.word_tokenize(sentence)));
    return np.mean(nu)

answer_seven()


# ### Question 8
# 
# What are the 5 most frequent parts of speech in this text? What is their frequency?
# 
# *This function should return a list of tuples of the form `(part_of_speech, frequency)` sorted in descending order of frequency.*

# In[82]:

def answer_eight():
    # Create a dictionary with pos, frequency
   
    d = {};
    li = [];
    for word, pos in nltk.pos_tag(moby_tokens):
        if pos not in d.keys():
            d[pos] = 1;
        else:
            d[pos] += 1;
    
   # sorting gives back a list of d's keys, sorted.
    for pos in sorted(d,key=d.get,reverse=True):
        li.append( (pos,d[pos]) );
    
    
    return li[:5]

answer_eight()


# ## Part 2 - Spelling Recommender
# 
# For this part of the assignment you will create three different spelling recommenders, that each take a list of misspelled words and recommends a correctly spelled word for every word in the list.
# 
# For every misspelled word, the recommender should find find the word in `correct_spellings` that has the shortest distance*, and starts with the same letter as the misspelled word, and return that word as a recommendation.
# 
# *Each of the three different recommenders will use a different distance measure (outlined below).
# 
# Each of the recommenders should provide recommendations for the three default words provided: `['cormulent', 'incendenece', 'validrate']`.

# In[69]:

from nltk.corpus import words

correct_spellings = words.words()


# ### Question 9
# 
# For this recommender, your function should provide recommendations for the three default words provided above using the following distance metric:
# 
# **[Jaccard distance](https://en.wikipedia.org/wiki/Jaccard_index) on the trigrams of the two words.**
# 
# *This function should return a list of length three:
# `['cormulent_reccomendation', 'incendenece_reccomendation', 'validrate_reccomendation']`.*

# In[79]:

def answer_nine(entries=['cormulent', 'incendenece', 'validrate']):
    c = [w for w in correct_spellings if w[0] == 'c'];
    # Use Jaccard Distance package from nltk, and use trigram method from nltk (use n-gram for generalisation)
    first = [(  nltk.jaccard_distance(set(nltk.ngrams(entries[0],n=3)), set(nltk.ngrams(w,n=3)))  ,w) for w in c];
    
    i = [w for w in correct_spellings if w[0] == 'i'];
    v = [w for w in correct_spellings if w[0] == 'v'];
    
    second = [(  nltk.jaccard_distance(set(nltk.ngrams(entries[1],n=3)), set(nltk.ngrams(w,n=3)))  ,w) for w in i];
    third = [(  nltk.jaccard_distance(set(nltk.ngrams(entries[2],n=3)), set(nltk.ngrams(w,n=3)))  ,w) for w in v];
    
    return [ sorted(first,key = lambda x: x[0])[0][1] , sorted(second,key = lambda x: x[0])[0][1] , sorted(third,key = lambda x: x[0])[0][1] ]
    
answer_nine() # It should be corpulent, incandescence and validate.


# ### Question 10
# 
# For this recommender, your function should provide recommendations for the three default words provided above using the following distance metric:
# 
# **[Jaccard distance](https://en.wikipedia.org/wiki/Jaccard_index) on the 4-grams of the two words.**
# 
# *This function should return a list of length three:
# `['cormulent_reccomendation', 'incendenece_reccomendation', 'validrate_reccomendation']`.*

# In[80]:

def answer_ten(entries=['cormulent', 'incendenece', 'validrate']):
    c = [w for w in correct_spellings if w[0] == 'c'];
    # Use Jaccard Distance package from nltk, and use trigram method from nltk (use n-gram for generalisation)
    first = [(  nltk.jaccard_distance(set(nltk.ngrams(entries[0],n=4)), set(nltk.ngrams(w,n=4)))  ,w) for w in c];
    
    i = [w for w in correct_spellings if w[0] == 'i'];
    v = [w for w in correct_spellings if w[0] == 'v'];
    
    second = [(  nltk.jaccard_distance(set(nltk.ngrams(entries[1],n=4)), set(nltk.ngrams(w,n=4)))  ,w) for w in i];
    third = [(  nltk.jaccard_distance(set(nltk.ngrams(entries[2],n=4)), set(nltk.ngrams(w,n=4)))  ,w) for w in v];
    
    return [ sorted(first,key = lambda x: x[0])[0][1] , sorted(second,key = lambda x: x[0])[0][1] , sorted(third,key = lambda x: x[0])[0][1] ]
    
answer_ten() # Actually worse.


# ### Question 11
# 
# For this recommender, your function should provide recommendations for the three default words provided above using the following distance metric:
# 
# **[Edit distance on the two words with transpositions.](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance)**
# 
# *This function should return a list of length three:
# `['cormulent_reccomendation', 'incendenece_reccomendation', 'validrate_reccomendation']`.*

# In[81]:

def answer_eleven(entries=['cormulent', 'incendenece', 'validrate']):
    '''
    In computational linguistics and computer science, 
    edit distance is a way of quantifying how dissimilar two strings (e.g., words) are to one another 
    by counting the minimum number of operations required to transform one string into the other.
    '''
    c = [w for w in correct_spellings if w[0] == 'c'];
    i = [w for w in correct_spellings if w[0] == 'i'];
    v = [w for w in correct_spellings if w[0] == 'v'];
    
    first = [ (nltk.edit_distance(entries[0],w, transpositions = True) , w) for w in c  ]
    second = [ (nltk.edit_distance(entries[1],w,transpositions = True) , w) for w in i  ]
    third = [ (nltk.edit_distance(entries[2],w, transpositions = True) , w) for w in v  ]
    return [sorted(first,key=lambda x : x[0])[0][1] , sorted(second,key=lambda x : x[0])[0][1] , sorted(third,key=lambda x : x[0])[0][1]    ]
    
answer_eleven() # Better but still can't get incandescence.


# In[ ]:



