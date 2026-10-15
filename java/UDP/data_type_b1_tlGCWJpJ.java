package UDP;
import java.net.*;
import java.util.*;

public class data_type_b1_tlGCWJpJ {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server = InetAddress.getByName("36.50.135.242");

        String msg = ";B23DCCN609;tlGCWJpJ";
        byte[] data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2207));

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());
        String[] parts = response.split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1]);

        Set<Integer> numbers = new HashSet<>();
        for (String s : parts[2].split(",")) {
            numbers.add(Integer.parseInt(s.trim()));
        }

        StringJoiner result = new StringJoiner(",");
        for (int i = 1; i <= n; i++) {
            if (!numbers.contains(i)) {
                result.add(String.valueOf(i));
            }
        }

        msg = requestId + ";" + result;
        data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2207));

        socket.close();
    }
}
