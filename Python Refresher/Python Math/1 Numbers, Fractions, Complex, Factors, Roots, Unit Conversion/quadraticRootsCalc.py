#! quadraticRootCalc.py
#  Finds roots of quadratic equations, including even complex roots!

def roots(a,b,c): # a,b,c are the coefficients
    D = (b*b - 4*a*c)**0.5;
    x_1 = (-b+D)/(2*a);
    x_2 = (-b-D)/(2*a);

    print("x1: {0}".format(x_1));
    print("x2: {0}".format(x_2));
    #print("x1: %f"%(x_1)); Doesn't work for complex
    #print("x2: %f"%(x_2));

if(__name__=="__main__"):
    a = input("Enter a: ");
    b = input("Enter b: ");
    c = input("Enter c: ");
    roots(float(a),float(b),float(c));
