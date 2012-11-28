package client.domain;

import generic.configuration.Connection;
import generic.domain.ClientData;
import generic.interfaces.IDataCollector;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TreeSet;
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
        data = new ClientData();
        
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

        collector = serviceFactory.getPort(IDataCollector.class);
    }
    
    public void sendCompleteUpdate()
    {
        collector.setClientData(data);
    }
    
    public String getClientData(){
        return data.toString();
    }
    
    public void updateClientData(){
        data = new ClientData();
    }
    
    public void removeClientData(){
        //verwijdert ClientData van de NETWERK, en niet van DATACLIENT (behalve als wij opteren om beide te doen, maar dat veroorzaakt problemen zoals NullPointerException's)
        collector.removeClientData(data);
    }
    
    public String getNetwork(){
        TreeSet<ClientData> list = collector.getClientDataList();
        String ret = list.size() + " clients logged on: ";
        for (ClientData data : list){
            ret = ret + "[" + data.toString() + "] ";
        }
        return ret;
    }
}
