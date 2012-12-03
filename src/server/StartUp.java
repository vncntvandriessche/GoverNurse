package server;

import server.endpoint.DataCollectorPublisher;

/**
 *
 * @author Vincent Van Driessche
 */
public class StartUp //SERVER
{
    public StartUp()
    {
        System.out.println("Publishing server...");
        new DataCollectorPublisher();
    }
    
    public static void main(String[] args)
    {
        new StartUp();
    }
}
