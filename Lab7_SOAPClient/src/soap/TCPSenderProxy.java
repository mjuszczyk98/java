package soap;

public class TCPSenderProxy implements soap.TCPSender {
  private String _endpoint = null;
  private soap.TCPSender tCPSender = null;
  
  public TCPSenderProxy() {
    _initTCPSenderProxy();
  }
  
  public TCPSenderProxy(String endpoint) {
    _endpoint = endpoint;
    _initTCPSenderProxy();
  }
  
  private void _initTCPSenderProxy() {
    try {
      tCPSender = (new soap.TCPSenderServiceLocator()).getTCPSender();
      if (tCPSender != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tCPSender)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tCPSender)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tCPSender != null)
      ((javax.xml.rpc.Stub)tCPSender)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public soap.TCPSender getTCPSender() {
    if (tCPSender == null)
      _initTCPSenderProxy();
    return tCPSender;
  }
  
  public boolean register(java.lang.String port) throws java.rmi.RemoteException{
    if (tCPSender == null)
      _initTCPSenderProxy();
    return tCPSender.register(port);
  }
  
  public java.lang.String getNextStep(java.lang.String port) throws java.rmi.RemoteException{
    if (tCPSender == null)
      _initTCPSenderProxy();
    return tCPSender.getNextStep(port);
  }
  
  public void remove(java.lang.String port) throws java.rmi.RemoteException{
    if (tCPSender == null)
      _initTCPSenderProxy();
    tCPSender.remove(port);
  }
  
  
}