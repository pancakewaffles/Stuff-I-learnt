% dummiesguidetoSymbolicMaths.m

% Symbolic Algebra
% Essentially have MATLAB do the algebraic manipulations for us as we would
% do them on paper: with variables and coefficients, not numerically.

% Basically use MATLAB to do your homework for you.

% As helpful as it is, it's not actually a core component of MATLAB. It's
% an add-on.

% Symbolic Algebra uses a new class, called symbols.
myeq = sym('a*x^2 + b*x + c') % Note how to define a symbol.

% Or just this.
sym('3*x + 21') + sym('2*x-sin(pi*x)')

% Display it nicely!
rootsq = sym( '-(b + (b^2-4*a*c)^(1/2) ) / (2*a)' );
pretty(rootsq)
latex(rootsq) % Gives you the latex code which you can then copy and paste it in your document.
              % Latex, fyi, gives you some high-quality rendering in your
              % document.

% Solve it! Do my homework for me!
roots = solve(sym('a*x^2 + b*x + c = 0'));

% Solve takes x as the variable to solve for, unless you specify otherwise.
solve(myeq,'a');

% It can also solve simultaneous equations.
[X,Y] = solve('a*x - b*x*y + 3 = 0','3*x - y = 5');

% The subs command
a = 7;
subs(myeq); % Subs a = 7 into the equation.

% Simplify. I am Harry Potter and I say Simplify!
simplify( sym('exp( c*log(sqrt(a+b)) )') ); % See if you can work this out in your brain first!

% There are other commands. expand , factor , collect, numden, etc.
% The command simple, runs through all commands and gives you the simplest
% expression. The nice thing is that it shows you the process.
% simple( sym('exp( c*log(sqrt(a+b)) )') ) Somehow it didn't work on my
% copy of MATLAB.

% Differentiation and Integration
diff( sym('4*x - 5*x^3 + log(sin(3*x))') ); % using symbols with diff produces symbolic differentiation. It is the exact same function as diff from the numerical differentiation chapter.
diff('x^6',3); % Symbolic differentiation, to the third degree.
int('x^3*cos(x)'); % Symbolic integration. Different to quad, the numerical integrator.

int('exp(-a^2)','a') % Integrates wrt a. Fun fact: This is the Gaussian function, whose integral is the error function.


