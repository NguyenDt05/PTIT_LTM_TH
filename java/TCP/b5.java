package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
public class b5 {
    static String giaima(String str, int s){
        String kq= "";
        for (char c:str.toCharArray()){
            if (c >= 'A' && c <='Z'){
                c = (char) ((c-'A' - s + 26)%26 +'A');
            } else if (c>='a' && c<='z'){
                c= (char)((c- 'a' - s + 26)%26 + 'a');
            }
            kq+=c;
        }
        return kq;
    }

    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2207;
        try {
            // ket noi
            Socket socket = new Socket (host, port);
            socket.setSoTimeout(5000);
            // khai bao
            DataInputStream in= new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            // y a
            out.writeUTF("B23DCCN609;ei7GVjmC");
            out.flush();
            // nhan - y b
            String mh = in.readUTF();
            int s= in.readInt();
             // giai ma - y c
            String kq = giaima(mh, s);
            out.writeUTF(kq);

            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
