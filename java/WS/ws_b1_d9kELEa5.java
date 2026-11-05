
package WS;

import java.util.*;

public class ws_b1_d9kELEa5 {
    public static void main(String[] args) throws Exception {
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();

        String studentCode = "B23DCCN609";
        String qCode = "d9kELEa5";

        List<Integer> data = port.getData(studentCode, qCode);
        List<String> numbers = new ArrayList<>();

        for (int n : data) {
            numbers.add(String.valueOf(n));
        }

        numbers.sort((a, b) -> (b + a).compareTo(a + b));

        String result = String.join("", numbers);
        port.submitDataString(studentCode, qCode, result);
    }
}
