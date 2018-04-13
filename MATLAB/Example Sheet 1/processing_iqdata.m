iqdata_col1 = dlmread('iqdata.csv',',', [1 0 2000 0]);
iqdata_col2 = dlmread('iqdata.csv',',', [1 1 2000 1]);
iqdata_col3 = dlmread('iqdata.csv',',', [1 2 2000 2]);

hold on
plot(iqdata_col1,iqdata_col2,'k-','LineWidth',1);
plot(iqdata_col1,iqdata_col3,'r-','LineWidth',1);

xlim([0,5]);
ylim([-3,3]);
xlabel('Q','fontsize',16);
ylabel('i(Q)','fontsize',16);
set(gca,'fontsize',16);
set(gca,'TickLength',[0.02,0.0]);
title('\pi,\Theta, adding Greek because it is cool','fontsize',16);
legend('Data','Model');

print  -depsc2 'canyouspotthelossless.eps';
print -dpng -r300 'canyouspotthelossless.png';
print -dpng 'canyouspotthelossless.jpg';

hold off




