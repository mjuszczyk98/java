/**
 * TCPSender.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package soap;

public interface TCPSender extends java.rmi.Remote {
    public void remove(java.lang.String port) throws java.rmi.RemoteException;
    public boolean register(java.lang.String port) throws java.rmi.RemoteException;
    public java.lang.String getNextStep(java.lang.String port) throws java.rmi.RemoteException;
}
