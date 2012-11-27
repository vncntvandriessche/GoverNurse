package generic.domain;

/**
 *
 * @author Vincent Van Driessche
 */
public class Service
{
    private String location;
    private String name;
    
    public Service(String location, String name)
    {
        setLocation(location);
        setName(name);
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
