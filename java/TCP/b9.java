package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
public class b9 {

    static void gui(SocketChannel sc, String s) throws Exception {
        byte[] data = s.getBytes(StandardCharsets.UTF_8);

        ByteBuffer b = ByteBuffer.allocate(4 + data.length);
        b.putInt(data.length);
        b.put(data);
        b.flip();

        while (b.hasRemaining())
            sc.write(b);
    }

    static String nhan(SocketChannel sc) throws Exception {

        // Doc du 4 byte do dai
        ByteBuffer lenBuf = ByteBuffer.allocate(4);

        while (lenBuf.hasRemaining()) {
            if (sc.read(lenBuf) == -1)
                throw new Exception();
        }

        lenBuf.flip();
        int len = lenBuf.getInt();

        // Doc du payload
        ByteBuffer dataBuf = ByteBuffer.allocate(len);

        while (dataBuf.hasRemaining()) {
            if (sc.read(dataBuf) == -1)
                throw new Exception();
        }

        dataBuf.flip();

        byte[] data = new byte[len];
        dataBuf.get(data);

        return new String(data, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {

        String host = "36.50.135.242";
        int port = 2211;
        String ma = "B23DCCN609;PjJNuVqZ";

        try {
            SocketChannel sc = SocketChannel.open(
                    new InetSocketAddress(host, port)
            );

            // a. Gui frame dau
            gui(sc, ma);

            // b. Nhan 3 frame
            String s = "";

            for (int i = 0; i < 3; i++) {
                s += nhan(sc);
            }

            // c. Tinh do dai va checksum
            int len = s.length();

            long checksum = 0;
            for (char c : s.toCharArray()) {
                checksum += c;
            }

            checksum %= 100000;

            // d. Gui ket qua
            String kq = "len=" + len + ";checksum=" + checksum;
            gui(sc, kq);

            sc.close();

        } catch (Exception e) {
        }
    }
}