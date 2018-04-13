% generalised_gravOrbit3D_twosuns.m
% This is based off generalised_gravOrbit2D_twosuns.m, but generates in 3D.
% Where changes are made, they are marked with '% NEW %'
% Again, the velocity Verlet integration is as follows:
%
% Algorithm
% x(t) = x(t-dt) + v(t-dt)dt + a(t-dt)/2 dt^2
% a(t) = F[x(t)]/m
% v(t) = v(t-dt) + [a(t) + a(t-dt)]/2 dt

dm = 3;                % Dimensions % CHANGE % 
m = 1;                 % Mass of moving particle (planet) 
GMm = 10;              % Gravitation G*m (F = am*m/r)
dt = 0.05;             % time step
nt = 17001;            % number of time steps % NEW, Edited this value %
ti = 0.;               % Initial time
tf = ti + (nt-1) * dt; % Final time
xsun(:,1) = [0;0;0];   % Position of Sun 1 % NEW % 
xsun(:,2) = [1;-1;2];  % Position of Sun 2 % NEW %

% Initial variables
x0 = [10; 10; 0]; v0 = [0.4; -0.4; 0]; % NEW %

% Set up arrays
t = linspace(ti,tf,nt);
x = zeros(dm,nt); v = zeros(dm,nt); a = zeros(dm,nt);

% Initiate the algorithm
x(:,1) = x0; v(:,1) = v0;
a(:,1) = gravforce(x(:,1) , xsun(:,1) ,GMm )/m + ...  % NEW %
         gravforce(x(:,1) , xsun(:,2) ,GMm )/m; % Second sun at coordinate (1,-1,0).

for i = 2:nt
    x(:,i) = x(:,i-1) + v(:,i-1)*dt + a(:,i-1)/2 * dt^2;
    a(:,i) = gravforce( x(:,i), xsun(:,1),GMm )/m + ... % NEW %
             gravforce( x(:,i), xsun(:,2),GMm )/m;   % A second sun at coordinate (1,-1,0).
    
    v(:,i) = v(:,i-1) + ( a(:,i) + a(:,i-1) )/2 * dt;
end

plot3( x(1,:) , x(2,:), x(3,:) ); % Plot it.
grid on % NEW, we definitely need a grid for 3D.%

hold on

% NEW, Plots the suns%
sun = plot3(xsun(1,:),xsun(2,:),xsun(3,:),'ro' );
set(sun,'MarkerSize',8);
set(sun,'MarkerFaceColor','r');

set(gca,'xlim',[-15,15]);
set(gca,'ylim',[-15,15]);
set(gca, 'zlim',[-15,15]); % NEW %
xlabel('x');ylabel('y');zlabel('z');

title('What a beautiful 3D orbit','fontsize',12);

set(gcf,'PaperPosition',[1 1 13 12]);
print -deps2c generalised_gravOrbit3D_twosuns.eps

hold off