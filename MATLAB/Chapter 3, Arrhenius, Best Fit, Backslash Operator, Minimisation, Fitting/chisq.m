function f = chisq(a,x,y,sigma)
% Computes the agreement between a set of data points and
% a computed straight line equation

chi = y - (a(1) + a(2)*x);
chi = chi./sigma;
f = sum(chi.^2);
