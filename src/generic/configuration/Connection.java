package generic.configuration;

import generic.domain.Service;
import server.domain.DataCollector;

/**
 *
 * @author Vincent Van Driessche
 */
public final class Connection
{
    public static final String PUBLICATION_URL = "http://127.0.0.1";
    public static final int PORT = 8889;
    public static final String CHILD = "dataserver";
    public static final String PARAM = "?wsdl";
    
    public static final Service QUALIFIED_SERVICE = new Service("http://domain.server/", "DataCollectorService");
    
    public static final Object COLLECTOR_INSTANCE = (new DataCollector());
}