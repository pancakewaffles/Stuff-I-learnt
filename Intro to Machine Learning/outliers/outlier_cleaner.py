#!/usr/bin/python


def outlierCleaner(predictions, ages, net_worths):
    """
        Clean away the 10% of points that have the largest
        residual errors (difference between the prediction
        and the actual net worth).

        Return a list of tuples named cleaned_data where 
        each tuple is of the form (age, net_worth, error).
    """
    
    cleaned_data = []
    REMOVE_PERCENTAGE = 0.1;
    ### your code goes here
    numberofRemovals = int(len(ages) * REMOVE_PERCENTAGE);

    cleaned_data = tuplesMaker(predictions,ages,net_worths);
    
    cleaned_data.sort(key = lambda x: x[2]);

    for i in range(numberofRemovals):
        cleaned_data.pop();

    
    return cleaned_data;

def tuplesMaker(predictions,ages,net_worths):
    lst = [];
    for i in range(len(ages)):
        lst.append((ages[i],net_worths[i],abs(net_worths[i] - predictions[i])));
    return lst;


