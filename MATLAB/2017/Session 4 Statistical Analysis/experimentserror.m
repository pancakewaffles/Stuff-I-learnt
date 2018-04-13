% experimentserror.m
S = load('experiment.mat');
voltages = S.V;
pulseData = S.pulse_data;

% Constants
% To calculate this I have used the following assumptions:
% Charge of an electron = 1.6 * 10^-19
% I = pulses * 10^-3 / 50 
% totalCharge = I * 10*10^-9
% numberElectrons = totalCharge/(1.6*10^-19)
K = (((10^-3)/50)*10*10^-9)/(1.6*10^-19);
numberElectrons = pulseData*K;
m = mean(numberElectrons,2);
standardDeviation = std(numberElectrons,[],2); % The [] is there to 'create' a new vector for storage.

errorbar(voltages,m,standardDeviation);
title('Number of electrons against voltage, best voltage: 2600V','fontsize',10);
xlim([800 3000]);
xlabel('CEM Voltages');
ylabel('Number of electrons');




