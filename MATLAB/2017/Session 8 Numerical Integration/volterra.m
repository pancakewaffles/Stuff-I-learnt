% volterra.m
function dy = volterra(t,y)
    % Initial parameters
    a = 1;
    b = 0.03;
    c = 1;
    d = 0.02;
    
    dy = zeros(2,1);
    dy(1) = a.*y(1) -b.*y(1).*y(2);
    dy(2) = -c.*y(2) + d .* y(1).*y(2);

end