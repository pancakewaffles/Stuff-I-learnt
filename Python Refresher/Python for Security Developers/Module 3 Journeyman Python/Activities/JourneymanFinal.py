import socket
'''
1.5) Python Journeyman: Write a Python server which:
	receives a connection from the included client (JourneymanFinalTester.py)
	stores received data in a file, then adds the file to a list
	returns the data from the file when requested
	deals with errors and missing files
'''

'''
An alternative solution is to save the files as classes rather than actual files on your hard disk.
'''
def saveData(connection):
        # SAVE
        filename = connection.recv(5);
        print "Saving %s\n" % filename;
        fobject = file(filename, 'w+');
        data = connection.recv(1024);
        fobject.write(data);
        fobject.close();
        connection.close(); # Remember to close everything

        return filename, data;

def loadData(connection,filename):
    # LOAD
     fobject = file(filename,'r+');
     content = fobject.read();
     print "Loading %s\t%s\n" % (filename,content);
     connection.send(content);
     connection.close();
     fobject.close();
    
def main():
    HOST = '';
    PORT = 50002;
    sentinel = False;
    
    options = ["SAVE","LOAD"];
    fileList = [];
    
    server = socket.socket(socket.AF_INET,socket.SOCK_STREAM);
    server.bind( (HOST,PORT) ); # For clients, we use .connect, for servers, we use .bind
    
    
    while(sentinel != True):
        server.listen(1); # How many connections do we want to listen for?
        connection , address = server.accept(); # Connects to the client, and creates a socket object for the client.
                                                # This client's socket object allows us to receive info from the client's connection.
        mode = connection.recv(4); # 4 bytes of data i.e. 4 letters. Anything more and we will receive the main body of the data.
        if (mode == options[0] ): #SAVE
            filename ,data  = saveData(connection);
            fileList.append(filename);

        elif (mode == options[1] ): #LOAD
            filename = connection.recv(5);
            if(filename not in fileList):
                connection.send("File Not Found");
                connection.close();
            else:
                loadData(connection,filename);

        else:
            print mode;
            sentinel = True;
        

    server.close();

main()
