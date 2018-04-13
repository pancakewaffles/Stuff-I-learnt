'''
Some theory on sockets.
Ethernet <== For Windows, raw sockets are no longer allowed to access the Ethernet layer (DataLink Layer). Not so for Linux.

IP Family (4/6)

Protocol (TCP, UDP)

https://en.wikipedia.org/wiki/Ethernet_frame#Structure <= How an ethernet packet looks like.
'''
import struct
import socket
import binascii
import os

def analyseEtherHeader(data):
    ipBool = False;
    
    etherHeader = struct.unpack("!6s6sH",data[:14]); 
    # Check this page out to see how an ethernet packet look like.
    # https://en.wikipedia.org/wiki/Ethernet_frame#Structure
    '''
    The Ethernet packet starts from the MAC destination address. 6 octets ~ 6 bytes because octets = 8 bits = 1 byte
    struct.unpack("!6s <- 6 bytes string, 6s <- 6 bytes string, H <- Unsigned(because header fields are never signed) short of 2 bytes (Ethertype (Ethernet II) or length part check wiki page if you are don't understand. It basically tells you if it is IPv4 or IPv6 packet.)
    Now, because we are taking only the header out, we take only 14 bytes (6 + 6 + 2) of the data out.
    Then we return the data back, 14 bytes less and without a head.
    '''
    macDestination = etherHeader[0];
    macSource = etherHeader[1];
    proto = etherHeader[2];

    macDestination = binascii.hexlify(macDestination); #macDestination is in binary.
    macSource =  binascii.hexlify(macSource); # macSource is in binary, so we convert it into hex. MAC addresses are always in hex.
    proto = hex(proto); # proto (the Ethertype (Ethernet II) or length (IEEE 802.3) field, tells you whether it's IPv4 or IPv6), is in hex. So we have to convert it to string from hex.

    print "|=========The Ethernet Header=======|";
    print "|Dest MAC:\t%s:%s:%s:%s:%s:%s" % (macDestination[0:2], macDestination[2:4],macDestination[4:6],macDestination[6:8], macDestination[8:10],macDestination[10:12]);
    print "|Source MAC:\t\t%s:%s:%s:%s:%s:%s" % (macSource[0:2], macSource[2:4],macSource[4:6],macSource[6:8], macSource[8:10],macSource[10:12]);
    print "|Protocol:\t\t%s" % (proto);
    
    if (proto == 0x0800): # #IPv4 = 0x0800
        ipBool = True;
        
    data = data[14:];  
    return data , ipBool;

def analyseIPHeader(data):
    ''' This is how the IP header looks like : https://tools.ietf.org/html/rfc791#section-3.1 '''
    ipHeader = struct.unpack("!6H4s4s",data[:20]); # Unlike 6s which specifies how long the string is, 2H specifies how many H fields. Also, we are taking 20 bytes of data.

    '''Unfortunately, H gives back 2 bytes = 16 bits. This encompasses version, IHL and Type of Service.
        So we need to remove IHL and Type of Service. How do we do that you ask? By shifting bits.'''
    ver = ipHeader[0] >> 12; # Shift 12 bits.
    ihl = (ipHeader[0] >> 8) & 0x0f; # 0x0f = 00001111 See if you can figure out what I am doing here.
    typeofService = ipHeader[0] % 0x00ff; # Type of Service determines how important the traffic is. Check the RFC for more information.

    ''' The 2nd H (2 bytes = 16 bits)'''
    totalLength = ipHeader[1];

    ''' The 3rd H (2 bytes = 16 bits)'''
    ipID = ipHeader[2];

    ''' The 4th H (2 bytes = 16 bits)'''
    flags = ipHeader[3] >> 13; # only the first 3 bits
    fragOffset = ipHeader[3] & 0x1fff; # 0x1fff = 00011111111111111

    ''' The 5th H (2 bytes = 16 bits)'''
    ipTTL = ipHeader[4] >> 8;
    ipProtocol = ipHeader[4] & 0x00ff;

    ''' The 6th H (2 bytes...you get it.)'''
    checksum = ipHeader[5]; 

    ''' The 4 bytes of string. Wait how many bytes are left????? '''
    sourceAddress = socket.inet_ntoa(ipHeader[6]);

    ''' The final 4 bytes of string.'''
    destinationAddress = socket.inet_ntoa(ipHeader[7]);

    no_frag = flags >> 1;
    more_frag = flags & 0x1;
    
    print "|===========IP Header=========|";
    print "|\tVersion:\t%hu" % ver; #%hu = unsigned short
    print "|\tIHL:\t\t%hu" % ihl;
    print "|\tTOS:\t\t%hu" % typeofService;
    print "|\tLength:\t\t%hu" % totalLength;
    print "|\tID:\t\t%hu" % ipID;
    print "|\tNo Frag:\t%hu" % no_frag;
    print "|\tMore Frag:\t%hu" % more_frag;
    print "|\tOffset:\t\t%hu" % fragOffset;
    print "|\tTTL:\t\t%hu" % ipTTL;
    print "|\tNext Protocol:\t\t%hu" % ipProtocol;
    print "|\tChecksum:\t\t%hu" % checksum;
    print "|\tSource IP:\t\t%hu" % sourceAddress;
    print "|\tDest IP:\t\t%hu" % destinationAddress;

    
    if(ipProtocol == 6): #TCP magic number
        nextProtocol = "TCP";
    elif(ipProtocol == 17): #UDP magic number
        nextProtocol = "UDP";
    else:
        nextProtocol = "OTHER";


    
        
    data = data[20:];
    return data,nextProtocol;
    

