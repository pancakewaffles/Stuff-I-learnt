% plotAtAContourLevel.m
% Sometimes in life we just want the plot at a particular contour level.
% aka plot me y against x, at the level of z = 0.7 (You should be able to
% understand this easily. Basically it's just an implicit 2D function of x and y, at z
% = 0.7.

t = linspace(-2,2,100);
[x,y] = meshgrid(t,t);
z = sin(x.^2 + 3*y.^3);

% How it actually looks like in 3D
surf(z);
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('How it looks like in 3D','fontsize',12);

figure
contour(x,y,z, [0.7,0.7], 'k-' , 'linewidth',2); % The syntax goes : contour(x,y,z, [ z_level , z_level ] )
% Some usual figure tuning
set(gca,'fontsize',16);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('How it looks like at z = 0.7','fontsize',12);