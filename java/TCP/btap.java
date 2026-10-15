package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
public class btap {
    public static void main(String[] args) {
        String host  = "36.50.135.242";
        int port = 2206;
        String a = "B23DCCN609;pk304ftK";

        try {
            // 1 connect server
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            // tao lenh doc/ghi
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // send msv - y a
            out.write(a.getBytes());
            out.flush();
            // nhan dl - y b
            byte[] b = new byte[1024];
            int len= in.read(b);
            String data = new String(b, 0, len);

            // tach mang - y c

            String[] so = data.trim().split(",");
            int[] ds= new int[so.length];

            for (int i=0; i<so.length; i++){
                ds[i]= Integer.parseInt(so[i].trim());
            }
            Arrays.sort(ds);

            // tim kc min
            int min= Integer.MAX_VALUE;
            int so1= 0;
            int so2=0;
            for (int i =0 ;i<ds.length - 1; i++){
                int kc = ds[i+1] - ds[i];
                if (kc <min){
                    min = kc;
                    so1=ds[i];
                    so2= ds[i+1];
                }
            }

            // tao ketqua de gui
            StringBuilder kq = new StringBuilder(min + "," + so1 + "," + so2);
            // gui
            out.write(kq.toString().getBytes());
            out.flush();

            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}