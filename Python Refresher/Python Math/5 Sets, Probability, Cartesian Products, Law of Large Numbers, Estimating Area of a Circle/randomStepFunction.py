#! randomStepFunction.py
# Hey doesn't the output look a little like the charts from the stock market?
# Oops. (Efficient Market Hypothesis)

'''
Example of selecting a transformation from two equally probably transformations.
The two functions are:
        (x,y) -->  (x+1,y-1)
        
        (x,y) -->  (x+1,y+1)
'''
import matplotlib.pyplot as plt;
import random;


def transformation_1(p):
    x = p[0];
    y = p[1];
    return x+1,y-1;

def transformation_2(p):
    x = p[0];
    y = p[1];
    return x+1,y+1;

def transform(p):
    # List of transformation functions
    transformations = [transformation_1,transformation_2];
    # Pick a random transformation function and call it
    t = random.choice(transformations);
    x,y = t(p);
    return x,y;

def build_trajectory(p,n):
    x = [p[0]];
    y = [p[1]];
    for i in range(n):
        p = transform(p);
        x.append(p[0]);
        y.append(p[1]);

    return x,y;

if(__name__=="__main__"):
    # Intial Point
    p = (1,1);
    n = int(input("Enter the number of iterations: "));
    x,y = build_trajectory(p,n);
    #Plot
    plt.plot(x,y);
    plt.xlabel("X");
    plt.ylabel("Y");
    plt.show();
