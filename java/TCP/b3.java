package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
public class b3 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2206;
        String a = "B23DCCN609;a0qxgnqJ";

        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);
            InputStream in  = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            out.write(a.getBytes());
            out.flush();

            byte[] b = new byte[1024];
            int len = in.read(b);
            String data = new String(b,0,len);
            String[] dsso = data.trim().split(",");
            int[] ds =  new int[dsso.length];
            for (int i = 0; i<dsso.length; i++){
                ds[i]= Integer.parseInt(dsso[i]);
            }
            int max= Integer.MIN_VALUE;
            int so2 = Integer.MIN_VALUE;
            for (int i=0; i<ds.length; i++){
                if (ds[i] > max){
                    so2 = max;
                    max = ds[i];
                } else if (ds[i] > so2 && ds[i] < max){
                    so2 = ds[i];
                }
            }
            int idx = -1;
            for (int i=0; i<ds.length; i++){
                if (ds[i] == so2){
                    idx = i;
                    break;
                }
            }
            String kq =so2 + "," + idx;
            out.write(kq.toString().getBytes());
            out.flush();

            socket.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
