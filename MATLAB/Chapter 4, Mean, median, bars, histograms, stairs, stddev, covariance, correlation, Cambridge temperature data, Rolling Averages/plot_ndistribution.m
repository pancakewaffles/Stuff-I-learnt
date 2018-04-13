% plot_ndistribution.m
% Plotting the normal distribution using histograms and stairs

% Let's see how the plot does for different data sets.
%y1 = randn(1,1000);
%y2 = randn(1,10000);
%y3 = randn(1,100000);
%y4 = randn(1,1000000);
%y5 = randn(1,10000000);
y = randn(1,1000000);
[rhist,rx] = hist(y,40);
plot(rx,rhist)
figure
stairs(rx,rhist,'Color',[0 0 0])