% waveequationandpulses.m
% Plots in 3D, contour, mesh, surf, and moving and spreading waves.

figure('Units','centimeters','Position',[0 0 23 30]);

% Right moving pulse
subplot(3,2,1);

ct = 0.;
x = linspace(-30,30,200);
psi = sin(x-ct) ./ (x-ct);
plot(x,psi,'-k','linewidth',2);

% annotate
title('Pulse travelling to the right');
set(gca,'fontsize',16,'linewidth',1);
xticks(-30:10:30);
yticks(-0.5:0.5:1.0);
xlim([-30 30]);
ylim([-0.3 1.1]);
xlabel('x');
ylabel('\psi');


hold on
ct = 2.;
psi2 = sin(x-ct) ./ (x-ct);
plot(x,psi2,'--r','linewidth',2);
legend('t = 0','t = 2');



% The spreading pulse
subplot(3,2,2);
ct = 0.;
psi = 0.5 * sin(x-ct) ./ (x-ct) + 0.5 * sin(x+ct) ./ (x+ct);
plot(x,psi,'-k','linewidth',2);

hold on

ct = 3.;
psi2 = 0.5 * sin(x-ct) ./ (x-ct) + 0.5 * sin(x+ct) ./ (x+ct);
plot(x,psi2,':b','linewidth',2);

hold on 

ct = 20.;
psi4 = 0.5 * sin(x-ct) ./ (x-ct) + 0.5 * sin(x+ct) ./ (x+ct);
plot(x,psi4,'-.r','linewidth',2);

legend('t = 0','t = 3','t = 20');

% annotate
title('The spreading pulse');
set(gca,'fontsize',16,'linewidth',1);
xticks(-30:10:30);
yticks(-0.5:0.5:1.0);
xlim([-30 30]);
ylim([-0.3 1.1]);
xlabel('x');
ylabel('\psi');

% The right travelling pulse in 3D
subplot(3,2,3);
np = 101;
tt = linspace(-4.,15.,np);
xx = linspace(-15.,15.,np);
[x,t] = meshgrid(xx,tt);

psi = sin(x-t) ./ (x-t);
mesh(x,t,psi);

% Colours and annotations
colormap('jet');
title('Pulse travelling to the right');
set(gca,'fontsize',16,'linewidth',1);
xticks(-15:10:15.); yticks(0.:5.:15.); zticks(-0.5:0.5:1.0);
xlim([-15 15]); ylim([-4 15]); zlim([-0.3 1.1]);
xlabel('x'); ylabel('t'); zlabel('\psi');
%view([-1 -3 3]);

% Contour Plots
subplot(3,2,4);
np = 50;
tt = linspace(-4.,15.,np);
xx = linspace(-15.,15.,np);
[x,t] = meshgrid(xx,tt);

psi = 0.5 * sin(x-t) ./ (x-t) + 0.5 * sin(x+t) ./ (x+t);
contourf(x,t,psi,20);

% Colours and annotations
colormap('jet');
title(['Pulse spreading (contour), np = ' num2str(np)]);
set(gca,'fontsize',16,'linewidth',1);
xticks(-15:10:15.); yticks(0.:5.:15.); zticks(-0.5:0.5:1.0);
xlim([-15 15]); ylim([-4 15]); zlim([-0.3 1.1]);
xlabel('x'); ylabel('t'); zlabel('\psi');

% 3D plot of the spreading pulse (surf) 
subplot(3,2,5);
np = 101;
tt = linspace(-4.,15.,np);
xx = linspace(-15.,15.,np);
[x,t] = meshgrid(xx,tt);

psi = 0.5 * sin(x-t) ./ (x-t) + 0.5 * sin(x+t) ./ (x+t);

surf(x,t,psi);

% Colours and annotations
colormap('jet');
title('Pulse spreading (surf)');
set(gca,'fontsize',16,'linewidth',1);
xticks(-15:10:15.); yticks(0.:5.:15.); zticks(-0.5:0.5:1.0);
xlim([-15 15]); ylim([-4 15]); zlim([-0.3 1.1]);
xlabel('x'); ylabel('t'); zlabel('\psi');
view([-1 -3 3]);

% 3D plot of the spreading pulse (mesh)
subplot(3,2,6);

mesh(x,t,psi);

% Colours and annotations
colormap('jet');
title('Pulse spreading (mesh)');
set(gca,'fontsize',16,'linewidth',1);
xticks(-15:10:15.); yticks(0.:5.:15.); zticks(-0.5:0.5:1.0);
xlim([-15 15]); ylim([-4 15]); zlim([-0.3 1.1]);
xlabel('x'); ylabel('t'); zlabel('\psi');
view([-1 -3 3]);

print('waveequation.png','-dpng','-r600');



