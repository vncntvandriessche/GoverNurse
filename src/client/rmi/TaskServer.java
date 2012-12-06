package client.rmi;

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
        port = 1099;
        run();
    }
    
    public TaskServer(int port) {
        this.port = port;
        run();
    }
    
    private void run() {
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
    }
}
