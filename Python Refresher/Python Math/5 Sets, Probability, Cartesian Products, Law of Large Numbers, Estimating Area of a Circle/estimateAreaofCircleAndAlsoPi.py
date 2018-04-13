#! estimateAreaofCircleAndAlsoPi.py
# Are you familiar with the dartboard estimation of the area of a circle?
'''
Consider a dartboard with a circle of radius r inscribed in a square with side 2r.
Now let's say you start throwing a alrge number of darts at it.
Some of these will hit the board within the circle (e.g. N) and others outside (e.g. M).
If we consider the fraction of darts that land inside the circle,
    f = N/ (N+M)
then the value of f*A where A is the area of the square would roughly be equal
to the area of the circle.

Square of side 2r

  A = 4r^2
  Actual area of circle = pi*r^2
  f = pi/4 = N /(N+M)

'''
import random;
from sympy import pi;

def throw(radius):
    return [random.uniform(-1*radius,1*radius) , random.uniform(-1*radius,1*radius)];

def withinCircle(coordinates,radius):
    if( (coordinates[0]**2 + coordinates[1]**2)**0.5 <= radius ):
        return True;
    return False;

radius = 2;
area_of_square = (2*radius)**2;
actual_circle_area = (pi*(radius**2)).evalf();

print("Radius: %d"%(radius));

darts_arr = [1000,100000,1000000];

for darts in darts_arr:
    M = 0;
    N = 0;
    for i in range(darts):
        dart_lands = throw(radius);
        if(withinCircle(dart_lands,radius)):
            N += 1; # Number of those which land in the circle
        else:
            M += 1; # Number of those which land outside the circle

    f = N/(N+M);
    estimated_circle_area = f*area_of_square;
    estimated_PI = f*4;
    

    print("Area: {0}, Estimated ({1} darts): {2}".format(actual_circle_area, darts, estimated_circle_area));
    print("PI: {0}, Estimated PI: {1}".format(pi.evalf(),estimated_PI));
    
    
    




