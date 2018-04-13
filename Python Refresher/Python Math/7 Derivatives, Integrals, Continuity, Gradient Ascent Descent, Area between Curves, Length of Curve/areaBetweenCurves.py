#! areaBetweenCurves.py
# Finds the area between two curves using integration.

from sympy import Integral, solve, sympify;
from sympy.plotting import plot;

def plot(f1,f2,var):
    p = plot(f1,f2,legend=True,show=False,ylabel = "y",xlabel="x"); # We are using Sympy's plot module, not matplotlib's here.
    p[0].line_color = "b";
    p[1].line_color = "r";
    p.show();
'''
def find_limits(f1,f2,var):
    intersections = solve( (f1,f2),var);
    if(len(intersections)<2):
        print(intersections);
        return (None,None);
    print(intersections);
'''
    
def find_area(f1,f2,var,a,b):
    #a,b = find_limits(f1,f2,var);
    
    result = Integral( (f1-f2), (var,a,b) ).doit().evalf();
    print("Enclosed area is {0] units.".format(result));
        
    

if(__name__ == "__main__"):
    f1 = input("Enter the upper function: ");
    f2 = input("Enter the lower function: ");
    a = input("Enter first limit: ");
    b = input("Enter second limit: ");
    try:
        f1 = sympify(f1);
        f2 = sympify(f2);
        var = sympify(var);
        a = float(a);
        b = float(b);
    except SympifyError:
        print("Please enter valid functions.");
    else:
        find_area(f1,f2,var,a,b);
        
    
