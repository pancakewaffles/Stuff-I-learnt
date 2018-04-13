% volterra.m
% The Volterra equations were propsed to model to evolution of the simplest
% ecosystem, with a predator species and a prey.
% y1(t) is the population of prey
% y2(t) is the population of predator

% The Volterra Equations:
% dy1/dt = ay1 - b*y1*y2
% dy2/dt = -cy2 + d*y1*y2

function dy = volterra(t,y)

a = 1;b = 0.03; c = 1; d = 0.02;
dy = zeros(2,1); % Initialise a vector
dy(1) = a*y(1) - b*y(1)*y(2);
dy(2) = -c*y(2) + d*y(1)*y(2);
end

% a quantifies the exponential growth of the prey population in the absence
% of predators. (if b = 0, the first eqn decouples from the second.) 
% The fact that the offspring number is proportional to hte reproducing
% population gives rise to an exponential growth.

% The model assumes unlimited food supply for the prey.

% b accounts for the decrease of prey population due to deadly encounters
% with the predator. The term assumes that such deaths are proportional to
% the probability of their meeting, whcih is then proportional to the
% product of their respective populations. 

% c accounts for predator population variation by natural deaths, which is
% proportional to the population itself, thus leading to exponential decay
% in the absence of prey.

% The model assumes that prey deaths by other causes are negligible.

% This extremely simple model gives rise to very rich dynamics.



