/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generic;

/**
 *
 * @author vincent
 */
import generic.domain.ClientData;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author vincent
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface IDataCollector
{
    @WebMethod
    void setClientData( ClientData clientData );
}
