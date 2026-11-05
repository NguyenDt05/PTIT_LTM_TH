
package WS;

import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class ws_b1_d9kELEa5 {
    public static void main(String[] args) {
        DataService service = new DataService();
        SoapDataService sv = service.getSoapDataServicePort();

        String studentCode = "B23DCCN609";
        String qCode = "d9kELEa5";

        List<Integer> a = sv.getData(studentCode, qCode);
        List<String> numbers = new ArrayList<>();

        for (int x : a) {
            numbers.add(String.valueOf(x));
        }

        numbers.sort((x, y) -> (y + x).compareTo(x + y));

        String result = String.join("", numbers);
        sv.submitDataString(studentCode, qCode, result);
    }
}
