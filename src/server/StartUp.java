package server;

import server.cli.CollectorCli;
import server.endpoint.DataCollectorPublisher;

/**
 *
 * @author Vincent Van Driessche
 */
public class StartUp //SERVER
{
    public StartUp()
    {
        new CollectorCli();
    }
    
    public static void main(String[] args)
    {
        new StartUp();
    }
}
