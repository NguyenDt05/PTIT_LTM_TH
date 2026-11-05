
package RMI;

import java.rmi.registry.*;
import java.util.*;

public class rmi_b6_jZ2VXKlN {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService service = (DataService) registry.lookup("RMIDataService");

        String studentCode = "B23DCCN609";
        String qCode = "jZ2VXKlN";

        String data = (String) service.requestData(studentCode, qCode);
        String[] parts = data.split(",");

        double[] a = new double[parts.length];
        double sum = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = Double.parseDouble(parts[i].trim());
            sum += a[i];
        }

        double avg = sum / a.length;
        double variance = 0;

        for (double x : a) {
            variance += Math.pow(x - avg, 2);
        }

        double stddev = Math.sqrt(variance / a.length);

        Arrays.sort(a);
        int index = (int) Math.ceil(a.length * 0.95) - 1;
        double p95 = a[index];

        String result = String.format(Locale.US,
                "average=%.2f;stddev=%.2f;p95=%.2f",
                avg, stddev, p95);

        service.submitData(studentCode, qCode, result);
    }
}
