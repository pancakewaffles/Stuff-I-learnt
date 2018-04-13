% LinearAlgebraandTaylor.m
% Matrices of symbols.

mymat = [sym('3*x-2'),sym('b'),sym('2*x') ; ...
 sym('5'), sym('x^2'),sym('a') ; ...
 sym('32*x'),sym('x'),sym('21')] % Isn't it tedious lol.

det(mymat)
eig(mymat)

% And many more possibilities. Series summations, calculation of limits,
% Taylor expansions, Fourier and Laplace transforms, etc.
taylor( sym('a/sqrt(1-x^2)'),sym('x')); % Syntax: taylor( f , var , what value of var to expand at)
pretty(ans)

% Collection of easy-plotting commands. ezplot, ezplot3, ezsurf , ezmesh

ezmesh('x*y*(x-1)') % No fuzz needed.
figure
% To change the x and y range...
ezmesh('x*y*(x-1)', [1,5,1,5])