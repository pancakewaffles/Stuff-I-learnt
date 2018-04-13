x = linspace(0.02,0.6);
plot(x,f(x))

array = [];
for j = 1:10   
    [q,fcnt] = quad(@f,0.01,10,power(10,-1*j))
    array(j) = fcnt;
    
end

j = 1:10;
plot(j,array)