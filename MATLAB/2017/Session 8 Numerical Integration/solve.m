% solve.m
% Solves the volterra differential equations to obtain the population of
% hares and foxes over time.
hares = 100;
foxes = 20;
[T,Y] = ode45(@volterra,[0 20],[hares foxes]);


plot(T,Y(:,1)); % Plot population of hares
hold on
plot(T,Y(:,2)); % Plot population of foxes
title("Population of hares/foxes over time, starting with " + hares + " hares and " + foxes + " foxes");
xlabel("Time");ylabel("Population");legend("Hares","Foxes");


figure
plot(Y(:,1) , Y(:,2)); % Plot pop of foxes against hares
hold on
hares = 20;
foxes = 100;
[T,Y] = ode45(@volterra,[0 20],[hares foxes]);
plot(Y(:,1) , Y(:,2)); % Plot pop of foxes against hares

title("Population of foxes against hares");
xlabel("Population of hares");ylabel("Population of foxes"); legend("100 hares, 20 foxes","20 hares, 100 foxes");
