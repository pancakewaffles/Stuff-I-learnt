% annual.m
% ***Computes running average with the previous 11 months.***
% NOT AVERAGE OF THE TWELVE MONTHS IN A YEAR.
% The function returns two column arrays in a matrix, both of which are 11
% elements shorter than the input array. The first array is the mean of
% each value and its previous 11 values, and the second array is the
% corresponding std dev.

function y = annual(t)
n =length(t);
yt = t(12:n);
for(i=2:12)
    yt(:,i) = t( (12+1-i) : (n+1-i) );
end
y = [mean(yt,2) , std(yt,0,2)]; % mean(array,1) calculates the mean of each column. Averaging vertically.
                                % Naturally mean(array,2) would be the
                                % averaging of rows. Averaging
                                % horizontally.
