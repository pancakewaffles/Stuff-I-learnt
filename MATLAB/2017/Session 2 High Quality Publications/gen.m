data = dlmread('shares.dat', ',' , 3,0);

date = data(:,1);
appleShares = data(:,2);
ibmShares = data(:,3);
microsoftShares = data(:,4);

hold on
plot(date,appleShares);
plot(date,ibmShares);
plot(date,microsoftShares);


xlim([2000 2015]);
xticks([2000 2003 2006 2009 2012 2015]);

grid on

xlabel('Year');
ylabel('Share Price');
title('Share prices of Apple, IBM, and Microsoft over time');

legend('Share price of Apple','Share price of IBM','Share price of Microsoft');
print('myfigure.png','-dpng','-r600');

hold off
