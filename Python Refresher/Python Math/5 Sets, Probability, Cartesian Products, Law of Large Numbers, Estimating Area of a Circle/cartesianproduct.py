#! cartesianproduct.py
# To truly illustrate the cartesian product, let's have an example.
'''
We have a simple pendulum and we want to find the time period T.
By simple physics we know that T = 2pi sqrt(L/g)

We want to find out the values for different L and different g.
Say we are conducting this experiment for different lengths at different locations.
So we create a cartesian product of length and g_values.
          i.e. For each L, we have different g.
E.g. L = { 15, 18 , 21 , 22.5, 25}
     g_values = {9.81, 9.78, 9.83}
'''
from sympy import FiniteSet,pi;

def time_period(length,g):
    T = 2*pi*(length/g)**0.5;
    return T;
if(__name__=="__main__"):
    L = FiniteSet(15,18,21,22.5,25);
    g_values = FiniteSet(9.81, 9.78, 9.83);
    print("{0:^15}{1:^15}{2:^15}".format("Length(cm)","Gravity(m/s^2)","Time Period(s)"));
    for elem in L*g_values:
        '''
        15 cm, 9.78
        15 cm, 9.81
        15 cm, 9.83
        
        18 cm, 9.78
        etc you get the idea
        '''
        l = elem[0];
        g = elem[1];
        t = time_period(l/100,g);

        print("{0:^15}{1:^15}{2:^15.3f}".format(float(l),float(g),float(t)));
        # 15 = 15 spaces wide
        # ^ = center it
        # .3f = to 3 d.p.
