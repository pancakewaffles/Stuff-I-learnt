#! animatingCircle.py
# A growing circle!

from matplotlib import pyplot as plt;
from matplotlib import animation;

def create_circle():
    circle = plt.Circle( (0,0), 0.05);
    return circle;
def update_radius(i,circle): 
    circle.radius = i*0.1;
    return circle;

def create_animation():
    '''
    Four steps to creating an animation using matplotlib:
    A. Get the axes
    B. Create the "patch" (image you are drawing)
    C. Add the "patch" to the axes
    D. anim = animation.FuncAnimation(
                       fig, updater, fargs = "patch", frames, interval
                       )
    '''
    fig = plt.gcf();
    ax = plt.axes(xlim = (-10,10) , ylim = (-10,10)); 
    ax.set_aspect("equal"); # Sets the aspect ratio to 1:1

    circle = create_circle();
    ax.add_patch(circle);

    anim = animation.FuncAnimation(
            fig, # the canvas on which we are animating
            update_radius, # the updater 
            fargs = (circle,), # what arguments to pass to the updater?
                               # Note that the frame number is automatically passed into the updater. 
            frames = 60,    # number of frames in the animation. The updater is called this many times.
            interval = 17); # time interval between two frames (in millieconds)
    plt.title("Simple Circle Animation"); # Simple? HAHAHA
    plt.show();


if(__name__=="__main__"):
    create_animation();
