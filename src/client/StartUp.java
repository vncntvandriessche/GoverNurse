package client;

import client.domain.DataClient;

/**
 *
 * @author Vincent Van Driessche
 */
public class StartUp //CLIENT
{
    public StartUp()
    {
        new DataClient();
    }
    
    public static void main(String[] args)
    {
        new StartUp();
    }
}
