
package WS;

import java.util.*;
import ptit.dblab.judge.network.server.soap.*;

public class ws_b2_JC3nbcoV {
    public static void main(String[] args) {
        DataService service = new DataService();
        SoapDataService sv = service.getSoapDataServicePort();

        String studentCode = "B23DCCN609";
        String qCode = "JC3nbcoV";

        List<Integer> a = sv.getData(studentCode, qCode);

        int sum = 0;
        for (int x : a) {
            sum += x;
        }

        sv.submitDataInt(studentCode, qCode, sum);
    }
}
