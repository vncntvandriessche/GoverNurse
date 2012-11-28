package generic.domain;

import generic.domain.Process;
import generic.interfaces.IClientData;
import java.util.ArrayList;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author Vincent Van Driessche
 */
public class ClientData implements IClientData
{

    private Sigar sigar = new Sigar();
    private String userName, osName;
    private ArrayList<Process> processList = new ArrayList<Process>();

    public ClientData()
    {
        setUserName(System.getProperty("user.name", "Unknown"));
        setOSName(System.getProperty("os.name", "Unknown") + " (" + System.getProperty("os.version", "") + ")");
        setProcessList();
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public void setUserName(String user)
    {
        this.userName = user;
    }

    @Override
    public String toString()
    {
        //return getUserName() + ", " + getOSName() + "\n" +;
        String processes = "\nProcessList:";
        for(Process process : getProcessList())
        {
            processes = processes + String.format("\n%d - %s", process.getId(), process.getName());
        }
        return String.format("%s, %s%s", getUserName(), getOSName(), processes);
    }

    @Override
    public String getOSName()
    {
        return osName;
    }

    @Override
    public void setOSName(String os)
    {
        osName = os;
    }

    public ArrayList<Process> getProcessList()
    {
        return processList;
    }

    @Override
    public void setProcessList()
    {
        long[] procLijst = null;
        try
        {
            procLijst = sigar.getProcList();

            for (long proc : procLijst)
            {
                processList.add(new Process(proc, sigar.getProcState(proc).getName()));
            }
        }
        catch (SigarException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public int compareTo(IClientData clientData)
    {
        return userName.compareTo(clientData.getUserName());
    }

    public boolean equals(IClientData clientData)
    {
        return userName.equals(clientData.getUserName());
    }
}
