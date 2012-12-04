package server.domain;

import generic.domain.ClientData;
import generic.interfaces.IDataCollector;
import java.util.TreeSet;
import javax.jws.WebService;

/**
 *
 * @author Vincent Van Driessche
 */
@WebService(endpointInterface = "generic.interfaces.IDataCollector")
public class DataCollector implements IDataCollector
{

    private TreeSet<ClientData> clientData = new TreeSet<ClientData>();

<<<<<<< HEAD
    private void printClientData()
    {
        Iterator<ClientData> iterator = clientData.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next().getMacList());
        }
    }

=======
>>>>>>> 90b61a36f6f7c856a90197cfcc91ab0f98ce839e
    @Override
    public void setClientData(ClientData clientData)
    {
        this.clientData.remove(clientData);
        this.clientData.add(clientData);
        System.out.println("*****");
        showRegisteredClients();
    }

    @Override
    public void removeClientData(ClientData clientData)
    {
        this.clientData.remove(clientData);
        System.out.println("*****");
        showRegisteredClients();
    }

    @Override
    public ClientData[] getClientDataList()
    {
        System.out.println("Sending List:");
        showRegisteredClients();
        return clientData.toArray(new ClientData[clientData.size()]);
    }

    private void showRegisteredClients()
    {
<<<<<<< HEAD
        ClientData[] clientDataList = clientData.toArray(new ClientData[clientData.size()]);

        System.out.println("Showing all registered clients");
        for (ClientData client : clientDataList)
=======
        System.out.println("Showing all registered clients");
        for(ClientData client: clientData)
>>>>>>> 90b61a36f6f7c856a90197cfcc91ab0f98ce839e
        {
            System.out.println(String.format("\n%s - %s", client.getUserName(), client.getOSName()));
        }
    }
}