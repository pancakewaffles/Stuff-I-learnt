% runningAverage.m
% Given an input sequence of numbers, its running average is another
% sequence in which each number corresponds to an average of the previous N
% numbers in the input set. 
% This function does that, receiving as input a data array and N.

function output = runningAverage(data,N)
    rArray = data(N:end);
    rArray = rArray';
    for i=2:N
        rArray(:,i) = data( (N + 1 - i) : ( end + 1 - i) ); % The looking glass has shifted.
    end
    rArray
    output = mean(rArray,2);
   
    
        