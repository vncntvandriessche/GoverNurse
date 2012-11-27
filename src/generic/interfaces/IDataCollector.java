package generic.interfaces;

import generic.domain.ClientData;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author Vincent Van Driessche
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface IDataCollector
{

    @WebMethod
    void setClientData(ClientData clientData);
}