
package RMI;

import java.rmi.registry.*;

public class rmi_b1_ZQ1B7gMy {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ByteService service = (ByteService) registry.lookup("RMIByteService");

        String studentCode = "B23DCCN609";
        String qCode = "ZQ1B7gMy";

        byte[] data = service.requestData(studentCode, qCode);

        int[] count = new int[256];
        for (byte b : data) {
            count[b & 0xFF]++;
        }

        byte result = 0;
        int min = Integer.MAX_VALUE;

        for (byte b : data) {
            if (count[b & 0xFF] < min) {
                min = count[b & 0xFF];
                result = b;
            }
        }

        byte[] answer = {result, (byte) min};
        service.submitData(studentCode, qCode, answer);
    }
}
