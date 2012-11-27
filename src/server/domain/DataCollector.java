package server.domain;

import generic.domain.ClientData;
import generic.interfaces.IDataCollector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.jws.WebService;

/**
 *
 * @author Vincent Van Driessche
 */
@WebService(endpointInterface = "generic.interfaces.IDataCollector")
public class DataCollector implements IDataCollector
{

    private Set<ClientData> clientData = new TreeSet<ClientData>();

    public void printClientData()
    {
        System.out.println("*****");
        Iterator<ClientData> iterator = clientData.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }

    @Override
    public void setClientData(ClientData clientData)
    {
        this.clientData.add(clientData);
        printClientData();
    }
}