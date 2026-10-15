
package UDP;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 20171107L;

    public String id, code, name, email;

    public Student(String id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public Student(String code) {
        this.code = code;
    }
}
