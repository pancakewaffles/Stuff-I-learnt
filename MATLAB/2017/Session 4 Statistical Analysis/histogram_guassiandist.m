% histogram_guassiandist.m
% Loads in data from cem.mat, calculates number of electrons, and plots a
% histogram.
% It then fits a Gaussian (Normal) Distribution to the histogram.

S = load('cem.mat');
pulses = S.pulses;

% Constants
% To calculate this I have used the following assumptions:
% Charge of an electron = 1.6 * 10^-19
% I = pulses * 10^-3 / 50 
% totalCharge = I * 10*10^-9
% numberElectrons = totalCharge/(1.6*10^-19)
K = (((10^-3)/50)*10*10^-9)/(1.6*10^-19);
numberElectrons = K*pulses;

% Statistical Analysis

m = mean(numberElectrons);
standardDeviation = std(numberElectrons)
hist(numberElectrons,[0.5*10^6:0.1*10^6:1.5*10^6]); % Looks a little like the normal distribution
[bincounts bincenters]= hist(numberElectrons,[0.5*10^6:0.1*10^6:1.5*10^6]); 
xlabel('Number of electrons per pulse');
ylabel('Frequency of occurrence');
title('Histogram of number of electrons per pulse, and Gaussian fit, std dev = ','fontsize',10);

smallest = min(numberElectrons);
largest = max(numberElectrons);
range = largest - smallest; % should be around 6x standardDeviation
range/standardDeviation; % around 4.9; close enough

hold on
f = fit(bincenters',bincounts','gauss1');
A = f.a1;
mu = f.b1
sigma = f.c1/sqrt(2) % Standard deviation and sigma correspond quite well with each other

xfit = [0.5*10^6:0.01*10^6:1.5*10^6];
yfit = feval(f,xfit);
plot(xfit,yfit,'r-');
legend('Histogram','Gaussian Fit');
hold off


