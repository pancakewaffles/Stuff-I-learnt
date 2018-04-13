% AssessedTask_ipod_investment.m
% Plots how an investment portfolio of a value of the iPod retail
% price fares in three different investment vehicles, Apple, IBM and
% Microsoft, over a period of 10 years.

initial_investment = 399;

hold on

apple_date = dlmread('apple.csv',',', [1 0 143 0]);
apple_shareprice = dlmread('apple.csv',',', [1 1 143 1]);
apple_investmentvalue = apple_shareprice*initial_investment;
plot(apple_date,apple_investmentvalue,'r-');

ibm_date = dlmread('ibm.csv',',', [1 0 143 0]);
ibm_shareprice = dlmread('ibm.csv',',', [1 1 143 1]);
ibm_investmentvalue = ibm_shareprice*initial_investment;
plot(ibm_date,ibm_investmentvalue,'k-');

microsoft_date = dlmread('microsoft.csv',',', [1 0 143 0]);
microsoft_shareprice = dlmread('microsoft.csv',',', [1 1 143 1]);
microsoft_investmentvalue = microsoft_shareprice*initial_investment;
plot(microsoft_date,microsoft_investmentvalue,'b-');

legend('Apple','IBM','Microsoft','Location','northwest');
title('Investing in either Apple, IBM or Microsoft ','fontsize',11);
xlabel('Year','fontsize',11);
ylabel('Investment Value','fontsize',11);

set(gca,'fontsize',11);
set(gca,'TickLength',[0.02,0.0]);

hold off

format short;

apple_investmentvaluetoday = apple_investmentvalue(1)
ibm_investmentvaluetoday = ibm_investmentvalue(1)
microsoft_investmentvaluetoday = microsoft_investmentvalue(1)







