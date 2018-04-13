% visualise4D.m
% Nah jk. You can't do that, at least not now. What we can do, is to plot
% something called an isosurface.
% A straight plot of f versus x,y and z would require 4D visualisations.
% What we do, is we project this 4D onto a 3D plot. An isosurface for such
% a function is the surface made by the points in 3D space for which f
% acquires a certain value.
%                f(x,y,z) = a

% It is analogous to what we did for functions f(x,y) by plotting contours
% in 2D.

% Standard stuff, just defining the (x,y,z) mesh on which to evaluate
% f(x,y,z)
t = linspace(-2,2,50);
[x,y,z] = meshgrid(t,t,t);

r = sqrt(x.^2 +y.^2 + z.^2);
f = exp(-r); % Can you guess how it would look like? Don't let that sqrt() throw you off!

% the plot
figure
isosurface(x,y,z,f,0.2); % f = 0.2. This plots the isosurface for the 1s orbital of the hydrogen atom.

xlim([-2,2]);ylim([-2,2]);zlim([-2,2]);
set(gca,'fontsize',16,'linewidth',1);
xlabel('x');ylabel('y');zlabel('z');
set(gca,'XTick',[-1:1:2]);
set(gca,'YTick',[-2:1:2]);
set(gca,'XTick',[-1:1:2]);
axis equal % to get an undistorted figure
grid on

set(gcf,'PaperPosition',[1,1,13,12]);
%print -deps2c 'isosurf.eps'
