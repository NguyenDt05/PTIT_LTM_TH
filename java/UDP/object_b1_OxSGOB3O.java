
package UDP;

import java.net.*;
import java.io.*;
import java.util.*;

public class object_b1_OxSGOB3O {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server = InetAddress.getByName("36.50.135.242");

        byte[] data = ";B23DCCN609;OxSGOB3O".getBytes();
        socket.send(new DatagramPacket(data, data.length, server, 2209));

        byte[] buffer = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String requestId = new String(packet.getData(), 0, 8);
        ByteArrayInputStream bis = new ByteArrayInputStream(
                packet.getData(), 8, packet.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Student student = (Student) ois.readObject();

        String[] words = student.name.trim().toLowerCase().split("\\s+");
        StringBuilder name = new StringBuilder();
        StringBuilder email = new StringBuilder(words[words.length - 1]);

        for (int i = 0; i < words.length; i++) {
            name.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1)).append(" ");

            if (i < words.length - 1) {
                email.append(words[i].charAt(0));
            }
        }

        student.name = name.toString().trim();
        student.email = email + "@ptit.edu.vn";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(requestId.getBytes());
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(student);
        oos.flush();

        data = bos.toByteArray();
        socket.send(new DatagramPacket(data, data.length, server, 2209));

        socket.close();
    }
}
