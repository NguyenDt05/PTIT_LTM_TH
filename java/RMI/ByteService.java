
package RMI;

import java.rmi.*;

public interface ByteService extends Remote {
    byte[] requestData(String studentCode, String qCode) throws RemoteException;
    void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
