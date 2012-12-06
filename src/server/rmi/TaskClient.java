package server.rmi;

import generic.configuration.Connection;
import generic.domain.ClientData;
import generic.interfaces.IMathSolver;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rapsey
 */
public class TaskClient extends Thread {
    public TaskClient() {
        super("TaskClient");
        //run();
    }
    
    @Override
    public void run() {
        while(true) {
            SortedSet<ClientData> clientDataSet = Connection.COLLECTOR_INSTANCE.getClientDataSet();
            for (ClientData cd : clientDataSet) {
                break;
            }
            String serverAddress = "127.0.0.1";
            int port = 9901;
            try {
                Registry registry = LocateRegistry.getRegistry(serverAddress, port);
                for (String s : registry.list())
                    System.out.println("Found service: "+s);
                IMathSolver mathServer = (IMathSolver)(registry.lookup("MathSolver"));
                System.out.println("Pi: " + mathServer.getPi(10000000, 100));
            } catch(RemoteException e){
                System.err.println(e);
            } catch(NotBoundException e){
                System.err.println(e);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
