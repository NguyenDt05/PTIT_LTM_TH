package UDP;


import java.net.*;

public class string_b2_Hm6KnJGp {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server = InetAddress.getByName("36.50.135.242");

        String msg = ";B23DCCN609;Hm6KnJGp";
        byte[] data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2208));

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());
        String[] parts = response.split(";", 2);
        String requestId = parts[0];
        String str = parts[1].trim().toLowerCase();

        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }

        msg = requestId + ";" + result.toString().trim();
        data = msg.getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2208));

        socket.close();
    }
}
