package UDP;

import java.net.*;
import java.util.*;

public class data_type_b2_6582m4L5 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server = InetAddress.getByName("36.50.135.242");

        String msg = ";B23DCCN609;6582m4L5";
        byte[] data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2207));

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());
        String[] parts = response.split(";");

        String requestId = parts[0];
        String[] numbers = parts[1].split(",");

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (String s : numbers) {
            int n = Integer.parseInt(s.trim());
            min = Math.min(min, n);
            max = Math.max(max, n);
        }

        msg = requestId + ";" + max + "," + min;
        data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2207));

        socket.close();
    }
}

