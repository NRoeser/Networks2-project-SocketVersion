package uni.lu.implementationB;

//A Java program for a Client
import java.net.*;
import java.io.*;

public class Client
{
 // initialize socket and input output streams
 private Socket socket            = null;
 private DataInputStream  input   = null;
 private DataInputStream  serverFeedBack   = null; // GET THE RETURN FROM SERVER
 private DataOutputStream out     = null;

 // constructor to put ip address and port
 @SuppressWarnings("deprecation")
public Client(String address, int port)
 {
     // establish a connection
     try
     {
         socket = new Socket(address, port);
         System.out.println("Connected");

         // takes input from terminal
         input  = new DataInputStream(System.in);
         serverFeedBack = new DataInputStream(
                 new BufferedInputStream(socket.getInputStream()));
         // sends output to the socket
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
             line = input.readLine();
             out.writeUTF(line);
             
             response = serverFeedBack.readUTF();
             if(!response.equals("")) {
            	 System.out.println(response);
             }
             
             
         }
         catch(IOException i)
         {
             System.out.println(i);
         }
     }

     // close the connection
     try
     {
         input.close();
         out.close();
         socket.close();
     }
     catch(IOException i)
     {
         System.out.println(i);
     }
 }

 public static void main(String args[])
 {
     Client client = new Client("127.0.0.2", 5000);
 }
}
