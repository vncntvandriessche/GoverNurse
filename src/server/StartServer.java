package server;

import java.io.IOException;
import java.net.BindException;
import java.net.SocketException;
import server.endpoint.DataCollectorPublisher;

/**
 *
 * @author Vincent Van Driessche
 */
public class StartServer //SERVER
{
    public StartServer()
    {
        System.out.println("Publishing server...");
        try{
            new DataCollectorPublisher();
        }catch(Exception be){
            System.out.println("The server already seems to be running (Or a different service is using the resources needed).");
            System.exit(4);
        }
    }
    
    public static void main(String[] args)
    {
        new StartServer();
    }
}
