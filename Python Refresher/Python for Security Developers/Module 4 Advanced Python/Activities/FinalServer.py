'''
The final activity for the Advanced Python section is a drive-wide FTP-like
tool. You should be able to receive multiple connections, each on their 
own thread. You should take several commands:
DRIVESEARCH <filename>
    DRIVESEARCH looks for the given filename across the entire drive. If
    it finds the file, it sends back the drive location.
DIRSEARCH <directory> <filename>
    DIRSEARCH looks for the file in the given directory or its 
    subdirectories. If it finds the file, it sends back the location.
DOWNLOAD <filename>
    DOWNLOAD requires the full file path, or at least the relative path,
    of the file. It sends the contents of the file across the network.
UPLOAD <filename>
    UPLOAD requires the full file path, or at least the relative path,
    where the user wants to locate the file. It reads the file contents
    from across the network connection.
CLOSE
    CLOSE ends the connection
    
This activity will require you to use multithreading, ctypes, regular
expressions, and some libraries with which you're unfamiliar. ENJOY!
'''

import os, re, socket, threading, struct
from ctypes import *

def read_file(filename): #ctypes
    fhandle = windll.Kernel32.CreateFileA(filename,0x10000000,0,0,3,0x80,0);
    if(fhandle == -1): # ErrorFileNotFound.
        return -1;

    data = create_string_buffer(4096);
    dataRead = c_int(0);

    bool_success = windll.Kernel32.ReadFile(fhandle,byref(data), 4096, byref(dataRead),0);
    windll.Kernel32.CloseHandle(fhandle);
    if(bool_success == 0): # Fails to read file.
        return -1;
    
    return data.value;

def create_file(filename, data): #ctypes
    fhandle = windll.Kernel32.CreateFileA(filename,0x10000000,0,0,2,0x80,0);
    if(fhandle == -1):
        return -1;

    dataWritten = c_int(0);
    
    bool_success = windll.Kernel32.WriteFile(fhandle,data, len(data),byref(dataWritten),0);
    windll.Kernel32.CloseHandle(fhandle);
    if(bool_success == 0): # WriteFile fails.
        return -1; # -1 is our default error number.
    
    return 0;
    

def recv_data(sock): #Implement a networking protocol
    # How much data is there?
    dataLen, = struct.unpack('!I',sock.recv(4)); # Struct unpacks into a tuple. Also integers are all 4 bytes.
    # Okay send me that much data.
    return sock.recv(dataLen);

def send_data(sock,data): #Implement a networking protocol
    dataLen = len(data);
    sock.send(struct.pack('!I',dataLen)); # Sends the length of the the data.
    sock.send(data); # Then sends the data.
    return

def search_drive(file_name): #DRIVESEARCH
    
    match = re.compile(file_name);
    for root , dirs, files in os.walk("C:\\"):
        for i in files:
            if(re.search(match,i)):
                return os.path.join(root,i);
    return -1;

def search_directory(file_name): #DIRSEARCH
    
    # In os there is a really fantastic method called os.walk();
    # It's a tree generator. It generates a tree while it goes down the directory.
    # os.getcwd -> Get current working directory.

    """ This is less neat.
    root, dirs, files = os.walk(os.getcwd(),True, None, False);
    match = re.compile(file_name);
    for i in files:
        if(re.search(match,i)):
            return os.path.join(root,i); # Return path as a string.
    return -1;
    """

    # This is neater. It returns while it searches.
    match = re.compile(file_name);
    for root, dirs, files in os.walk(os.getcwd()):
        for i in files:
            if(re.search(match,i)):
               return os.path.join(root,i);
    return -1;
               

def send_file_contents(file_name,usersock,userinfo): #DOWNLOAD
    # If our user requests a file, we send it to them.
    content = read_file(file_name);
    if(content == -1):
        return -1;
    
    send_data(usersock,content);

def receive_file_contents(file_name,usersock):#UPLOAD
    # If our user sends us something, we create a file and store it in there.
    if ( create_file(file_name,recv_data(usersock)) == -1 ):
        send_data(usersock, "File Creation Failed.");
    
    return

def handle_connection(usersock,userinfo): 
        # The five main commands
    commandsList = ["DRIVESEARCH",
                    "DIRSEARCH",
                    "DOWNLOAD",
                    "UPLOAD",
                    "CLOSE"];
    sentinel = True;

    while(sentinel == True):
        send_data(usersock,"COMMAND: ");
               
        command = recv_data(usersock).upper();
    
        if(command == "DRIVESEARCH"):
               
            send_data(usersock,"Filename: ");
            filename = recv_data(usersock);
               
            search_results = search_drive(filename);
               
            if(search_results == -1):
               send_data(usersock,"FILE NOT FOUND");
            else:
               send_data(usersock,search_results);
               
        elif(command == "DIRSEARCH"):
            send_data(usersock,"Filename: ");
            filename = recv_data(usersock);
               
            search_results = search_directory(filename);

            if(search_results == -1):
               send_data(usersock,"FILE NOT FOUND");
            else:
               send_data(usersock,search_results); 

            
        elif(command == "DOWNLOAD"):
            bool_success = send_file_contents(recv_data(usersock),usersock,userinfo);
            if(bool_success == -1):
               send_data(usersock,"FILE DOWNLOAD UNSUCCESSFUL.");
        elif(command == "UPLOAD"):
           receive_file_contents(recv_data(usersock),usersock);
        elif(command == "CLOSE"):
           #exit
            sentinel = False;
        else:
            send_data(usersock,"INVALID COMMAND");
    return

def main():

    # Each user will get a handle_connection. Probably some multithreading involved.
    # Remember, server connections are a 4-step process.
    """
    1) Create the virtual socket.
    2) Bind the virtual socket to the computer and a port.
    3) Get the socket to listen to however many connections you want it to.
    4) Start accepting connections. (Returns user sockets and user information)
    """
    while(True):
        server_sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM); #1
        server_sock.bind(('',55555)); #2
        print "Waiting for connection...";
        server_sock.listen(8); #3
        usersock, userinfo = server_sock.accept(); #4
        print "Connection Established.";

    # Here it starts a thread, and runs it, while listening for another connection.
        print "Connection moved to a Thread.";
        conn_thread = threading.Thread(None,handle_connection,None, (usersock,userinfo));
        conn_thread.start();

    return;

main()
