% Cambridgesine.m
function output = Cambridgesine(coeffs,x,y,period)
% Computes the absolute difference between a set of data points and a
% sloping sine equation.
% Usage: coeffs = [ a0 , a1 , a2, phi];
chi = y - (coeffs(1) + coeffs(2)*(x-1961) + coeffs(3)*sin( (2*pi/period)*x + coeffs(4)));
chisq = sum(chi.^2);
output = chisq;
end