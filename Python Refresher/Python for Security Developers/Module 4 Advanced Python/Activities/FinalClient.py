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
    
This activity will require you to use multithreading, regular
expressions, and some libraries with which you're unfamiliar. ENJOY!
'''

import os, re, socket, threading, struct, sys
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
    
def send_file_contents(file_name,usersock): #UPLOAD
    # I will send a file to the server.
    content = read_file(file_name);
    if(content == -1):
        return -1;
    
    send_data(usersock,content);

def receive_file_contents(file_name,usersock):#DOWNLOAD
    # I need a file from the server.
    send_data(usersock,file_name);

    data = recv_data(usersock);
    
    return data;


def testScript(commandsList, client_sock):
    print commandsList;
    command = raw_input(recv_data(client_sock)).upper();
    send_data(client_sock,command);

    if(command == "DRIVESEARCH"):
        # DRIVESEARCH Protocol
        # Server asks what file?
        # Client replies.
        # Client receives data.
        filename = raw_input(recv_data(client_sock));
        send_data(client_sock,filename);
               
        receiveddata = recv_data(client_sock);

        print receiveddata;
               
    elif(command == "DIRSEARCH"):
        # DIRSEARCH Protocol
        # Server asks what file?
        # Client replies.
        # Client receives data.
        filename = raw_input(recv_data(client_sock));
        send_data(client_sock,filename);
               
        receiveddata = recv_data(client_sock);

        print receiveddata;

            
    elif(command == "DOWNLOAD"):
        # What file do you want?
        filename = raw_input("Filename: ");
        # Then send the data.
        receive_file_contents(filename,client_sock);
    elif(command == "UPLOAD"):
        # What file name are you going to call it?
        filename = raw_input("Filename: ");
        send_data(client_sock,filename);
        # Then send the data.
        send_file_contents(filename,client_sock);
        
    elif(command == "CLOSE"):
        #exit
        return; 
        
    else:
        print recv_data(client_sock);  
    
        
    
    
    
    
def main():
    # The five main commands
    commandsList = ["DRIVESEARCH",
                    "DIRSEARCH",
                    "DOWNLOAD",
                    "UPLOAD",
                    "CLOSE"];

    print "Setting up connection.";
    client_sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM);
    client_sock.connect(('127.0.0.1',55555));
    print "Connection established.";
    sentinel = True;
    while(sentinel == True):
        testScript(commandsList,client_sock);
        end = raw_input("Do you want to end the connection?").upper();
        if(end == "YES"):
            sentinel = False;

    client_sock.close();

    
    
    return
            
main()
            
            
            
