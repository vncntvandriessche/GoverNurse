package server.domain;

import generic.domain.ClientData;
import generic.interfaces.IDataCollector;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
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

    private void printClientData()
    {
        Iterator<ClientData> iterator = clientData.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next().toString());
        }
    }

    @Override
    public void setClientData(ClientData clientData)
    {
        this.clientData.remove(clientData);
        this.clientData.add(clientData);
        System.out.println("*****");
        printClientData();
    }

    @Override
    public void removeClientData(ClientData clientData)
    {
        this.clientData.remove(clientData);
        System.out.println("*****");
        printClientData();
    }

    @Override
    public ClientData[] getClientDataList()
    {
        System.out.println("Sending List:");
        printClientData();
        return clientData.toArray(new ClientData[clientData.size()]);
    }
}