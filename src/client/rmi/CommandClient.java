package client.rmi;

import generic.configuration.Connection;
import generic.interfaces.IMathSolver;
import generic.interfaces.ITaskCommander;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Rapsey
 */
public class CommandClient {
    public CommandClient() {
        super();
    }

    public BigDecimal getPi(Integer cycles, Integer scale) {
        String serverAddress = Connection.SERVER_IP;
        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress);
            ITaskCommander taskCommander = (ITaskCommander)(registry.lookup("TaskCommander"));
            return taskCommander.getPi(cycles, scale);
        } catch(RemoteException e){
            System.err.println(e);
        } catch(NotBoundException e){
            System.err.println(e);
        }
        return null;
    }
}
