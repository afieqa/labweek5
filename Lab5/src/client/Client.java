package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import itemproduct.ItemProduct;


/**
 * This class demonstrate the application of ObjectInputStream and 
 * ObjectOutputStream at the server-side application.
 * 
 * How to run this program?
 * 
 * 1. Open Terminal
 * 2. Change the directory to /workspace-dad/demoObjectStream/bin
 * 3. Run -> java console.server.ClientSideApp
 * 
 * @author emalianakasmuri
 *
 */

public class Client {

	/**
	 * Main entry point of program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("ClientSideApp: Demo of Object Stream\n");

		// Request data
		ItemProduct itemProduct = new ItemProduct();
		itemProduct.setName("Programming Lab 1");
		

		try {

			// Data to establish connection to server
			int portNo = 8571;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 8571
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " + itemProduct);
			outputStream.writeObject(itemProduct);
			outputStream.flush();
			
			// Open stream to receive object
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream and display details
			itemProduct = (ItemProduct) inputStream.readObject();
			System.out.println ("Id for " + itemProduct.getName() + " is " + itemProduct.getItemProductId());
			
			// Close all closeable objects
			outputStream.close();
			inputStream.close();
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nClientSideApp: End of application.\n");

	}

}