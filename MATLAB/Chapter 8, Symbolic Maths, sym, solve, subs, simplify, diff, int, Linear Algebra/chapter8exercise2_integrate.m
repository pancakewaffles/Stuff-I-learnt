% chapter8exercise2_integrate.m

symbolic_integration = int(sym('x'),0,1)

f = @(x) x;
numerical_integration = quad(f,0,1)