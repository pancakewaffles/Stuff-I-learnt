#! lawofLargeNumbers.py
# Verifying the law of large numbers hold for a dice roll.
'''
Law of Large Numbers says that the average value of results over multiple trials
approaches the expected value as the number of trials increases.

For a dice roll,
Expected Value = 3.5
'''

import random;
def roll():
    return random.randint(1,6);


if(__name__=="__main__"):
    e = 1*(1/6) + 2*(1/6) + 3*(1/6) + 4*(1/6) + 5*(1/6) + 6*(1/6);
    print("Expected value: {0}".format(e));
    trials = [100, 1000, 10000, 100000, 500000];
    for trial in trials:

        roll_values = [];
        
        while(len(roll_values) != trial):
            r = roll();
            roll_values.append(r);

        average = sum(roll_values)/trial;
        
        print("Trials: {0} Trial average {1}".format(trial,average));

    
    
    
