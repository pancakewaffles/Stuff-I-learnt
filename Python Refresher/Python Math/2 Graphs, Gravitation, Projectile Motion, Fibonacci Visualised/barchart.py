#! barchart.py
# Horizontal bar chart
import matplotlib.pyplot as plt;
def create_bar_chart(data,labels):
    num_bars = len(data);
    # This list is the point on the y-axis where each bar is centered.
    # Here it will be [1,2,3...]
    positions = range(1,num_bars+1);
    plt.barh(positions,data,align="center");

    plt.yticks(positions,labels); # Sets the labels as the y-axis
    plt.xlabel("Steps");
    plt.ylabel("Day");
    plt.title("Number of steps walked");

    plt.grid() # Turns on the grid which may assist visual estimation
    plt.show();

if(__name__=="__main__"):
    steps = [6534,7000,8900,10786,3467,11045,5095];
    labels = ["Sun","Mon","Tues","Wed","Thu","Fri","Sat"];
    create_bar_chart(steps,labels);
    

