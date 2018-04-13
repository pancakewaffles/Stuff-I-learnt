#! sierpinski.py
# Sierpinski Triangle using probabilities.
'''
This is a different method of drawing Sierpinski.
We originally drew it in Java using recursion, remember?
Here we employ a different method, that of probabilities.

Transformation 1:
    x_(n+1) = 0.5x_n
    y_(n+1) = 0.5y_n

Transformation 2:
    x_(n+1) = 0.5x_n + 0.5
    y_(n+1) = 0.5y_n + 0.5

Transformation 3:
    x_(n+1) = 0.5x_n + 1
    y_(n+1) = 0.5y_n

Each of the transformations has an equal probability of being selected - 1/3.
If you think about it, not that different from the recursion method, eh?

In this case, we draw a point at each coordinate.
'''
import random;
import matplotlib.pyplot as plt;

def transformation_1(p):
    x = p[0];
    y = p[1];
    x1 = 0.5*x;
    y1 = 0.5*y;
    return x1,y1;

def transformation_2(p):
    x = p[0];
    y = p[1];
    x1 = 0.5*x + 0.5;
    y1 = 0.5*y + 0.5;
    return x1,y1;

def transformation_3(p):
    x = p[0];
    y = p[1];
    x1 = 0.5*x + 1;
    y1 = 0.5*y;
    return x1,y1;

def transform(p):
    transformations_list = [ transformation_1,transformation_2,transformation_3];
    r = random.random();
    if(r<= 1/3):
        x1,y1 = transformation_1(p);
    elif(r<= 2/3):
        x1,y1 = transformation_2(p);
    elif(r<= 3/3):
        x1,y1 = transformation_3(p);
        
    return x1,y1;
    
def draw(n):
    x = [0]; # Start at point (0,0);
    y = [0];

    x_n = 0;
    y_n = 0;
    for i in range(n):
        x_n , y_n = transform( (x_n,y_n) );
        x.append(x_n);
        y.append(y_n);
    
    
    return x,y;
    
if(__name__ == "__main__"):
    n = int(input("Enter how many points you want to use: "));
    x,y = draw(n); # returns list of x and y coordinates
    plt.plot(x,y,"o");
    plt.title("Sierpinski Triangle with {0} points".format(n));
    plt.show();
    


    
    

