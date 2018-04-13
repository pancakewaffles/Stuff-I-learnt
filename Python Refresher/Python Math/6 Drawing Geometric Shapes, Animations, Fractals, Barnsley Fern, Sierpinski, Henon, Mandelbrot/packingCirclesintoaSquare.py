#! packingCirclesintoaSquare.py
# Draw a square
from matplotlib import pyplot as plt;

def draw_square():
    ax = plt.axes(xlim=(0,6),ylim=(0,6));
    ax.set_aspect("equal"); # Make aspect ratio 1:1
    square = plt.Polygon([(1,1) , (5,1) , (5,5) , (1,5)], closed = True);
    ax.add_patch(square);
    return ax;

def draw_circle(x,y):
    circle = plt.Circle((x,y),0.5,color = "w");
    return circle;

def pack_circles(ax):
    circle_count = 0;
    y = 1.5;
    while(y<5):
        x = 1.5;
        while(x<5):
            circle = draw_circle(x,y);
            ax.add_patch(circle);
            circle_count += 1;
            x += 1.0;
        y += 1.0;

    print("Total number of circles: {0}".format(circle_count));

def show():
    plt.show();


ax = draw_square();
pack_circles(ax);
show();
