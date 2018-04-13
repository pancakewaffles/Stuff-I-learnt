#! continuity.py
# Verify the continuity of a function at a point.
# How to do limits:
# Limit((St1_delta-St1)/delta_t, delta_t, 0).doit()

from sympy import Symbol, Limit,sympify;

def check_continuity(f,var,a):
    epsilon = 1e-4;
    step_size = 1e-6;
    f_a = f.subs({var:a}).evalf();
    l_positive = Limit(f,var, a + step_size).doit();
    l_negative = Limit(f,var, a - step_size).doit();

    if(abs(l_positive-l_negative) < epsilon and abs(l_positive-f_a)<epsilon):
        print("{0} is continuous at {1}".format(f,a));
    else:
        
        print("{0} is not continuous at {1}".format(f,a));
    
    


if(__name__ == "__main__"):
    
    f = input("Enter a function in one variable: ");
    var = input("Enter the variable: ");
    a = input("Enter the point to check the continuity at: ");
    try:
        f = sympify(f);
        var = sympify(var);
        a = float(a);
    except SympifyError:
        print("Please enter a valid function.");
    else:
        check_continuity(f,var,a);
    
