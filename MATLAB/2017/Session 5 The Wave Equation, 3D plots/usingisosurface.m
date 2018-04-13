% usingisosurface.m
t = linspace(-2,2,50);
[x,y,z] = meshgrid(t,t,t);

r = sqrt(x.^2 + y.^2 + z.^2);
f = x.*y.*exp(-r);
isosurface(x,y,z,f,0.15);
hold on
isosurface(x,y,z,f,-0.15);
axis equal;
grid on