#! barnsleyFern.py
# Draw a Barnsley Fern.
'''
The British mathematician Michael Barnsley described how to create fern-like
structures using repeated applications of a simple transformation on a point.

He proposed the following steps to create fern-like structures:

Start with the point (0,0).
Randomly select one of the following transformations with the assigned probability:

    Transformation 1 (0.85 prob):
    x_(n+1) = 0.85x_n + 0.04y_n
    y_(n+1) = -0.04y_n + 0.85y_n + 1.6
    (okay one day i am going to code the euler method for solving ODEs.)
    
    Transformation 2 (0.07 prob)
    x_(n+1) = 0.2x_n - 0.26y_n
    y_(n+1) = 0.23y_n + 0.22y_n + 1.6

    Transformation 3 (0.07 prob)
    x_(n+1) = -0.15x_n -0.28x_n
    y_(n+1) = 0.26y_n + 0.24y_n + 0.44

    Transformation 4 (0.01 prob) Was wondering where that 0.01 prob went to
    x_(n+1) = 0
    y_(n+1) = 0.16y_n

Each of these transformations is responsible for creating a part of the fern.
The first transformation selected with the highest probability (and hence max number of times)
creates the stem and the bottom fronds of the fern.

The second and third transformations create the bottom fronds on the left and right, respectively.

The fourth transformation creates the stem of the fern.

    
'''
import random;
import matplotlib.pyplot as plt;

def transformation_1(p):
    x = p[0];
    y = p[1];
    x1 = 0.85*x + 0.04*y;
    y1 = -0.04*x + 0.85*y + 1.6;
    return x1,y1;

def transformation_2(p):
    x = p[0];
    y = p[1];
    x1 = 0.2*x -0.26*y;
    y1 = 0.23*x + 0.22*y + 1.6;
    return x1,y1;

def transformation_3(p):
    x = p[0];
    y = p[1];
    x1 = -0.15*x + 0.28*y;
    y1 = 0.26*x + 0.24*y + 0.44;
    return x1,y1;

def transformation_4(p):
    x = p[0];
    y = p[1];
    x1 = 0;
    y1 = 0.16*y;
    return x1,y1;

# See if you can recall how this function works. It's pretty smart.
def get_index(probability):
    r = random.random();
    c_probability = 0;
    sum_probability = [];
    for p in probability:
        c_probability += p;
        sum_probability.append(c_probability);
    for index,sp in enumerate(sum_probability):
        if(r<=sp):
            return index;
    return len(probability)-1;

def transform(p):
    # List of transformation functions
    transformations = [ transformation_1,transformation_2,transformation_3,transformation_4];
    probability = [0.85,0.07,0.07,0.01];
    #Pick a random transformation function and call it
    tindex = get_index(probability);
    t = transformations[tindex];
    x,y = t(p);
    return x,y;

def draw_fern(n):
    # We start with (0,0)
    x = [0];
    y = [0];

    x1,y1 = 0,0;
    for i in range(n):
        x1,y1 = transform((x1,y1));
        x.append(x1);
        y.append(y1);

    return x,y;

if(__name__=="__main__"):
    n = int(input("Enter the number of points in the Fern: "));
    x,y = draw_fern(n);
    # Plot the points
    plt.plot(x,y,'o');
    plt.title("Fern with {0} points".format(n));
    plt.show();
    
