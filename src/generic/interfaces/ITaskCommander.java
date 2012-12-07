package generic.interfaces;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Rapsey
 */
public interface ITaskCommander extends Remote {
    BigDecimal getPi(Integer cycles, Integer scale) throws RemoteException;
    void forceUpdate() throws RemoteException;
}
