package services;

/**
 *
 * @author Abdo
 */
import java.net.*;
import java.io.*;
import java.util.*;



import model.*;
public class Server extends Thread implements Serializable{
    
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket clientConnection;
    //arrayList that acts like a database
    private static ArrayList<Shape> shapesDB = new ArrayList<>();
    //temp arrayList that 
    private ArrayList<Shape> shapes = new ArrayList<>();
    private final int port = 5555;
    
    public Server(Socket s)
    {
        this.clientConnection = s;
    }
    
    public  void runServer()
    { 
       
        System.out.println("Starting Server: ");
       // System.out.println("Please enter port number: ");
        try 
        { 
            //open an input stream to get data from client 
            in = new ObjectInputStream(clientConnection.getInputStream());
            //open output stream to send data to the client 
            out = new ObjectOutputStream(clientConnection.getOutputStream());
            out.flush();
            //call read from file to load any old shapes
            readFromFile();
            //call get clientRequest to process any incoming requests     
            getClientRequest();
            
        }//end try
        catch(IOException ioe)
        {
            //incase anything happens save everything to the file before ending the sarver
           saveToFile();
            System.out.println("Error: " + ioe.getMessage());
        }//end catch
    }//end of runServer

    //checks if there is a request, if so process it 
    public void getClientRequest()
    {
        do{//a do while loop that will constantly check for the client's request 
                try {
                    //read from the input stream of the server and check if there is anything 
                    //store the request in a temp object 
                    Object clientRequest = in.readObject();
                    //check if the client sent a list of shapes
                    if (clientRequest instanceof ArrayList)
                    {
                        //type cast the object into an arrayList of shapes 
                        shapes =  (ArrayList<Shape>) clientRequest;
                        System.out.println("Got shapes from client");
                        //add new shapes to the server's database "ArrayLsit"
                        for (Shape shp : shapes)
                        {
                            shapesDB.add(shp);
                        }
                    }
                    else//the client requests to get shapes 
                    {
                        //create a temp ArrayList the will hold the shapes to be sent to the client 
                        ArrayList<Shape> shapesToBeSent = new ArrayList<>();
                        //type cast the client request object into a char
                        char requestShapeType = (char)clientRequest;
                        //call filterShapes method to search for what the client asked for 
                        shapesToBeSent = filterShapes(requestShapeType);
                       //send the results back to the client 
                        out.writeObject(shapesToBeSent);
                        //fulsh the output Stream to make sure no data left in it 
                        out.flush();
                        System.out.println("sent shapes to client");
                    }
                    
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex.getMessage()); 
                    break;
                } catch (IOException ex) {
                    //in case client suddenly closed the connection save all the data 
                    saveToFile();
                    System.out.println(ex.getMessage());
                    System.out.println(Arrays.toString(ex.getStackTrace()));
                    break;
                }
            }while(true);
    }
    public ArrayList<Shape> filterShapes(char chr)
    {
        //temp array lsit that will hold all the shapes that will be sent to the client 
        ArrayList<Shape> filtterdList = new ArrayList<>();
        //dpending on what the client sent 
        switch (chr) {
            case 'c':
                
                for (Shape tempShape: shapesDB)
                {
                    //check if it is the type of shape that the user asked for. if so add it to the temp list
                    if (tempShape instanceof Circle)
                    {
                        filtterdList.add(tempShape);
                    }
                }   break;
            case 't':
                for (Shape tempShape: shapesDB)
                {
                    if (tempShape instanceof Triangle)
                    {
                        filtterdList.add(tempShape);
                    }
                }   break;
            case 'r':
                for (Shape tempShape: shapesDB)
                {
                    if (tempShape instanceof Rectangle)
                    {
                        filtterdList.add(tempShape);
                    }
                }   break;
            case 'y':
                for (Shape tempShape: shapesDB)
                {
                    if (tempShape instanceof Cylinder)
                    {
                        filtterdList.add(tempShape);
                    }
                }   break;
            case 's':
                for (Shape tempShape: shapesDB)
                {
                    if (tempShape instanceof Sphere)
                    {
                        filtterdList.add(tempShape);
                    }
                }   break;
                case 'a':
                for (Shape tempShape: shapesDB)
                {
                        filtterdList.add(tempShape);
                }   break;
            default:
                System.out.println("Error unknowen option");
        }
        return filtterdList;
    }
    public void saveToFile()
    {
        try {
            //create a file blue print
            File backupFile = new File("ShapesData.ser");
             // create New File if file already exists will do nothing
            backupFile.createNewFile();
            //create file output Stream and pass it the new file 
            FileOutputStream oFile = new FileOutputStream(backupFile, false);
            //create new Object outputStream that will be used to serilaize the data that is sent to the file 
            ObjectOutputStream fileStream = new ObjectOutputStream(oFile);
            //write the object to the file 
            fileStream.writeObject(shapesDB);
            fileStream.close();
            oFile.close();
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void readFromFile()
    {
        FileInputStream fileInputStream = null;
        try {
            //create new file input Stream 
            fileInputStream = new FileInputStream("ShapesData.ser");
            //create new object input stream to deserialize the data 
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //read the file and type cast it to arrayList of shapes 
            shapesDB = (ArrayList<Shape>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            
        } catch (FileNotFoundException ex) {
            //exception in case the server had no past records 
            System.out.println("No data to load from file.");
        } catch (IOException | ClassNotFoundException ex) {
             System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void run() {
     runServer();
    }
}
