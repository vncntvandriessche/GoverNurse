package server.cli;

import server.endpoint.DataCollectorPublisher;

/**
 *
 * @author Vincent Van Driessche
 */
public class CollectorCli
{
    
    public CollectorCli()
    {
        System.out.println("Publishing dataserver...");
        new DataCollectorPublisher();
    }
}
