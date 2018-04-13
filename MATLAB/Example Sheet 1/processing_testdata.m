col1 = dlmread('test.csv',',',[3 0 12 0]);
col2 = dlmread('test.csv',',',[3 1 12 1]);
col3 = dlmread('test.csv',',',[3 2 12 2]);

% Turns out, col1 = x, col2 = y, col3 = error in y.
hold on
plot(col1,col2,'k-');
errorbar(col2,col3,'MarkerSize',6,'Marker','o');


xlabel('col1','fontsize',14);
ylabel('col2','fontsize',14);
set(gca,'fontsize',14);
set(gca,'TickLength',[0.02,0.0]);
title('col1 against col2 with errorbars','fontsize',16);


