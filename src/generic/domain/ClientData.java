package generic.domain;

import generic.interfaces.IClientData;

/**
 *
 * @author Vincent Van Driessche
 */
public class ClientData implements IClientData
{
    
    private String userName, osName;
    
    public ClientData()
    {
        setUserName(System.getProperty("user.name", "Unknown"));
        //setUserName("Edwin");
        setOSName(System.getProperty("os.name", "Unknown") + " (" + System.getProperty("os.version", "") + ")");
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
        return getUserName() + ", " + getOSName();
    }

    @Override
    public String getOSName() {
        return osName;
    }

    @Override
    public void setOSName(String os) {
        osName = os;
    }

    @Override
    public int compareTo(IClientData clientData) {
        return userName.compareTo(clientData.getUserName());
    }
    
    public boolean equals(IClientData clientData) {
        return userName.equals(clientData.getUserName());
    }
}
