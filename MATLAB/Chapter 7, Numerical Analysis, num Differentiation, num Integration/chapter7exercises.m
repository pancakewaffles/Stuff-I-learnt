% chapter7exercises.m

n = 1;

F = @(x) exp(-1*n*x) .* sin(2*pi*n*x);

quad(F,0,1)

% How does this function look like?
x = [-5:0.1:5];
y = exp(-1*n*x) .* sin(2*pi*n*x);
plot(x,y,'k-','linewidth',2);
xlabel('x');ylabel('y');


% Differentiate x
h = 0.01; 
x = [0:h:5];
f = x;
dfdx = diff(f)/h;

figure
plot(x,f,'k-','linewidth',2);
hold on
plot( x(1:end-1) , dfdx,'r-','linewidth',2);
legend('y=x','dydx');
set(gca,'fontsize',16);