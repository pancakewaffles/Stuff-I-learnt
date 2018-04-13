% Spline Interpolation - Joining ***ALL*** data points with a different polynomial
% between each interval such that the polynomials on either side of every
% point have the same 1) value, 2) first derivative, 3) second derivative.

% Usually cubic polynomials give the best results.

% Fitting a cubic spline to an exponential function computed a few points


x = [0:10];
y = exp(-x/4); % This is the function we want to fit a cubic spline to.
plot(x,y,'ko')

xx = [0:0.1:10];
yy = spline(x,y,xx);
hold on
plot(xx,yy,'k-')

% What we have done here is to create a function with a few points (x and
% y) and then create a new array of x values with much finer mesh, located
% in array xx.
% The spline command creates the new array of points yy using the finer
% mesh of xx.

% Just aesthetics
xlabel('Uniform x variable','fontsize',16);
ylabel('Exponential function','fontsize',16);
set(gca,'fontsize',16);
set(gca,'TickLength',[0.02,0.0]);
