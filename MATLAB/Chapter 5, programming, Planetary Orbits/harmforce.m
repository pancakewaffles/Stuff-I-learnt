function [output] = harmforce(x1,x2,k)
% Harmonic force
% k : spring constant
output = -k*(x1-x2);