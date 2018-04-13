from ctypes import *
from time import sleep #need for multithreading
import threading, re

'''2.1 Ctypes: Write a function which takes two arguments, title and body
and creates a MessageBox with those arguments'''
def python_message_box(title = '' , body = ''):
    windll.user32.MessageBoxA(0,body,title,4);
    return


'''2.2 Ctypes: Write a function which takes two arguments, filename and
data and creates a file with that data written to it'''
def python_create_file(filename = '' , data = ''):
    fhandle = windll.Kernel32.CreateFileA(filename, 0x10000000 , 0, 0, 2,0x80,0);
    written_data = c_int(0);
    windll.Kernel32.WriteFile(fhandle,data, len(data),byref(written_data),0);
    #byref is a reference to written_data. It's just a variable for windll to store how many bytes it has written.
    windll.Kernel32.CloseHandle(fhandle);
    return
    

'''2.3 Ctypes: Write a function which takes one argument, a filename,
and prints the data from that file'''
def python_read_file(filename = ''):
    fhandle = windll.Kernel32.CreateFileA(filename, 0x10000000 , 0, 0, 3,0x80,0);
    
    
    data = create_string_buffer(4096); # Just a buffer for whatever data it has read.
    read_data = c_int(0); # Just a buffer for amount of data it has read.
    windll.Kernel32.ReadFile(fhandle, byref(data),4096, byref(read_data),0);
   
    s = data.value;
    print s;
    
    
    windll.Kernel32.CloseHandle(fhandle);

    
    return

'''2.4 Regex: Write a regular expression to search a data block for a 
string contained in <> (html-style) brackets. IE: <span color=black>'''
def regex_html(data):
    match = re.compile("<.*>");
    results = re.search(match,data);
    print data;
    print results.group();
    return
    

'''2.5 Regex: Write a regular expression to search a data block for 
phone numbers in the form of (xxx) xxx-xxxx'''
def regex_phone(data):
    match = re.compile("\(\d{3}\) \d{3}-\d{4}");
    results = re.search(match,data);
    print data;
    print results.group();
    return

'''2.6 Regex: Write a regular expression to find every instance of the 
phrase "Nobody expects" and replace it with "The Spanish Inquisition"'''
def monty_python(data):
    match = re.compile("Nobody expects");
    results = re.findall(match,data); # Use findall to find all instances of "Nobody expects" in your string.
    print len(results); # How many of them are there?
    print data;
    print re.sub(match, "The Spanish Inquisition",data);
    # re.sub doesn't actually replace data. To replace data, do data = re.sub(match, "",data);
    return


'''2.7 Multi-threading: Write a function which runs this entire program,
each function getting its own thread.'''
def multiple_threads():
    functionsList = [python_message_box,
                     python_create_file,
                     python_read_file,
                     regex_html,
                     regex_phone,
                     monty_python];
    
    argsList = [ ("Hello","World!"),
                 ("Test.txt","LALALAD"),
                 ("Test.txt",), # With a comma, it becomes a tuple with 1 element
                 ("DSIOJSSFSOIJ<HTMLCODE>sdasdasdf",),
                 ("dsadsadasd(555) 555-5555sdfsdfsdfsdf",),
                 ("Nobody expects Nobody expects",)
                 ];
    # Here's how threading works.
    threadsList = [];

    for i in range(len(functionsList)):
        threadsList.append(threading.Thread(None,functionsList[i],None,argsList[i]));
    # Well, we created the threads and stored them in the threadsList. Now we need to start them.
    for i in threadsList:
        i.start();
        sleep(1);
    return

def main():
    multiple_threads()
    
    #python_message_box("Hello","World!");
    #python_create_file("Test1.txt","It works!");
    #python_read_file("Test1.txt");
    #regex_html("SDDSDSDSDDSDSD<HTMLCODE>dfsdfdf");
    #regex_phone("dsadsadasd(555) 555-5555sdfsdfsdfsdf");
    #monty_python("Nobody expects");
main()






