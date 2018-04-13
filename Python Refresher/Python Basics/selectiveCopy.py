#! selectiveCopy.py
# Walks through a folder tree and searches for files
# with a certain file extension (such as .pdf or .jpg). Copy these files
# from whatever location they are in to a new folder.

import os, shutil, re;

def selectiveCopy(folder,ext):

    folder = os.path.abspath(folder);
    
    for foldername, subfolders, filenames in os.walk(folder):
        print("Going through %s..."%(foldername));
        for filename in filenames:
            if(filename.endswith(ext)):
                print("Copying %s.."%(filename),end=" ");
                shutil.copy(os.path.join(folder,filename),os.path.join(os.getcwd(),"selectivelyCopiedFiles"));
                print("Done.");

        
    print("All done!");
    
selectiveCopy("F:\\Test",".nonsense");
        
    
