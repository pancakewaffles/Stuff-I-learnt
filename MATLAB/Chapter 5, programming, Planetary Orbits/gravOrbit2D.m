% gravOrbit2D.m
% Planetary orbit simulation in 2D
% Newtonian dynamics via velocity Verlet integration (don't wiki it. You
% won't understand it.)

% Algorithm
% x(t) = x(t-dt) + v(t-dt)dt + a(t-dt)/2 dt^2
% a(t) = F(x(t))/m
% v(t) = v(t-dt) + (a(t) + a(t-dt))/2 dt

% F = GMm/r^2 ; F vector pointing towards origin; r: distance to origin

% Establish parameters (so they can be easily changed)
am = 10.; % Gravitation G*m (F = am*m/r)
dt = 0.01; % time step!!!***
nt = 30001; % number of time steps
ti = 0.; % Initial time
tf = ti + (nt-1) * dt; % Final time ( Honestly I would have just gone for nt instead of nt-1.)

% Initial conditions
x0 = 10.;
y0 = 10.;
vx0 = 0.3;
vy0 = -0.3;

% Set up arrays
t = linspace(ti,tf,nt); % linspace generates a row array of evenly spaced points from ti to tf. nt number of points.
x = zeros(nt,1); y = zeros(nt,1);
vx = zeros(nt,1); vy = zeros(nt,1);
ax = zeros(nt,1); ay = zeros(nt,1);

% Initiate the algorithm
x(1) = x0; y(1) = y0;
vx(1) = vx0; vy(1) = vy0;

% Initial acceleration. This is tricky. Make sure you understand it well.
distance = sqrt(x(1)^2 + y(1)^2);
ax(1) = -am * x(1)/(distance^3);  % The acceleration is towards the origin (the Sun),
                                % so to get the acceleration in the x and y
                                % direction, ax = a*cos(theta) and ay = a*sin(theta)
                                % JUST VISUALISE IT. sin(theta) =
                                % y(1)/distance and cos(theta) =
                                % x(1)/distance
                                % That's why there is an additional
                                % x(1)/distance term in it.
ay(1) = -am * y(1)/(distance^3);


for i = 2:nt % generating the next few coordinates. The programmer you should be able
             % to understand this for loop.
             
    x(i) = x(i-1) + vx(i-1)*dt + ax(i-1)/2 * dt^2;
    y(i) = y(i-1) + vy(i-1)*dt + ay(i-1)/2 * dt^2;
    
    distance = sqrt(x(i)^2 + y(i)^2);
    ax(i) = -am * x(i)/(distance^3);
    ay(i) = -am * y(i)/(distance^3);
    
    vx(i) = vx(i-1) +  ( ax(i) + ax(i-1) )/2   * dt;
    vy(i) = vy(i-1) +  ( ay(i) + ay(i-1) )/2   * dt;
end

% Plot it.
plot(x,y);
set(gca,'xlim',[-5,15])
set(gca,'ylim',[-5,15])
hold on
sun = plot(0,0,'ro'); % Plot the sun.
set(sun,'MarkerSize',10);
set(sun,'MarkerFaceColor','r');
xlabel('x');ylabel('y');

set(gcf,'PaperPosition',[1 1 13 12]); % The last two numbers define size.
print -deps2c orbit.eps

hold off
    

