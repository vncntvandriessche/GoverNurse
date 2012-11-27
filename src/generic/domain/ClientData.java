package generic.domain;

import generic.interfaces.IClientData;

/**
 *
 * @author Vincent Van Driessche
 */
public class ClientData
{
    
    private String name;
    
    public ClientData()
    {
        setName("Hallo daar");
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return getName();
    }
    
    
}
