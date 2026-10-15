package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
public class b4 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2207;

        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            DataInputStream in= new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("B23DCCN609;8xvdVmhq");
            out.flush();

            // nhan 2 so tu server
            int a= in.readInt();
            int b= in.readInt();

            int tong = a+b;
            int tich = a*b;
            out.writeInt(tong);
            out.writeInt(tich);
            out.flush();

            socket.close();


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
