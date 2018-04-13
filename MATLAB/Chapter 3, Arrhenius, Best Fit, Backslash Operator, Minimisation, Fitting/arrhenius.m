ea = 640;
tau0 = 0.9;
tauinv0 = 1/tau0;
t = [50:10:2000];
tinv = 1./t;
tauinvcalc = tauinv0*exp(-ea*tinv); % The mathematical model

% The experimental data
texp = [200:50:2000];
texpinv = 1./texp;
tauinvexp = tauinv0*exp(-ea*texpinv) + 0.08 * rand(1,37)-0.04; % This generates the experimental data

plot(t,tauinvcalc,'k')
hold on
plot(texp,tauinvexp,'ko','MarkerSize',6,'MarkerFaceColor','k');
hold off
set(gca,'TickLength',[0.02,0]);
set(gca,'fontsize',16');
xlim([0,2000]),ylim([0,0.9]);
xlabel('Temperature (K)','fontsize',16);
ylabel('Reaction rate (MHz)','fontsize',16);

logtau = log(1./tauinvcalc);
plot(tinv*1000,logtau,'k'); % The Arrhenius equation, in case you forgot, is tau = tau0 * exp(ea / t). Or, log(tau) = log(tau0) + ea/t
                            % Therefore plotting log(tau) against 1/t will
                            % give you a straight line with gradient ea.