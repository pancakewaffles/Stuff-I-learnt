#! graphicalEquationSolver.py
# Solves two equations graphically.

from sympy import Symbol,expand,sympify,pprint,solve;
from sympy.core.sympify import SympifyError;
from sympy.plotting import plot;


def plot_graph(expr1,expr2):

    y = Symbol("y");
    x = Symbol("x");
    solutions = solve(expr1,"y"); # Easier if we don't use dict=True
    expr1_y = solutions[0];
    solutions = solve(expr2,"y");
    expr2_y = solutions[0];
    
    pprint("Plotting {0} and {1}".format(expr1_y,expr2_y));

    intersection = solve( (expr1,expr2) ,dict=True)[0]; # Returns a LIST of DICTIONARIES.
                                                        # To get the quantity we want we need to get the first dictionary in this list.
    pprint(intersection);
    
    p = plot(expr1_y,expr2_y,legend=True,show=False,ylabel = "y",xlabel="x"); # Don't show first, we need to make some edits.
    p[0].line_color = "b";
    p[1].line_color = "r"; # Changing the line colors.
    p.show(); # Now we show the plots.
    

if(__name__ == "__main__"):
    expr1 = input("Enter the first expression: ");
    expr2 = input("Enter the second expression: ");

    try:
        expr1 = sympify(expr1);
        expr2 = sympify(expr2);
    except SympifyError:
        print("Invalid input");
    else:
        plot_graph(expr1,expr2);
    
