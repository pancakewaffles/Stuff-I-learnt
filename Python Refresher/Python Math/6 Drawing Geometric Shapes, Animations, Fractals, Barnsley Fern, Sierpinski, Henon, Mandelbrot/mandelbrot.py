#! mandelbrot.py
# Yea the mandelbrot.
import matplotlib.pyplot as plt;
import matplotlib.cm as cm;
import random;

def initialise_image(x_p,y_p):
    image = [];
    for i in range(y_p):
        x_colors = [];
        for j in range(x_p):
            x_colors.append(0);
        image.append(x_colors);
    return image; # Basically a list of lists, of which the elements are all 0.

def mandelbrot():
    x_p = 400;
    y_p = 400;
    image = initialise_image(x_p,y_p);

    bottom_left = (-2.5,-1.0);
    top_right = (1.0,1.0);
    x_step = float((top_right[0] - bottom_left[0]))/x_p;
    y_step = float((top_right[1] - bottom_left[1]))/y_p;
    
    
    max_iteration = 1000;
    
    for i in range(x_p):
        for k in range(y_p):

            x = bottom_left[0] + i*x_step;
            y = bottom_left[1] + k*y_step;

            z1 = complex(0,0); # yes, these are complex numbers
            c = complex(x,y);
            iteration = 0;
            while( abs(z1)<2 and iteration < max_iteration ):
                z1 = z1*z1 + c;
                iteration = iteration + 1;
                

            
            #image[i][k] = iteration; # Setting the colour of the point (x,y) to the value in iteration.
                                     # Whaa? Yea, apparently this is called the escape-time algorithm.
                                     # When the max number of iterations is reached before a point's
                                     # magnitude exceeds 2, that point belongs to the Mandelbrot set
                                     # and is coloured white.
                                     # The points that exceed the magnitude within fewer iterations
                                     # are said to "escape"; they don't belong in the Mandelbrot set
                                     # and are coloured black.
                                     
            image[k][i] = iteration; # I want the image to flip about y=x. Makes for a nicer picture.
            
    '''
    for i in range(x_p):
        for j in range(y_p):
            print(image[i][j], end=" ");
        print();
    '''
    
    plt.imshow(image,
               origin="lower",
               extent=(bottom_left[0],top_right[0],bottom_left[1],top_right[1]),
               cmap=cm.Greys_r
               );
    plt.show();


mandelbrot();
    
            
                
            
