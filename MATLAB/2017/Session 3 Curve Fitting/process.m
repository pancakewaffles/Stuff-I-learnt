data = dlmread('cambridge.dat','',4,0);
year = data(:,1);
month = data(:,2);
tMax = data(:,3);
tMin = data(:,4);
daysAF = data(:,5);
rainfall = data(:,6);
totalSunshineHours = data(:,7);

date = year + (month-1)/12; % Placing the time onto a year scale.
avgTemp = (tMin + tMax)/2;

%plot(date,avgTemp,'ko');
%xlim([1960 1964]); %Helps you see the curve.

% Let's fit it as such: a0 + a1(t-1961) + a2sin(2pi/period t + phi)
% Let's use fminsearch
guessCoeffs = [10,2,6,-pi/2];
bestFitCoefficients = fminsearch(@(coeffs) Cambridgesine(coeffs,date,avgTemp,1),guessCoeffs);

yfit = bestFitCoefficients(1) + bestFitCoefficients(2)*(date-1961) ...
    + bestFitCoefficients(3)*sin( (2*pi/1)*date + bestFitCoefficients(4));

lineData = bestFitCoefficients(1) + bestFitCoefficients(2)*(date-1961);
figure
plot(date,avgTemp,'ko','MarkerSize',1);
hold on
plot(date,yfit,'b-','LineWidth',1);
plot(date,lineData,'r-','LineWidth',1);
title("Using fminsearch and Cambridgesine, avgTemp increase over 50 years = " + bestFitCoefficients(2)*50,'fontsize',10);
hold off

% Using fittype and fit. Requires x to be the independent variable.
% Provides a better fit, since we did not assume tau to be 1.
ft = fittype('a0 + a1*(x-1961) + a2*sin( (2*pi/1)*x + phi )')
f = fit(date,avgTemp,ft)
xfiner = [1961:0.1:2010];
yfittypefit = feval(f,xfiner);
figure
plot(date,avgTemp,'ko','MarkerSize',1);
hold on
plot(xfiner,yfittypefit,'b');
fitcoefficients = coeffvalues(f);
title("Temperature against time. Avg Temp increase over 50 years = " + fitcoefficients(2)*50 +"°C");
legend("Data points","Best fit Sloping Sine");

print("myfigure.png","-dpng","-r600");

