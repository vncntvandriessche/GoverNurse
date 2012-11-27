package generic.interfaces;

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
}
