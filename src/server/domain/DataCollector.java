package server.domain;

import generic.domain.ClientData;
import generic.interfaces.IDataCollector;
import javax.jws.WebService;

/**
 *
 * @author Vincent Van Driessche
 */
@WebService(endpointInterface = "generic.interfaces.IDataCollector")
public class DataCollector implements IDataCollector
{

    private ClientData clientData;

    public void printClientData()
    {
        System.out.println("Clientdata: " + clientData.getName());
    }

    @Override
    public void setClientData(ClientData clientData)
    {
        this.clientData = clientData;
        printClientData();
    }
}