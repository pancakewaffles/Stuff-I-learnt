% complexin3D.m
% Now this is starting to get complex. We want to plot a function of one
% complex variable z. Now, z = x+iy , but it isn't a function of x and y. 
% Instead, it is an independent, complex variable that is depended on by a
% function f.

% Some of the commands (surf, contour) don't deal with complex numbers
% directly, but what we do, is we plot out separately the real and
% imaginary parts ( or analogous slicings of the problem e.g. magnitude and
% phase) using surf and contour.

% Standard stuff
t = linspace(-2,2,50);
[x,y] = meshgrid(t,t);

% ***Defining the mesh of complex numbers in the plane***
z = x + i*y;

% and now the function
f = z.^2;

% The plot
figure 
surf(x,y,real(f));
xlabel('Re[z]');ylabel('Im[z]');zlabel('Re[z^2]'); % The labels tell it as it is.
                                                   % ***We basically get to
                                                   % understand the
                                                   % relationship between
                                                   % Re[z],Im[z] and
                                                   % Re[z^2]
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-2:1:2]);
set(gca,'YTick',[-1:1:2]);
set(gca,'ZTick',[-2:2:2]);
title('Relationship between Re[z^2], Re[z] and Im[z]','fontsize',12);

figure 
surf(x,y,imag(f));
view([-1,-3,1]); % Ooh changing the view, eh?
xlabel('Re[z]');ylabel('Im[z]');zlabel('Im[z^2]');
% Some usual figure tuning
set(gca,'fontsize',16,'linewidth',1);
set(gca,'XTick',[-2:1:2]);
set(gca,'YTick',[-1:1:2]);
set(gca,'ZTick',[-2:2:2]);
title('Relationship between Im[z^2], Re[z] and Im[z]','fontsize',12);

% Analogously, we could plot magnitude and phase.
% surf(x,y,abs(f)) and surf(x,y,angle(f))
figure
surf(x,y,angle(f)/pi) % Phase angle in terms of pi








