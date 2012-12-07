package client.rmi;

import client.domain.DataClient;
import generic.interfaces.IMathSolver;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Rapsey
 */
public class MathSolver extends UnicastRemoteObject implements IMathSolver {

    DataClient client;

    protected MathSolver() throws RemoteException {
        super();
    }
    
    protected MathSolver(DataClient client) throws RemoteException{
        super();
        this.client = client;
    }
    
    @Override
    public BigDecimal getPi(Integer cycles, Integer scale) throws RemoteException {
        BigDecimal pi = new BigDecimal(4);
        BigDecimal four = pi;
        boolean plus = false;
        for (int i = 3; i < cycles; i += 2) {
            if (plus) {
                pi = pi.add(four.divide(new BigDecimal(i), scale, RoundingMode.HALF_UP));
            } else {
                pi = pi.subtract(four.divide(new BigDecimal(i), scale, RoundingMode.HALF_UP));
            }
            plus = !plus;
        }
        return pi;
    }
    
    @Override
    public void forceUpdate() throws RemoteException{
        client.updateClientData();
        client.sendCompleteUpdate();
    }
}
