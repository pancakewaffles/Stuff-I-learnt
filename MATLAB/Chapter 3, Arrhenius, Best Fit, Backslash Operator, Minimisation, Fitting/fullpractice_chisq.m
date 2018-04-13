% fullpractice_chisq.m
function output = fullpractice_chisq(coeffs,x,y)

chi = y - (coeffs(1) + coeffs(2)*exp(-x/coeffs(3)));
output = sum(chi.^2);