function [output] = gravforce(x1,x2,G)
% Computes teh gravitational force between a point particle
% of vector position x1, and another one at x2.
% G: G*M*m
x = x1 - x2;
distance = sqrt(sum(x.^2));
output = -G * x / (distance^3);