
package UDP;

import java.net.*;
import java.io.*;
import java.util.*;

public class object_b2_drVfljb6 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server = InetAddress.getByName("36.50.135.242");

        byte[] data = ";B23DCCN609;drVfljb6".getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2209));

        byte[] buffer = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String requestId = new String(packet.getData(), 0, 8);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(packet.getData(), 8, packet.getLength() - 8));
        Customer customer = (Customer) ois.readObject();

        String[] words = customer.name.trim().toLowerCase().split("\\s+");
        StringBuilder name = new StringBuilder();
        StringBuilder username = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            name.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1)).append(" ");
            username.append(words[i].charAt(0));
        }

        String lastName = words[words.length - 1];
        customer.name = lastName.toUpperCase() + ", " + name.toString().trim();
        customer.userName = username.append(lastName).toString();

        String[] date = customer.dayOfBirth.split("-");
        customer.dayOfBirth = date[1] + "/" + date[0] + "/" + date[2];

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(requestId.getBytes());
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(customer);
        oos.flush();

        data = bos.toByteArray();
        socket.send(new DatagramPacket(data, data.length, server, 2209));

        socket.close();
    }
}
