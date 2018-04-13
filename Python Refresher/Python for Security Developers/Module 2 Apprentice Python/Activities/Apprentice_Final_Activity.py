import operator

saved_string = ''

def remove_letter(): #Remove a selected letter from a string
    base_string = str(raw_input("Enter String: "));
    letter = str(raw_input("Letter to remove: "));
    
    
    i = len(base_string) -1 ;
    
    while(i < len(base_string) and i >= 0):
        if(base_string[i] == letter):
            base_string = base_string[:i] + base_string[i+1::];
            
        i -= 1;
    print base_string;
            

def num_compare(): #Compare 2 numbers to determine the larger
    a = int(raw_input("First number: "));
    b = int(raw_input("Second number: "));
        
    if(a>b):
        return "%d is larger than %d." % (a,b);
    elif(a<b):
        return "%d is larger than %d." % (b,a);
    else:
        return "%d is equal to %d." %(a,b);
    

def print_string(): #Print the previously stored string
    print saved_string;
    return 

def calculator(): #Basic Calculator (addition, subtraction, multiplication, division)
    return

def accept_and_store(): #Accept and store a string
    input_string = raw_input("Enter your string: ");
    global saved_string;
    saved_string = str(input_string);
    return

def main(): #menu goes here
    opt_list = [accept_and_store,
                calculator,
                print_string,
                num_compare,
                remove_letter];
    
    while(True):
        choice = int(raw_input(" Enter your choice : "));
        opt_list[choice]();
    
     

main()