def analyseUDPHeader(data):
    # By now you should get the idea. Here's how the UDP Header looks like: https://www.ietf.org/rfc/rfc768.txt
    udpHeader = struct.unpack('!4H',data[:8]);

    sourcePort = udpHeader[0];
    destPort = udpHeader[1];
    length = udpHeader[2];
    checksum = udpHeader[3];

    print "|===========UDP Header=========|";
    print "|\tSource Port:\t\t%hu" % sourcePort;
    print "|\tDest Port:\t\t%hu" % destPort;
    print "|\tLength:\t\t%hu" % length;
    print "|\tChecksum:\t\t%hu" % checksum;

    data = data[8:];
    return data;

def analyseTCPHeader(data):
    # By now you should get the idea. Here's how the TCP Header looks like: https://tools.ietf.org/html/rfc793#section-3.1
    tcpHeader = struct.unpack('!2H2I4H',data[:20]);
    ''' The 1st H '''
    sourcePort = tcpHeader[0];
    ''' The 2nd H '''
    destPort = tcpHeader[1];
    ''' The 1st I '''
    sequenceNumber = tcpHeader[2]; # The sequence and acknowledgement number establishes where you are at in a specific conversation.
                                   # It establishes and defines a specific conversation.
    ''' The 2nd I '''
    acknowledgementNumber = tcpHeader[3];

    ''' The 3rd H '''
    dataOffset = tcpHeader[4] >> 12;
    reserved = (tcpHeader[4] >> 6)  & 0x03ff  ; #0x03ff = 0000001111111111
    flags = tcpHeader[4] & 0x003f; # 0x003f = 0000000000111111
    '''
    Theory time! TCP flags!
    URG: Urgent Pointer field (not used much except for network scanning purposes. If you see this it means someone is testing out your network.)
    ACK: Acknowledgement field
    PSH: Push function ("I have data for you!")
    RST: Reset the connection
    SYN: Synchronise sequence numbers
    FIN: No more data from sender
    '''
    urg = bool( flags & 0x0020 ); # 0000000000100000
    ack = bool( flags & 0x0010 ); # 0000000000010000
    psh = bool( flags & 0x0008 ); # 0000000000001000
    rst = bool( flags & 0x0004 ); # 0000000000000100
    syn = bool( flags & 0x0002 ); # 0000000000000010
    fin = bool( flags & 0x0001 ); # 0000000000000001

    ''' The 4th H '''
    window = tcpHeader[5]; # window is the size of the amount of data the connection can send.

    ''' The 5th H '''
    checksum = tcpHeader[6];

    ''' The 6th H '''
    urgPointer = tcpHeader[7]; # different from urg flag


    print "|===========TCP Header==========|";
    print "|\tSource Port:\t\t%hu" % sourcePort;
    print "|\tDest Port:\t\t%hu" % destPort;
    print "|\tSeq Number:\t\t%hu" % sequenceNumber;
    print "|\tAck Number:\t\t%hu" % acknowledgementNumber;
    print "|\tFlags:";
    print "|\t\tURG:%d" % urg;
    print "|\t\tACK:%d" % ack;
    print "|\t\tPSH:%d" % psh;
    print "|\t\tRST:%d" % rst;
    print "|\t\tSYN:%d" % syn;
    print "|\t\tFIN:%d" % fin;
    print "|\tWindow:\t\t%hu" % window;
    print "|\tChecksum:\t\t%hu" % checksum;
    data = data[20:];
    return data;
    
    
def main():
                                    # v this means "give me the entire packet", not just only from the IP layer.
    snifferSocket = socket.socket(socket.AF_INET, socket.SOCK_RAW, socket.IPPROTO_RAW); 
                                                        #^ Raw socket, not just TCP/UDP
    #sniffer_socket.bind(()); <- Don't do this, because the packet sniffer will only listen to a specific port you bound it to.
    #Removing .bind allows the packet sniffer to listen to every port.

    receivedData = snifferSocket.recv(2048);
    
    # Now receivedData is a struct (binary data). We need to unpack it. (You should be familiar with struct from the Advanced Python section.)
    data , ipBool = analyseEtherHeader(receivedData);

    # Now we know whether the data is an IPv4 or IPv6 packet.
    if(ipBool == True):
        data , nextProtocol = analyseIPHeader(data);
    else:
        return;

    if(nextProtocol == "TCP"):
        data = analyseTCPHeader(data);
    elif(nextProtocol == "UDP"):
        data = analyseUDPHeader(data);
    else:
        return;
        



main()
