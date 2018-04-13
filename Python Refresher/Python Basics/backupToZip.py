#! backupToZip.py
# Copies an entire folder and its contents into a ZIP file
# whose filename increments.

import zipfile, os;
def backupToZip(folder):
    # Backup the entire contents of "folder" into a ZIP file.
    folder = os.path.abspath(folder); # make sure folder is absolute

    number = 1;
    sentinel = True;
    while(sentinel == True):
        zipFilename = os.path.basename(folder) + "_" + str(number) + ".zip";
        if not os.path.exists(zipFilename):
            break;
        number = number + 1;

    # Todo: Create the ZIP file.
    print("Creating %s... in %s" %(zipFilename,os.path.dirname(folder)));
    backupZip = zipfile.ZipFile(os.path.join(os.path.dirname(folder),zipFilename),"w");
    
    # Todo: Walk the entire folder tree and compress the files in each folder.
    for foldername, subfolders, filenames in os.walk(folder):
        print("Adding files in %s..." % (foldername));
        # Add the current folder to the ZIP file.
        backupZip.write(foldername);
        # Add all the files in this folder to the ZIP file.
        for filename in filenames:
            newBase = os.path.basename(folder) + "_";
            if(filename.startswith(newBase) and filename.endswith(".zip")):
                continue; # Don't backup the backup ZIP files
            backupZip.write(os.path.join(foldername,filename));

    backupZip.close();    
    print("Done.");


backupToZip("F:\\Test");   
