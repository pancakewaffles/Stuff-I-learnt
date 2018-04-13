z = [1,2,3;3,4,5;5,6,7];
z2 = z(1:2 , 1:2) %i.e. z(1,1) , z(1,2) 
                  %     z(2,1) , z(2,2) Confused? z(row,col) picks out the
                  %     element at row,col. This uncomfortable handling
                  %     means to pick out the elements at rows 1, columns 1
                  %     and 2. And then pick out the elements at rows 2,
                  %     columns 1 and 2.
z3 = z(:,3) % I just want the third column.

x = [-10:0.1:10];
quadratic_coeff = 2;
y = x.^3 + quadratic_coeff*x.^2 + 3*x + 4;
plot(x,y);
hold on
xlabel('x axis');
ylabel('y axis');
title('Title of my graph');
set(gca,'fontsize',16);
plot(x,y,'rdiamond');
hold off

% For organisation's sake, I will put it in another figure
figure
hold on
xlabel('x axis');
ylabel('y axis');
title('Graph of function with varying quadratic coefficients');
set(gca,'fontsize',11);
xlim([-1,1]);


quadratic_coeff = 1;
y = x.^3 + quadratic_coeff*x.^2 + 3*x + 4;
plot(x,y,'k-','LineWidth',1);

quadratic_coeff = 2;
y = x.^3 + quadratic_coeff*x.^2 + 3*x + 4;
plot(x,y,'b-','LineWidth',1);

quadratic_coeff = 3;
y = x.^3 + quadratic_coeff*x.^2 + 3*x + 4;
plot(x,y,'r-','LineWidth',1);

hold off