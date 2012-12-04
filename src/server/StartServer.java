package server;

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
        new DataCollectorPublisher();
    }
    
    public static void main(String[] args)
    {
        new StartServer();
    }
}
