package generic.domain;

import generic.interfaces.IClientData;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
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
    private final ArrayList<String> macList = new ArrayList<String>();

    public ClientData() throws SocketException
    {
        setUserName(System.getProperty("user.name", "Unknown"));
        setOSName(System.getProperty("os.name", "Unknown") + " (" + System.getProperty("os.version", "") + ")");
        setProcessList();
        setMacList();
    }

    @Override
    public void setMacList() throws SocketException
    {
        ArrayList<NetworkInterface> interfaceList = Collections.list(NetworkInterface.getNetworkInterfaces());

        for (NetworkInterface currentInterface : interfaceList)
        {
            if (!currentInterface.isLoopback() && !currentInterface.isVirtual()
                    && currentInterface.getHardwareAddress() != null
                    && currentInterface.getHardwareAddress().length > 0
                    && currentInterface.getInterfaceAddresses().size() > 0
                    && !currentInterface.getDisplayName().toLowerCase().contains("teredo")
                    && !currentInterface.getDisplayName().toLowerCase().contains("tunnel")
                    && !macToString(currentInterface.getHardwareAddress()).equals("020054554E01")
                    && !macToString(currentInterface.getHardwareAddress()).equals("000000000000"))
            {
                System.out.println(currentInterface.getHardwareAddress());
                macList.add(macToString(currentInterface.getHardwareAddress()));
            }
        }
    }

    private String macToString(byte[] mb)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mb.length; i++)
        {
            sb.append(String.format("%02X:", mb[i]));
        }
        String mac = sb.toString();
        if (mac == null)
        {
            mac = "00:00:00:00:00:00";
            //Throw exception??
        }
        else if (mac.length() > 12)
        {
            mac = mac.substring(0, 12);
        }
        else
        {
            while (mac.length() < 12)
            {
                mac = "0" + mac;
            }
        }
        return mac;
    }

    @Override
    public ArrayList<String> getMacList()
    {
        return macList;
    }

    @Override
    public void setUserName(String user)
    {
        this.userName = user;
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public void setOSName(String os)
    {
        osName = os;
    }

    @Override
    public String getOSName()
    {
        return osName;
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
    public ArrayList<Process> getProcessList()
    {
        return processList;
    }

    @Override
    public String toString()
    {
        //return getUserName() + ", " + getOSName() + "\n" +;
        String processes = "\nProcessList:";
        for (Process process : getProcessList())
        {
            processes = processes + String.format("\n%d - %s", process.getId(), process.getName());
        }
        
        String identifier = "";
        for(String currentMac : macList)
        {
            identifier = String.format("%s-%s", identifier, currentMac);
        }
        return String.format("*******%s: %s********\n%s, %s%s\n", "Identifier", identifier,getUserName(), getOSName(), processes);
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
