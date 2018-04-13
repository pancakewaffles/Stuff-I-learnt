function chisq = slopingsine(b,x,y)
% Computes the agreement between a set of data points and
% a computed straight line equation

chi = y - (b(1) + b(2)*x + b(3)*sin(x*pi*b(4)))
chisq = sum(chi.^2)
