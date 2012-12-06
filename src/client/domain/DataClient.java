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
 * Connects to the server endpoint and calls upon the resource when requested.
 * @author 
 */
public class DataClient
{

    private ClientData data;
    private IDataCollector collector;

    /**
     * Creates a new instance of this class, setting up the necessary resources.
     */
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

    /**
     * Sends a complete copy of a client's data to the server.
     */
    public void sendCompleteUpdate()
    {
        collector.setClientData(data);
    }

    /**
     * Makes a human-readable representation of the client's data.
     * @return : The human readable version of the client's data.
     */
    public String getClientData()
    {
        return data.toString();
    }

    /**
     * Creates a new representation of the client's data, with up-to-date information.
     */
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

    /**
     * Deletes the client's information from the server.
     */
    public void removeClientData()
    {
        //verwijdert ClientData van de NETWERK, en niet van DATACLIENT (behalve als wij opteren om beide te doen, maar dat veroorzaakt problemen zoals NullPointerException's)
        collector.removeClientData(data);
    }

    /**
     * Requests a list of information for clients who are currently logged in to the network.
     * @return : A List of all the currently-logged-on clients. 
     */
    public List<ClientData> getNetwork()
    {
        ClientData[] list = collector.getClientDataList();
        return Arrays.asList(list);
    }
}
