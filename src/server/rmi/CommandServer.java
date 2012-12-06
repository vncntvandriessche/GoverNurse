package server.rmi;

import client.rmi.MathSolver;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Rapsey
 */
public class CommandServer {
    private int port;

    public CommandServer() {
        port = 1099;
        run();
    }

    public CommandServer(int port) {
        this.port = port;
        run();
    }

    private void run() {
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("TaskCommander", new TaskCommander());
            System.out.println ("CommandServer is ready. ("+port+")");
        } catch (AlreadyBoundException e) {
            System.out.println ("Server failed: " + e);
        } catch (AccessException e) {
            System.out.println ("Server failed: " + e);
        } catch (RemoteException e) {
            System.out.println ("Server failed: " + e);
        }
    }
}
