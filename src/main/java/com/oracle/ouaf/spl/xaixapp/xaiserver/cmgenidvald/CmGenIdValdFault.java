
package com.oracle.ouaf.spl.xaixapp.xaiserver.cmgenidvald;

import javax.xml.ws.WebFault;
import com.oracle.ouaf.Fault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "Fault", targetNamespace = "http://ouaf.oracle.com/")
public class CmGenIdValdFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private Fault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public CmGenIdValdFault(String message, Fault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public CmGenIdValdFault(String message, Fault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.oracle.ouaf.Fault
     */
    public Fault getFaultInfo() {
        return faultInfo;
    }

}
