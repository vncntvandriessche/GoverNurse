package server;

import server.endpoint.DataCollectorPublisher;
import server.rmi.CommandServer;
import server.rmi.TaskClient;

/**
 *
 * @author Vincent Van Driessche
 */
public class StartServer //SERVER
{
    public StartServer()
    {
        System.out.println("Publishing server...");
        try {
            new DataCollectorPublisher();
        } catch(Exception be) {
            System.out.println("The server already seems to be running (Or a different service is using the resources needed).");
            System.exit(4);
        }
        new CommandServer();
        new TaskClient();
    }
    
    public static void main(String[] args)
    {
        new StartServer();
    }
}
