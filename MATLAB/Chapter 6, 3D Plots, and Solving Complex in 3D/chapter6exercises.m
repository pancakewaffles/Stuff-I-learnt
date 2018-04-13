% chapter6exercises.m

t = linspace(-2,2,50);
[x,y] = meshgrid(t,t);

% (i) Plotting an mv function, with different constants.
for a = -1:1
    f = -1* (sqrt(x.^2+y.^2+a^2)).^-1; % SINK HOLE!!!
    figure
    mesh(x,y,f);
    title(strcat('mesh plot for a = ', num2str(a)),'fontsize',14);
    xlim([-2,2]);ylim([-2,2]);
    set(gca,'fontsize',16,'linewidth',1);
    xlabel('x');ylabel('y');zlabel('z');
    figure
    contour(x,y,f);
    title(strcat('contour plot for a = ', num2str(a)),'fontsize',14);
    xlim([-2,2]);ylim([-2,2]);
    set(gca,'fontsize',16,'linewidth',1);
    xlabel('x');ylabel('y');
end

% (ii) Plotting/Solving 3x^2 -xy + 4y^2 = 0
t = linspace(-10,10,50);
[x,y] = meshgrid(t,t);
f = 3*x.^2 - x.*y + 4*y.^2;
%mesh(f); if you are interested in how it looks like
figure
contour(x,y,f,[1,1],'k-','linewidth',1); % Well, no doubt you see nothing. 
                                         % The solution to this equation is
                                         % a point, (0,0).
title('(ii) Solving 3x^2-xy+4y^2 = 0','fontsize',14);
set(gca,'fontsize',16,'linewidth',1);
xlabel('x');ylabel('y');

% (iii) Plot separately the real and imag parts, and the magnitude and
% phase of e^ikr, where r =(x,y) and k = (2,1)

% Okay, this question is a little unclear. I am not sure if r and k are
% tuples or vectors. Probably vectors because it is Cambridge afterall.
% If so, the notation is a little off. It should be e^i(k dot r)
% That means: e^i(k dot r) = e^i(k1x xhat + k2y yhat) = e^i(2x) + e^i(y);

f = exp(i*2*x) .* exp(i*y);

figure
surf(x,y,real(f)); 
xlabel('x');ylabel('y');zlabel('Re[e^i(k dot r)]');
set(gca,'fontsize',16,'linewidth',1);
title('(iii) Relationship between Re[e^i(k dot r)] , Re[z] and Im[z]','fontsize',12);

figure
surf(x,y,imag(f));
xlabel('x');ylabel('y');zlabel('Im[e^i(k dot r)]');
set(gca,'fontsize',16,'linewidth',1);
title('(iii) Relationship between Im[e^i(k dot r)] , Re[z] and Im[z]','fontsize',12);

figure
surf(x,y,abs(f)); % You should be able to guess this value. It would help you verify that your graphs are correct.
xlabel('x');ylabel('y');zlabel('abs[e^i(k dot r)]');
set(gca,'fontsize',16,'linewidth',1);
title('(iii) Relationship between abs[e^i(k dot r)] , Re[z] and Im[z]','fontsize',12);

figure
surf(x,y,angle(f)/pi);
xlabel('x');ylabel('y');zlabel('angle[e^i(k dot r)]/\pi');
set(gca,'fontsize',16,'linewidth',1);
title('(iii) Relationship between angle[e^i(k dot r)] , Re[z] and Im[z]','fontsize',12);





% (iv) Plot Im( log(z) ), z being a complex number.
z = x + i*y; % Define the complex mesh. We are now in the realm of complex.
f = log(z);
figure
surf(x,y,imag(f));
xlabel('Re[z]');ylabel('Im[z]');zlabel('Im[log(z)]');
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
title('(iv) Relationship between Im[log(z)], Re[z] and Im[z]','fontsize',12);



% (v) the sphere command
figure
%sphere -this just creates a sphere at (0,0,0)
% Plotting multiple spheres
[x,y,z] = sphere; % This defines x,y and z as coordinates of a sphere at (0,0,0)
surf(x,y,z);
hold on
surf(x+3,y-2,z); % centered at (3,-2,0) *Do not be confused here. 
                 % Usually when we say x+3 we are talking about shifting the graph to the left (negative x direction).
                 % In this case though, (x,y,z) are just substitutes for
                 % (0,0,0).
surf(x,y+1,z-3); % centered at (0,1,-3)
title('(v) Creating spheres','fontsize',12);



