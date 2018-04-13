% myfirst3Dplots.m
% Introduces 3D plotting. I am assuming you are familiar with multivariable
% functions ( f(x,y) = z , etc)

% There are three methods to plot 3D graphs in Matlab. 
% mesh, surf and contour
% mesh and surf plot surface representations
% contour plots, well, contours.

% Surfaces

t = linspace(-2,2,100); % You should be familiar with this function.
[x,y] = meshgrid(t,t); % ** This is quite important, because you want x, y and z to be all matrices.
z = cos(x.^2 + y.^2) .* exp(-0.6*(x.^2+y.^2));

figure %1
mesh(x,y,z) % mesh(z) works too. It plots a surface that looks like that of a water being splashed.
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('My first 3D surface plot using mesh','fontsize',12);

figure %2
surf(x,y,z) % or surf(z) works too.
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('My first 3D surface plot using surf','fontsize',12);

% Contours
z = cos(pi*x) + cos(pi*y);
figure %3
contour(x,y,z) % or contour(z) works too.
%contourf(x,y,z)  Uglier contour.
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('My first contour plot','fontsize',12);


figure %4
[Contour_matrix,handle] = contour(x,y,z,[-1.6:0.4:1.6],'linewidth',2); % Plot contours of specific height.
clabel(Contour_matrix,handle); % Labels the contour plots.
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('My labelled contour plot','fontsize',12);

figure %5
contour3(x,y,z,40) % What the crap? It looks like a slinky.
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('My slinky contour plot','fontsize',12);

% Mixing them up!
% Surf + Contour
figure %6
surfc(z) % surfc(x,y,z) works too!
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('My surf+contour plot using surfc','fontsize',12);

% Mesh + Contour
figure %7
meshc(z) % meshc(x,y,z) works too!
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:1:1]);
set(gca,'YTick',[-1:1:1]);
set(gca,'ZTick',[0:0.5:1]);
title('My mesh+contour plot using meshc','fontsize',12);



