package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
class Customer implements Serializable {

    private static final long serialVersionUID = 20170711L; // điền đúng đề

    private int id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public Customer(int id, String code, String name,
                    String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
public class B8 {

    static String chuanHoaTen(String s) {
        String[] a = s.trim().toLowerCase().split("\\s+");
        String kq = "";

        for (int i = 0; i < a.length - 1; i++)
            kq += Character.toUpperCase(a[i].charAt(0))
                    + a[i].substring(1) + " ";

        return a[a.length - 1].toUpperCase()
                + ", " + kq.trim();
    }

    static String chuanHoaNgay(String s) {
        String[] a = s.split("-");
        return a[1] + "/" + a[0] + "/" + a[2];
    }

    static String taoUser(String s) {
        String[] a = s.trim().toLowerCase().split("\\s+");
        String kq = "";

        for (int i = 0; i < a.length - 1; i++)
            kq += a[i].charAt(0);

        return kq + a[a.length - 1];
    }

    public static void main(String[] args) {

        String host = "36.50.135.242";
        int port = 2209;
        String ma = "B23DCCN609;EKTY0orP";

        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            ObjectOutputStream out =
                    new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            ObjectInputStream in =
                    new ObjectInputStream(socket.getInputStream());

            out.writeObject(ma);
            out.flush();

            Customer c = (Customer) in.readObject();

            String tenCu = c.getName();

            c.setName(chuanHoaTen(tenCu));
            c.setDayOfBirth(chuanHoaNgay(c.getDayOfBirth()));
            c.setUserName(taoUser(tenCu));

            out.writeObject(c);
            out.flush();

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
        }
    }
}