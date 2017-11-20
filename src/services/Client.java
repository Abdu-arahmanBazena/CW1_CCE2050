package services;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;


public class Client implements Serializable{
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ArrayList<Shape> shapes = new ArrayList<>();
    //private ArrayList<Shape> tempShapesFromServer = new ArrayList<>();
    private String userInput;
    private double userInputDouble;
    private static String host ="127.0.0.1";
   // private static int port =5555;
    public String getUserInputString(String s)
    {
        //get input from user  
        String tempUserInputString;
        //new Scanner to accept user input
        Scanner userInputSanner = new Scanner(System.in);
        //read next line from the scanner 
        tempUserInputString = userInputSanner.nextLine();
        //return the string 
        return tempUserInputString;
    }
      public double getUserInput ()
    {
        //new scanner to get user input
        Scanner userInputSanner = new Scanner(System.in);
        //temp double var
        double tempUserInputInt = 0 ;
        //store data from the scanner into a tmep string
        userInput = userInputSanner.nextLine();
        try{//try to parse user input into double
            
            tempUserInputInt = Double.parseDouble(userInput);

        }catch (NumberFormatException ex)
        {
            System.out.println("Please Enter the number next to the selected option");
        }
        return tempUserInputInt;
    }
    
     public  void run(int port)
    {   
        try
        {
            //create a socket for the client 
            final Socket client = new Socket(host, port);
            //create i/o streams 
            out =new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());
            //call displyMenu 
            displayMenu();
           
        }
        catch(IOException ioe)
        {
            System.out.println("Error: "  + ioe.getMessage());
        }  
    }//end run method
     public  void displayMenu() 
    {
      do {
       System.out.println("<<Please Select an option");
       System.out.println("1. Create 2D Shape.");
       System.out.println("2. Create 3D Shape.");
       System.out.println("3. Send Shapes.");
       System.out.println("4. Receive Shapes.");
       System.out.println("5. Exit.");
       //get user date 
       userInputDouble = getUserInput();
       if(userInputDouble == 1.0)//2d shape
       {
          TwoDMenu();    
       }
       else if(userInputDouble == 2)//if selected 3d shapes 
       {
           ThreeDMenu();
       }
       else if(userInputDouble == 3)//if selected Send Shapes 
       {
           if (shapes.isEmpty()== false)//check if the arralist to be sent is empty
           {
            sendSpahes();
            System.out.println("All shapes were sent to server");
           }
           else
           {
            System.out.println("No shapes to be sent! create some shapes first");
           }
       }
       else if(userInputDouble == 4)//if selected Receive Shapes
       {
           ReceiveShapesMenu();
       }
       else if(userInputDouble == 5)//if selected Exit
       {
           try {
               out.close();
               in.close();
           } catch (IOException ex) {
               Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
           }
          System.out.println("Closing");
       }
      }while(true);
       
       } 
     public void ReceiveShapesMenu()
     {
       
            System.out.println("Please Select Shape Type: ");
            System.out.println("R for Rectangle.");
            System.out.println("C for Circle.");
            System.out.println("T for Triangle.");
            System.out.println("S for Sphere.");
            System.out.println("Y for Cylinder.");
            System.out.println("A for All.");
            while (true)
            {
                String clientOption = getUserInputString("").toLowerCase();
                char clientOptionChar = clientOption.charAt(0);
                //get user input to select shape type 
                if((clientOptionChar == 'r')||(clientOptionChar == 'c')||(clientOptionChar == 't')||(clientOptionChar == 's')||(clientOptionChar == 'y')||(clientOptionChar == 'a'))
                {
                        try {
                        out.writeObject(clientOptionChar);
                        out.flush();
                        if((clientOptionChar == 'r')||(clientOptionChar == 'c')||(clientOptionChar == 't'))
                        {
                           ArrayList<TwoDShapes>  tempTwoShapesFromServer = (ArrayList<TwoDShapes>) in.readObject();
                            for (TwoDShapes shape :tempTwoShapesFromServer )
                            {
                                System.out.println("************");
                                System.out.println("Shape Type: "+shape.getShapeType());
                                System.out.println("Shape Name: "+shape.getShapeName());
                                System.out.println("Shape Area: "+shape.getArea());
                                System.out.println("Shape Primeter: "+shape.getPerimeter());
                                System.out.println("************");

                            }
                        }
                        else if((clientOptionChar == 's')||(clientOptionChar == 'y'))
                        {
                            //read the client request and create an arrayList that holds Threed shapes if the client requested 3d shapes 
                            ArrayList<ThreeDShapes> tempShapesFromServer1 = (ArrayList<ThreeDShapes>) in.readObject();
                             for (ThreeDShapes shape :tempShapesFromServer1 )
                            {
                                System.out.println("************");
                                System.out.println("Shape Type: "+shape.getShapeType());
                                System.out.println("Shape Name: "+shape.getShapeName());
                                System.out.println("Shape Area: "+shape.getSurfaceArea());
                                System.out.println("Shape Primeter: "+shape.getVolume());
                                System.out.println("************");

                            }    
                        }
                        else if (clientOptionChar == 'a')
                        //array list of shapes that holds shapes in general 
                        {
                          ArrayList<Shape> tempShapesFromServer = (ArrayList<Shape>) in.readObject();
                            for (Shape shape :tempShapesFromServer )
                            {
                                System.out.println("************");
                                System.out.println("Shape Type: "+shape.getShapeType());
                                System.out.println("Shape Name: "+shape.getShapeName());
                                System.out.println("************");

                            }  
                        }
                        break;
                        } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                else
                {
                    System.out.println("unsupported Option please select:");
                }
            }
     }
    public void TwoDMenu()
    {
         boolean goBack = false;
             do
             {
                System.out.println("Please select shape type");
                System.out.println("1. Triangle");
                System.out.println("2. Circle");
                System.out.println("3. Rectangle");
                System.out.println("4. Go back.");
                userInputDouble = getUserInput();
                
                if((userInputDouble == 1) ||(userInputDouble == 2) ||(userInputDouble == 3))
                {
                    //Shape newShape = null;
                    if (userInputDouble == 1)
                    {
                        double hypotenuse;
                        double oppsite; 
                        double adjacent; 
                        final String TempShapeName = getUserInputString("Triangle");
                        System.out.println("Please enter the hypotenuse");
                        hypotenuse = getUserInput();
                        System.out.println("Please enter the oppsite");
                        oppsite = getUserInput();
                        System.out.println("Please enter the adjacent");
                        adjacent = getUserInput();
                        Triangle newShape = new Triangle(TempShapeName, "Triangle" ,hypotenuse , oppsite, adjacent);
                        shapes.add(newShape);
                        System.out.println(newShape.getShapeName());
                        System.out.println(newShape.getShapeType());
                    }  
                    else if (userInputDouble == 2)
                    {
                        final String TempShapeName = getUserInputString("Circle");
                        System.out.println("Please enter the diameter of the circle");
                        userInputDouble = getUserInput();
                        Circle newShape = new Circle(TempShapeName, "Circle" ,userInputDouble);
                        shapes.add(newShape);
                    }
                    else
                    {
                        double length;
                        double width;
                        boolean wrongInput = true;
                        while (wrongInput != false)
                        {
                           String TempShapeName = getUserInputString("Rectangle");
                           System.out.println("Please enter the length:");
                           length = getUserInput();
                           System.out.println("please enter the width:");
                           width= getUserInput();
                           if(length > width){
                              Rectangle newShape = new Rectangle(TempShapeName, "Rectangle" ,length , width);
                              shapes.add(newShape);
                               wrongInput = false;
                           }
                           else{
                            System.out.println("length should be larger than width");
                           }
                        }
                    }
                   
                }
                else if (userInputDouble == 4){
                    goBack = true;
                }
             }while(goBack != true );  
     }
    public void ThreeDMenu()
    {
           boolean goBack = false;
           //Shape tempNewShape ;
             do
             {
                System.out.println("Please select shape type");
                System.out.println("1. Cylinder");
                System.out.println("2. Sphere");
                System.out.println("3. Go back.");
                userInputDouble = getUserInput();
                
                if((userInputDouble == 1) || (userInputDouble == 2))
                {
                    if (userInputDouble == 1)
                    {
                        double cylinderRadius;
                        double cylinderHeight;
                        System.out.println("Please enter the name");
                        String TempShapeName = getUserInputString("");
                        System.out.println("Please enter the radius");
                        cylinderRadius = getUserInput();
                        System.out.println("Please enter the height");
                        cylinderHeight = getUserInput();
                        Cylinder tempNewShape = new Cylinder (TempShapeName, "Cylinder" ,cylinderRadius , cylinderHeight);
                        shapes.add(tempNewShape); 
                    }
                    else
                    {
                            double SphereRadius;
                            String TempShapeName = getUserInputString("Sphere");
                            System.out.println("Please enter the radius");
                            SphereRadius = getUserInput();
                            Sphere tempNewShape = new Sphere (TempShapeName, "Sphere" ,SphereRadius);
                            shapes.add(tempNewShape); 
                    }
                }
                else if (userInputDouble == 3){
                    goBack = true;
                }
             }while(goBack != true );  
      }
      
    public void sendSpahes()
    {
        try {
            //send the array list of shapes be the output stream 
            out.writeObject(shapes);
            out.flush();
            shapes = new ArrayList<>();
            System.out.println("Shapes were sent to server");
        } catch (IOException ex) {
           
            System.out.println(ex.getMessage());

        }
    }
}

