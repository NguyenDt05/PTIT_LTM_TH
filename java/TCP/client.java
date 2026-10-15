package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
public class client {
    public static void main(String[] args){
        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCCN609";
        String qCode = "ky4jwPJW";

        try {
            // 1 connect server
            Socket socket = new Socket(host,port);
            socket.setSoTimeout(5000);
            // 2 read
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            // 3 write
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            //4 send
            out.write(studentCode + ";" + qCode);
            out.newLine();
            out.flush();
            //5 nhan dl
            String data = in.readLine();

            //6 tach ds ten mien
            String[] miens= data.split(",\\s*");
            StringBuilder kq = new StringBuilder();

            // 7 tim ten mien .edu
            for (String i: miens){
                i = i.trim();
                if (i.endsWith(".edu")){
                    if (kq.length() > 0){
                        kq.append(", ");
                    }
                    kq.append(i);
                }
            }

            // 8 send kq
            out.write(kq.toString());
            out.newLine();
            out.flush();

            // 9 dong connect
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}