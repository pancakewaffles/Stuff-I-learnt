#! henonfunction.py
# Exploring Henon's Function
# P(x,y) -> Q( y+1-1.4x^2, 0.3x)
# Start with point (1,1)

import matplotlib.pyplot as plt;
from matplotlib import animation;

def henon_transform(p):
    x = p[0];
    y = p[1];
    return (y+1-1.4*x*x , 0.3*x);

def generate(n):
    x = [1];
    y = [1];

    x_n = 1;
    y_n = 1;
    
    for i in range(n):
        (x_n,y_n) = henon_transform( (x_n,y_n) );
        x.append(x_n);
        y.append(y_n);
    return (x,y);

def draw(x,y,n):
    plt.plot(x,y,"o");
    plt.title("Henon's Function for {0} points".format(n));
    plt.show();

def updater(i,x,y): # Remember, for animation the first argument is always the current frame number
    plt.plot(x[i],y[i],"o");
    
def animate(x,y,n): 
    fig = plt.gcf();

    anim = animation.FuncAnimation( fig,
                                    updater,
                                    fargs = (x,y),
                                    frames = len(x), interval = 1,
                                    repeat = False);
    plt.title("Animation of Henon's Function for {0} points".format(n));
    plt.show();

if(__name__=="__main__"):
    print("Visualising 20,000 iterations of Henon's Function.");
    print("Starting Coordinate (1,1)");
    n = 20000;
    x,y = generate(n);
    #draw(x,y,n);
    animate(x,y,n);

    
