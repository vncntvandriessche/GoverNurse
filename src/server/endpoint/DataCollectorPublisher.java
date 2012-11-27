package server.endpoint;

import javax.xml.ws.Endpoint;
import server.domain.DataCollector;

/**
 *
 * @author vincent
 */
public class DataCollectorPublisher
{

    public static final String PUBLICATION_URL = "http://127.0.0.1";
    public static final int PORT = 9876;
    public static final String CHILD = "dataserver";
    public static final Object INSTANCE = (new DataCollector());

    public DataCollectorPublisher()
    {
        Endpoint.publish((PUBLICATION_URL + ":" + PORT + "/" + CHILD), INSTANCE);
    }
}