% dummiesguidetoDifferentiationandIntegration.m
% Numerical Differentiation and Numerical Integration.

% Numerical Integration
% int( sin(x)/x  dx) from -2 to 3
% syntax: quad(@fun, a , b, tol) , tol is the tolerance controlling the
% accuracy of the integration. If absent, it uses a default value of 1e-6.

% You could define the function in a new .m file, as in below:
% fun1.m
% function y = fun1(x)
% y = sin(x)/x;
% end
% I = quad(@fun1,-2,3); --> Refers to the function by its handle @fun1

% But nah. I am too lazy to do that.
% This is called, the anonymous function handle.
F = @(x) sin(x) ./x; % whereby the definition of the function has happened interactively, and not through a .m file.
I = quad(F,-2,3)

% quadgk and quadl perform the same functions (with same inputs) but based
% on different (higher-order) algorithms, the Gauss-Kronrod and the Lobatto
% ones, respectively.

% Double integration
% A double integration is like integrating a function in a rectangular area
% defined by the corners (x1,y1) , (x2,y1), (x1,y2) and (x2,y2).
% dblquad(@fun,x1,x2,y1,y2,tol)

F = @(x,y) exp(-1* (x.^2+y.^2));
dblquad(F,0,2,0,2)

% Triple Integration
% A triple integration is like integrating a function in a box defined by
% the corners... sigh you get it don't you?
% triplequad(@fun,x1,x2,y1,y2,z1,z2,tol)

F = @(x,y,z) exp(-1* (x.^2+y.^2+z.^2));
triplequad(F,0,2,0,2,0,2)

% Something special: quad2d(@fun,x1,x2,y1,y2) This performs the integration
% when y1 and y2 are functions of x.

% Numerical Differentiation
% Using diff. Now the diff method, when provided with an array of N numbers
% gives another array back with N-1 numbers, which are the differences
% between consecutive numbers in the original array. 

diff([0,1,3,5,9])

% This can be used to do the lowest order calculation of a derivative.
% This approach is called the finite differences approach, because we use
% specify the differences.
h = 0.01;
x = [0:h:5];
f = sin(x);
dfdx = diff(f)/h;
plot(x(1:500)/pi,dfdx);
xlabel('x/\pi','fontsize',16);ylabel('dfdx','fontsize',16);
title('dfdx against x, does it not look like a cosine curve?','fontsize',16);
% The array contained in dfdx represents a good approx. to the derivative
% of the original function. Plot it and you should see a very good approx
% to cos(x). Note however, that because dfdx is one element shorter than x,
% you would have to account for it.
% plot( x(1:500) , dfdx);

% diff(f,n) extends the differentiation to the n-th order.

% gradient(f) and gradient(f,n). The clue is in the name.

% del2(f,n) does the corresponding n-order finite-differences approx to the
% well, you guessed it, Laplacian.





