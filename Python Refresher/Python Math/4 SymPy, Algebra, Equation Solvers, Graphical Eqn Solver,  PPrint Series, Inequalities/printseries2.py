#! printseries2.py
# Generalisation of printseries.py
# Capable of finding the sum of an arbitrary series.

from sympy import Symbol,pprint,summation,sympify;

def find_sum(summand,number_of_terms):
    n = Symbol("n");
    s = summation(summand, (n,1,number_of_terms));
    pprint(s);

if(__name__ == "__main__"):
    summand = sympify(input("Enter the nth term: "));
    number_of_terms = int(input("Enter the number of terms: "));
    find_sum(summand,number_of_terms);

    
    
