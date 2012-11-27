package client.domain;

import generic.configuration.Connection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import generic.interfaces.IDataCollector;
import generic.domain.ClientData;

/**
 *
 * @author Vincent Van Driessche
 */
public class DataClient
{

    public DataClient()
    {
        sendCompleteUpdate();
    }
    
    public void sendCompleteUpdate()
    {
                URL url = null;
        try
        {
            url = new URL(
                    Connection.PUBLICATION_URL
                    + ":"
                    + Connection.PORT
                    + "/"
                    + Connection.CHILD
                    + Connection.PARAM);
        }
        catch (MalformedURLException ex)
        {
            System.out.println("**Bad url.");
        }
        QName qualifiedNameOfService = new QName(
                Connection.QUALIFIED_SERVICE.getLocation(),
                Connection.QUALIFIED_SERVICE.getName());

        Service serviceFactory = Service.create(url, qualifiedNameOfService);

        IDataCollector datacollector = serviceFactory.getPort(IDataCollector.class);
        datacollector.setClientData(new ClientData());
    }
}
