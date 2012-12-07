package server.rmi;

import generic.configuration.Connection;
import generic.domain.ClientData;
import generic.interfaces.IMathSolver;
import generic.interfaces.ITaskCommander;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.SortedSet;

/**
 *
 * @author Rapsey
 */
public class TaskCommander extends UnicastRemoteObject implements ITaskCommander {

    protected TaskCommander() throws RemoteException {
        super();
    }

    @Override
    public BigDecimal getPi(Integer cycles, Integer scale) throws RemoteException {
        String serverAddress = getBestClientAddress();
        int port = 9901;
        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress, port);
            IMathSolver mathServer = (IMathSolver)(registry.lookup("MathSolver"));
            return mathServer.getPi(cycles, scale);
        } catch(RemoteException e){
            System.err.println(e);
        } catch(NotBoundException e){
            System.err.println(e);
        }
        return null;
    }

    private String getBestClientAddress() {
        String ip = "127.0.0.1";
        int procs, limit = Integer.MAX_VALUE;
        SortedSet<ClientData> clientDataSet = Connection.COLLECTOR_INSTANCE.getClientDataSet();
        for (ClientData cd : clientDataSet) {
            procs = cd.getProcessList().size();
            if(procs < limit) {
                System.out.println("Procs on "+cd.getRemoteAddress()+" are "+procs+"/"+limit);
                ip = cd.getRemoteAddress();
                limit = procs;
            }
        }
        return ip;
    }

    @Override
    public void forceUpdate() throws RemoteException {
        String serverAddress = getBestClientAddress();
        int port = 9901;
        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress, port);
            IMathSolver mathServer = (IMathSolver)(registry.lookup("MathSolver"));
            mathServer.forceUpdate();
        } catch(RemoteException e){
            System.err.println(e);
        } catch(NotBoundException e){
            System.err.println(e);
        }
    }
}
