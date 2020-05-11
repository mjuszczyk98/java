/**
 * TCPSenderServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package soap;

public class TCPSenderServiceLocator extends org.apache.axis.client.Service implements soap.TCPSenderService {

    public TCPSenderServiceLocator() {
    }


    public TCPSenderServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TCPSenderServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TCPSender
    private java.lang.String TCPSender_address = "http://localhost:8080/Lab7_SOAP/services/TCPSender";

    public java.lang.String getTCPSenderAddress() {
        return TCPSender_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TCPSenderWSDDServiceName = "TCPSender";

    public java.lang.String getTCPSenderWSDDServiceName() {
        return TCPSenderWSDDServiceName;
    }

    public void setTCPSenderWSDDServiceName(java.lang.String name) {
        TCPSenderWSDDServiceName = name;
    }

    public soap.TCPSender getTCPSender() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TCPSender_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTCPSender(endpoint);
    }

    public soap.TCPSender getTCPSender(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            soap.TCPSenderSoapBindingStub _stub = new soap.TCPSenderSoapBindingStub(portAddress, this);
            _stub.setPortName(getTCPSenderWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTCPSenderEndpointAddress(java.lang.String address) {
        TCPSender_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (soap.TCPSender.class.isAssignableFrom(serviceEndpointInterface)) {
                soap.TCPSenderSoapBindingStub _stub = new soap.TCPSenderSoapBindingStub(new java.net.URL(TCPSender_address), this);
                _stub.setPortName(getTCPSenderWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TCPSender".equals(inputPortName)) {
            return getTCPSender();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap", "TCPSenderService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap", "TCPSender"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TCPSender".equals(portName)) {
            setTCPSenderEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
