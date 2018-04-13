% solveVolterra_FoxesandRabbits.m
% Recall: y1(t) -> Prey population
%         y2(t) -> Predator population

[T,Y] = ode45(@volterra,[0,20],[100,20]); % ode45 ODE solver.

%syntax: ode45(function, [initial t, final t] , [initial y1, initial y2] );

% So in this example, the initial prey (rabbits) population is 100, and initial
% predator (foxes) population is 20. We want to see how the population
% changes as time goes from 0 to 20.

% The variables T and Y, refer to the discrete values of time and the
% populations of each species at each time, respectively.
% E.g.  T       Y    
%       1    100  20
%       2    80   25
%       3    60   30 ...etc

hold off
plot(T,Y(:,1),'-k','linewidth',2);
hold on
plot(T,Y(:,2),'--k','linewidth',2);
legend('Rabbits','Foxes','fontsize',16);
title('Population against time','fontsize',16);
set(gca,'fontsize',16,'linewidth',1);
set(gca,'ticklength',[0.02,0]);
xlabel('Time');ylabel('Population');
set(gca,'XTick',[0:5:20]);
set(gca,'YTick',[20:40:100]);

set(gcf,'PaperPosition',[1,1,13,12]); % defining size
print -deps2c 'volterra_populationtimegraph.eps'

figure
% Population of foxes vs that of rabbits for different initial conditions
[T,Y1] = ode45(@volterra,[0,7],[100,20]);
[T,Y2] = ode45(@volterra,[0,7.2],[100,10]);
[T,Y3] = ode45(@volterra,[0,8.0],[100,5]);

hold off
plot(Y1(:,1),Y1(:,2),'-k','linewidth',2);
hold on
plot(Y2(:,1),Y2(:,2),'--k','linewidth',2);
plot(Y3(:,1),Y3(:,2),'-.k','linewidth',2);

legend('100 Rabbits, 20 Foxes','100 Rabbits, 10 Foxes','100 Rabbits, 5 Foxes','fontsize',16);
title('Population of Rabbits against Foxes','fontsize',16);
set(gca,'fontsize',16,'linewidth',1);
set(gca,'ticklength',[0.02,0]);
xlabel('Population of Rabbits');ylabel('Population of Foxes');


set(gcf,'PaperPosition',[1,1,13,12]); % defining size
print -deps2c 'volterra_populationgraph.eps'




