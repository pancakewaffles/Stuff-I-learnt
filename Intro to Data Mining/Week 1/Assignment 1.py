
# coding: utf-8

# ---
# 
# _You are currently looking at **version 1.1** of this notebook. To download notebooks and datafiles, as well as get help on Jupyter notebooks in the Coursera platform, visit the [Jupyter Notebook FAQ](https://www.coursera.org/learn/python-text-mining/resources/d9pwm) course resource._
# 
# ---

# # Assignment 1
# 
# In this assignment, you'll be working with messy medical data and using regex to extract relevant infromation from the data. 
# 
# Each line of the `dates.txt` file corresponds to a medical note. Each note has a date that needs to be extracted, but each date is encoded in one of many formats.
# 
# The goal of this assignment is to correctly identify all of the different date variants encoded in this dataset and to properly normalize and sort the dates. 
# 
# Here is a list of some of the variants you might encounter in this dataset:
# * 04/20/2009; 04/20/09; 4/20/09; 4/3/09
# * Mar-20-2009; Mar 20, 2009; March 20, 2009;  Mar. 20, 2009; Mar 20 2009;
# * 20 Mar 2009; 20 March 2009; 20 Mar. 2009; 20 March, 2009
# * Mar 20th, 2009; Mar 21st, 2009; Mar 22nd, 2009
# * Feb 2009; Sep 2009; Oct 2010
# * 6/2008; 12/2009
# * 2009; 2010
# 
# Once you have extracted these date patterns from the text, the next step is to sort them in ascending chronological order accoring to the following rules:
# * Assume all dates in xx/xx/xx format are mm/dd/yy
# * Assume all dates where year is encoded in only two digits are years from the 1900's (e.g. 1/5/89 is January 5th, 1989)
# * If the day is missing (e.g. 9/2009), assume it is the first day of the month (e.g. September 1, 2009).
# * If the month is missing (e.g. 2010), assume it is the first of January of that year (e.g. January 1, 2010).
# * Watch out for potential typos as this is a raw, real-life derived dataset.
# 
# With these rules in mind, find the correct date in each note and return a pandas Series in chronological order of the original Series' indices.
# 
# For example if the original series was this:
# 
#     0    1999
#     1    2010
#     2    1978
#     3    2015
#     4    1985
# 
# Your function should return this:
# 
#     0    2
#     1    4
#     2    0
#     3    1
#     4    3
# 
# Your score will be calculated using [Kendall's tau](https://en.wikipedia.org/wiki/Kendall_rank_correlation_coefficient), a correlation measure for ordinal data.
# 
# *This function should return a Series of length 500 and dtype int.*

# In[4]:

import pandas as pd
import re

def standardiseyear(year):
    if len(year) == 2:
        return "19{}".format(year)
    else:
        return year;

def standardisemonth(month):
    return {
        'Jan': "1",
        'Feb': "2",
        'Mar': "3",
        'Apr': "4",
        'May': "5",
        'Jun': "6",
        'Jul': "7",
        'Aug': "8",
        'Sep': "9",
        'Oct': "10",
        'Nov': "11",
        'Dec': "12",
    }[month[:3]]
    
def standardiseday(day):
    p = re.compile("\d{1,2}")
    return p.search(day).group(0)

def convert_month_to_int(month):
    return int(month);

doc = []
with open('dates.txt') as file:
    for line in file:
        doc.append(line)

df = pd.Series(doc)
df.head(10)
# Cleaning the data

first_extraction = df.str.extract("(?P<Month>\d{1,2})[-\/]+(?P<Day>\d{1,2})[-\/]+(?P<Year>(?:\d{4}|\d{2}))").dropna()
first_extraction["index"] = first_extraction.index
first_extraction["Year"] = first_extraction["Year"].map(standardiseyear)
first_extraction["Month"] = first_extraction["Month"].map(convert_month_to_int)

print(len(first_extraction))


second_extraction = df.str.extract("(?P<Month>(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[a-z]*)[-., ]+(?P<Day>\d{1,2}[a-z]*)[-., ]+(?P<Year>(?:\d{4}|\d{2}))").dropna()
second_extraction["Month"] = second_extraction["Month"].map(standardisemonth)
second_extraction["Day"] = second_extraction["Day"].map(standardiseday)
second_extraction["Year"] = second_extraction["Year"].map(standardiseyear)
second_extraction["index"] = second_extraction.index
print(len(second_extraction))


third_extraction = df.str.extract("(?P<Day>\d{1,2}[a-z]*)[-., ]+(?P<Month>(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[a-z]*)[-., ]+(?P<Year>(?:\d{4}|\d{2}))").dropna()
third_extraction["Month"] = third_extraction["Month"].map(standardisemonth)
third_extraction["Day"] = third_extraction["Day"].map(standardiseday)
third_extraction["Year"] = third_extraction["Year"].map(standardiseyear)
third_extraction["index"] = third_extraction.index
print(len(third_extraction))



#print(third_extraction.loc[126]) 126 is the label of the index


fourth_extraction = df.str.extract("(?P<Month>(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[a-z.]*)[-., ]+(?P<Year>(?:\d{4}|\d{2}))").dropna()
fourth_extraction["Month"] = fourth_extraction["Month"].map(standardisemonth)    
arrayofones = ["01" for i in range(max(fourth_extraction.index)+1)];
fourth_extraction["Day"] = pd.Series(arrayofones)
fourth_extraction["Year"] = fourth_extraction["Year"].map(standardiseyear)
fourth_extraction["index"] = fourth_extraction.index
for i in fourth_extraction.index:
    if i in third_extraction.index or i in second_extraction.index:
        fourth_extraction = fourth_extraction.drop(i);
print(len(fourth_extraction))

notfirst_extraction = df.str.extract("(?P<Month>\d{1,2})[-\/]+(?P<Year>(?:\d{4}|\d{2}))").dropna()
notfirst_extraction["index"] = notfirst_extraction.index
notfirst_extraction["Year"] = notfirst_extraction["Year"].map(standardiseyear)
notfirst_extraction["Month"] = notfirst_extraction["Month"].map(convert_month_to_int)
arrayofones = ["1" for i in range(max(notfirst_extraction.index)+1)];
notfirst_extraction["Day"] = pd.Series(arrayofones)
for i in notfirst_extraction.index:
    if i in first_extraction.index or i in second_extraction.index or i in third_extraction.index or i in fourth_extraction.index:
        notfirst_extraction = notfirst_extraction.drop(i);
print(len(notfirst_extraction))
        
fifth_extraction = df.str.extract("(?P<Year>(?:\d{4}))").dropna()    
fifth_extraction = fifth_extraction.to_frame()
fifth_extraction["Month"] = pd.Series(["1" for i in range(max(fifth_extraction.index)+1)]);
fifth_extraction["Day"] = pd.Series(["1" for i in range(max(fifth_extraction.index)+1)]);  
fifth_extraction["index"] = fifth_extraction.index
for i in fifth_extraction.index:
    if i in first_extraction.index or i in notfirst_extraction.index or i in second_extraction.index or i in third_extraction.index or i in fourth_extraction.index:
        fifth_extraction = fifth_extraction.drop(i)
print(len(fifth_extraction))

full = pd.concat([first_extraction ,second_extraction, third_extraction, fourth_extraction, fifth_extraction,notfirst_extraction])


full2 = full.copy();
full2 = full2.drop(["index"],axis=1)
full2 = pd.to_datetime(full2).to_frame(name = "date")
full2["index"] = full["index"]
full2 = full2.sort_values("date")

print(full2)







# In[3]:

def date_sorter():
    
    # Your code here
    
    return # Your answer here

