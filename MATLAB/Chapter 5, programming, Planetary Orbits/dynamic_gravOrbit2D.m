%dynamic_gravOrbit2D.m
% It's the same as gravOrbit2D.m, but it's dynamic, so you see the
% progression over time.
% The algorithm follows that of gravOrbit2D.m, so make sure you study that
% before reading this.
% The only change is marked with a "CHANGE" below.
% I will be honest. It looks really cool! See if you could spot Kepler's
% Second Law from the animation.

% Establish parameters (so they can be easily changed)
am = 10.; % Gravitation G*m (F = am*m/r)
dt = 0.01; % time step
nt = 30001; % number of time steps
ti = 0.; % Initial time
tf = ti + (nt-1) * dt; % Final time

% Initial conditions
x0 = 10.;
y0 = 10.;
vx0 = 0.3;
vy0 = -0.3;

% Set up arrays
t = linspace(ti,tf,nt); 
x = zeros(nt,1); y = zeros(nt,1);
vx = zeros(nt,1); vy = zeros(nt,1);
ax = zeros(nt,1); ay = zeros(nt,1);

% Initiate the algorithm
x(1) = x0; y(1) = y0;
vx(1) = vx0; vy(1) = vy0;

% Initial acceleration. This is tricky. Make sure you understand it well.
distance = sqrt(x(1)^2 + y(1)^2);
ax(1) = -am * x(1)/(distance^3);
ay(1) = -am * y(1)/(distance^3);


for i = 2:nt 
             
    x(i) = x(i-1) + vx(i-1)*dt + ax(i-1)/2 * dt^2;
    y(i) = y(i-1) + vy(i-1)*dt + ay(i-1)/2 * dt^2;
    
    distance = sqrt(x(i)^2 + y(i)^2);
    ax(i) = -am * x(i)/(distance^3);
    ay(i) = -am * y(i)/(distance^3);
    
    vx(i) = vx(i-1) +  ( ax(i) + ax(i-1) )/2   * dt;
    vy(i) = vy(i-1) +  ( ay(i) + ay(i-1) )/2   * dt;
end

% CHANGE %
%plot(x,y);
%set(gca,'xlim',[-5,15])
%set(gca,'ylim',[-5,15])
hold on

sun = plot(0,0,'ro'); % Plot the sun first.
set(sun,'MarkerSize',10);
set(sun,'MarkerFaceColor','r');
xlabel('x');ylabel('y');


figure('doublebuffer','on');
axes('xlim',[-5,15],'ylim',[-5,15],'nextplot','add');
j = 1;
p1 = plot(x(j),y(j),'b-');
p2 = plot(x(j),y(j),'ko');
for j = 2:nt
    set(p1,'xdata',[get(p1,'xdata') x(j)], ...
            'ydata',[get(p1,'ydata') y(j)]);
    set(p2,'xdata',x(j),'ydata',y(j));
    pause(0.001);
end

% End of CHANGE %


set(gcf,'PaperPosition',[1 1 13 12]); % The last two numbers define size.
print -deps2c orbit.eps

hold off