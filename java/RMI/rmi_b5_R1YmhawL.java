
package RMI;

import java.rmi.registry.*;
import java.util.*;

public class rmi_b5_R1YmhawL {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService service = (DataService) registry.lookup("RMIDataService");

        String studentCode = "B23DCCN609";
        String qCode = "RlYmhdwL";

        Object data = service.requestData(studentCode, qCode);
        if (data == null) {
            System.err.println("Server tra ve null. Kiem tra ma sinh vien, ma cau hoi hoac server.");
            return;
        }

        if (!(data instanceof int[] a)) {
            System.err.println("Kieu du lieu khong phai int[]: " + data.getClass().getName());
            return;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            boolean left = i == 0 || a[i] > a[i - 1];
            boolean right = i == a.length - 1 || a[i] > a[i + 1];

            if (left && right) {
                result.add(i + 1);
            }
        }

        service.submitData(studentCode, qCode, result);
    }
}
