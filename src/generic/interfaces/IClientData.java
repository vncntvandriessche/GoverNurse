package generic.interfaces;

import generic.domain.Process;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Vincent Van Driessche
 */
public interface IClientData extends Comparable<IClientData>
{
    String getUserName();
    void setUserName(String user);
    String getOSName();
    void setOSName(String os);
    void setProcessList();
    ArrayList<Process> getProcessList();
    ArrayList<String> getMacList();
    void setMacList() throws SocketException;
}
