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
        System.out.println("Showing all registered clients");
        for(ClientData client: clientData)
        {
            System.out.println(String.format("\n%s - %s", client.getUserName(), client.getOSName()));
        }
    }
}