package server.endpoint;

import generic.configuration.Connection;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Vincent Van Driessche
 */
public class DataCollectorPublisher
{

    public DataCollectorPublisher()
    {
        Endpoint.publish((Connection.PUBLICATION_URL + ":" + Connection.PORT + "/" + Connection.CHILD), Connection.COLLECTOR_INSTANCE);
    }
}