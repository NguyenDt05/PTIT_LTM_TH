
package RMI;

import java.rmi.registry.*;

public class rmi_b4_99Gw3mrh {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

        String studentCode = "B23DCCN609";
        String qCode = "99Gw3mrh";

        String data = service.requestCharacter(studentCode, qCode);
        int n = Integer.parseInt(data.trim());

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L",
                "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (n >= values[i]) {
                result.append(romans[i]);
                n -= values[i];
            }
        }

        service.submitCharacter(studentCode, qCode, result.toString());
    }
}
