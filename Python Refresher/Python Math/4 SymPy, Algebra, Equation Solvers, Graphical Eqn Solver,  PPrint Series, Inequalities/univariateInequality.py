#! univariateInequality.py
# Solving single-variable inequalities

from sympy import Symbol,sympify,pprint,Poly,solve;
from sympy import solve_poly_inequality;
from sympy import solve_rational_inequalities;
from sympy import solve_univariate_inequality;

def isolve(inequality, inequality_type):
    x = Symbol("x");
    lhs = inequality.lhs;
    rel = inequality.rel_op;
    
    if(inequality_type == "polynomial"):
        p = Poly(lhs,x);
        pprint(solve_poly_inequality(p,rel));
        
    elif(inequality_type == "rational"):
        numer,denom = lhs.as_numer_denom();
        p1 = Poly(numer);
        p2 = Poly(denom);
        pprint( solve_rational_inequalities([[((p1,p2),rel)]]) );
    else:
        pprint( solve_univariate_inequality(inequality,x,relational=False) );
        



if(__name__=="__main__"):
    inequality = sympify(input("Enter an inequality you wish to solve: "));

    # Inequality has to be xyzw*something > 0 RHS must be zero.
    expression = inequality.lhs; 

    if(expression.is_polynomial()):
        isolve(inequality,"polynomial");
    elif(expression.is_rational_function()):
        isolve(inequality,"rational");
    else:
        isolve(inequality,"others");
        
