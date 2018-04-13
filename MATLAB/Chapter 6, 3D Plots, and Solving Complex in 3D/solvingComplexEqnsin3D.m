% solvingComplexEqnsin3D.m

% Analogously to the implicit y(x) functions we were plotting before (see
% plotAtAContourLevel.m for more details), we can use the contour command
% to plot the figure on the complex plane defined by solving a
% complex-number equation.

% Say we want to solve the equation
%       |z-1| = |z-i| + 1
% i.e. we want to find the set of complex numbers that fulfill it. How
% would we do it?
% It's simple using graphs. We plot f = |z-1| - |z-i|, and equate them to f = 1 (A plane in f that's equal to 1).
% It's exactly what it looks like had we cut the function f, at a plane f=1
%
%

z = x + i*y; % Complex Definition
f = abs(z-1) - abs(z-i);
contour(x,y,f,[1,1],'k-','linewidth',2);
xlabel('Re[z]');ylabel('Im[z]')
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-1:0.5:1]);
set(gca,'YTick',[0:0.5:2]);
xlim([-1,1]);ylim([0,2]);
title('What it looks like, at f = 1 (it is also the solution to |z-1| = |z-i| + 1)','fontsize',10);

