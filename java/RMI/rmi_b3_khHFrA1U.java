
package RMI;

import java.rmi.registry.*;
import java.net.URI;

public class rmi_b3_khHFrA1U {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

        String studentCode = "B23DCCN609";
        String qCode = "khHFrA1U";

        String data = service.requestCharacter(studentCode, qCode);

        String result = new URI(null, null, data, null).toASCIIString();

        service.submitCharacter(studentCode, qCode, result);
    }
}
