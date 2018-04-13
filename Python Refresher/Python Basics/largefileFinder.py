#! largefileFinder.py
# Deleting (or rather finding) large files

import os;

def findLargeFiles(folder,size):
    folder = os.path.abspath(folder);
    print("Finding files larger than %d bytes in %s..."%(size,folder));
    
    for foldername,subfolders,filenames in os.walk(folder):
        print("Scanning %s..."%(foldername));
        for filename in filenames: # To get the absolute path of a file,
                                   # use os.path.join(foldername,filename)
            fileSize = os.path.getsize(os.path.join(foldername,filename));
            if(fileSize > size):
                print("%s is %d bytes."%(os.path.join(foldername,filename),fileSize));

        
        
    print("Finished Scanning.");



findLargeFiles("F:\\Test",1024);
