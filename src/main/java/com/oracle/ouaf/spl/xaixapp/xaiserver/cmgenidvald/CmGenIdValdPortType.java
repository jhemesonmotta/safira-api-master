
package com.oracle.ouaf.spl.xaixapp.xaiserver.cmgenidvald;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import com.oracle.cmgenidvald.CmGenIdVald;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CmGenIdValdPortType", targetNamespace = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmGenIdVald")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    com.oracle.ouaf.ObjectFactory.class,
    com.oracle.cmgenidvald.ObjectFactory.class
})
public interface CmGenIdValdPortType {


    /**
     * 
     * @param body
     * @throws CmGenIdValdFault
     */
    @WebMethod(operationName = "CmGenIdVald", action = "http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmGenIdVald")
    public void cmGenIdVald(
        @WebParam(name = "CmGenIdVald", targetNamespace = "http://oracle.com/CmGenIdVald.xsd", mode = WebParam.Mode.INOUT, partName = "body")
        Holder<CmGenIdVald> body)
        throws CmGenIdValdFault
    ;

}
