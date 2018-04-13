#! fibo.py

from matplotlib import pyplot as plt;

def fibo(n):
    if(n==1):
        return [1];
    if(n==2):
        return [1,1];

    #n>2
    a = 1;
    b = 1;
    series = [a,b];
    
    for i in range(n):
        c = a+b;
        series.append(c);
        a = b;
        b = c;
        
    return series;

def ratio(a,b):
    return b/a;

def draw_graph(x,y):
    plt.plot(x,y);
    plt.xlabel("No.");
    plt.ylabel("Ratio");
    plt.title("Ratio between consecutive Fibonacci numbers");
    plt.show();
    
    
n = 100000;

series = fibo(n);

list_of_ratios = [];

for i in range(len(series)-1):
    r = ratio(series[i],series[i+1]);
    list_of_ratios.append(r);

draw_graph(range(n+1),list_of_ratios);
    



