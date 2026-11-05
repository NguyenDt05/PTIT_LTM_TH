
package RMI;

import java.rmi.*;

public interface CharacterService extends Remote {
    String requestCharacter(String studentCode, String qCode) throws RemoteException;
    void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}
