x = [0:10];
y = 2 + 3*x; % So if our proposed function is f(x) = sum ai gi(x), then a1 = 2 and a2 = 3; We will use our script to find them.
y = y + randn(1,11);
sigma = ones(1,11);

% What we are doing is using our fminsearch to find a1 and a2, which should
% be as close as possible to 2 and 3 respectively.

a0 = [1,1]; % Where we will be starting our search from
a = fminsearch(@(a) chisq(a,x,y,sigma),a0); % We search for the set of a that causes the minimum difference between our data, and function.
minimum_chisq = chisq(a,x,y,sigma); % We have found our set of a, and substitute it in.

a(1)
a(2)

yy = a(1) + a(2)*x; % The best fit function. It should be as close as possible to y = 2+3*x
errorbar(x,y,sigma,'ko','MarkerSize',6,'MarkerFaceColor','k');
hold on
plot(x,yy,'k-'); % Plotting the best fit function.
xlabel('Uniform x variable','fontsize',16);
ylabel('Semi-random data','fontsize',16);
set(gca,'fontsize',16);
set(gca,'TickLength',[0.02,0.0]);
xlim([0,10]);
hold off



