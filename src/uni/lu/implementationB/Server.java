package uni.lu.implementationB;

//A Java program for a Server
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Server
{
 //initialize socket and input stream
 private Socket          socket   = null;
 private Socket			 socket1 = null;
 private ArrayList<ServerSocket>    server   = new ArrayList<ServerSocket>();
 private DataInputStream in       =  null;
 private DataOutputStream out     = null;
 private DataInputStream in1       =  null;
 private DataOutputStream out1     = null;
 private HashMap<String,String> keyValue = new HashMap<String,String>();
 
 // constructor with port
 public Server(int port1, int port2)
 {
	 
     // starts server and waits for a connection
     try
     {
    	 
         ServerSocket socketserver1 = new ServerSocket(port1);
         ServerSocket socketserver2 = new ServerSocket(port2);
         server.add(socketserver1);
         server.add(socketserver2);
         System.out.println("Server started");

         System.out.println("Waiting for a client ...");
         
         socket = socketserver1.accept();
         socket1 = socketserver2.accept();
         System.out.println("Client accepted");

         // takes input from the client socket 
         in = new DataInputStream(
             new BufferedInputStream(socket.getInputStream()));
         
         // Sends output to the socket
         out    = new DataOutputStream(socket.getOutputStream());
         
         // takes input from the client socket 
         in1 = new DataInputStream(
             new BufferedInputStream(socket1.getInputStream()));
         
         // Sends output to the socket
         out1    = new DataOutputStream(socket1.getOutputStream());
         
         String line = "";
         String valueResponse;

         // reads message from client until "Over" is sent
         while (!line.equals("Over"))
         {
                 try
                 {
                     line = in.readUTF();
                     System.out.println(line);
                     out1.writeUTF(line);
   
                 }
                 catch(IOException i)
                 {
                     System.out.println(i);
                 }
         }
         System.out.println("Closing connection");

         // close connection
         socket.close();
         in.close();
     }
     catch(IOException i)
     {
         System.out.println(i);
     }
 }

 public static void main(String args[])
 {
     Server server = new Server(5000, 6000);
 }
}
