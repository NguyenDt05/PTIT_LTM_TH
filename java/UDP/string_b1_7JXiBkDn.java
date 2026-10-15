package UDP;


import java.net.*;

public class string_b1_7JXiBkDn {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server = InetAddress.getByName("36.50.135.242");

        String msg = ";B23DCCN609;7JXiBkDn";
        byte[] data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2208));

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());
        String[] parts = response.split(";", 2);
        String requestId = parts[0];
        String str = parts[1];

        int[] count = new int[65536];
        char ch = 0;
        int max = 0;

        for (char c : str.toCharArray()) {
            count[c]++;
            if (count[c] > max) {
                max = count[c];
                ch = c;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                result.append(i+1).append(",");
            }
        }

        msg = requestId + ";" + ch + ":" + result;
        data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2208));

        socket.close();
    }
}
