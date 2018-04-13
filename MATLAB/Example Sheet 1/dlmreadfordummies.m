dlmread('test.csv',',',3,0) % dlmread( filename , delimiter , row offset* , col offset*)
                            % So for this command, I will read in starting
                            % from the fourth row, first column.

col1 = dlmread('test.csv',',',[3 0 12 0])  % dlmread( filename , delimiter , [ row offset 1 col offset 1 row offset 2 col offset 2]
                                           % i.e. I read from the fourth
                                           % row, first column, to the
                                           % thirteenth row, first column.
                                           % Basically the first column.
                                              
col2 = dlmread('test.csv',',',[3 1 12 1])  % dlmread( filename , delimiter , [ row offset 1 col offset 1 row offset 2 col offset 2]
                                           % i.e. I read from the fourth
                                           % row, second column, to the
                                           % thirteenth row, second column.
                                           % Basically the second column.

col3 = dlmread('test.csv',',',[3 2 12 2])  % dlmread( filename , delimiter , [ row offset 1 col offset 1 row offset 2 col offset 2]
                                           % i.e. I read from the fourth
                                           % row, third column, to the
                                           % thirteenth row, third column.
                                           % Basically the third column.