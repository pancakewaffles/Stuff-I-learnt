#! lengthofCurve.py
# Find length of any arc AB for any arbitrary function f(x);
'''
The whole idea is a tiny little segment of arc length called ds.
ds = sqrt(dx^2 + dy^2)
And the whole idea is just to integrate ds.
'''

from sympy import Integral,Derivative,solve,S,pprint,sympify,sqrt;

def find_arc_length(f,arc_start,arc_end):
    d = Derivative(f,var).doit();
    
    I = Integral(sqrt(1+d**2)  , (var,arc_start,arc_end)).doit().evalf();

    print("Length is: {0}".format(I));
    return;

if(__name__=="__main__"):
    f = input("Enter the function: ");
    var = input("Enter the variable: ");
    arc_start = input("Enter the starting x coord: ");
    arc_end = input("Enter the final x coord: ");

    try:
        f = sympify(f);
        var = sympify(var);
        arc_start = float(arc_start);
        arc_end = float(arc_end);
    except SympifyError:
        print("Please enter valid functions.");
    else:
        find_arc_length(f,arc_start,arc_end);
        
