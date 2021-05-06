package uni.lu.implementationB;

//A Java program for a Server
import java.net.*;
import java.util.HashMap;
import java.io.*;

public class ServerA {
//initialize socket and input stream
	private Socket socket = null;
	private Socket socket1 = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private HashMap<String, String> keyValue = new HashMap<String, String>();

// constructor with port
	public ServerA(String address, int port) {

		// starts server and waits for a connection
		try
	     {
	         socket = new Socket(address, port);
	         System.out.println("Connected");

	         // takes input from terminal
	         in  = new DataInputStream(socket.getInputStream());
	         out    = new DataOutputStream(socket.getOutputStream());
	     }
	     catch(UnknownHostException u)
	     {
	         System.out.println(u);
	     }
	     catch(IOException i)
	     {
	         System.out.println(i);
	     }

	     // string to read message from input
	     String line = "";
	     String response ="";
	     // keep reading until "Over" is input
	     while (!line.equals("Over"))
	     {
	         try
	         {
	             line = in.readUTF();
	             System.out.println("Hello from Server A: " + line);
	         }
	         catch(IOException i)
	         {
	             System.out.println(i);
	         }
	     }

	     // close the connection
	     try
	     {
	         in.close();
	         out.close();
	         socket.close();
	     }
	     catch(IOException i)
	     {
	         System.out.println(i);
	     }
	}

	public static void main(String args[]) {
		ServerA server = new ServerA("127.0.0.3", 6000);
	}
}
