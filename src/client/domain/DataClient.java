package client.domain;

import generic.configuration.Connection;
import generic.domain.ClientData;
import generic.interfaces.IDataCollector;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Vincent Van Driessche
 */
public class DataClient
{

    private ClientData data;
    private IDataCollector collector;

    public DataClient()
    {
        updateClientData();

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
            //Throw exception instead?
        }
        QName qualifiedNameOfService = new QName(
                Connection.QUALIFIED_SERVICE.getLocation(),
                Connection.QUALIFIED_SERVICE.getName());

        Service serviceFactory = Service.create(url, qualifiedNameOfService);

        collector = serviceFactory.getPort(IDataCollector.class);
    }

    public void sendCompleteUpdate()
    {
        collector.setClientData(data);
    }

    public String getClientData()
    {
        return data.toString();
    }

    public void updateClientData()
    {
        try
        {
            data = new ClientData();
        }
        catch (SocketException socketException)
        {
            System.err.println("Geen identificatie kunnen ophalen");
        }
    }

    public void removeClientData()
    {
        //verwijdert ClientData van de NETWERK, en niet van DATACLIENT (behalve als wij opteren om beide te doen, maar dat veroorzaakt problemen zoals NullPointerException's)
        collector.removeClientData(data);
    }

    public List<ClientData> getNetwork()
    {
        ClientData[] list = collector.getClientDataList();
        return Arrays.asList(list);
    }
}
