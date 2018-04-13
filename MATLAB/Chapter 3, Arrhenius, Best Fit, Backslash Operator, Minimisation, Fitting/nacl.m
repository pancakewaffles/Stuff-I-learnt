% Set up the plot and axes
figure;                                % Creates a new figure window
set(gcf,'units','centimeters');        % Sets the units of the window
set(gcf,'position',[5 5 9 14]);     % Sets the size of the window
set(gca,'units','centimeters');        % Sets the units of the plot
set(gca,'position',[1.25 1.5 7.5 12])  % Sets the size and position of the plot
set(gca,'fontsize',14,'linewidth',1)

% Set up the data
xia = [0.1:0.1:0.5]';                  % Data for acoustic mode ...
freqa = [0.9, 1.8, 2.8, 3.3, 3.7]';
errorsa = 0.1*ones(size(freqa))';
xio = [0:0.1:0.5]';                    % Data for the optic mode
freqo =[5.2, 5.1, 5.0, 4.8, 4.4, 4.2]';
errorso = 0.1*ones(size(freqo))';

% Compute values of alpha and beta using the backslash operator method
E = [sin(pi*xia), sin(3*pi*xia)];
alpha = E\freqa;
E = [ones(size(xio)),cos(2*pi*xio),cos(4*pi*xio)];
beta = E\freqo;       

% Now compute the fitted curve using a fine grid of xi values
xic = [0.0:0.01:0.5]';
E = [sin(pi*xic), sin(3*pi*xic)];      
freqac = E*alpha;
E = [ones(size(xic)),cos(2*pi*xic),cos(4*pi*xic)];
freqoc = E*beta;

% Plot the data and create axis properties
errorbar(xia,freqa,errorsa,'ko','MarkerFaceColor','k');
hold on
errorbar(xio,freqo,errorso,'ko','MarkerFaceColor','k');
plot(xic,freqac,'k-','LineWidth',1)
plot(xic,freqoc,'k-','LineWidth',1)
xlim([0,1/2])
xlabel('Reduced wave vector','FontSize',14)
ylabel('Frequency (THz)','FontSize',14)
set(gca,'TickLength',[0.02,0.0])
hold off
