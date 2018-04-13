#! gravitation.py
# Newton's Law of Gravitation.

import matplotlib.pyplot as plt;
import math;

def draw_graph(x,y):
    plt.plot(x,y,marker="o");
    plt.xlabel("Distance in meters");
    plt.ylabel("Gravitation force in Newtons.");
    plt.title("Gravitational force and distance.");
    plt.show();

def generate_F_r():
    r = range(100,1001,50);
    forceValues=[];

    G = 6.674*(10**-11);

    m1 = 0.5;
    m2 = 1.5;

    for dist in r:
        force = G*m1*m2/(dist**2);
        forceValues.append(force);
        

    draw_graph(r,forceValues);

if(__name__=="__main__"):
    generate_F_r();


    
