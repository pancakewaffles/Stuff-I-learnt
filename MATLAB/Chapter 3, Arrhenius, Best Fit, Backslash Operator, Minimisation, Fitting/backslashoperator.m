% Program to compute a set of data with random errors which are then fitted
% by a polynomial taken to linear order using the backslash operator, and
% then plotted.

% The backslash operator* This is tricky. Read it twice, perhaps.
% Basically it does the same thing as the best fit file, with the backslash
% operator doing what the fminsearch function is doing.

% The basic idea is that you represent the set of simultaneous equations in
% matrix form, F = G mm A , where mm is matrix multiplication, and A is the
% coefficients in column matrix form.

% Our job is to find A. 
% A = inv(G) mm F <- Now you see this only works for square G.
% A =  G\F <- backslash operator. We "slash G off F's back."

% The above works great for square G. What about G that isn't square? Then
% you use the backslash operator. It automatically fits it for you.

x = [0:10];

y = 2 + 3*x;
% Y = G mm A, G = 1 x            and A = 2
%                 1 x                    3
%                 1 x (depending on how many terms are in Y; i.e. how many
%                 data points)
% If we can get it into this form, then we can easily find A = G\Y


y = y + randn(1,11);

g = [ones(size(y')) , x']; 
% Generates something like this:  1 x1
%                                 1 x2
%                                 1 x3

% Hey we are almost there! A = G\Y' -> Y has to be made into a column
% matrix; hence Y'.
a = g\y';
a = a'


xfit = [0:0.01:10];
yfit = polyval( [a(2),a(1)] ,xfit,1); % Your a = [ a0 , a1] so polyval thinks it's a0x + a1, whilst it should have been a0 + a1x. So you have to flip a.
errorbar(x,y,sigma,'ko','MarkerSize',6,'MarkerFaceColor','k');
hold on
plot(xfit,yfit,'k-')
xlabel('Uniform x variable','fontsize',16)
ylabel('Semi-random data','fontsize',16)
set(gca,'fontsize',16)
set(gca,'TickLength',[0.02,0.0])
xlim([0,10])
hold off;


