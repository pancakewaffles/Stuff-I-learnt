% analyse_cambridge_temperature.m
% Aka proving global warming

cambridge = dlmread('cambridge.dat','',4,0);
year = cambridge(:,1);
month = cambridge(:,2);
tmax = cambridge(:,3);
tmin = cambridge(:,4);
rain = cambridge(:,6);
sun = cambridge(:,7);

% The correlation of Min Temp and Max Temp
figure;
plot(tmin,tmax,'ko','MarkerSize',1);
xlabel('Min Temp');
ylabel('Max Temp');
coeffs = polyfit(tmin,tmax,1);
linevalues = polyval(coeffs,tmin);
hold on
plot(tmin,linevalues,'r-');
hold off
covarianceOfTminandTmax = cov(tmin,tmax)
correlationCoeff = corrcoef(tmin,tmax)

% 

tave = (tmax+tmin)/2;
date = year + (month-1)/12;
t = annual(tave);
tannual = t(:,1)
stdannual = t(:,2);
y = date(12:end);

% Deduce the best-fit straight lines
b1 = polyfit(y,tannual,1)
b2 = polyfit(y,stdannual,1)
tfit = polyval(b1,y);
sfit = polyval(b2,y);

figure;
plot(y,tannual,'k-')
hold on
plot(y,stdannual,'r-')
plot(y,tfit,'k-')
plot(y,sfit,'r-')
set(gca,'fontsize',16,'ticklength',[0.02,0]);
xlabel('Year','fontsize',16);
ylabel('Temperature (C)','fontsize',16);

% Let's prove global warming.
gradient = b1(1) % The coefficient of x; the gradient of our best fit line.
temperatureRise = b1(1)*50 % gradient times time (50 years) ans ~ 1.5. Wow. Over 50 years the temperature has risen by 1.5 degrees.
         % GLOBAL WARMING PROVEN! Well, given that there are fears of
         % another 2 degrees increase in the next few years, this result is
         % quite significant.
isthisagoodfit = std(tfit - tannual) % How volatile is the difference from the data points to the best fit line? Is it a good fit?

% There is a common supposition that rises in temperature will give greater
% swings in the climate. For the Cambridge data, we can check this by
% looking for correlations between the temperature and the fluctuations.

doeshigherTempsleadtohigherFluctuations = corrcoef(tannual,stdannual)


         