#!/usr/bin/python

import pickle
import sys
import matplotlib.pyplot
sys.path.append("../tools/")
from feature_format import featureFormat, targetFeatureSplit


### read in data dictionary, convert to numpy array
data_dict = pickle.load( open("../final_project/final_project_dataset.pkl", "r") )
features = ["salary", "bonus"]
data_dict.pop("TOTAL");
data = featureFormat(data_dict, features) #data is now a N x 2 array, where N is the number of data

for key in data_dict.keys():
    if((data_dict[key]["bonus"] != "NaN") and (data_dict[key]["salary"] != "NaN")):
        if((data_dict[key]["bonus"] > 5e+06) and (data_dict[key]["salary"] > 1e+06)):
            print key;
    


### your code below

for point in data:
    salary = point[0];
    bonus = point[1];
    matplotlib.pyplot.scatter(salary,bonus);

matplotlib.pyplot.xlabel("salary");
matplotlib.pyplot.ylabel("bonus");
matplotlib.pyplot.show();




