% AssessedTask2_CambridgetemperaturesandSlopingsine.m

data = dlmread('cambridge.dat','',4,0);

year = data(:,1);
month = data(:,2);
tMax = data(:,3);
tMin = data(:,4);
daysAF = data(:,5);
rainfall = data(:,6);
totalSunshineHours = data(:,7);

date = year + (month-1)/12; % Placing the time onto a year scale
tavg = (tMin + tMax)/2;

% It looks like a sloping snusoidal function with a period of one year.
% Let's fit it as such: a0 + a1(t-1961) + a2sin(2pi/period t + phi)
% And let's use fminsearch to fit it.
guessCoeffs = [10 ,2,6,-pi/2]
bestFitCoefficients = fminsearch(@(coeffs) Cambridgesine(coeffs,date,tavg,1), guessCoeffs)

yfit = bestFitCoefficients(1) + bestFitCoefficients(2)*(date-1961) ...
    + bestFitCoefficients(3)*sin( (2*pi/1)*date + bestFitCoefficients(4));

figure %1
plot(date,tavg,'ko','MarkerSize',1);
hold on
plot(date,yfit,'b-','LineWidth',1);
hold off
xlim([2000,2010]);
ylim([3,20]);
xlabel('Year','fontsize',16);
ylabel('Avg. Temperature','fontsize',16);
set(gca,'fontsize',16,'TickLength',[0.02,0]);

% bestFitCoefficients(2) is the gradient here, which you can multiply by 50
% to get the average temperature increase in 50 years.
avgTempincreaseoverfiftyyears = bestFitCoefficients(2)*50

% Monthly Rainfall data, in histogram
figure %2
binEdges = [0:10:170];
binCounts = histc(rainfall,binEdges);
b = bar(binEdges,binCounts,'histc'); % Drawing a histogram using the bar method allows for more control.
set(b,'FaceColor',[0 1 1]);
xlabel('Rainfall (mm)','fontsize',16);
ylabel('Number of times','fontsize',16);
set(gca,'ticklength',[0.02,0]);
set(gca,'YMinorTick','on');
title('The histc method','fontsize',16);

figure %3
hist(rainfall,[5:10:165]) % You could use histc, or this as well. [5:10;165] specifies where the bar centers are.
h = findobj(gca,'Type','patch');
set(h,'FaceColor',[1 1 0]);
xlabel('Rainfall (mm)','fontsize',16);
ylabel('Number of times','fontsize',16);
set(gca,'ticklength',[0.02,0]);
set(gca,'YMinorTick','on','fontsize',16);
title('The one-liner');

