#! printseries.py
'''
Prints the series:
x + x**2 + x**3 + x**4 + ... x**n
    _      _      _          _
    2      3      4          n
Okay you know what, I am going to print the exponential Maclaurin series instead because
it is so similar to the series above.

e^x = sum x^n/n!

The method below uses a complicated way to calculate the series.
We could simply use sympy's summation method.
s = summation(x**n/n,(n,0,5));
s.subs({x:1.2}); <-- Gets the value for x = 1.2
'''
from sympy import Symbol,pprint,init_printing;
from math import factorial;
def print_series(n,x_value):
    # Initialise printing system with reverse order
    init_printing(order = "rev-lex");
    x = Symbol("x");
    series = 1;
    for i in range(1,n+1):
        series = series + x**i/factorial(i);
    
    pprint(series);

    # Evaluate the series at x_value
    series_value = series.subs({x:x_value});
    
    # Basically e^x_value. The larger the n, the higher the accuracy.
    print("\nValue of the series at {0}: {1}".format(x_value,series_value));
    

if(__name__=="__main__"):
    n = input("Enter the maximum power you want the series to end at: ");
    x_value = input("Enter the value of x at which you want to evaluate the series: ");
    print_series(int(n),float(x_value));
    
