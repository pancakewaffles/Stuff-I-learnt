#! gradientascent.py
'''
Use gradient ascent to find the angle at which the projectile
has maximum range for a fixed velocity, 25m/s.

Gradient Ascent method:
1. x_new = x_old + x_step * dy/dx
2. if abs(x_new - x_old) > epsilon, we set x_old = x_new and return to step 1.
3. x_new is an approx value of x for which y has the maximum value.

epsilon is a value we set to determine when we decide to stop the iteration of the algorithm.
'''
import math;
from sympy import Derivative,Symbol,sin,solve;

def grad_ascent(x0,f1x,x):
    # Check if f1x = 0 has a solution (f1x is the 1st derivative of f(x))
    if(not solve(f1x)):
        print("Cannot continue, solution for {0} = 0 does not exist.".format(f1x));
        return;
    epsilon = 1e-6; # must always be a very small positive value close to 0.
    step_size = 1e-4;
    x_old = x0;
    x_new = x_old +step_size * f1x.subs({x:x_old}).evalf();

    while(abs(x_old-x_new)>epsilon):
        x_old = x_new;
        x_new = x_old + step_size*f1x.subs({x:x_old}).evalf();

    return x_new;

def find_max_theta(R,theta):
    # Calculate the first derivative
    R1theta = Derivative(R,theta).doit();
    theta0 = 1e-3;
    theta_max = grad_ascent(theta0,R1theta,theta);
    return theta_max;

if(__name__=="__main__"):
    g = 9.81;
    # Assume initial velocity
    u = 25;

    theta = Symbol("theta");
    R = u**2*sin(2*theta)/g;

    theta_max = find_max_theta(R,theta);
    print("Theta : {0}".format(math.degrees(theta_max)));
    print("Maximum Range: {0}".format(R.subs({theta:theta_max})));

''' A generic program for Gradient Ascent
if(__name__ == "__main__"):
    f = input("Enter a function in one variable: ");
    var = input("Enter the variable to differentiate wrt: ");
    var0 = float(input("Enter the initial value of the variable: "));
    try:
        f = sympify(f);
    except SympifyError:
        print("Invalid function entered.");
    else:
        var = Symbol(var);
        d = Derivative(f,var).doit();
        var_max = grad_ascent(var0,d,var);
        if(var_max):
           print("{0}: {1}".format(var.name,var_max));
           print("Maximum value: {0}".format(f.subs({var:var_max})));
'''
    
