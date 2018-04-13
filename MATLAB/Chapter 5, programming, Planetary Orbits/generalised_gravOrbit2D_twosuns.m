% generalised_gravOrbit2D_twosuns.m
% This is based off generalised_gravOrbit2D.m, and adds another sun into
% the simulation.
% Where changes are made, they are marked with '%CHANGE%'
% Again, the velocity Verlet integration is as follows:
%
% Algorithm
% x(t) = x(t-dt) + v(t-dt)dt + a(t-dt)/2 dt^2
% a(t) = F[x(t)]/m
% v(t) = v(t-dt) + [a(t) + a(t-dt)]/2 dt

dm = 2;                % Dimensions 
m = 1;                 % Mass of moving particle (planet) 
GMm = 10;              % Gravitation G*m (F = am*m/r)
dt = 0.05;             % time step
nt = 11511;            % number of time steps
ti = 0.;               % Initial time
tf = ti + (nt-1) * dt; % Final time

% Initial variables
x0 = [10; 10]; v0 = [0.4; -0.4]; 

% Set up arrays
t = linspace(ti,tf,nt);
x = zeros(dm,nt); v = zeros(dm,nt); a = zeros(dm,nt);

% Initiate the algorithm
x(:,1) = x0; v(:,1) = v0;
a(:,1) = gravforce(x(:,1) , [0;0] ,GMm )/m + ...  % CHANGE %
         gravforce(x(:,1) , [1;1] ,GMm )/m; % Second sun at coordinate (1,1).

for i = 2:nt
    x(:,i) = x(:,i-1) + v(:,i-1)*dt + a(:,i-1)/2 * dt^2;
    a(:,i) = gravforce( x(:,i),[0;0],GMm )/m + ... % CHANGE %
             gravforce( x(:,i),[1;1],GMm )/m;   % A second sun at coordinate (1,1).
    
    v(:,i) = v(:,i-1) + ( a(:,i) + a(:,i-1) )/2 * dt;
end

plot( x(1,:) , x(2,:) ); % Plot it.

hold on

sun = plot(0,0,'ro'); % Plot the sun first.
set(sun,'MarkerSize',10);
set(sun,'MarkerFaceColor','r');

% CHANGE %
% Second Sun
sun2 = plot(1,1,'ro'); 
set(sun2,'MarkerSize',10);
set(sun2,'MarkerFaceColor','r');

set(gca,'xlim',[-15 15])
set(gca,'ylim',[-15 15])
set(gca,'ticklength',[0.02,0.])
set(gca,'fontsize',16,'linewidth',1)
set(gca,'XTick',[-10:5:10])
set(gca,'YTick',[-10:5:10])

title('What a beautiful orbit','fontsize',12);

set(gcf,'PaperPosition',[1 1 13 12]);
print -deps2c generalised_gravOrbit2D_twosuns.eps

hold off