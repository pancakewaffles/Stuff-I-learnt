% The three methods to fit data points are 1) Polyfit, 2) Backslash
% operator, 3) Minimisation

% The data to fit; edit to play with it
x = [0:1:10];
y = 1 + 3*exp(-x/4) + randn(1,11)/10;
plot(x,y,'ko','MarkerFaceColor','k');
hold on

% 1) Polyfit * Turns out a quartic function fits best.
coeffs = polyfit(x,y,4);
yy= polyval(coeffs,x);
plot(x,yy,'b-','LineWidth',1);
coeffs

% 2) The backslash operator method
% y = 1 + 3*exp(-x/4) + random
E = [ones(size(x')),exp(-x'/4)];
backslash_coeffs = E\y';
backslash_y = E*backslash_coeffs;
plot(x,backslash_y','r-','LineWidth',1)
backslash_coeffs

% 3) Minimisation
guess_coeffs = [0.9,2,3];
minimum_coeffs = fminsearch(@(coeffs) fullpractice_chisq(coeffs,x,y), guess_coeffs) % Yes you find your minimum coefficients starting with a set of guess coefficients.
minimum_chisq = fullpractice_chisq(minimum_coeffs,x,y) % a minimum chisq of 0 is best.
yfit = minimum_coeffs(1) + minimum_coeffs(2)*exp(-x/minimum_coeffs(3));
plot(x,yfit,'k-','LineWidth',1)

% *** Notice the difference between the backslash operator and minimisation
% methods. backslash_coeffs only contains two coefficients because it
% can only take in a linear problem and cannot evaluate the coefficient in
% the exponential function (the 4 in -x/4).

% Axes and plot properties
xlim([0,10]);
ylim([0,5]);
xlabel('x variable','fontsize',16);
ylabel('The y function','fontsize',16);
set(gca,'fontsize',16);
set(gca,'TickLength',[0.02,0.0]);


hold off;
% The Spline function
figure;
xlim([0,1]);
ylim([-1,1]);
xlabel('x variable','fontsize',16);
ylabel('The sine function','fontsize',16);
set(gca,'fontsize',16);
set(gca,'TickLength',[0.02,0.0]);

x2 = [0:0.1:1];
y2 = sin(pi*x/3.5);
plot(x2,y2,'ko');
hold on
x2_splinemesh = [0:0.01:1];
y2_spline = spline(x2,y2,x2_splinemesh);
plot(x2_splinemesh,y2_spline,'r-');
hold off














