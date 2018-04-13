% generalised_gravOrbit2D_Energy.m
% This is based off generalised_gravOrbit2D.m but calculates the kinetic,
% potential and total energies of the system and plots them versus time.
% We will use the velocity Verlet algorithm here.

% Algorithm
% x(t) = x(t-dt) + v(t-dt)dt + a(t-dt)/2 dt^2
% a(t) = F[x(t)]/m
% v(t) = v(t-dt) + [a(t) + a(t-dt)]/2 dt

% Establish parameters (so they can be easily changed)
dm = 2;                % Dimensions !*NEW
m = 1;                 % Mass of moving particle (planet) !*NEW
GMm = 10;              % Gravitation G*m (F = am*m/r)
dt = 0.01;             % time step
nt = 30001;            % number of time steps
ti = 0.;               % Initial time
tf = ti + (nt-1) * dt; % Final time

% Initial conditions (the dimensions must match dm)
x0 = [10; 10]; v0 = [0.3; -0.3]; % If you haven't noticed, we are introducing the position 2D vector as a vector,
                                 % instead of keeping track of x and y
                                 % components explicitly.

% Set up arrays
t = linspace(ti,tf,nt);
x = zeros(dm,nt); v = zeros(dm,nt); a = zeros(dm,nt);


% Initiate the algorithm
x(:,1) = x0; v(:,1) = v0;
a(:,1) = gravforce(x(:,1) , [0;0] ,GMm )/m;% !*NEW
         

for i = 2:nt
    x(:,i) = x(:,i-1) + v(:,i-1)*dt + a(:,i-1)/2 * dt^2;
    a(:,i) = gravforce( x(:,i),[0;0],GMm )/m;  % To change to harmonic force (harmforce), it's as simple as modifying this.
             
    
    v(:,i) = v(:,i-1) + ( a(:,i) + a(:,i-1) )/2 * dt;
end

figure
plot( x(1,:) , x(2,:) ); % Plot it.

hold on 

sun = plot(0,0,'ro'); % Plot the sun first.
set(sun,'MarkerSize',10);
set(sun,'MarkerFaceColor','r');

xlabel('x','fontsize',16);ylabel('y','fontsize',16);
set(gca,'ticklength',[0.02,0])
set(gca,'fontsize',16,'linewidth',1)
set(gca,'XTick',[-10:5:10]);
set(gca,'YTick',[-10:5:10]);

set(gcf,'PaperPosition',[1 1 13 12]);
print -deps2c generalised_gravOrbit2D.eps

hold off

figure
hold on
% Plot the Energies
% Kinetic Energy
v_squared = sum(v.^2,1);
plot( t , m*v_squared/2 )

% Potential Energy
r = sqrt(sum(x.^2,1));
plot( t , -GMm* 1./r )

% Total
total = -GMm* 1./r + m*v_squared/2;
plot( t , total)

% Note that the total energy is negative. The physics student in you
% shouldn't be surprised. If it weren't negative, then the mass would have
% flown off instead of orbit around the sun.

legend('Kinetic','Potential','Total');

hold off



