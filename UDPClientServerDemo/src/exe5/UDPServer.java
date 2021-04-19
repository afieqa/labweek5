package exe5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPServer {

	public static void main(String[] args) {
		
		System.out.println("---------UDP Server Side App | Demonstration of UDP Server-Side Application----------");
        
        try {
        	       
    		// Declaration of port no to received datagram packet
    		int portNo = 8571;
    		
    		// Create a socket to listen at port 8571
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
        	while (true) {
                
                // Variable to received data from port 1234
                byte[] receivedBuf = new byte[65535];
          
                // Datagram to hold received packet
                DatagramPacket receivedPacket = new DatagramPacket(receivedBuf, receivedBuf.length);
                
                // Received from client
		datagramSocket.receive(receivedPacket);
				
		// Retrieve data from packet 
		String retreiveMessage = new String(receivedPacket.getData());
		System.out.println("\nMessage received from client: " + retreiveMessage + ".\n");
				
				
		String sendText = Integer.toString(countWords(retreiveMessage));
		System.out.println("Number of words in sentence is  " + sendText);
				
				
		byte sendBuffer[] = new byte[65535];				
		sendBuffer = sendText.getBytes();
				
		//get the client address
		InetAddress ca = receivedPacket.getAddress();
		int ClientPort = receivedPacket.getPort();
				
		//create object UDP packet utk send ke client
		DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ca, ClientPort);
				
		//send balik data to client guna DatagramPacket
		datagramSocket.send(sendPacket);
	            
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
       
    }
	
public static int countWords(String text) {
		
		String trim = text.trim();
        if (trim.isEmpty())
                return 0;
        return trim.split("\s+").length; // separate string around spaces
        
	}
}