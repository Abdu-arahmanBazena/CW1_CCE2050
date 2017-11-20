/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Abdo
 */

import java.io.Serializable;
import java.util.*;
import model.*;
import services.*;

public class Main implements Serializable{
   
public static void main (String[]args) throws ClassNotFoundException
   {
       String userInput;
       char userInputChar;
       int port =5555;
       Server myServer = null;
       //myServer.runServer();
       Client currentClient;
       Scanner userInputSanner = new Scanner(System.in);
       System.out.println(">> Lunch Server");
       System.out.println(">> Lunch Client");
       userInput = userInputSanner.nextLine().toLowerCase();
       userInputChar = userInput.charAt(0); 
       if (userInputChar == 's')
       {
           new ServerConnection().runServer();
           //myServer = new Server();
           //Thread newThread = new Thread(myServer);
           //newThread.start();
       }
       else if(userInputChar =='c')
       {
           currentClient = new Client();
           currentClient.run(port);
           
           
       }

   }
}
