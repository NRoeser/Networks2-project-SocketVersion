package uni.lu.implementationB;

//A Java program for a Server
import java.net.*;
import java.util.HashMap;
import java.io.*;

public class ServerA
{
//initialize socket and input stream
private Socket          socket   = null;
private Socket			 socket1 = null;
private ServerSocket    server   = null;
private DataInputStream in       =  null;
private DataOutputStream out     = null;
private HashMap<String,String> keyValue = new HashMap<String,String>();

// constructor with port
public ServerA(int port)
{
	 
   // starts server and waits for a connection
   try
   {
  	 
       server = new ServerSocket(port);
       System.out.println("Server started");

       System.out.println("Waiting for a client ...");
       
       
       socket = server.accept();
       System.out.println("Client accepted");

       // takes input from the client socket 
       in = new DataInputStream(
           new BufferedInputStream(socket.getInputStream()));
       
       // Sends output to the socket
       out    = new DataOutputStream(socket.getOutputStream());
       
       String line = "";
       String valueResponse;

       // reads message from client until "Over" is sent
       while (!line.equals("Over"))
       {
      	 valueResponse="";
           try
           {
              line = in.readUTF();
              String[] splitInput = line.split(":");
              if(splitInput[0].equals("SET")) {
              	
              	keyValue.put(splitInput[1], splitInput[2]);
              	System.out.println(keyValue +"Stored");
              	
              }else if(splitInput[0].equals("GET")) {
              	valueResponse=keyValue.get(splitInput[1]);
              	System.out.println("got a get request for key: "+splitInput[1]);
              	
              	
              	//out.writeUTF(keyValue.get(splitInput[1]));		doesnt work here
              	
              	
              }
              
              out.writeUTF(valueResponse);
              
              
              
              
              

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
   ServerA server = new ServerA(5000);
}
}