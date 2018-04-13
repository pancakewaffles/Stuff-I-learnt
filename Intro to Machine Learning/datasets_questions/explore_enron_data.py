#!/usr/bin/python

""" 
    Starter code for exploring the Enron dataset (emails + finances);
    loads up the dataset (pickled dict of dicts).

    The dataset has the form:
    enron_data["LASTNAME FIRSTNAME MIDDLEINITIAL"] = { features_dict }

    {features_dict} is a dictionary of features associated with that person.
    You should explore features_dict as part of the mini-project,
    but here's an example to get you started:

    enron_data["SKILLING JEFFREY K"]["bonus"] = 5600000
    
"""

import pickle

enron_data = pickle.load(open("../final_project/final_project_dataset.pkl", "r"))

print "Total number of ppl: ", len(enron_data);

#totalPersons = len(enron_data);

print "Total number of features for each person: ", len(enron_data["SKILLING JEFFREY K"]);

count = 0;
for i in enron_data:
    if(enron_data[i]["poi"] == True):
        print "POI: ",i;
        count += 1;
        
totalPersons = count;  
print "Total number of Persons of Interests: ", count;

"""
count = 0;
for i in enron_data:
    if(enron_data[i]["salary"] != "NaN"):
        
        count += 1;

        
print "Total number of Persons with quantified salary: ", count;

count = 0;
for i in enron_data:
    if(enron_data[i]["email_address"] != "NaN"):
        
        count += 1;
        

        
print "Total number of Persons with known email addresses: ", count;
"""

count = 0;
for i in enron_data:
    if(enron_data[i]["total_payments"] == "NaN" and enron_data[i]["poi"] == True):
        count += 1;
        

       
print "Total number of Persons with NaN in Total Payments: ", count;


print "Percentage: ", float(count)/float(totalPersons);


def nameLookUp():
    name = raw_input("Who do you want to snoop on, you little up to no good? ");
    for i in enron_data:
        if(str(i) == name):
            print enron_data[name];
            featureLookUp(name);
            return;
        
    print "There is no one named ",name;
    return;

def featureLookUp(name):
     feature = raw_input("What do you want to snoop on from " + name + " ");
     for j in enron_data[name]:
        if(str(j) == feature):
            print "The feature you are looking for from ", name, " is ", enron_data[name][feature];
            return;
     featureLookUp(name);


T = 3;
while(T > 0):
    nameLookUp();
    T -= 1;
