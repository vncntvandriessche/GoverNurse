package server;

import server.endpoint.DataCollectorPublisher;

/**
 *
 * @author vincent
 */
public class StartUp
{
    public StartUp()
    {
        System.out.println("Publishing dataserver...");
        new DataCollectorPublisher();
    }
    
    public static void main(String[] args)
    {
        new StartUp();
    }
}
