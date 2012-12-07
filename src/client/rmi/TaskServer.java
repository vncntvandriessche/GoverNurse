package client.rmi;

import client.domain.DataClient;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Rapsey
 */
public class TaskServer {
    private int port;
    
    
    public TaskServer() {
        this(1099, null);
    }
    
    /*public TaskServer(int port) {
        this.port = port;
        run();
    }*/
    
    public TaskServer(int port, DataClient client){
        this.port = port;
        run(client);
    }
    
    /*private void run() {
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("MathSolver", new MathSolver());
            System.out.println ("TaskServer is ready. ("+port+")");
        } catch (AlreadyBoundException e) {
            System.out.println ("Server failed: " + e);
        } catch (AccessException e) {
            System.out.println ("Server failed: " + e);
        } catch (RemoteException e) {
            System.out.println ("Server failed: " + e);
        }
    }*/
    
    private void run(DataClient client) {
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("MathSolver", new MathSolver(client));
            System.out.println ("TaskServer is ready. ("+port+")");
        } catch (AlreadyBoundException e) {
            System.out.println ("Server failed: " + e);
        } catch (AccessException e) {
            System.out.println ("Server failed: " + e);
        } catch (RemoteException e) {
            System.out.println ("Server failed: " + e);
        }
    }
}
