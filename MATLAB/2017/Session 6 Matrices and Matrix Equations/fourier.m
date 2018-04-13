% fourier.m
% Fourier series with 7 terms
% f(x) = a1g1 + a2g2 + a3g3 +... a7g7 ; a are the coefficients, g are the
% functions.
x = (0:1:6);
f = randn(1,7);
plot(x,f,'ok','MarkerFaceColor','r','MarkerSize',10);
hold on;
title('Fourier fit with 7 terms'); xlabel('x');ylabel('f');
xlim([-5 12]);ylim([-5 5]);

% a = G-1 mm f , mm is matrix multiplication

% G matrix generation

k = 2*pi / 7;

G(1:7,1) = ones(7,1); % First column full of 1's
auxi = sin(k.*x);
G(1:7,2) = auxi'; % second (transpose the auxiliary array auxi)
auxi = cos(k.*x);
G(1:7,3) = auxi';
auxi = sin(2. .* k .* x);
G(1:7,4) = auxi';
auxi = cos(2. .* k .* x);
G(1:7,5) = auxi';
auxi = sin(3. .* k.*x);
G(1:7,6) = auxi';
auxi = cos(3. .* k.*x);
G(1:7,7) = auxi';

a = G \ f';

xp = linspace(-5,12,200);
ff = a(1) + a(2) .* sin(k.*xp) + a(3).*cos(k.*xp) + ...
    a(4).*sin(2.*k.*xp) + a(5).*cos(2.*k.*xp) + ...
    a(6).*sin(3.*k.*xp) + a(7).*cos(3.*k.*xp);
plot(xp,ff,'k','LineWidth',2);
hold off

print('fourier.png','-dpng','-r600');

