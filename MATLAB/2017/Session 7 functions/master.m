%  master.m
% Plots F against q. Then finds the q that produces the minimum F for a
% variety of T.

%Variables
q = linspace(-1,1);
Tc = 1.0;
T = [0.8,0.9,1.0,1.1];

%Plotting the graphs for T = 0.8, 0.9, 1.0, 1.1
hold on
for i = 1:length(T)
    F = bw(q,Tc,T(i));
    plot(q,F);
    
end

legend("T = 0.8","T = 0.9","T = 1.0","T = 1.1"); % Labels of the graph
xlabel("Q");ylabel("F");
title("Graph of F against Q");

hold off

figure

% Plot of Qc against T
T = linspace(0.5,1.3,50); % Changing T.
for j = 1:50
    data(j) = fminsearch(@(q) bw(q,Tc,T(j)) , 0.5);  % Calculates q that produces minimum free energy, then adds it into an array. 
end

plot(T,data) % data is an array of length T that contains, for each T, q that minimises the free energy.
xlabel("T");ylabel("Qc"); % Labels of the graph
title("Graph of Qc against T");







    


